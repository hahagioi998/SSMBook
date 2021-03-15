package com.snwjas.ssmbook.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一些视图的全局变量
 *
 * @author snwjas
 */
@Component
public class ViewResolverConfig {

	@Autowired
	private void setStaticVariables(ThymeleafViewResolver viewResolver) {
		if (viewResolver != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("systemName", MyConstant.SYSTEM_NAME);
			viewResolver.setStaticVariables(map);
		}
	}

}
