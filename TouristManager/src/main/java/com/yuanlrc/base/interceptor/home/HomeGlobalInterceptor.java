package com.yuanlrc.base.interceptor.home;

import com.alibaba.fastjson.JSON;
import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.constant.SessionConstant;
import com.yuanlrc.base.util.SessionUtil;
import com.yuanlrc.base.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台全局拦截器
 * @author SiruiYao 2024/03/06
 *
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor{


	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

		return true;
	}
}
