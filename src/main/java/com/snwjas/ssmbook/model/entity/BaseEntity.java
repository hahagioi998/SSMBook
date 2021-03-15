package com.snwjas.ssmbook.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础实体
 *
 * @author snwjas
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

	private LocalDateTime updateTime;

	private LocalDateTime createTime;

}
