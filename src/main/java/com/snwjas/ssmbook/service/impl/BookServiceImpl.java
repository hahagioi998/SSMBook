package com.snwjas.ssmbook.service.impl;

import com.snwjas.ssmbook.mapper.BookMapper;
import com.snwjas.ssmbook.model.entity.BookEntity;
import com.snwjas.ssmbook.model.vo.BookVO;
import com.snwjas.ssmbook.model.vo.CategoryVO;
import com.snwjas.ssmbook.service.BookService;
import com.snwjas.ssmbook.service.CategoryService;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 书籍接口实现类
 */
@Service
public class BookServiceImpl implements BookService {

	private final BookMapper mapper;

	private final CategoryService categoryService;

	public BookServiceImpl(BookMapper bookMapper, CategoryService categoryService) {
		this.mapper = bookMapper;
		this.categoryService = categoryService;
	}

	@Override
	public int saveBook(BookVO bookVO) {
		BookEntity bookEntity = covertToBookEntity(bookVO);
		return mapper.saveBook(bookEntity);
	}

	@Override
	public int deleteBookById(int id) {
		return mapper.deleteBookById(id);
	}

	@Override
	public int updateBook(BookVO bookVO) {
		BookEntity bookEntity = covertToBookEntity(bookVO);
		return mapper.updateBook(bookEntity);
	}

	@Override
	public BookVO getBookById(int id) {
		BookEntity bookEntity = mapper.getBookById(id);
		if (bookEntity == null) {
			return null;
		}
		List<BookEntity> bookEntities = new ArrayList<>();
		bookEntities.add(bookEntity);
		return covertToBookVO(bookEntities).get(0);
	}

	@Override
	public List<BookVO> listBooksByKeywordAndPage(String keyword, int page, int pageSize) {
		int offset = pageSize * (page - 1);
		List<BookEntity> bookEntityList = mapper.listBooksByLikeAndLimit(keyword, offset, pageSize);
		return covertToBookVO(bookEntityList);
	}

	@Override
	public int countByKeyword(String keyword) {
		return mapper.countByLike(keyword);
	}

	@Override
	public List<BookVO> covertToBookVO(@NotNull List<BookEntity> books) {
		List<BookVO> bookVOList = new ArrayList<>();
		for (BookEntity book : books) {
			BookVO bookVO = new BookVO().convertFrom(book);

			int categoryId = book.getCategoryId();
			CategoryVO categoryVO = categoryService.getCategoryById(categoryId);
			bookVO.setCategory(categoryVO);

			bookVOList.add(bookVO);
		}
		return bookVOList;
	}

	@Override
	public BookEntity covertToBookEntity(BookVO bookVO) {
		BookEntity bookEntity = bookVO.convertTo(new BookEntity());
		bookEntity.setCategoryId(bookVO.getCategory().getId());
		return bookEntity;
	}
}
