package com.snwjas.ssmbook.controller;

import com.snwjas.ssmbook.model.vo.CategoryVO;
import com.snwjas.ssmbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 书籍分类 controller
 *
 * @author snwjas
 */
@Controller
@RequestMapping("/book/category")
public class CategoryController {

	private static final int CATEGORY_LIST_PAGE_SIZE = 5;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 书籍分类 列表
	 */
	@GetMapping({"/list/{page}", "/list", ""})
	public String toCategory(Model model
			, @PathVariable(value = "page", required = false) Integer page) {

		model.addAttribute("isAdd", true);
		model.addAttribute("categoryVO", CategoryVO.EMPTY_CATEGORYVO);
		model.addAllAttributes(listCategoryByCondition(page, CATEGORY_LIST_PAGE_SIZE));

		return "category";
	}

	/**
	 * 添加分类
	 */
	@PostMapping("/add")
	public String add(HttpServletRequest request
			, @ModelAttribute CategoryVO categoryVO) {

		categoryService.saveCategory(categoryVO);

		return "redirect:" + request.getHeader("Referer");
	}

	/**
	 * 删除分类
	 */
	@PostMapping("/delete")
	public String delete(HttpServletRequest request
			, @RequestParam("categoryId") Integer categoryId) {

		categoryService.deleteCategoryById(categoryId);

		return "redirect:" + request.getHeader("Referer");
	}

	/**
	 * 跳转到 修改书籍 页面
	 */
	@GetMapping("/list/{page}/update/{categoryId}")
	public String toUpdate(Model model
			, @PathVariable("page") Integer page
			, @PathVariable("categoryId") Integer categoryId) {

		CategoryVO categoryVO = categoryService.getCategoryById(categoryId);

		model.addAttribute("isAdd", false);
		model.addAttribute("categoryVO", categoryVO);
		model.addAllAttributes(listCategoryByCondition(page, CATEGORY_LIST_PAGE_SIZE));

		return "category";
	}

	/**
	 * 修改书籍
	 */
	@PostMapping("/update")
	public String update(@ModelAttribute CategoryVO categoryVO
			, @RequestParam("currentPage") Integer page) {

		categoryService.updateCategory(categoryVO);
		// 添加 currentPage 参数 方便跳转到之前的页面，
		// 也可以在 request.getHeader("Referer") 中提取
		return "redirect:/book/category/list/" + (page == null ? 1 : page);
	}

	/**
	 * 根据条件查询分类，并分页
	 *
	 * @param page     第几页
	 * @param pageSize 每页条数
	 * @return 返回给页面的数据
	 */
	private Map<String, Object> listCategoryByCondition(Integer page, int pageSize) {

		int count = categoryService.count();

		int totalPages = (int) Math.ceil(count * 1.0 / pageSize);

		int currentPage = page == null || page < 1
				? 1
				: Math.min(page, totalPages);

		List<CategoryVO> categoryList = categoryService.listCategoryByPage(currentPage, pageSize);

		Map<String, Object> map = new HashMap<>(4);
		map.put("categoryList", categoryList);
		map.put("currentPage", currentPage);
		map.put("totalPages", totalPages);

		return map;
	}


}
