package com.snwjas.ssmbook.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体类
 *
 * @author snwjas
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UserEntity extends BaseEntity {

	private Integer id;

	private String username;

	private String password;

	private String nickname;

	private String description;

	private String role;

}
