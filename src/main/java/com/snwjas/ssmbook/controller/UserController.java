package com.snwjas.ssmbook.controller;

import cn.hutool.crypto.SecureUtil;
import com.snwjas.ssmbook.model.enums.RoleEnum;
import com.snwjas.ssmbook.model.vo.UserVO;
import com.snwjas.ssmbook.service.UserService;
import com.snwjas.ssmbook.support.MyConstant;
import com.snwjas.ssmbook.support.RememberMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器
 *
 * @author snwjas
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转到登录页面
	 */
	@GetMapping("/login")
	public String toLogin(HttpServletRequest request) {
		Object userInfo = request.getSession().getAttribute(MyConstant.SessionKey.USER_INFO);
		if (userInfo != null) {
			return "redirect:" + request.getHeader("Referer");
		}
		return "login";
	}

	/**
	 * 登录
	 */
	@PostMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response
			, @RequestParam("username") String username
			, @RequestParam("password") String password
			, @RequestParam(value = "rememberMe", required = false) String rememberMe) {

		UserVO userVO = userService.getByUsername(username);
		if (userVO == null) {
			model.addAttribute("message", "用户不存在");
		} else {
			// 密码加密（原密码 + 用户id）
			String pwd = SecureUtil.md5(password + userVO.getId());
			// 密码正确
			if (pwd.equals(userVO.getPassword())) {
				request.getSession().setAttribute(MyConstant.SessionKey.USER_INFO, userVO);
				// 记住我开启
				if ("true".equals(rememberMe) || "on".equals(rememberMe)) {
					String token = RememberMeService.createToken(username, userVO.getPassword(),
							MyConstant.REMEMBER_ME_SECONDS);
					Cookie cookie = new Cookie(MyConstant.CookieKey.REMEMBER_ME, token);
					cookie.setMaxAge(MyConstant.REMEMBER_ME_SECONDS);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				return "redirect:/book";
			} else {
				model.addAttribute("message", "密码错误");
			}
		}
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("rememberMe", rememberMe);
		return "login";
	}

	/**
	 * 注销登录
	 */
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(MyConstant.CookieKey.REMEMBER_ME, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		request.getSession().invalidate();
		return "redirect:/user/login";
	}

	@GetMapping("/profile")
	public String toUser(Model model) {

		List<RoleEnum> roleEnumList = userService.listRoles();

		model.addAttribute("roleList", roleEnumList);

		return "user";
	}

	@PostMapping("/update")
	public String updateProfile(RedirectAttributes model, HttpServletRequest request
			, @ModelAttribute UserVO userVO) {

		HttpSession session = request.getSession();

		UserVO user = (UserVO) session.getAttribute(MyConstant.SessionKey.USER_INFO);

		userVO.setPassword(user.getPassword());

		int i = userService.updateUser(userVO);

		if (i != 0) {
			session.setAttribute(MyConstant.SessionKey.USER_INFO, userVO);
			model.addFlashAttribute("message", "用户信息更新成功");
		} else {
			model.addFlashAttribute("message", "用户信息更新失败");
		}

		return "redirect:/user/profile";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(RedirectAttributes model, HttpServletRequest request
			, @RequestParam("oldPassword") String oldPassword
			, @RequestParam("newPassword") String newPassword) {

		HttpSession session = request.getSession();

		UserVO userVO = (UserVO) session.getAttribute(MyConstant.SessionKey.USER_INFO);

		String encodeOldPassword = SecureUtil.md5(oldPassword + userVO.getId());
		if (encodeOldPassword.equals(userVO.getPassword())) {
			String encodeNewPassword = SecureUtil.md5(newPassword + userVO.getId());
			userVO.setPassword(encodeNewPassword);
			userService.updateUser(userVO);
			session.setAttribute(MyConstant.SessionKey.USER_INFO, userVO);
			model.addFlashAttribute("message", "密码修改成功");
		} else {
			model.addFlashAttribute("message", "原密码错误");
			model.addFlashAttribute("oldPassword", oldPassword);
			model.addFlashAttribute("newPassword", newPassword);
		}
		return "redirect:/user/profile";
	}

}
