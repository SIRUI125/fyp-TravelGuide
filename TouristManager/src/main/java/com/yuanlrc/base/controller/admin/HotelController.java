package com.yuanlrc.base.controller.admin;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.HotelType;
import com.yuanlrc.base.service.admin.OperaterLogService;
import com.yuanlrc.base.service.common.HotelService;
import com.yuanlrc.base.service.common.HotelTypeService;
import com.yuanlrc.base.util.ValidateEntityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 后台酒店管理控制器
 * @author SiruiYao 2024/03/06
 *
 */
@RequestMapping("/admin/hotel")
@Controller
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@Autowired
	private HotelTypeService hotelTypeService;

	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * 酒店列表页面
	 * @param model
	 * @param hotel
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Hotel hotel, PageBean<Hotel> pageBean){
		model.addAttribute("title", "酒店列表");
		model.addAttribute("name", hotel.getName());
		model.addAttribute("pageBean", hotelService.findList(hotel, pageBean));
		return "admin/hotel/list";
	}

	/**
	 * 新增酒店页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/hotel/add";
	}

	/**
	 * 酒店添加表单提交处理
	 * @param hotel
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Hotel hotel){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(hotel);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//到这说明一切符合条件，进行数据库新增
		if(hotelService.save(hotel) == null){
			return Result.error(CodeMsg.HOME_HOTEL_ADD_ERROR);
		}
		operaterLogService.add("添加酒店，酒店名：" + hotel.getName());
		return Result.success(true);
	}

	/**
	 * 酒店编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("hotel", hotelService.find(id));
		return "admin/hotel/edit";
	}

	/**
	 * 编辑酒店信息表单提交处理
	 * @param hotel
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Hotel hotel){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(hotel);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(hotel.getId() == null || hotel.getId().longValue() <= 0){
			return Result.error(CodeMsg.HOME_HOTEL_NOT_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库保存
		Hotel existHotel = hotelService.find(hotel.getId());
		//讲提交的酒店信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(hotel, existHotel, "id","createTime","updateTime");
		if(hotelService.save(existHotel) == null){
			return Result.error(CodeMsg.HOME_HOTEL_EDIT_ERROR);
		}
		operaterLogService.add("编辑酒店，酒店名：" + hotel.getName());
		return Result.success(true);
	}

	/**
	 * 删除酒店
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			hotelService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.HOME_HOTEL_DELETE_ERROR);
		}
		operaterLogService.add("删除酒店，酒店ID：" + id);
		return Result.success(true);
	}


	/**
	 * 酒店客房列表
	 * @param model
	 * @param hotelType
	 * @param pageBean
	 * @return
	 */
	@GetMapping("/hotel_type_list")
	public String hotelTypeList(Model model, HotelType hotelType, PageBean<HotelType> pageBean){
		model.addAttribute("title","酒店客房列表");
		model.addAttribute("hotelId",hotelType.getHotel().getId());
		model.addAttribute("name",hotelType.getName());
		model.addAttribute("pageBean",hotelTypeService.findList(hotelType,pageBean));
		return "admin/hotel_type/list";
	}


	/**
	 * 添加客房页面
	 * @param hotelId
	 * @param model
	 * @return
	 */
	@GetMapping("/hotel_type_add")
	public String addHotelType(@RequestParam(name = "hotelId")Long hotelId,Model model){
		model.addAttribute("hotelId",hotelId);
		return "admin/hotel_type/add";
	}


	/**
	 * 添加客房操作
	 * @param hotelType
	 * @return
	 */
	@ResponseBody
	@PostMapping("/hotel_type_add")
	public Result<Boolean> addHotelType(HotelType hotelType){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(hotelType);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		hotelType.setDefaultNumber(hotelType.getRoomNumber());
		//到这说明一切符合条件，进行数据库新增
		if(hotelTypeService.save(hotelType) == null){
			return Result.error(CodeMsg.HOME_HOTEL_TYPE_ADD_ERROR);
		}
		operaterLogService.add("添加客房，客房名：" + hotelType.getName());
		return Result.success(true);
	}


	/**
	 * 编辑客房页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/hotel_type_edit")
	public String editHotelType(@RequestParam(name = "id")Long id,
								Model model){
		model.addAttribute("hotelType",hotelTypeService.find(id));
		return "admin/hotel_type/edit";
	}


	/**
	 * 编辑客房操作
	 * @param hotelType
	 * @return
	 */
	@ResponseBody
	@PostMapping("/hotel_type_edit")
	public Result<Boolean> editHotelType(HotelType hotelType){
		//用统一验证实体方法验证是否合法
		CodeMsg validate = ValidateEntityUtil.validate(hotelType);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(hotelType.getId() == null || hotelType.getId().longValue() <= 0){
			return Result.error(CodeMsg.HOME_HOTEL_TYPE_NOT_EXIST_ERROR);
		}
		//到这说明一切符合条件，进行数据库保存
		HotelType existHotelType = hotelTypeService.find(hotelType.getId());
		//讲提交的酒店信息指定字段复制到已存在的user对象中,该方法会覆盖新字段内容
		BeanUtils.copyProperties(hotelType, existHotelType, "id","createTime","updateTime","hotel","defaultNumber");
		if(hotelTypeService.save(existHotelType) == null){
			return Result.error(CodeMsg.HOME_HOTEL_TYPE_EDIT_ERROR);
		}
		operaterLogService.add("编辑客房，客房名：" + hotelType.getName());
		return Result.success(true);
	}

	/**
	 * 删除客房
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/hotel_type_delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteHotelType(@RequestParam(name="id",required=true)Long id){
		try {
			hotelTypeService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.HOME_HOTEL_TYPE_DELETE_ERROR);
		}
		operaterLogService.add("删除客房，客房ID：" + id);
		return Result.success(true);
	}

}
