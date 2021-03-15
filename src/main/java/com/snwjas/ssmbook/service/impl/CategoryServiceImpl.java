package com.snwjas.ssmbook.service.impl;

import com.snwjas.ssmbook.mapper.CategoryMapper;
import com.snwjas.ssmbook.model.entity.CategoryEntity;
import com.snwjas.ssmbook.model.vo.CategoryVO;
import com.snwjas.ssmbook.service.CategoryService;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 书籍分类接口实现类
 *
 * @author snwjas
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryMapper mapper;

	public CategoryServiceImpl(CategoryMapper categoryMapper) {
		this.mapper = categoryMapper;
	}

	@Override
	public List<CategoryVO> listCategory() {
		List<CategoryEntity> categoryEntityList = mapper.listCategory();
		return covertToCategoryVO(categoryEntityList);
	}

	@Override
	public List<CategoryVO> listCategoryByPage(int page, int pageSize) {
		int offset = pageSize * (page - 1);
		List<CategoryEntity> categoryEntityList = mapper.listCategoryByLimit(offset, pageSize);
		return covertToCategoryVO(categoryEntityList);
	}

	@Override
	public CategoryVO getCategoryById(int id) {
		CategoryEntity categoryEntity = mapper.getCategoryById(id);
		if (categoryEntity == null) {
			return null;
		}
		List<CategoryEntity> categoryEntityList = new ArrayList<>();
		categoryEntityList.add(categoryEntity);
		return covertToCategoryVO(categoryEntityList).get(0);
	}

	@Override
	public int saveCategory(CategoryVO categoryVO) {
		CategoryEntity categoryEntity = covertToCategoryEntity(categoryVO);
		return mapper.saveCategory(categoryEntity);
	}

	@Override
	public int deleteCategoryById(int id) {
		return mapper.deleteCategoryById(id);
	}

	@Override
	public int updateCategory(CategoryVO categoryVO) {
		CategoryEntity categoryEntity = covertToCategoryEntity(categoryVO);
		return mapper.updateCategory(categoryEntity);
	}

	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public List<CategoryVO> covertToCategoryVO(@NotNull List<CategoryEntity> categoryList) {
		List<CategoryVO> categoryVOList = new ArrayList<>();
		for (CategoryEntity categoryEntity : categoryList) {
			CategoryVO categoryVO = new CategoryVO().convertFrom(categoryEntity);
			categoryVOList.add(categoryVO);
		}
		return categoryVOList;
	}

	@Override
	public CategoryEntity covertToCategoryEntity(CategoryVO categoryVO) {
		return categoryVO.convertTo(new CategoryEntity());
	}
}
