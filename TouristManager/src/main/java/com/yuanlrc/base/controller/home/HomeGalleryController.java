package com.yuanlrc.base.controller.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.Gallery;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.service.common.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 前台照片库列表控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/home/gallery")
@Controller
public class HomeGalleryController {

	@Autowired
	private GalleryService galleryService;


	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 照片库列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model){
		model.addAttribute("title", "照片库列表");
		model.addAttribute("galleryList",galleryService.findByStatus(Gallery.PHOTO_STATUS_ON));
		return "home/gallery/list";
	}



}
