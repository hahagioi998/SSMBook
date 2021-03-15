package com.snwjas.ssmbook.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 书籍分类 视图对象
 *
 * @author snwjas
 */
@Data
@Accessors(chain = true)
public class CategoryVO implements BeanConvert {

	private Integer id;

	private String name;

	private String description;

	public static final CategoryVO EMPTY_CATEGORYVO = getEmptyCategoryVO();

	public static CategoryVO getEmptyCategoryVO() {
		return new CategoryVO()
				.setId(0)
				.setName("")
				.setDescription("");
	}
}
