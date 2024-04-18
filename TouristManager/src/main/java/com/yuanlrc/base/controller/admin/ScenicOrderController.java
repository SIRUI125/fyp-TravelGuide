package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.home.ScenicOrder;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.service.home.ScenicOrderService;
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
 * 后台景点订单管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/scenic_order")
@Controller
public class ScenicOrderController {

	@Autowired
	private ScenicOrderService scenicOrderService;


	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 景点订单列表页面
	 * @param model
	 * @param scenicOrder
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, ScenicOrder scenicOrder, PageBean<ScenicOrder> pageBean){
		model.addAttribute("title", "景点订单列表");
		model.addAttribute("mobile", scenicOrder.getAccount()==null?"":scenicOrder.getAccount().getMobile());
		model.addAttribute("pageBean", scenicOrderService.findAdminList(scenicOrder, pageBean));
		return "admin/scenic_order/list";
	}


}
