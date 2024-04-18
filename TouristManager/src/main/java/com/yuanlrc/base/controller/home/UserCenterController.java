package com.yuanlrc.base.controller.home;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.constant.SessionConstant;
import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.common.Gallery;
import com.yuanlrc.base.entity.common.RechargeRecord;
import com.yuanlrc.base.entity.home.*;
import com.yuanlrc.base.service.common.GalleryService;
import com.yuanlrc.base.service.common.RechargeRecordService;
import com.yuanlrc.base.service.home.*;
import com.yuanlrc.base.util.SessionUtil;
import com.yuanlrc.base.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 * 个人中心控制器
 * 创建人 SiruiYao on 2024/03/06
 */

@Controller
@RequestMapping("/home/user_center")
public class UserCenterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ScenicOrderService scenicOrderService;

    @Autowired
    private OrderCommentService orderCommentService;

    @Autowired
    private ScenicFavoritesService scenicFavoritesService;

    @Autowired
    private HotelRoomOrderService hotelRoomOrderService;

    @Autowired
    private HotelRoomOrderItemService hotelRoomOrderItemService;

    @Autowired
    private HotelFavoritesService hotelFavoritesService;


    @Autowired
    private HotelOrderCommentService hotelOrderCommentService;

    @Autowired
    private HotelCommentReplyService hotelCommentReplyService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private RechargeRecordService rechargeRecordService;


    /**
     * 个人中心页面
     *
     * @param model
     * @return
     */
    @GetMapping("/dashboard")
    public String dashborad(Model model) {
        Account account = SessionUtil.getLoginedHomeUser();
        Long id = account.getId();
        model.addAttribute("scenicOrderCount",scenicOrderService.countByAccountId(id));
        model.addAttribute("hotelOrderCount",hotelRoomOrderService.countByAccountId(id));
        model.addAttribute("commentCount",orderCommentService.countByAccountId(id));
        return "home/user_center/dashboard";
    }

    /**
     * 个人信息页面
     *
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title", "个人信息");
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        Account account = accountService.find(loginedHomeUser.getId());
        model.addAttribute("account", account);
        return "home/user_center/profile";
    }


    /**
     * 个人信息修改操作
     *
     * @param account
     * @return
     */
    @ResponseBody
    @PostMapping("/edit_profile")
    public Result<Boolean> editProfile(Account account) {
        String cardNo = account.getCardNo();
        if (StringUtils.isEmpty(cardNo)) {
            return Result.error(CodeMsg.HOME_ACCOUNT_CARD_NO_NOT_EXIST_ERROR);
        }
        if (!StringUtil.isCard(cardNo)) {
            return Result.error(CodeMsg.HOME_ACCOUNT_FORMAT_CART_NO_ERROR);
        }
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        Account existAccount = accountService.findByCardNo(account.getCardNo());
        if (existAccount != null) {
            if (!existAccount.getId().equals(onlineAccount.getId())) {
                return Result.error(CodeMsg.HOME_ACCOUNT_CARD_NO_EXIST_ERROR);
            }
        }
        Account currentAccount = accountService.find(onlineAccount.getId());
        currentAccount.setCardNo(account.getCardNo());
        currentAccount.setAge(account.getAge());
        currentAccount.setSex(account.getSex());
        currentAccount.setUsername(account.getUsername());
        String headPic = account.getHeadPic();
        if (!StringUtils.isEmpty(headPic)) {
            currentAccount.setHeadPic(headPic);
        }
        if (accountService.save(currentAccount) == null) {
            return Result.error(CodeMsg.HOME_ACCOUNT_EDIT_PROFILE_ERROR);
        }
        SessionUtil.set(SessionConstant.SESSION_HOME_USER_LOGIN_KEY, currentAccount);
        return Result.success(true);
    }


    /**
     * 修改密码操作
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @PostMapping("/edit_password")
    public Result<Boolean> editPassword(@RequestParam(name = "oldPassword") String oldPassword,
                                        @RequestParam(name = "newPassword") String newPassword) {
        if (StringUtils.isEmpty(oldPassword)) {
            return Result.error(CodeMsg.ADMIN_USER_UPDATE_OLD_PWD_EMPTY);
        }
        if (StringUtils.isEmpty(newPassword)) {
            return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_EMPTY);
        }
        if (newPassword.length() < 8) {
            return Result.error(CodeMsg.HOME_ACCOUNT_PASSWORD_LENGTH_ERROR);
        }
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        Account account = accountService.find(loginedHomeUser.getId());
        if (!account.getPassword().equals(oldPassword)) {
            return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_ERROR);
        }
        account.setPassword(newPassword);
        if (accountService.updatePassword(account.getId(), newPassword) <= 0) {
            return Result.error(CodeMsg.HOME_ACCOUNT_EDIT_PASSWORD_ERROR);
        }
        SessionUtil.set(SessionConstant.SESSION_HOME_USER_LOGIN_KEY, account);
        return Result.success(true);
    }


    /**
     * 景点订单列表
     *
     * @param model
     * @param scenicOrder
     * @param pageBean
     * @return
     */
    @GetMapping("/scenic_orders")
    public String scenicOrders(Model model, ScenicOrder scenicOrder, PageBean<ScenicOrder> pageBean) throws ParseException {
        model.addAttribute("title", "景点订单列表");
        Account account = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean", scenicOrderService.findHomeList(account.getId(), pageBean));
        return "home/user_center/scenic_orders";
    }


    /**
     * 取消订单操作
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/cancel_order")
    public Result<Boolean> cancelOrder(@RequestParam(name = "id") Long id) {
        ScenicOrder scenicOrder = scenicOrderService.find(id);
        if (scenicOrder == null) {
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_NOT_EMPTY_ERROR);
        }
        if (scenicOrder.getStatus() != ScenicOrder.ORDER_STATUS_PAST_DUE) {
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_STATUS_ERROR);
        }
        scenicOrderService.cancelOrder(scenicOrder);
        return Result.success(true);
    }


    /**
     * 删除订单操作
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_order")
    public Result<Boolean> deleteOrder(@RequestParam(name = "id") Long id) {
        ScenicOrder scenicOrder = scenicOrderService.find(id);
        if (scenicOrder.getStatus() == ScenicOrder.ORDER_STATUS_USERD || scenicOrder.getStatus() == ScenicOrder.ORDER_STATUS_PAST_DUE) {
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_STATUS_DELETE_ERROR);
        }
        try {
            scenicOrderService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_DELETE_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 订单评论操作
     *
     * @param oid
     * @param score
     * @param content
     * @return
     */
    @ResponseBody
    @PostMapping("/order_comment")
    public Result<Boolean> orderComment(
            @RequestParam(name = "oid") Long oid,
            @RequestParam(name = "score") int score,
            @RequestParam(name = "content") String content
    ) {
        if (oid == null) {
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_NOT_EMPTY_ERROR);
        }
        ScenicOrder scenicOrder = scenicOrderService.find(oid);
        if (scenicOrder == null) {
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_NOT_EMPTY_ERROR);
        }
        if (score <= 0) {
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_SCORE_ERROR);
        }
        if (StringUtils.isEmpty(content)) {
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_CONTENT_EMPTY_ERROR);
        }
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        OrderComment orderComment = new OrderComment();
        orderComment.setAccount(onlineAccount);
        orderComment.setContent(content);
        orderComment.setScenic(scenicOrder.getScenicPrice().getScenic());
        orderComment.setScenicOrder(scenicOrder);
        orderComment.setScore(score);
        try {
            orderCommentService.commentSuccess(orderComment);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_ADD_ERROR);
        }
        return Result.success(true);
    }




    /**
     * 订单评论列表
     * @param model
     * @param orderComment
     * @param pageBean
     * @return
     */
    @GetMapping("/order_comment_list")
    public String commentOrderList(Model model,OrderComment orderComment,PageBean<OrderComment> pageBean){
        model.addAttribute("title","订单评论列表");
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean",orderCommentService.findHomeList(onlineAccount.getId(),pageBean));
        return "home/user_center/order_comment_list";
    }


    /**
     * 删除订单评论
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_comment_order")
    public Result<Boolean> deleteCommentOrder(@RequestParam(name = "id")Long id){
        try{
            orderCommentService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_DELETE_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 景点收藏列表
     * @param model
     * @param scenicFavorites
     * @param pageBean
     * @return
     */
    @GetMapping("/scenic_favorites")
    public String scenicFavorites(Model model, ScenicFavorites scenicFavorites,
                                  PageBean<ScenicFavorites> pageBean) {
        model.addAttribute("title", "景点收藏列表");
        Account account = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean", scenicFavoritesService.findHomeList(account.getId(), pageBean));
        return "home/user_center/scenic_favorites";
    }


    /**
     * 取消收藏操作
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_favorites")
    public Result<Boolean> deleteFavorites(@RequestParam(name = "id")Long id){
        try {
            scenicFavoritesService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_SCENIC_FAVORITES_DELETE_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 酒店订单列表
     * @param model
     * @param hotelRoomOrder
     * @param pageBean
     * @return
     * @throws ParseException
     */
    @GetMapping("/hotel_orders")
    public String hotelOrders(Model model, HotelRoomOrder hotelRoomOrder,PageBean<HotelRoomOrder> pageBean) throws ParseException {
        model.addAttribute("title","酒店订单列表");
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean",hotelRoomOrderService.findHomeList(onlineAccount.getId(),pageBean));
        return "home/user_center/hotel_orders";
    }


    /**
     * 删除酒店订单
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_hotel_order")
    public Result<Boolean> deleteHotelOrder(@RequestParam(name = "id")Long id){
        try {
            hotelRoomOrderService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_DELETE_ERROR);
        }

        return Result.success(true);
    }


    /**
     * 取消酒店订单操作
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/cancel_hotel_order")
    public Result<Boolean> cancelHotelOrder(@RequestParam(name = "id")Long id){
        HotelRoomOrder hotelRoomOrder = hotelRoomOrderService.find(id);
        if(hotelRoomOrder==null){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_EMPTY_ERROR);
        }
        try {
            hotelRoomOrderService.cancelOrder(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_CANCEL_STATUS_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 获取订单项列表
     * @param id
     * @return
     */
    @GetMapping("/get_order_item")
    public String getOrderItem(@RequestParam(name = "id")Long id,Model model){
        model.addAttribute("orderItemList",hotelRoomOrderItemService.findByOrderId(id));
        return "home/user_center/get_order_item";
    }


    /**
     * 酒店收藏列表
     * @param model
     * @param hotelFavorites
     * @param pageBean
     * @return
     */
    @GetMapping("/hotel_favorites")
    public String hotelFavorites(Model model, HotelFavorites hotelFavorites,
                                  PageBean<HotelFavorites> pageBean) {
        model.addAttribute("title", "酒店收藏列表");
        Account account = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean", hotelFavoritesService.findHomeList(account.getId(), pageBean));
        return "home/user_center/hotel_favorites";
    }


    /**
     * 取消收藏操作
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_hotel_favorites")
    public Result<Boolean> deleteHotelFavorites(@RequestParam(name = "id")Long id){
        try {
            hotelFavoritesService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_SCENIC_FAVORITES_DELETE_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 酒店订单评论操作
     *
     * @param oid
     * @param score
     * @param content
     * @return
     */
    @ResponseBody
    @PostMapping("/hotel_order_comment")
    public Result<Boolean> hotelOrderComment(
            @RequestParam(name = "oid") Long oid,
            @RequestParam(name = "score") int score,
            @RequestParam(name = "content") String content
    ) {
        if (oid == null) {
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_EMPTY_ERROR);
        }
        HotelRoomOrder hotelRoomOrder = hotelRoomOrderService.find(oid);
        if (hotelRoomOrder == null) {
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_EMPTY_ERROR);
        }
        if (score <= 0) {
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_SCORE_ERROR);
        }
        if (StringUtils.isEmpty(content)) {
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_CONTENT_EMPTY_ERROR);
        }
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        HotelOrderComment hotelOrderComment = new HotelOrderComment();
        hotelOrderComment.setAccount(onlineAccount);
        hotelOrderComment.setContent(content);
        hotelOrderComment.setHotel(hotelRoomOrder.getHotel());
        hotelOrderComment.setHotelRoomOrder(hotelRoomOrder);
        hotelOrderComment.setScore(score);
        try {
            hotelOrderCommentService.commentSuccess(hotelOrderComment);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_ADD_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 订单评论列表
     * @param model
     * @param orderComment
     * @param pageBean
     * @return
     */
    @GetMapping("/hotel_order_comment_list")
    public String hotelCommentOrderList(Model model,HotelOrderComment orderComment,PageBean<HotelOrderComment> pageBean){
        model.addAttribute("title","订单评论列表");
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean",hotelOrderCommentService.findHomeList(onlineAccount.getId(),pageBean));
        return "home/user_center/hotel_order_comment_list";
    }


    /**
     * 删除酒店订单评论
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_hotel_comment_order")
    public Result<Boolean> deleteHotelCommentOrder(@RequestParam(name = "id")Long id){
        try {
            hotelOrderCommentService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_DELETE_ERROR);
        }
        return Result.success(true);
    }


    /**
     * 照片库列表
     * @param model
     * @param pageBean
     * @return
     */
    @GetMapping("/gallery_list")
    public String galleryList(Model model, PageBean<Gallery> pageBean){
        model.addAttribute("title","照片列表");
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean",galleryService.findHomeList(onlineAccount.getId(),pageBean));
        return "home/user_center/gallery_list";
    }

    /**
     * 添加图片页面
     * @param model
     * @return
     */
    @GetMapping("/add_gallery")
    public String addGallery(Model model){

        return "home/user_center/add_gallery";
    }


    /**
     * 保存图片操作
     * @param gallery
     * @return
     */
    @ResponseBody
    @PostMapping("/add_gallery")
    public Result<Boolean> addGallery(Gallery gallery){
        if(StringUtils.isEmpty(gallery.getTitle())){
            return Result.error(CodeMsg.HOME_GALLERY_TITLE_EMPTY_ERROR);
        }
        if(StringUtils.isEmpty(gallery.getImages())){
            return Result.error(CodeMsg.HOME_GALLERY_IMAGE_EMPTY_ERROR);
        }
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        gallery.setAccount(loginedHomeUser);
        if(galleryService.save(gallery)==null){
            return Result.error(CodeMsg.HOME_GALLERY_ADD_ERROR);
        }
        return Result.success(true);
    }

    /**
     * 图片删除操作
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delete_gallery")
    public Result<Boolean> deleteGallery(@RequestParam(name = "id")Long id){
        galleryService.delete(id);
        return Result.success(true);
    }


    /**
     * 充值金额操作
     * @param rechargeRecord
     * @return
     */
    @ResponseBody
    @PostMapping("/recharge_add")
    public Result<Boolean> rechargeAdd(RechargeRecord rechargeRecord){
        BigDecimal price = rechargeRecord.getPrice();
        if(price.compareTo(BigDecimal.ZERO)<=0){
            return Result.error(CodeMsg.HOME_RECHARGE_PRICE_ERROR);
        }
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        rechargeRecord.setAccount(onlineAccount);
        rechargeRecordService.save(rechargeRecord);
        return Result.success(true);
    }


    /**
     * 充值记录
     * @param model
     * @param rechargeRecord
     * @param pageBean
     * @return
     */
    @GetMapping("/recharge_list")
    public String rechargeList(Model model,RechargeRecord rechargeRecord,PageBean<RechargeRecord> pageBean){
        model.addAttribute("title","充值记录列表");
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        model.addAttribute("pageBean",rechargeRecordService.findList(loginedHomeUser.getId(),pageBean));
        return "home/user_center/recharge_list";
    }
}
