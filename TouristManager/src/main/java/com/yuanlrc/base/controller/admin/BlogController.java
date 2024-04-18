package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.HotelType;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.service.common.HotelService;
import com.yuanlrc.base.service.common.HotelTypeService;
import com.yuanlrc.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 后台博客管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/blog")
@Controller
public class BlogController {

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
		model.addAttribute("pageBean", blogService.findList(blog, pageBean));
		return "admin/blog/list";
	}

	/**
	 * 新增博客页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/blog/add";
	}

	/**
	 * 博客添加表单提交处理
	 * @param blog
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Blog blog){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(blog);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		if(blogService.save(blog) == null){
			return Result.error(CodeMsg.ADMIN_BLOG_ADD_ERROR);
		}
		operaterLogService.add("添加博客，博客名：" + blog.getTitle());
		return Result.success(true);
	}

	/**
	 * 博客编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("blog", blogService.find(id));
		return "admin/blog/edit";
	}

	/**
	 * 编辑博客信息表单提交处理
	 * @param hotel
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Blog blog){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(blog);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(blog.getId() == null || blog.getId().longValue() <= 0){
			return Result.error(CodeMsg.ADMIN_BLOG_NOT_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库保存
		Blog existBlog = blogService.find(blog.getId());
		//讲提交的博客信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(blog, existBlog, "id","createTime","updateTime");
		if(blogService.save(existBlog) == null){
			return Result.error(CodeMsg.ADMIN_BLOG_EDIT_ERROR);
		}
		operaterLogService.add("编辑博客，博客名：" + blog.getTitle());
		return Result.success(true);
	}

	/**
	 * 删除博客
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			blogService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_BLOG_DELETE_ERROR);
		}
		operaterLogService.add("删除博客，博客ID：" + id);
		return Result.success(true);
	}

}
