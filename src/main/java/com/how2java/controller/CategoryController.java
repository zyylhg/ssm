package com.how2java.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;
import com.how2java.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
	@Autowired
    CategoryService categoryService;

	@RequestMapping("listCategory")
	public ModelAndView listCategory(Page page){
		ModelAndView mav = new ModelAndView();
		PageHelper.offsetPage(page.getStart(),5);
		List<Category> cs= categoryService.list();
		int total = (int) new PageInfo<>(cs).getTotal();
		
		page.caculateLast(total);
		
		// 放入转发参数
		mav.addObject("cs", cs);
		// 放入jsp路径
		mav.setViewName("listCategory");
		return mav;
	}

	@RequestMapping(value="addCategory",method = RequestMethod.POST)
	public ModelAndView AddCategory(Category category){
		categoryService.add(category);
		ModelAndView mav=new ModelAndView("redirect:/listCategory");
		return mav;
	}

	@RequestMapping(value="deleteCategory/{id}",method = RequestMethod.GET)
	public ModelAndView deleteCategory(@PathVariable Integer id){
		Category category =new Category();
		category.setId(id);
		categoryService.delete(category);
		ModelAndView mav = new ModelAndView("redirect:/listCategory");
		return mav;
	}


	@RequestMapping(value="editCategory",method=RequestMethod.GET)
	public ModelAndView editCategory(Category category) {
		Category c = categoryService.getOneCategory(category.getId());
		ModelAndView mav = new ModelAndView("editCategory");
		mav.addObject("c", c);
		return mav;
	}

	@RequestMapping(value="updateCategory",method=RequestMethod.POST)
	public ModelAndView updateCategory(Category category) {
		categoryService.update(category);
		ModelAndView mav = new ModelAndView("redirect:/listCategory");
		return mav;
	}

	@RequestMapping("categoryList")
	@ResponseBody
	public Category categoryList(){
		List<Category> cs= categoryService.list();
		return cs.get(0);
	}




}
