package com.snwjas.ssmbook.support;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;

import java.util.Date;

/**
 * 记住我
 * Token格式：
 * Base64(username:expireTime:MD5(username:expireTime:password))
 *
 * @author snwjas
 */
public class RememberMeService {

	private static final String SEPARATOR = ":";


	/**
	 * 创建记住我Token
	 *
	 * @param username   用户名
	 * @param password   密码（数据库）
	 * @param expireTime 过期时间(单位 秒)
	 * @return token
	 */
	public static String createToken(String username, String password, long expireTime) {
		expireTime = new Date().getTime() + expireTime * 1000;
		String key = username + SEPARATOR + expireTime + SEPARATOR + password;
		String base64Encode = username + SEPARATOR + expireTime + SEPARATOR + SecureUtil.md5(key);
		return Base64.encode(base64Encode);
	}

	/**
	 * 检查token
	 *
	 * @param token    记住我token
	 * @param password 密码（数据库）
	 * @return true:正确且未过期
	 */
	public static boolean checkToken(String token, String password) {
		String[] data = getValidToken(token);
		if (data == null) {
			return false;
		}
		String key = data[0] + SEPARATOR + data[1] + SEPARATOR + password;
		boolean isRight = data[2].equals(SecureUtil.md5(key));
		// token格式正确，检查其是否未过期
		return isRight && new Date().before(new Date(Long.parseLong(data[1])));
	}

	/**
	 * 获得有效token
	 * 0: username:
	 * 1: expireTime
	 * 2: MD5(username:expireTime:password)
	 *
	 * @param token 记住我token
	 * @return null，不是有效的token
	 */
	public static String[] getValidToken(String token) {
		String base64Decode = Base64.decodeStr(token);
		String[] data = base64Decode.split(SEPARATOR);
		if (data.length != 3) {
			return null;
		}
		return data;
	}


}
