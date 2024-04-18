package com.yuanlrc.base.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author SiruiYao 2024/03/06
 *
 */
public class RuntimeConstant {

	//后台登录拦截器无需拦截的url
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/home/**",
			"/system/login",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/kindeditor/**",
			"/cpacha/generate_cpacha",
			"/admin/hotel_order/get_order_item",
			"/system/auth_order",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/upload/**",
			"/photo/view"
	);
	//后台权限拦截器无需拦截的url
	public static List<String> authorityExcludePathPatterns = Arrays.asList(
			"/home/**",
			"/system/login",
			"/system/auth_order",
			"/system/index",
			"/system/no_right",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/kindeditor/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/admin/hotel_order/get_order_item",
			"/system/logout",
			"/system/update_userinfo",
			"/system/update_pwd",
			"/upload/**",
			"/photo/view"
	);

	//前台登录拦截器无需拦截的url
	public static List<String> homeLoginExcludePathPatterns = Arrays.asList(
			"/admin/**",
			"/home/tourist/list",
			"/home/hotel/list",
			"/home/blog/**",
			"/home/gallery/**",
			"/home/index/**",
			"/home/layui/**",
			"/system/auth_order",
			"/home/tourist/detail",
			"/home/hotel/detail",
			"/home/css/**",
			"/home/fonts/**",
			"/home/js/**",
			"/home/images/**",
			"/home/assets/**",
			"/home/styles/**",
			"/home/vendors/**",
			"/system/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/photo/view",
			"/upload/uploadFile",
			"/upload/upload_photo"
	);
	//前台全局拦截器无需拦截的url
	public static List<String> homeGlobalExcludePathPatterns = Arrays.asList(
			"/admin/**",
			"/home/layui/**",
			"/home/tourist/list",
			"/home/gallery/**",
			"/home/hotel/list",
			"/home/tourist/detail",
			"/home/hotel/detail",
			"/home/blog/**",
			"/home/index/**",
			"/home/css/**",
			"/home/fonts/**",
			"/home/js/**",
			"/home/images/**",
			"/home/assets/**",
			"/home/styles/**",
			"/home/vendors/**",
			"/cpacha/generate_cpacha",
			"/system/**",
			"/error",
			"/photo/view",
			"/upload/upload_photo",
			"/upload/uploadFile",
			"/cpacha/generate_cpacha"
	);
}
