package com.snwjas.ssmbook.mapper;

import com.snwjas.ssmbook.model.entity.BookEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Book Mapper
 */
public interface BookMapper {
	/**
	 * 增加一本Book
	 */
	int saveBook(BookEntity book);

	/**
	 * 根据id删除一本Book
	 */
	int deleteBookById(@Param("bookId") int id);

	/**
	 * 更新Book
	 */
	int updateBook(BookEntity book);

	/**
	 * 根据id查询,返回一本Book
	 */
	BookEntity getBookById(@Param("bookId") int id);


	/**
	 * 通过书名和作者模糊查询书籍，并分页
	 * keyword 为 null 查询全部
	 */
	List<BookEntity> listBooksByLikeAndLimit(@Param("keyword") String keyword
			, @Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 根据keyword查询图书的数量
	 *
	 * @param keyword 书名和作者,如 为 null 则查询全部
	 */
	int countByLike(@Param("keyword") String keyword);
}
