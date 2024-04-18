package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.RechargeRecord;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.BlogService;
import com.yuanlrc.base.service.common.RechargeRecordService;
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
 * 后台充值金额记录管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/recharge_record")
@Controller
public class RechargeRecordController {

	@Autowired
	private RechargeRecordService rechargeRecordService;


	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 充值金额记录列表页面
	 * @param model
	 * @param rechargeRecord
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, RechargeRecord rechargeRecord, PageBean<RechargeRecord> pageBean){
		model.addAttribute("title", "充值金额记录列表");
		model.addAttribute("name", rechargeRecord.getAccount()==null?"":rechargeRecord.getAccount().getUsername());
		model.addAttribute("pageBean", rechargeRecordService.findAdminList(rechargeRecord, pageBean));
		return "admin/recharge_record/list";
	}



}
