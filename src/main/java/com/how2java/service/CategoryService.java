package com.how2java.service;

import com.how2java.pojo.Category;

import java.util.List;

public interface CategoryService {

	List<Category> list();

    void add(Category category);

    void delete(Category category);

    void update(Category category);

    Category getOneCategory(Integer id);
}
