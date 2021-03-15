package com.snwjas.ssmbook.service;

import com.snwjas.ssmbook.model.entity.BookEntity;
import com.snwjas.ssmbook.model.vo.BookVO;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * 图书管理服务接口
 */
public interface BookService {
	/**
	 * 增加一本Book
	 */
	int saveBook(BookVO bookVO);

	/**
	 * 根据id删除一本Book
	 */
	int deleteBookById(int id);

	/**
	 * 更新Book
	 */
	int updateBook(BookVO bookVO);

	/**
	 * 根据id查询,返回一本Book
	 */
	BookVO getBookById(int id);

	/**
	 * 通过书名和作者模糊查询书籍，并分页
	 * keyword 为 null 查询全部
	 *
	 * @param keyword  书名或作者
	 * @param page     起始页
	 * @param pageSize 每页数量
	 */
	List<BookVO> listBooksByKeywordAndPage(String keyword, int page, int pageSize);

	/**
	 * 根据keyword查询图书的数量
	 *
	 * @param keyword 书名和作者,如 为 null 则查询全部
	 */
	int countByKeyword(String keyword);

	List<BookVO> covertToBookVO(@NotNull List<BookEntity> books);

	BookEntity covertToBookEntity(BookVO bookVO);
}
