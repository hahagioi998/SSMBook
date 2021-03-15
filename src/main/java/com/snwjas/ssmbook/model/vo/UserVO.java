package com.snwjas.ssmbook.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户视图对象
 *
 * @author snwjas
 */
@Data
@Accessors(chain = true)
public class UserVO implements BeanConvert {

	private Integer id;

	private String username;

	private String password;

	private String nickname;

	private String description;

	private String role;

}
