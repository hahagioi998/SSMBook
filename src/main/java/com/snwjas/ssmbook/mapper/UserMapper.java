package com.snwjas.ssmbook.mapper;

import com.snwjas.ssmbook.model.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * User Mapper
 *
 * @author snwjas
 */
public interface UserMapper {

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户实体
	 */
	UserEntity getByUsername(@Param("username") String username);

	/**
	 * 新增用户信息
	 *
	 * @param userEntity 用户实体
	 * @return 主键id
	 */
	int saveUser(UserEntity userEntity);

	/**
	 * 更新用户信息
	 *
	 * @param userEntity 用户实体
	 * @return 主键id
	 */
	int updateUser(UserEntity userEntity);

	/**
	 * 根据用户id删除用户
	 *
	 * @param userId 用户id
	 * @return 主键id
	 */
	int deleteUseById(@Param("userId") int userId);

}
