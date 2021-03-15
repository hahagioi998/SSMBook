package com.snwjas.ssmbook.model.vo;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.BeanUtils;

/**
 * po dto vo ... bean转换
 *
 * @author snwjas
 */
public interface BeanConvert {

	@SuppressWarnings("unchecked")
	default <T extends BeanConvert> T convertFrom(@NotNull Object source) {
		if (source == null) {
			return null;
		}
		BeanUtils.copyProperties(source, this);
		return (T) this;
	}

	default <T> T convertTo(@NotNull T target) {
		if (target == null) {
			return null;
		}
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
