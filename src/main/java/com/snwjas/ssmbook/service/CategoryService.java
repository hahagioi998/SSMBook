package com.snwjas.ssmbook.service;

import com.snwjas.ssmbook.model.entity.CategoryEntity;
import com.snwjas.ssmbook.model.vo.CategoryVO;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * 书籍分类管理服务接口
 *
 * @author snwjas
 */
public interface CategoryService {

	/**
	 * 获取所有分类信息
	 */
	List<CategoryVO> listCategory();

	/**
	 * 分页获取分类信息
	 */
	List<CategoryVO> listCategoryByPage(int page, int pageSize);

	/**
	 * 根据分类id获取分类信息
	 */
	CategoryVO getCategoryById(int id);

	/**
	 * 添加分类信息
	 */
	int saveCategory(CategoryVO categoryVO);

	/**
	 * 根据id删除分类信息
	 */
	int deleteCategoryById(int id);

	/**
	 * 更新分类信息
	 */
	int updateCategory(CategoryVO categoryVO);

	/**
	 * 书籍分类的数量
	 */
	int count();

	/**
	 * CategoryEntity 转成 CategoryVO
	 */
	List<CategoryVO> covertToCategoryVO(@NotNull List<CategoryEntity> categoryList);

	/**
	 * CategoryVO 转成 CategoryEntity
	 */
	CategoryEntity covertToCategoryEntity(CategoryVO categoryVO);

}
