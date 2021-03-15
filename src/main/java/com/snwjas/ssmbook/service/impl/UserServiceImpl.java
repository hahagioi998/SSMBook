package com.snwjas.ssmbook.service.impl;

import com.snwjas.ssmbook.mapper.UserMapper;
import com.snwjas.ssmbook.model.entity.UserEntity;
import com.snwjas.ssmbook.model.enums.RoleEnum;
import com.snwjas.ssmbook.model.vo.UserVO;
import com.snwjas.ssmbook.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * User Service implement
 *
 * @author snwjas
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserVO getByUsername(String username) {
		UserEntity userEntity = userMapper.getByUsername(username);
		return new UserVO().convertFrom(userEntity);
	}

	@Override
	public int saveUser(UserVO userVO) {
		UserEntity userEntity = userVO.convertTo(new UserEntity());
		return userMapper.saveUser(userEntity);
	}

	@Override
	public int updateUser(UserVO userVO) {
		UserEntity userEntity = userVO.convertTo(new UserEntity());
		return userMapper.updateUser(userEntity);
	}

	@Override
	public int deleteUseById(int userId) {
		return userMapper.deleteUseById(userId);
	}

	@Override
	public List<RoleEnum> listRoles() {
		return Arrays.asList(RoleEnum.values());
	}

}
