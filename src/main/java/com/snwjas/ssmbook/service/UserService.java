package com.snwjas.ssmbook.service;

import com.snwjas.ssmbook.model.enums.RoleEnum;
import com.snwjas.ssmbook.model.vo.UserVO;

import java.util.List;

/**
 * User Service
 *
 * @author snwjas
 */
public interface UserService {

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户实体
	 */
	UserVO getByUsername(String username);

	/**
	 * 新增用户信息
	 *
	 * @param userVO 用户视图对象
	 * @return 主键id
	 */
	int saveUser(UserVO userVO);

	/**
	 * 更新用户信息
	 *
	 * @param userVO 用户视图对象
	 * @return 主键id
	 */
	int updateUser(UserVO userVO);

	/**
	 * 根据用户id删除用户
	 *
	 * @param userId 用户id
	 * @return 主键id
	 */
	int deleteUseById(int userId);


	/**
	 * 列举所有角色
	 */
	List<RoleEnum> listRoles();

}
