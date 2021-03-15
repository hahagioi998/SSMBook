package com.snwjas.ssmbook.interceptor;

import com.snwjas.ssmbook.model.vo.UserVO;
import com.snwjas.ssmbook.service.UserService;
import com.snwjas.ssmbook.support.MyConstant;
import com.snwjas.ssmbook.support.RememberMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截未登录的请求
 * 在spring-mvc.xml中配置拦截与放行的请求，并注入baen
 *
 * @author snwjas
 */
public class NotLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();

		Object userInfo = session.getAttribute(MyConstant.SessionKey.USER_INFO);

		// 未登录或记住我验证失败
		boolean isNotAuth = true;

		// 用户未登录
		if (userInfo == null) {
			// 检查用户的rememberMe
			UserVO userVO = checkRememberMe(request.getCookies());
			if (userVO != null) {
				session.setAttribute(MyConstant.SessionKey.USER_INFO, userVO);
				isNotAuth = false;
			}
		} else {
			isNotAuth = false;
		}

		if (isNotAuth) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		return true;
	}

	/**
	 * 检查token是否有效
	 *
	 * @param cookies 记住我token
	 * @return 有效：返回用户信息，无效：null
	 */
	public UserVO checkRememberMe(Cookie[] cookies) {
		if (cookies == null || cookies.length == 0) {
			return null;
		}

		for (Cookie cookie : cookies) {
			boolean equals = MyConstant.CookieKey.REMEMBER_ME.equals(cookie.getName());
			if (equals) {
				String token = cookie.getValue();
				String[] tokenData = RememberMeService.getValidToken(token);
				if (tokenData == null) {
					return null;
				}
				String username = tokenData[0];
				UserVO userVO = userService.getByUsername(username);
				if (userVO != null && RememberMeService.checkToken(token, userVO.getPassword())) {
					return userVO;
				}
			}
		}
		return null;
	}


}
