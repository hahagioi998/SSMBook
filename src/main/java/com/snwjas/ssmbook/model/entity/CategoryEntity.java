package com.snwjas.ssmbook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 书籍分类实体类
 *
 * @author snwjas
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CategoryEntity extends BaseEntity {

	private Integer id;

	private String name;

	private String description;

}
