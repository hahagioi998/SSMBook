package com.snwjas.ssmbook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 书籍实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class BookEntity extends BaseEntity {

	private Integer id;

	private String isbn;

	private String name;

	private String author;

	private String press;

	private Double price;

	private Integer count;

	private String introduction;

	private Integer categoryId;

}
