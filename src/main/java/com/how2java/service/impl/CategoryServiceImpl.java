package com.how2java.service.impl;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService{
	@Autowired
    CategoryMapper categoryMapper;
	
	
	public List<Category> list(){
		return categoryMapper.list();
	}

	@Override
	public void add(Category category) {
		categoryMapper.add(category);
	}

	@Override
	public void delete(Category category) {
		categoryMapper.delete(category.getId());
	}

	@Override
	public void update(Category category) {
		categoryMapper.update(category);
	}

	@Override
	public Category getOneCategory(Integer id) {
		return categoryMapper.get(id);
	}
}
