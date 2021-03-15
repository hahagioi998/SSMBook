package com.snwjas.ssmbook.support;

/**
 * 一些常量
 *
 * @author snwjas
 */
public class MyConstant {

	public static final String SYSTEM_NAME = "Myles的小书屋";

	/**
	 * 记住我的时间，单位秒（1周 = 604800秒）
	 */
	public static final int REMEMBER_ME_SECONDS = 604800;

	/**
	 * Cookie Key
	 */
	public static class SessionKey {
		/**
		 * 用户信息
		 */
		public static final String USER_INFO = "UserInfo";
	}

	/**
	 * Cookie Key
	 */
	public static class CookieKey {
		/**
		 * 记住我
		 */
		public static final String REMEMBER_ME = "RememberMe";
	}


}
