package com.yuanlrc.base.util;

import com.alibaba.fastjson.JSONObject;
import com.yuanlrc.base.bean.CodeMsg;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目通用工具类
 * @author SiruiYao 2024/03/06
 *
 */
public class StringUtil {


	/**
	 * 返回指定格式的日期字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getFormatterDate(Date date,String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	/**
	 * 判断请求是否是ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(header))return true;
		return false;
	}

	/**
	 * 从流读取字符串
	 * @param inputStream
	 * @return
	 */
	public static String getStringFromInputStream(InputStream inputStream){
		String string = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
			String buf = null;
			try {
				while((buf = bufferedReader.readLine()) != null){
					string += buf;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return string;
	}
	/**
	 * 验证输入的邮箱格式是否符合
	 * @param email
	 * @return
	 */
	public static boolean emailFormat(String email){
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}


	/**
	 * 验证是否是手机号
	 * @param
	 * @return
	 */
	public static boolean  isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		String s2="^1[0-9]{10}$";// 验证手机号
		if(StringUtils.isNotBlank(str)){
			p = Pattern.compile(s2);
			m = p.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 验证是否是qq号
	 * @param qq
	 * @return
	 */
	public static boolean checkQQ(String qq) {
		//先验证是否为5—12位数字
		if(qq.length() < 5 || qq.length() > 12) {
			return false;
		}
		//首位不能是0
		if(qq.charAt(0) == '0') {
			return false;
		}
		//验证每一位数字都在1-9内
		for(int x = 0;x < qq.length();x++) {
			char ch = qq.charAt(x);
			if(ch < '0' || ch > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 身份证验证
	 * @param card
	 * @return
	 */
	public static boolean isCard(String card)
	{
		String checkIdCard = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
		Pattern c = Pattern.compile(checkIdCard);
		Matcher m2 = c.matcher(card);
		if (m2.matches()){
			return true;
		}
		return false;
	}



	public static String getMac(){
		String mac = "";
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			byte[] hardwareAddress = NetworkInterface.getByInetAddress(localHost).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<hardwareAddress.length; i++) {
				//字节转换为整数
				int temp = hardwareAddress[i]&0xff;
				String str = Integer.toHexString(temp);
				//System.out.println("每8位:"+str);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			mac = sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mac.toUpperCase();
	}

	public static boolean authOrder(String orderSn,String phone){
		Map<String, String> headerParaMap = new HashMap<String, String>();
		String mac = getMac();
		String paramToken = DESUtil.encrypt(orderSn, mac+"#"+orderSn+"#"+phone);
		headerParaMap.put("paramToken", paramToken);
		String timeToken = DESUtil.encrypt("muyi_ylrc", System.currentTimeMillis()+"");
		headerParaMap.put("timeToken", timeToken);
		String sendPost = HttpUtil.sendPost("http://120.25.120.129:8081/order_auth/verify",headerParaMap,"orderSn="+orderSn+"&phone="+phone+"&mac="+mac);
		JSONObject parseObject = JSONObject.parseObject(sendPost);
		if(parseObject.getIntValue("code") != CodeMsg.SUCCESS.getCode()){
			return false;
		}
		return true;
	}


	public static boolean checkOrder(String orderSn,String phone){
		Map<String, String> headerParaMap = new HashMap<String, String>();
		String mac = getMac();
		String paramToken = DESUtil.encrypt(orderSn, mac+"#"+orderSn+"#"+phone);
		headerParaMap.put("paramToken", paramToken);
		String timeToken = DESUtil.encrypt("muyi_ylrc", System.currentTimeMillis()+"");
		headerParaMap.put("timeToken", timeToken);
		headerParaMap.put("orderSn",orderSn);
		headerParaMap.put("phone",phone);
		headerParaMap.put("mac",mac);
		String sendPost = HttpUtil.httpPostMap("http://120.25.120.129:8081/order_auth/verify",headerParaMap);
		JSONObject parseObject = JSONObject.parseObject(sendPost);
		if(parseObject.getIntValue("code") != CodeMsg.SUCCESS.getCode()){
			return false;
		}
		return true;
	}


	/**
	 * 生成唯一字符串
	 * @return
	 */
	public static String generateSn(){
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}
}
