package com.yuanlrc.base.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.entity.home.*;
import com.yuanlrc.base.service.common.ScenicPlanService;
import com.yuanlrc.base.service.common.ScenicPriceService;
import com.yuanlrc.base.service.common.ScenicService;
import com.yuanlrc.base.service.home.*;
import com.yuanlrc.base.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * 景点Controller
 * 创建人 SiruiYao on 2024/03/06
 */
@Controller
@RequestMapping("/home/tourist")
public class TouristController {

    @Autowired
    private ScenicService scenicService;

    @Autowired
    private ScenicPlanService scenicPlanService;

    @Autowired
    private ScenicPriceService scenicPriceService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ScenicOrderService scenicOrderService;

    @Autowired
    private ScenicFavoritesService scenicFavoritesService;

    @Autowired
    private OrderCommentService orderCommentService;

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     *景点列表
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, Scenic scenic, PageBean<Scenic> pageBean){
        model.addAttribute("title","景点列表");
        model.addAttribute("name",scenic.getTitle());
        model.addAttribute("pageBean",scenicService.findHomeList(scenic,pageBean));
        model.addAttribute("minPrice",scenic.getMinPrice());
        model.addAttribute("maxPrice",scenic.getMaxPrice());
        model.addAttribute("scenic",scenic);
        return "home/tourist/list";
    }


    /**
     * 景点详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String details(@RequestParam(name = "id")Long id,Model model) throws ParseException {
        model.addAttribute("scenic",scenicService.find(id));
        model.addAttribute("title","景点详情信息");
        scenicService.updateViewNumber(id);
        model.addAttribute("scenicPlanList",scenicPlanService.findByScenicId(id));
        Account account = SessionUtil.getLoginedHomeUser();
        if(account!=null){
            model.addAttribute("favorites",scenicFavoritesService.findByAccountIdAndScenicId(account.getId(),id));
        }
        JSONArray jsonArray = scenicPriceService.findByScenicIdAndProductDateGreaterThanEqual(id);
        model.addAttribute("commentList",orderCommentService.findByScenicId(id));
        model.addAttribute("scenicPriceList",jsonArray.toJSONString());
        return "home/tourist/detail";
    }


    /**
     * 预约订单
     * @param scenicOrder
     * @return
     */
    @ResponseBody
    @PostMapping("/appoint_scenic")
    public Result<Boolean> appointTourist(ScenicOrder scenicOrder){
        Long pid = scenicOrder.getPid();
        if(pid==null){
            return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_NOT_EXIST_ERROR);
        }
        ScenicPrice scenicPrice = scenicPriceService.find(pid);
        if(scenicPrice==null){
            return Result.error(CodeMsg.ADMIN_SCENIC_PRICE_NOT_EXIST_ERROR);
        }
        int inventory = scenicPrice.getInventory();
        if(inventory<=0){
            return Result.error(CodeMsg.HOME_SCENIC_PRICE_INVENTORY_NUMBER_ERROR);
        }
        int limitation = scenicPrice.getLimitation();
        Integer count = scenicOrder.getCount();
        if(count>limitation){
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_COUNT_NUMBER_ERROR);
        }
        Account account = SessionUtil.getLoginedHomeUser();
        Account currentAccount = accountService.find(account.getId());
        if(StringUtils.isEmpty(currentAccount.getCardNo())){
            return Result.error(CodeMsg.HOME_ACCOUNT_CARD_NO_EMPTY_ERROR);
        }
        int buyNumber = scenicOrderService.countByAccountIdAndScenicPriceId(account.getId(), pid);
        if(buyNumber>=limitation){
            return Result.error(CodeMsg.HOME_SCENIC_ORDER_LIMIT_NUMBER_ERROR);
        }
        BigDecimal balance = currentAccount.getBalance();
        BigDecimal price = scenicPrice.getPrice();
        BigDecimal countNumber = new BigDecimal(count);
        BigDecimal multiply = countNumber.multiply(price);
        if(balance.compareTo(multiply)==-1){
            return Result.error(CodeMsg.HOME_SCENIC_ACCOUNT_BALANCE_ERROR);
        }
        scenicPrice.setInventory(inventory-count);
        BigDecimal defaultBalance = balance.subtract(multiply);
        currentAccount.setBalance(defaultBalance);
        scenicOrder.setAccount(account);
        scenicOrder.setScenicPrice(scenicPrice);
        scenicOrder.setStartDate(scenicPrice.getProductDate());
        scenicOrder.setPrice(multiply);
        scenicOrder.setDetails("您已购买"+count+"张"+scenicPrice.getScenic().getTitle()+"景点的"+scenicPrice.getProductDate()+"的门票");
        scenicOrder.setPayStatus(ScenicOrder.ORDER_PAY_STATUS_PAYED);
        scenicOrderService.paySuccess(scenicOrder,currentAccount,scenicPrice);
        return Result.success(true);
    }


    /**
     * 收藏景点
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/add_favorites")
    public Result<Boolean> addFavorites(@RequestParam(name = "id")Long id){
        Scenic scenic = scenicService.find(id);
        if(scenic==null){
            return Result.error(CodeMsg.ADMIN_SCENIC_NOT_EXIST_ERROR);
        }
        Account account = SessionUtil.getLoginedHomeUser();
        ScenicFavorites favorites = scenicFavoritesService.findByAccountIdAndScenicId(account.getId(), scenic.getId());
        if(favorites==null){
            ScenicFavorites scenicFavorites = new ScenicFavorites();
            scenicFavorites.setScenic(scenic);
            scenicFavorites.setAccount(account);
            if(scenicFavoritesService.save(scenicFavorites)==null){
                return Result.error(CodeMsg.HOME_SCENIC_FAVORITES_ADD_ERROR);
            }
        }else{
            scenicFavoritesService.delete(favorites.getId());
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
        OrderComment orderComment = orderCommentService.find(commentId);
        if(orderComment==null){
            return Result.error(CodeMsg.HOME_ORDER_COMMENT_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(content)){
            return Result.error(CodeMsg.HOME_ORDER_REPLY_CONTENT_EMPTY_ERROR);
        }
        Account account = accountService.find(accountId);
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        CommentReply commentReply = new CommentReply();
        commentReply.setAccount(account);
        commentReply.setOrderComment(orderComment);
        commentReply.setContent(content);
        commentReply.setReplyAccount(onlineAccount);
        if(commentReplyService.save(commentReply)==null){
            return Result.error(CodeMsg.HOME_ORDER_REPLY_ADD_ERROR);
        }
        return Result.success(true);
    }
}
