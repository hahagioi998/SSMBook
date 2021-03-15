package com.snwjas.ssmbook.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 书籍 视图对象
 *
 * @author snwjas
 */
@Data
@Accessors(chain = true)
public class BookVO implements BeanConvert {

	private Integer id;

	private String isbn;

	private String name;

	private String author;

	private String press;

	private Double price;

	private Integer count;

	private String introduction;

	private CategoryVO category;

	public static final BookVO EMPTY_BOOKVO = getEmptyBookVO();

	public static BookVO getEmptyBookVO() {
		return new BookVO()
				.setId(0)
				.setIsbn("")
				.setName("")
				.setAuthor("")
				.setPress("")
				.setPrice(0.00)
				.setCount(1)
				.setIntroduction("")
				.setCategory(new CategoryVO().setId(0).setName(""));
	}
}
