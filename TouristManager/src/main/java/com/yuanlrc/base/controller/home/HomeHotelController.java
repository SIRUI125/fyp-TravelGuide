package com.yuanlrc.base.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.HotelType;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.home.*;
import com.yuanlrc.base.service.common.HotelService;
import com.yuanlrc.base.service.common.HotelTypeService;
import com.yuanlrc.base.service.home.AccountService;
import com.yuanlrc.base.service.home.HotelCommentReplyService;
import com.yuanlrc.base.service.home.HotelFavoritesService;
import com.yuanlrc.base.service.home.HotelOrderCommentService;
import com.yuanlrc.base.util.DateUtil;
import com.yuanlrc.base.util.SessionUtil;
import com.yuanlrc.base.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 酒店Controller
 * 创建人 SiruiYao on 2024/03/06
 */
@Controller
@RequestMapping("/home/hotel")
public class HomeHotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelTypeService hotelTypeService;

    @Autowired
    private HotelFavoritesService hotelFavoritesService;

    @Autowired
    private HotelOrderCommentService hotelOrderCommentService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private HotelCommentReplyService hotelCommentReplyService;

    /**
     *酒店列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, Hotel hotel, PageBean<Hotel> pageBean){
        model.addAttribute("title","酒店列表");
        model.addAttribute("name",hotel.getName());
        model.addAttribute("pageBean",hotelService.findHomeList(hotel,pageBean));
        model.addAttribute("minPrice",hotel.getMinPrice());
        model.addAttribute("maxPrice",hotel.getMaxPrice());
        model.addAttribute("hotel",hotel);

        return "home/hotel/list";
    }

    /**
     * 酒店详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public String detail(@RequestParam(name = "id")Long id,Model model){
        model.addAttribute("hotel",hotelService.find(id));
        model.addAttribute("hotelTypeList",hotelTypeService.findByHotelIdAndStatus(id, Hotel.HOTEL_STATUS_ON));
        model.addAttribute("title","酒店详情");
        Account account = SessionUtil.getLoginedHomeUser();
        if(account!=null){
            model.addAttribute("favorites",hotelFavoritesService.findByAccountIdAndHotelId(account.getId(),id));
        }
        model.addAttribute("commentList",hotelOrderCommentService.findByHotelId(id));

        return "home/hotel/detail";
    }


    /**
     * 添加酒店收藏
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/add_hotel_favorites")
    public Result<Boolean> addHotelFavorites(@RequestParam(name = "id")Long id){
        Hotel hotel = hotelService.find(id);
        if(hotel==null){
            return Result.error(CodeMsg.HOME_HOTEL_NOT_EXIST_ERROR);
        }
        Account account = SessionUtil.getLoginedHomeUser();
        HotelFavorites existHotelFavorites = hotelFavoritesService.findByAccountIdAndHotelId(account.getId(), hotel.getId());
        if(existHotelFavorites==null){
            HotelFavorites hotelFavorites = new HotelFavorites();
            hotelFavorites.setHotel(hotel);
            hotelFavorites.setAccount(account);
            if(hotelFavoritesService.save(hotelFavorites)==null){
                return Result.error(CodeMsg.HOME_HOTEL_FAVORITES_ADD_ERROR);
            }
        }else{
            hotelFavoritesService.delete(existHotelFavorites.getId());
        }
        return Result.success(true);
    }


    /**
     * 回复评论操作
     * @param commentId
     * @return
     */
    @ResponseBody
    @PostMapping("/reply_comment")
    public Result<Boolean> replyComment(@RequestParam(name = "commentId")Long commentId,
                                        @RequestParam(name = "content")String content,
                                        @RequestParam(name = "accountId")Long accountId){
        if(commentId==null){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_NOT_EXIST_ERROR);
        }
        HotelOrderComment hotelOrderComment = hotelOrderCommentService.find(commentId);
        if(hotelOrderComment==null){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(content)){
            return Result.error(CodeMsg.HOME_ORDER_REPLY_CONTENT_EMPTY_ERROR);
        }
        Account account = accountService.find(accountId);
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        HotelCommentReply commentReply = new HotelCommentReply();
        commentReply.setAccount(account);
        commentReply.setHotelOrderComment(hotelOrderComment);
        commentReply.setContent(content);
        commentReply.setReplyAccount(onlineAccount);
        if(hotelCommentReplyService.save(commentReply)==null){
            return Result.error(CodeMsg.HOME_ORDER_REPLY_ADD_ERROR);
        }
        return Result.success(true);
    }
}
