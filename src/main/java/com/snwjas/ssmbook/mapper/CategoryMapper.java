package com.snwjas.ssmbook.mapper;

import com.snwjas.ssmbook.model.entity.CategoryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CategoryEntity Mapper
 *
 * @author snwjas
 */
public interface CategoryMapper {

	/**
	 * 获取所有分类信息
	 */
	List<CategoryEntity> listCategory();

	/**
	 * 分页获取分类信息
	 */
	List<CategoryEntity> listCategoryByLimit(@Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 根据分类id获取分类信息
	 */
	CategoryEntity getCategoryById(@Param("categoryId") int id);

	/**
	 * 添加分类信息
	 */
	int saveCategory(CategoryEntity categoryEntity);

	/**
	 * 根据id删除分类信息
	 */
	int deleteCategoryById(@Param("categoryId") int id);

	/**
	 * 更新分类信息
	 */
	int updateCategory(CategoryEntity categoryEntity);

	/**
	 * 书籍分类的数量
	 */
	int count();

}
