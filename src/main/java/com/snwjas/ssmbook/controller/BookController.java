package com.snwjas.ssmbook.controller;

import com.snwjas.ssmbook.model.vo.BookVO;
import com.snwjas.ssmbook.model.vo.CategoryVO;
import com.snwjas.ssmbook.service.BookService;
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
 * 书籍 controller
 */
@Controller
@RequestMapping("/book")
public class BookController {

	private static final int BOOK_LIST_PAGE_SIZE = 5;

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 书籍列表
	 */
	@GetMapping({"/list/{page}", "/list", ""})
	public String list(Model model
			, @PathVariable(value = "page", required = false) Integer page) {

		Map<String, Object> map = listBooksByCondition(null, page, BOOK_LIST_PAGE_SIZE);

		model.addAllAttributes(map);

		return "book";
	}

	/**
	 * 搜索书籍
	 */
	@GetMapping({"/search/{keyword}/{page}", "/search/{keyword}"})
	public String search(Model model
			, @PathVariable("keyword") String keyword
			, @PathVariable(value = "page", required = false) Integer page) {

		Map<String, Object> map = listBooksByCondition(keyword, page, BOOK_LIST_PAGE_SIZE);

		model.addAllAttributes(map);
		model.addAttribute("keyword", keyword);

		return "book";
	}

	/**
	 * 删除书籍
	 */
	@PostMapping("/delete")
	public String delete(HttpServletRequest request,
	                     @RequestParam(value = "bookId") Integer bookId) {

		bookService.deleteBookById(bookId);

		return "redirect:" + request.getHeader("Referer");
	}

	/**
	 * 跳转到 添加书籍 页面
	 */
	@GetMapping("/add")
	public String toAdd(Model model) {

		List<CategoryVO> categoryList = categoryService.listCategory();

		model.addAttribute("isAdd", true);
		model.addAttribute("bookVO", BookVO.EMPTY_BOOKVO);
		model.addAttribute("categoryList", categoryList);

		return "book-add-alter";
	}

	/**
	 * 添加书籍
	 */
	@PostMapping("/add")
	public String add(@ModelAttribute BookVO bookVO) {

		bookService.saveBook(bookVO);

		return "redirect:/book";
	}

	/**
	 * 跳转到 修改书籍 页面
	 */
	@GetMapping("/update/{bookId}")
	public String toUpdate(Model model
			, @PathVariable("bookId") Integer bookId) {

		BookVO bookVO = bookService.getBookById(bookId);
		List<CategoryVO> categoryList = categoryService.listCategory();

		model.addAttribute("isAdd", false);
		model.addAttribute("bookVO", bookVO);
		model.addAttribute("categoryList", categoryList);

		return "book-add-alter";
	}

	/**
	 * 修改书籍
	 */
	@PostMapping("/update")
	public String update(@ModelAttribute BookVO bookVO) {

		bookService.updateBook(bookVO);

		return "redirect:/book";
	}

	/**
	 * 根据条件查询书籍，并分页
	 *
	 * @param keyword  书名or作者，为 null 查询全部
	 * @param page     第几页
	 * @param pageSize 每页条数
	 * @return 返回给页面的数据
	 */
	private Map<String, Object> listBooksByCondition(String keyword, Integer page, int pageSize) {

		int count = bookService.countByKeyword(keyword);

		int totalPages = (int) Math.ceil(count * 1.0 / pageSize);

		int currentPage = page == null || page < 1
				? 1
				: Math.min(page, totalPages);

		List<BookVO> bookList = bookService.listBooksByKeywordAndPage(keyword, currentPage, pageSize);

		Map<String, Object> map = new HashMap<>(4);
		map.put("bookList", bookList);
		map.put("currentPage", currentPage);
		map.put("totalPages", totalPages);

		return map;
	}

}
