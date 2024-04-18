package com.yuanlrc.base.controller.home;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 前台博客列表控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/home/blog")
@Controller
public class HomeBlogController {

	@Autowired
	private BlogService blogService;


	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 博客列表页面
	 * @param model
	 * @param blog
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Blog blog, PageBean<Blog> pageBean){
		model.addAttribute("title", "博客列表");
		model.addAttribute("name", blog.getTitle());
		model.addAttribute("pageBean", blogService.findHomeList(blog, pageBean));
		return "home/blog/list";
	}


	/**
	 * 新闻详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "id")Long id,Model model){
		model.addAttribute("title","新闻详情页面");
		model.addAttribute("blog",blogService.find(id));
		return "home/blog/detail";
	}
}
