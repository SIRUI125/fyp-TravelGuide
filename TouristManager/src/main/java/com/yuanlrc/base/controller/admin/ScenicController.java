package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPlan;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.admin.RoleService;
import com.yuanlrc.base.service.admin.UserService;
import com.yuanlrc.base.service.common.ScenicPlanService;
import com.yuanlrc.base.service.common.ScenicPriceService;
import com.yuanlrc.base.service.common.ScenicService;
import com.yuanlrc.base.util.DateUtil;
import com.yuanlrc.base.util.ValidateEntityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台景点管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/scenic")
@Controller
public class ScenicController {

	@Autowired
	private ScenicService scenicService;

	@Autowired
	private ScenicPlanService scenicPlanService;

	@Autowired
	private ScenicPriceService scenicPriceService;

	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 景点列表页面
	 * @param model
	 * @param scenic
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Scenic scenic, PageBean<Scenic> pageBean){
		model.addAttribute("title", "景点列表");
		model.addAttribute("name", scenic.getTitle());
		model.addAttribute("pageBean", scenicService.findList(scenic, pageBean));
		return "admin/scenic/list";
	}

	/**
	 * 新增景点页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/scenic/add";
	}

	/**
	 * 景点添加表单提交处理
	 * @param scenic
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Scenic scenic){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(scenic);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//判断景点名是否存在
		if(scenicService.isExistTitle(scenic.getTitle(), 0l)){
			return Result.error(CodeMsg.ADMIN_SCENIC_NAME_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库新增
		if(scenicService.save(scenic) == null){
			return Result.error(CodeMsg.ADMIN_SCENIC_ADD_ERROR);
		}
		operaterLogService.add("添加景点，景点名：" + scenic.getTitle());
		return Result.success(true);
	}

	/**
	 * 景点编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("scenic", scenicService.find(id));
		return "admin/scenic/edit";
	}

	/**
	 * 编辑景点信息表单提交处理
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Scenic scenic){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(scenic);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}

		if(scenic.getId() == null || scenic.getId().longValue() <= 0){
			return Result.error(CodeMsg.ADMIN_SCENIC_NOT_EXIST_ERROR);
		}
		if(scenicService.isExistTitle(scenic.getTitle(), scenic.getId())){
			return Result.error(CodeMsg.ADMIN_SCENIC_NAME_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库保存
		Scenic existScenic = scenicService.find(scenic.getId());
		//讲提交的景点信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(scenic, existScenic, "id","createTime","updateTime");
		if(scenicService.save(existScenic) == null){
			return Result.error(CodeMsg.ADMIN_SCENIC_EDIT_ERROR);
		}
		operaterLogService.add("编辑景点，景点名：" + scenic.getTitle());
		return Result.success(true);
	}

	/**
	 * 删除景点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			scenicService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_SCENIC_DELETE_ERROR);
		}
		operaterLogService.add("删除景点，景点ID：" + id);
		return Result.success(true);
	}


	/**
	 * 景点攻略列表页面
	 * @param model
	 * @param scenicPlan
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list_plan")
	public String listPlan(Model model, ScenicPlan scenicPlan, PageBean<ScenicPlan> pageBean){
		model.addAttribute("title", "景点攻略列表");
		model.addAttribute("name", scenicPlan.getName());
		model.addAttribute("scenicId",scenicPlan.getScenic().getId());
		model.addAttribute("pageBean", scenicPlanService.findList(scenicPlan, pageBean));
		return "admin/scenic_plan/list";
	}

	/**
	 * 新增景点攻略页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add_plan",method=RequestMethod.GET)
	public String addPlan(Model model,@RequestParam(name = "sid")Long sid){
		model.addAttribute("sid",sid);
		return "admin/scenic_plan/add";
	}

	/**
	 * 景点攻略添加表单提交处理
	 * @param scenicPlan
	 * @return
	 */
	@RequestMapping(value="/add_plan",method=RequestMethod.POST)
	@ResponseBody
	public Result<Long> addPlan(ScenicPlan scenicPlan){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(scenicPlan);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		if(scenicPlanService.save(scenicPlan) == null){
			return Result.error(CodeMsg.ADMIN_SCENIC_PLAN_ADD_ERROR);
		}
		operaterLogService.add("添加景点攻略，景点攻略名：" + scenicPlan.getName());
		return Result.success(scenicPlan.getScenic().getId());
	}

	/**
	 * 景点攻略编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_plan",method=RequestMethod.GET)
	public String editPlan(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("scenicPlan", scenicPlanService.find(id));
		return "admin/scenic_plan/edit";
	}

	/**
	 * 编辑景点攻略信息表单提交处理
	 * @param
	 * @return
	 */
	@RequestMapping(value="/edit_plan",method=RequestMethod.POST)
	@ResponseBody
	public Result<Long> editPlan(ScenicPlan scenicPlan){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(scenicPlan);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}

		if(scenicPlan.getId() == null || scenicPlan.getId().longValue() <= 0){
			return Result.error(CodeMsg.ADMIN_SCENIC_PLAN_NOT_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库保存
		ScenicPlan existScenic = scenicPlanService.find(scenicPlan.getId());
		//讲提交的景点攻略信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(scenicPlan, existScenic, "id","createTime","updateTime");
		if(scenicPlanService.save(existScenic) == null){
			return Result.error(CodeMsg.ADMIN_SCENIC_PLAN_EDIT_ERROR);
		}
		operaterLogService.add("编辑景点攻略，景点攻略名：" + scenicPlan.getName());
		return Result.success(scenicPlan.getScenic().getId());
	}

	/**
	 * 删除景点攻略
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_plan",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deletePlan(@RequestParam(name="id",required=true)Long id){
		try {
			scenicPlanService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_SCENIC_PLAN_DELETE_ERROR);
		}
		operaterLogService.add("删除景点攻略，景点攻略ID：" + id);
		return Result.success(true);
	}


	/**
	 * 门票列表
	 * @param model
	 * @param scenicPrice
	 * @param pageBean
	 * @return
	 */
	@GetMapping("/list_price")
	public String scenicPriceList(Model model, ScenicPrice scenicPrice,PageBean<ScenicPrice> pageBean){
		model.addAttribute("title","门票列表");
		model.addAttribute("pageBean",scenicPriceService.findList(scenicPrice,pageBean));
		model.addAttribute("scenicId",scenicPrice.getScenic().getId());
		return "admin/scenic_price/list";
	}

	/**
	 * 给某一景点添加门票信息
	 * @param scenicId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add_price",method = RequestMethod.GET)
	public String add(@RequestParam("scenicId")Long scenicId,Model model){
		String minTime = null;
		ScenicPrice scenicPrice = scenicPriceService.findFirstByScenicIdOrderByProductDateDesc(scenicId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = DateUtil.ClearDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(scenicPrice!= null){
			if(scenicPrice.getProductDate().getTime() >= date.getTime()){
				date = DateUtil.tommorrow(scenicPrice.getProductDate(), 1);
			}
		}
		minTime = simpleDateFormat.format(date);
		model.addAttribute("minTime",minTime);
		model.addAttribute("scenic",scenicService.find(scenicId));
		return "admin/scenic_price/add";
	}


	/**
	 * 添加景点门票
	 * @param scenicPrice
	 * @return
	 */
	@ResponseBody
	@PostMapping("/add_price")
	public Result<Boolean>addScenicPrice(ScenicPrice scenicPrice,
										 @RequestParam(name = "startDate")String startDate,
										 @RequestParam(name = "endDate")String endDate) throws ParseException {
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(scenicPrice);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(StringUtils.isEmpty(startDate)){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_START_DETA_ERROR);
		}
		if(StringUtils.isEmpty(endDate)){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_END_DETA_ERROR);
		}
		Date parStartDate = DateUtil.date_sdf.parse(startDate);
		Date parEndDate = DateUtil.date_sdf.parse(endDate);
		if(parStartDate.getTime()>=parEndDate.getTime()){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_DATE_ERROR);
		}
		Scenic scenic = scenicService.find(scenicPrice.getScenic().getId());
		if(scenic==null){
			return Result.error(CodeMsg.ADMIN_SCENIC_NOT_EXIST_ERROR);
		}
		scenicPrice.setInventory(scenicPrice.getTicket());
		List<Date> betweenDates = DateUtil.getBetweenDates(parStartDate, parEndDate);
		List<ScenicPrice> scenicPriceList = new ArrayList<ScenicPrice>();
		for(Date date:betweenDates){
			ScenicPrice initScenicPrice = new ScenicPrice();
			initScenicPrice.setProductDate(date);
			initScenicPrice.setTicket(scenicPrice.getTicket());
			initScenicPrice.setInventory(scenicPrice.getInventory());
			initScenicPrice.setLimitation(scenicPrice.getLimitation());
			initScenicPrice.setPrice(scenicPrice.getPrice());
			initScenicPrice.setScenic(scenic);
			scenicPriceList.add(initScenicPrice);
		}
		if(scenicPriceService.saveAll(scenicPriceList).size()<=0){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_ADD_ERROR);
		}
		return Result.success(true);
	}


	/**
	 * 删除景点攻略
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_price",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deletePrice(@RequestParam(name="id",required=true)Long id){
		try {
			scenicPriceService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_DELETE_ERROR);
		}
		operaterLogService.add("删除景点门票，景点门票ID：" + id);
		return Result.success(true);
	}

	/**
	 * 编辑景点门票
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/edit_price")
	public String editPrice(Model model,@RequestParam(name = "id")Long id){
		ScenicPrice scenicPrice = scenicPriceService.find(id);
		model.addAttribute("title","景点门票编辑信息");
		model.addAttribute("scenicPrice",scenicPrice);
		return "admin/scenic_price/edit";
	}


	/**
	 * 编辑景点门票信息
	 * @param scenicPrice
	 * @return
	 */
	@ResponseBody
	@PostMapping("/edit_price")
	public Result<Boolean> editPrice(ScenicPrice scenicPrice){
		BigDecimal price = scenicPrice.getPrice();
		if(price.compareTo(BigDecimal.ZERO)<=0){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_PRICE_ERROR);
		}
		int limitation = scenicPrice.getLimitation();
		if(limitation<=0){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_LIMITATION_ERROR);
		}
		int ticket = scenicPrice.getTicket();
		if(ticket<=0){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_TICKET_ERROR);
		}
		ScenicPrice existScenicPrice = scenicPriceService.find(scenicPrice.getId());
		existScenicPrice.setPrice(price);
		existScenicPrice.setLimitation(limitation);
		existScenicPrice.setInventory(existScenicPrice.getInventory()+ticket);
		existScenicPrice.setTicket(existScenicPrice.getTicket()+ticket);
		if(scenicPriceService.save(existScenicPrice)==null){
			return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_EDIT_ERROR);
		}
		return Result.success(true);
	}
}
