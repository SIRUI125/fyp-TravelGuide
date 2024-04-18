package com.yuanlrc.base.controller.home;

import com.yuanlrc.base.bean.CodeMsg;
import com.yuanlrc.base.bean.Result;
import com.yuanlrc.base.constant.SessionConstant;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.HotelType;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.HotelRoomOrderItem;
import com.yuanlrc.base.service.common.HotelService;
import com.yuanlrc.base.service.common.HotelTypeService;
import com.yuanlrc.base.service.home.AccountService;
import com.yuanlrc.base.service.home.HotelRoomOrderItemService;
import com.yuanlrc.base.service.home.HotelRoomOrderService;
import com.yuanlrc.base.util.*;
import net.jodah.expiringmap.ExpirationListener;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 酒店订单控制器
 * 创建人 SiruiYao on 2024/03/06
 */

@Controller
@RequestMapping("/home/hotel_order")
public class OrderController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HotelRoomOrderService hotelRoomOrderService;

    @Autowired
    private HotelRoomOrderItemService hotelRoomOrderItemService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelTypeService hotelTypeService;

    ExpiringMap<String, Long> listenerMap = ExpiringMap.builder().variableExpiration().expirationListener(
            (thekey, thevalue) -> {
                mapListener(thekey, thevalue);
                System.out.println("key:" + thekey + "过期");
            }
    ).expiration(30, TimeUnit.MINUTES)
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();
    /**
     * 创建订单
     * @param orderItem
     * @return
     */
    @ResponseBody
    @PostMapping("/create_order")
    public Result<Long> createOrder(HotelRoomOrderItem orderItem,
                                       @RequestParam(name = "checkInDate")String checkInDate,
                                       @RequestParam(name = "checkOutDate")String checkOutDate,
                                       @RequestParam(name = "roomIds")String roomIds,
                                       @RequestParam(name = "hotelId")Long hotelId) throws ParseException {
        if(StringUtils.isEmpty(checkInDate)){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_CHECK_IN_EMPTY_ERROR);
        }
        if(StringUtils.isEmpty(checkOutDate)){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_CHECK_OUT_EMPTY_ERROR);
        }
        int personalNumber = orderItem.getPersonalNumber();
        if(personalNumber<=0){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_PERSONAL_NUMBER_ERROR);
        }
        if(StringUtils.isEmpty(roomIds)){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_ROOM_EMPTY_ERROR);
        }
        Hotel hotel = hotelService.find(hotelId);
        if(hotel==null){
            return Result.error(CodeMsg.HOME_HOTEL_NOT_EXIST_ERROR);
        }
        Date checkInDateParse = DateUtil.date_sdf.parse(checkInDate);
        Date checkOutDateParse = DateUtil.date_sdf.parse(checkOutDate);
        int daySub = DateUtil.getDaySub(checkInDateParse, checkOutDateParse)-1;
        if(daySub<=0){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_DAY_ERROR);
        }
        Account account = SessionUtil.getLoginedHomeUser();
        if(StringUtils.isEmpty(account.getCardNo())){
            return Result.error(CodeMsg.HOME_ACCOUNT_CARD_NO_EMPTY_ERROR);
        }
        HotelRoomOrder hotelRoomOrder = new HotelRoomOrder();
        hotelRoomOrder.setAccount(account);
        hotelRoomOrder.setHotel(hotel);
        hotelRoomOrder.setSn(String.valueOf(System.currentTimeMillis()));
        hotelRoomOrder.setNum(personalNumber);
        hotelRoomOrder.setCheckInDate(checkInDate);
        hotelRoomOrder.setCheckOutDate(checkOutDate);
        String[] split = roomIds.split(",");
        BigDecimal totalPrice=BigDecimal.ZERO;
        List<HotelRoomOrderItem> hotelRoomOrderItems = new ArrayList<HotelRoomOrderItem>();
        for(int i=0;i<split.length;i++){
            String id=split[i];
            HotelType hotelType = hotelTypeService.find(Long.valueOf(id));
            if(hotelType==null){
                return Result.error(CodeMsg.HOME_HOTEL_TYPE_NOT_EXIST_ERROR);
            }
            HotelRoomOrderItem hotelRoomOrderItem = new HotelRoomOrderItem();
            hotelRoomOrderItem.setHotelType(hotelType);
            hotelRoomOrderItem.setDay(daySub);
            BigDecimal price = hotelType.getPrice();
            BigDecimal day = new BigDecimal(daySub);
            BigDecimal multiply = price.multiply(day);
            hotelRoomOrderItem.setMoney(multiply);
            totalPrice=totalPrice.add(multiply);
            hotelRoomOrderItems.add(hotelRoomOrderItem);
        }
        hotelRoomOrder.setTotalPrice(totalPrice);
        HotelRoomOrder order = hotelRoomOrderService.createOrder(hotelRoomOrder, hotelRoomOrderItems);
        if(order==null){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_ADD_ERROR);
        }
        listenerMap.put("order_"+order.getId(),order.getId(),5,TimeUnit.MINUTES);
        return Result.success(order.getId());
    }


    /**
     * 支付页面
     * @param oid
     * @return
     */
    @GetMapping("/to_pay")
    public String toPay(@RequestParam(name = "oid")Long oid,Model model){
        model.addAttribute("title","订单支付页面");
        HotelRoomOrder hotelRoomOrder = hotelRoomOrderService.find(oid);
        if(hotelRoomOrder==null){
            model.addAttribute("msg","订单不存在");
            return "home/error/404";
        }
        Account account = hotelRoomOrder.getAccount();
        Account loginedHomeUser = SessionUtil.getLoginedHomeUser();
        if(!account.getId().equals(loginedHomeUser.getId())){
            model.addAttribute("msg","订单不存在");
            return "home/error/404";
        }
        model.addAttribute("order",hotelRoomOrder);
        model.addAttribute("orderItemList",hotelRoomOrderItemService.findByOrderId(oid));
        return "home/hotel_order/order_received";
    }


    /**
     * 订单支付操作
     * @param oid
     * @return
     */
    @ResponseBody
    @PostMapping("/order_pay")
    public Result<Boolean> orderPay(@RequestParam(name = "oid")Long oid){
        if(oid==null){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_EMPTY_ERROR);
        }
        HotelRoomOrder hotelRoomOrder = hotelRoomOrderService.find(oid);
        if(hotelRoomOrder==null){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_EMPTY_ERROR);
        }
        if(hotelRoomOrder.getStatus()!=HotelRoomOrder.ORDER_PAY_STATUS_NO_PAY){
            return Result.error(CodeMsg.HOME_HOTEL_ORDER_STATUS_ERROR);
        }
        Account onlineAccount = SessionUtil.getLoginedHomeUser();
        Account account = accountService.find(onlineAccount.getId());
        BigDecimal totalPrice = hotelRoomOrder.getTotalPrice();
        BigDecimal balance = account.getBalance();
        if(balance.compareTo(totalPrice)==-1){
            return Result.error(CodeMsg.HOME_SCENIC_ACCOUNT_BALANCE_ERROR);
        }
        BigDecimal subtract = balance.subtract(totalPrice);
        account.setBalance(subtract);
       try {
           hotelRoomOrderService.orderPay(account,hotelRoomOrder.getId());
       }catch (Exception e){
           return Result.error(CodeMsg.HOME_HOTEL_ORDER_PAY_ERROR);
       }
        return Result.success(true);
    }

    /**
     * map过期监听操作
     *
     * @param key
     * @param value
     */
    private void mapListener(Object key, Object value) {
        if(key!=null){
            Long orderId = Long.valueOf(String.valueOf(value));
            HotelRoomOrder hotelRoomOrder = hotelRoomOrderService.find(orderId);
            if(hotelRoomOrder.getStatus()==HotelRoomOrder.ORDER_PAY_STATUS_NO_PAY){
                hotelRoomOrderService.cancelOrder(orderId);
            }
        }
    }
}
