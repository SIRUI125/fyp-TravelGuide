package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.ScenicOrder;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.home.HotelRoomOrderItemService;
import com.yuanlrc.base.service.home.HotelRoomOrderService;
import com.yuanlrc.base.service.home.ScenicOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 后台酒店订单管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/hotel_order")
@Controller
public class HotelOrderController {

	@Autowired
	private HotelRoomOrderService hotelRoomOrderService;

	@Autowired
	private HotelRoomOrderItemService hotelRoomOrderItemService;

	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 酒店订单列表页面
	 * @param model
	 * @param hotelRoomOrder
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, HotelRoomOrder hotelRoomOrder, PageBean<HotelRoomOrder> pageBean){
		model.addAttribute("title", "酒店订单列表");
		model.addAttribute("mobile", hotelRoomOrder.getAccount()==null?"":hotelRoomOrder.getAccount().getMobile());
		model.addAttribute("pageBean", hotelRoomOrderService.findAdminList(hotelRoomOrder, pageBean));
		return "admin/hotel_order/list";
	}


	/**
	 * 获取订单项列表
	 * @param oid
	 * @return
	 */
	@GetMapping("/get_order_item")
	public String getOrderItem(@RequestParam(name = "oid")Long oid,Model model){
		model.addAttribute("orderItemList",hotelRoomOrderItemService.findByOrderId(oid));
		return "admin/hotel_order/get_order_item";
	}
}
