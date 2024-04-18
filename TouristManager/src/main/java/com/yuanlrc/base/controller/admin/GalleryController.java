package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.Gallery;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.service.common.GalleryService;
import com.yuanlrc.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台照片库管理控制器
 * @author Administrator
 *
 */
@RequestMapping("/admin/gallery")
@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;


	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 照片库列表页面
	 * @param model
	 * @param gallery
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Gallery gallery, PageBean<Gallery> pageBean){
		model.addAttribute("title", "照片库列表");
		model.addAttribute("name",gallery.getTitle());
		model.addAttribute("pageBean", galleryService.findList(gallery, pageBean));
		return "admin/gallery/list";
	}


	/**
	 * 删除照片库
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			galleryService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.HOME_GALLERY_DELETE_ERROR);
		}
		operaterLogService.add("删除照片库，照片库ID：" + id);
		return Result.success(true);
	}

}
