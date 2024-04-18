package com.yuanlrc.base.controller.home;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.constant.SessionConstant;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.service.common.HotelService;
import com.yuanlrc.base.service.common.ScenicService;
import com.yuanlrc.base.service.home.AccountService;
import com.yuanlrc.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 前台首页控制器
 * 创建人 SiruiYao on 2024/03/06
 */

@Controller
@RequestMapping("/home/index")
public class IndexController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ScenicService scenicService;

    @Autowired
    private HotelService hotelService;
    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model){
        List<Scenic> scenics = scenicService.findTop12ByOrderByCreateTime();
        model.addAttribute("scenics",scenics);
        model.addAttribute("hotelList",hotelService.findTop12ByStatusOrderByCreateTimeDesc(Hotel.HOTEL_STATUS_ON));
        return "home/index/index";
    }


    /**
     * 登录页面
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title","登录页面");
        return "home/index/login";
    }


    /**
     * 注册页面
     * @param model
     * @return
     */
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","注册页面");
        return "home/index/signup";
    }


    /**
     * 注册用户操作
     * @param account
     * @return
     */
    @ResponseBody
    @PostMapping("/sign_up")
    public Result<Boolean> signUp(Account account, @RequestParam(name = "cpacha")String cpacha,
    @RequestParam(name = "emailCpa")String emailCpa
    ){
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(account);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
        String mobile = account.getMobile();
        if(!StringUtil.isMobile(mobile)){
            return Result.error(CodeMsg.HOME_ACCOUNT_PHONE_FORMAT_ERROR);
        }
        String email = account.getEmail();
        if(!StringUtil.emailFormat(email)){
            return Result.error(CodeMsg.HOME_ACCOUNT_EMAIL_FORMAT_ERROR);
        }
        String password = account.getPassword();
        if(password.length()<8){
            return Result.error(CodeMsg.HOME_ACCOUNT_PASSWORD_LENGTH_ERROR);
        }
        if(StringUtils.isEmpty(cpacha)){
            return Result.error(CodeMsg.CPACHA_EMPTY);
        }
        if(StringUtils.isEmpty(emailCpa)){
            return Result.error(CodeMsg.HOME_ACCOUNT_EMAIL_CPACHA_EMPTY_ERROR);
        }
        String homeLogin = (String) SessionUtil.get("home_sign");
        if(homeLogin==null){
            return Result.error(CodeMsg.CPACHA_EMPTY);
        }
        if(!cpacha.equalsIgnoreCase(homeLogin)){
            return Result.error(CodeMsg.CPACHA_ERROR);
        }
        String emailCpacha = (String) SessionUtil.get(SessionConstant.SESSION_HOME_SIGN_SEND_EMAIL_KEY);
        if(emailCpacha==null){
            return Result.error(CodeMsg.HOME_ACCOUNT_EMAIL_CPACHA_ERROR);
        }
        if(!emailCpa.equalsIgnoreCase(emailCpacha)){
            return Result.error(CodeMsg.CPACHA_ERROR);
        }
        Account mobileAccount = accountService.findByMobile(mobile);
        if(mobileAccount!=null){
            return Result.error(CodeMsg.HOME_ACCOUNT_USER_MOBILE_EXIST_ERROR);
        }
        Account emailAccount = accountService.findByEmail(email);
        if(emailAccount!=null){
            return Result.error(CodeMsg.HOME_ACCOUNT_USER_EMAIL_EXIST_ERROR);
        }
        account.setBalance(BigDecimal.ZERO);
        if(accountService.save(account)==null){
            return Result.error(CodeMsg.HOME_ACCOUNT_SIGN_UP_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @ResponseBody
    @PostMapping("/send_email")
    public Result<Boolean> sendEmail(@RequestParam(name = "email")String email) throws Exception {
        if(StringUtils.isEmpty(email)){
            return Result.error(CodeMsg.HOME_ACCOUNT_EMAIL_FORMAT_ERROR);
        }
        if(!StringUtil.emailFormat(email)){
            return Result.error(CodeMsg.HOME_ACCOUNT_EMAIL_FORMAT_ERROR);
        }
        CpachaUtil cpachaUtil = new CpachaUtil(4);
        String generatorVCode = cpachaUtil.generatorVCode();
        SendEmailUtil sendEmailUtil = new SendEmailUtil();
        try {
            sendEmailUtil.sendMsg(email,generatorVCode);
            SessionUtil.set(SessionConstant.SESSION_HOME_SIGN_SEND_EMAIL_KEY,generatorVCode);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_ACCOUNT_SNED_EMAIL_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 登录操作
     * @param account
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Result<String> login(Account account){
        String mobile = account.getMobile();
        if(StringUtils.isEmpty(mobile)){
            return Result.error(CodeMsg.HOME_ACCOUNT_PHONE_EMPTY_ERROR);
        }
        String password = account.getPassword();
        if(StringUtils.isEmpty(password)){
            return Result.error(CodeMsg.ADMIN_PASSWORD_EMPTY);
        }
        if(!StringUtil.isMobile(mobile)){
            return Result.error(CodeMsg.HOME_ACCOUNT_PHONE_FORMAT_ERROR);
        }
        Account mobileAccount = accountService.findByMobile(mobile);
        if(mobileAccount==null){
            return Result.error(CodeMsg.HOME_ACCOUNT_USER_MOBILE_EMPTY_ERROR);
        }
        if(!mobileAccount.getPassword().equals(password)){
            return Result.error(CodeMsg.HOME_ACCOUNT_PASSWORD_ERROR);
        }
        SessionUtil.set(SessionConstant.SESSION_HOME_USER_LOGIN_KEY,mobileAccount);
        String forwardUrl = (String) SessionUtil.get(SessionConstant.SESSION_FORWARD_URL_KEY);
        return Result.success(forwardUrl);
    }


    /**
     * 注销操作
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        if(loginedHomeUser!=null){
            SessionUtil.set(SessionConstant.SESSION_HOME_USER_LOGIN_KEY,null);
        }
        return "redirect:/home/index/index";
    }
}
