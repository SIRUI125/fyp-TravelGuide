package com.yuanlrc.base.bean;
/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 * @author SiruiYao 2024/03/06
 *
 */
public class CodeMsg {

	private int code;//错误码

	private String msg;//错误信息

	/**
	 * 构造函数私有化即单例模式
	 *
	 * @param code
	 * @param msg
	 */
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//非法数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "验证码不能为空！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3, "");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "图片上传错误！");
	public static CodeMsg ORDER_SN_ERROR = new CodeMsg(-12, "订单编号错误！");
	public static CodeMsg PHONE_ERROR = new CodeMsg(-13, "手机号错误！");
	public static CodeMsg ORDER_AUTH_ERROR = new CodeMsg(-14, "\u8ba2\u5355\u9a8c\u8bc1\u5931\u8d25\uff0c\u8ba2\u5355\u7f16\u53f7\u6216\u624b\u673a\u53f7\u8f93\u5165\u6709\u8bef\u6216\u8005\u53ef\u80fd\u4f60\u8d2d\u4e70\u7684\u662f\u76d7\u7248\uff0c\u8bf7\u8054\u7cfb\u3010\u733f\u6765\u5165\u6b64\u3011\u5ba2\u670d\uff01");
	//登录凭证验证错误码
	public static CodeMsg HOME_WX_ERROR_CODE = new CodeMsg(-40029, "code无效");
	public static CodeMsg HOME_WX_AUTHEN_ERROR = new CodeMsg(-40030, "小程序繁忙,请稍后重试");
	public static CodeMsg HOME_WX_NOT_LOGIN_ERROR = new CodeMsg(-10000, "未找到该用户");

	//后台管理类错误码
	//用户管理类错误
	public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000, "用户名不能为空！");
	public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001, "密码不能为空！");
	public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002, "您所属的角色没有该权限！");

	//登录类错误码
	public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000, "该用户名不存在！");
	public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001, "密码错误！");
	public static CodeMsg ADMIN_USER_UNABLE = new CodeMsg(-3002, "该用户已被冻结，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_UNABLE = new CodeMsg(-3003, "该用户所属角色状态不可用，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_AUTHORITES_EMPTY = new CodeMsg(-3004, "该用户所属角色无可用权限，请联系管理员！");

	//后台菜单管理类错误码
	public static CodeMsg ADMIN_MENU_ADD_ERROR = new CodeMsg(-4000, "菜单添加失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_EDIT_ERROR = new CodeMsg(-4001, "菜单编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_ID_EMPTY = new CodeMsg(-4002, "菜单ID不能为空！");
	public static CodeMsg ADMIN_MENU_ID_ERROR = new CodeMsg(-4003, "菜单ID错误！");
	public static CodeMsg ADMIN_MENU_DELETE_ERROR = new CodeMsg(-4004, "改菜单下有子菜单，不允许删除！");
	//后台角色管理类错误码
	public static CodeMsg ADMIN_ROLE_ADD_ERROR = new CodeMsg(-5000, "角色添加失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_NO_EXIST = new CodeMsg(-5001, "该角色不存在！");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-5002, "角色编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-5003, "该角色下存在用户信息，不可删除！");
	//后台用户管理类错误码
	public static CodeMsg ADMIN_USER_ROLE_EMPTY = new CodeMsg(-6000, "请选择用户所属角色！");
	public static CodeMsg ADMIN_USERNAME_EXIST = new CodeMsg(-6001, "该用户名已存在，请换一个试试！");
	public static CodeMsg ADMIN_USE_ADD_ERROR = new CodeMsg(-6002, "用户添加失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_NO_EXIST = new CodeMsg(-6003, "用户不存在！");
	public static CodeMsg ADMIN_USE_EDIT_ERROR = new CodeMsg(-6004, "用户编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_DELETE_ERROR = new CodeMsg(-6005, "该用户存在关联数据，不允许删除！");

	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_USER_UPDATE_PWD_ERROR = new CodeMsg(-7000, "旧密码错误！");
	public static CodeMsg ADMIN_USER_UPDATE_PWD_EMPTY = new CodeMsg(-7001, "新密码不能为空！");
	public static CodeMsg ADMIN_USER_UPDATE_OLD_PWD_EMPTY=new CodeMsg(-7002,"旧密码不能为空");

	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_DATABASE_BACKUP_NO_EXIST = new CodeMsg(-8000, "备份记录不存在！");


	//后台景点类错误码
	public static CodeMsg ADMIN_SCENIC_ADD_ERROR = new CodeMsg(-9000, "景点添加失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_EDIT_ERROR = new CodeMsg(-9001, "景点编辑失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_DELETE_ERROR = new CodeMsg(-9002, "景点删除失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_NOT_EXIST_ERROR = new CodeMsg(-9003, "景点不存在");
	public static CodeMsg ADMIN_SCENIC_NAME_EXIST_ERROR = new CodeMsg(-9004, "景点名称已存在");


	//后台景点攻略类错误码
	public static CodeMsg ADMIN_SCENIC_PLAN_ADD_ERROR = new CodeMsg(-9101, "景点攻略添加失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PLAN_EDIT_ERROR = new CodeMsg(-9102, "景点攻略编辑失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PLAN_DELETE_ERROR = new CodeMsg(-9103, "景点攻略删除失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PLAN_NOT_EXIST_ERROR = new CodeMsg(-9104, "景点攻略不存在");
	public static CodeMsg ADMIN_SCENIC_PLAN_NAME_EXIST_ERROR = new CodeMsg(-9105, "景点攻略名称已存在");

	//后台景点门票类错误码
	public static CodeMsg ADMIN_SCENIC_PRICE_ADD_ERROR = new CodeMsg(-9201, "景点门票安排添加失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PRICE_EDIT_ERROR = new CodeMsg(-9202, "景点门票安排编辑失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PRICE_DELETE_ERROR = new CodeMsg(-9203, "景点门票安排删除失败,请联系管理员");
	public static CodeMsg ADMIN_SCENIC_PRICE_NOT_EXIST_ERROR = new CodeMsg(-9204, "景点门票安排不存在");
	public static CodeMsg ADMIN_SCENIC_PRICE_NAME_EXIST_ERROR = new CodeMsg(-9205, "景点门票安排名称已存在");
	public static CodeMsg ADMIN_SCENIC_PRICE_START_DETA_ERROR=new CodeMsg(-9206,"景点门票开始日期不能为空");
	public static CodeMsg ADMIN_SCENIC_PRICE_END_DETA_ERROR=new CodeMsg(-9207,"景点门票结束日期不能为空");
	public static CodeMsg ADMIN_SCENIC_PRICE_DATE_ERROR=new CodeMsg(-9208,"日期错误,开始日期不能大于结束日期");
	public static CodeMsg ADMIN_SCENIC_PRICE_PRICE_ERROR=new CodeMsg(-9209,"景点门票价格不能为空");
	public static CodeMsg ADMIN_SCENIC_PRICE_LIMITATION_ERROR=new CodeMsg(-9210,"景点门票单人限制不能为空");
	public static CodeMsg ADMIN_SCENIC_PRICE_TICKET_ERROR=new CodeMsg(-9211,"门票不能为空");


	//登录注册类错误码
	public static CodeMsg HOME_ACCOUNT_NAME_EMPTY_ERROR=new CodeMsg(-10000,"用户名称不能为空");
	public static CodeMsg HOME_ACCOUNT_PHONE_EMPTY_ERROR=new CodeMsg(-10001,"手机号不能为空");
	public static CodeMsg HOME_ACCOUNT_EMAIL_FORMAT_ERROR=new CodeMsg(-10002,"邮箱格式不正确");
	public static CodeMsg HOME_ACCOUNT_PHONE_FORMAT_ERROR=new CodeMsg(-10003,"手机号格式不正确");
	public static CodeMsg HOME_ACCOUNT_PASSWORD_LENGTH_ERROR=new CodeMsg(-10004,"密码长度不能低于8位");
	public static CodeMsg HOME_ACCOUNT_SIGN_UP_ERROR=new CodeMsg(-10005,"注册失败,请联系管理员");
	public static CodeMsg HOME_ACCOUNT_SNED_EMAIL_ERROR=new CodeMsg(-10006,"邮箱发送失败");
	public static CodeMsg HOME_ACCOUNT_EMAIL_CPACHA_EMPTY_ERROR=new CodeMsg(-10007,"邮箱验证码不能为空");
	public static CodeMsg HOME_ACCOUNT_EMAIL_CPACHA_ERROR=new CodeMsg(-10008,"邮箱验证码错误");
	public static CodeMsg HOME_ACCOUNT_USER_EMAIL_EXIST_ERROR=new CodeMsg(-10009,"该邮箱已被注册");
	public static CodeMsg HOME_ACCOUNT_USER_MOBILE_EXIST_ERROR=new CodeMsg(-10010,"该手机号已被注册");
	public static CodeMsg HOME_ACCOUNT_USER_MOBILE_EMPTY_ERROR=new CodeMsg(-10011,"手机号未被注册");
	public static CodeMsg HOME_ACCOUNT_PASSWORD_ERROR=new CodeMsg(-10012,"密码不正确");
	public static CodeMsg HOME_ACCOUNT_CARD_NO_EMPTY_ERROR=new CodeMsg(-10013,"用户未填写身份证号,请去个人信息里填写身份证号");
	public static CodeMsg HOME_ACCOUNT_CARD_NO_NOT_EXIST_ERROR=new CodeMsg(-10014,"身份证号不能为空");
	public static CodeMsg HOME_ACCOUNT_CARD_NO_EXIST_ERROR=new CodeMsg(-10015,"身份证号已存在");
	public static CodeMsg HOME_ACCOUNT_EDIT_PROFILE_ERROR=new CodeMsg(-10016,"用户信息更新失败");
	public static CodeMsg HOME_ACCOUNT_FORMAT_CART_NO_ERROR=new CodeMsg(-10017,"身份证格式不正确");
	public static CodeMsg HOME_ACCOUNT_EDIT_PASSWORD_ERROR=new CodeMsg(-10018,"密码修改失败");

	//订单类错误码
	public static CodeMsg HOME_SCENIC_ORDER_COUNT_NUMBER_ERROR=new CodeMsg(-11000,"购买数量不能为空");
	public static CodeMsg HOME_SCENIC_ACCOUNT_BALANCE_ERROR=new CodeMsg(-11001,"用户余额不足");
	public static CodeMsg HOME_SCENIC_ORDER_LIMIT_NUMBER_ERROR=new CodeMsg(-11002,"您已经达到当天门票的限购数");
	public static CodeMsg HOME_SCENIC_PRICE_INVENTORY_NUMBER_ERROR=new CodeMsg(-11003,"当前日期的门票已售罄");
	public static CodeMsg HOME_SCENIC_ORDER_CANCEL_ERROR=new CodeMsg(-11004,"取消订单失败");
	public static CodeMsg HOME_SCENIC_ORDER_NOT_EMPTY_ERROR=new CodeMsg(-11005,"订单不存在");
	public static CodeMsg HOME_SCENIC_ORDER_STATUS_ERROR=new CodeMsg(-11006,"当前订单状态不可取消");
	public static CodeMsg HOME_SCENIC_ORDER_STATUS_DELETE_ERROR=new CodeMsg(-11007,"当前订单状态不可删除");
	public static CodeMsg HOME_SCENIC_ORDER_DELETE_ERROR=new CodeMsg(-11008,"当前订单下有评论信息,先去删除评论信息");


	//收藏类错误码
	public static CodeMsg HOME_SCENIC_FAVORITES_ADD_ERROR=new CodeMsg(-12000,"添加收藏失败");
	public static CodeMsg HOME_SCENIC_FAVORITES_DELETE_ERROR=new CodeMsg(-12001,"取消收藏成功");

	//景点订单评论类错误码
	public static CodeMsg HOME_ORDER_COMMENT_NOT_EXIST_ERROR=new CodeMsg(-13003,"评论不存在");
	public static CodeMsg HOME_ORDER_COMMENT_ADD_ERROR=new CodeMsg(-13000,"评论失败");
	public static CodeMsg HOME_ORDER_COMMENT_SCORE_ERROR=new CodeMsg(-13001,"请选择评分");
	public static CodeMsg HOME_ORDER_COMMENT_CONTENT_EMPTY_ERROR=new CodeMsg(-13002,"评论内容不能为空");
	public static CodeMsg HOME_ORDER_REPLY_CONTENT_EMPTY_ERROR=new CodeMsg(-13003,"回复内容不能为空");
	public static CodeMsg HOME_ORDER_REPLY_ADD_ERROR=new CodeMsg(-13004,"回复评论失败");
	public static CodeMsg HOME_ORDER_COMMENT_DELETE_ERROR=new CodeMsg(-13005,"评论删除失败,评论下有关联回复");



	//酒店 客房类错误码
	public static CodeMsg HOME_HOTEL_TYPE_ADD_ERROR=new CodeMsg(-14000,"客房添加失败");
	public static CodeMsg HOME_HOTEL_TYPE_NOT_EXIST_ERROR=new CodeMsg(-14001,"客房不存在");
	public static CodeMsg HOME_HOTEL_TYPE_EDIT_ERROR=new CodeMsg(-14002,"客房编辑失败");
	public static CodeMsg HOME_HOTEL_TYPE_DELETE_ERROR=new CodeMsg(-14003,"客房删除失败");



	//酒店类错误码
	public static CodeMsg HOME_HOTEL_ADD_ERROR=new CodeMsg(-15000,"酒店添加失败");
	public static CodeMsg HOME_HOTEL_NOT_EXIST_ERROR=new CodeMsg(-15001,"酒店不存在");
	public static CodeMsg HOME_HOTEL_EDIT_ERROR=new CodeMsg(-15002,"酒店编辑失败");
	public static CodeMsg HOME_HOTEL_DELETE_ERROR=new CodeMsg(-15003,"酒店删除失败");

	//酒店订单 订单项 类错误码
	public static CodeMsg HOME_HOTEL_ORDER_ADD_ERROR=new CodeMsg(-16000,"订单创建失败");
	public static CodeMsg HOME_HOTEL_ORDER_CHECK_IN_EMPTY_ERROR=new CodeMsg(-16001,"入住日期不能为空");
	public static CodeMsg HOME_HOTEL_ORDER_CHECK_OUT_EMPTY_ERROR=new CodeMsg(-16002,"退房离开日期不能为空");
	public static CodeMsg HOME_HOTEL_ORDER_PERSONAL_NUMBER_ERROR=new CodeMsg(-16003,"请填写入住人数");
	public static CodeMsg HOME_HOTEL_ORDER_ROOM_EMPTY_ERROR=new CodeMsg(-16004,"客房不能为空");
	public static CodeMsg HOME_HOTEL_ORDER_DAY_ERROR=new CodeMsg(-16005,"日期出现错误");
	public static CodeMsg HOME_HOTEL_ORDER_EMPTY_ERROR=new CodeMsg(-16006,"订单不存在");
	public static CodeMsg HOME_HOTEL_ORDER_STATUS_ERROR=new CodeMsg(-16007,"当前订单状态不可支付");
	public static CodeMsg HOME_HOTEL_ORDER_PAY_ERROR=new CodeMsg(-16008,"订单支付失败");
	public static CodeMsg HOME_HOTEL_ORDER_DELETE_ERROR=new CodeMsg(-16009,"订单删除失败,有关联订单项数据!");
	public static CodeMsg HOME_HOTEL_ORDER_CANCEL_STATUS_ERROR=new CodeMsg(-16010,"订单取消失败,请联系管理员");


	//酒店收藏类错误码
	public static CodeMsg HOME_HOTEL_FAVORITES_ADD_ERROR=new CodeMsg(-17000,"添加酒店收藏失败");
	public static CodeMsg HOME_HOTEL_FAVORITES_DELETE_ERROR=new CodeMsg(-17001,"取消酒店收藏成功");


	//博客类错误码
	public static CodeMsg ADMIN_BLOG_ADD_ERROR=new CodeMsg(-18000,"博客添加失败");
	public static CodeMsg ADMIN_BLOG_NOT_EXIST_ERROR=new CodeMsg(-18001,"博客不存在");
	public static CodeMsg ADMIN_BLOG_EDIT_ERROR=new CodeMsg(-18002,"博客编辑失败");
	public static CodeMsg ADMIN_BLOG_DELETE_ERROR=new CodeMsg(-18003,"博客删除失败");


	//照片类错误码
	public static CodeMsg HOME_GALLERY_ADD_ERROR=new CodeMsg(-19000,"照片添加失败");
	public static CodeMsg HOME_GALLERY_NOT_EXIST_ERROR=new CodeMsg(-19001,"照片不存在");
	public static CodeMsg HOME_GALLERY_EDIT_ERROR=new CodeMsg(-19002,"照片编辑失败");
	public static CodeMsg HOME_GALLERY_DELETE_ERROR=new CodeMsg(-19003,"照片删除失败");
	public static CodeMsg HOME_GALLERY_TITLE_EMPTY_ERROR=new CodeMsg(-19004,"照片标题不能为空");
	public static CodeMsg HOME_GALLERY_IMAGE_EMPTY_ERROR=new CodeMsg(-19005,"照片图片不能为空");


	//充值类错误码
	public static CodeMsg HOME_RECHARGE_ADD_ERROR=new CodeMsg(-20000,"充值失败");
	public static CodeMsg HOME_RECHARGE_PRICE_ERROR=new CodeMsg(-20001,"充值金额不能为空");

}
