package com.snwjas.ssmbook.model.enums;

/**
 * 基础角色和权限
 *
 * @author snwjas
 */
public enum RoleEnum implements KeyValueEnum<String, String> {

	ADMIN("admin", "管理员: 拥有全部权限"),

	GUEST("guest", "游客: 拥有浏览权限");

	private final String role;

	private final String description;

	RoleEnum(String role, String description) {
		this.role = role;
		this.description = description;
	}

	@Override
	public String key() {
		return role;
	}

	@Override
	public String value() {
		return description;
	}

	public static RoleEnum resolve(String role) {
		for (RoleEnum re : values()) {
			if (re.role.equals(role)) {
				return re;
			}
		}
		return GUEST;
	}

}
