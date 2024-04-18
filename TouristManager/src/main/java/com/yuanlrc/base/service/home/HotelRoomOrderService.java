package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.HotelDao;
import com.yuanlrc.base.dao.common.HotelTypeDao;
import com.yuanlrc.base.dao.common.ScenicPriceDao;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.HotelRoomOrderDao;
import com.yuanlrc.base.dao.home.HotelRoomOrderItemDao;
import com.yuanlrc.base.dao.home.ScenicOrderDao;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.HotelType;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.HotelRoomOrderItem;
import com.yuanlrc.base.entity.home.ScenicOrder;
import com.yuanlrc.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 酒店预约订单管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class HotelRoomOrderService {

    @Autowired
    private HotelRoomOrderDao hotelRoomOrderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private HotelRoomOrderItemDao hotelRoomOrderItemDao;
    @Autowired
    private HotelTypeDao hotelTypeDao;

    /**
     * 根据酒店预约订单id查询
     *
     * @param id
     * @return
     */
    public HotelRoomOrder find(Long id) {
        return hotelRoomOrderDao.find(id);
    }


    /**
     * 酒店预约订单添加/编辑操作
     *
     * @param hotelRoomOrder
     * @return
     */
    public HotelRoomOrder save(HotelRoomOrder hotelRoomOrder) {
        return hotelRoomOrderDao.save(hotelRoomOrder);
    }

    /**
     * 分页查询酒店预约订单列表
     *
     * @param hotelRoomOrder
     * @param pageBean
     * @return
     */
    public PageBean<HotelRoomOrder> findAdminList(HotelRoomOrder hotelRoomOrder, PageBean<HotelRoomOrder> pageBean) {
        Specification<HotelRoomOrder> specification = new Specification<HotelRoomOrder>() {
            @Override
            public Predicate toPredicate(Root<HotelRoomOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("account").get("mobile"), "%" + (hotelRoomOrder.getAccount() == null ? "" : hotelRoomOrder.getAccount().getMobile()) + "%");
                return predicate;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(),sort);
        Page<HotelRoomOrder> findAll = hotelRoomOrderDao.findAll(specification,pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 分页查询酒店预约订单列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<HotelRoomOrder> findHomeList(Long userId, PageBean<HotelRoomOrder> pageBean) throws ParseException {
        Specification<HotelRoomOrder> specification = new Specification<HotelRoomOrder>() {
            @Override
            public Predicate toPredicate(Root<HotelRoomOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<HotelRoomOrder> findAll = hotelRoomOrderDao.findAll(specification, pageable);
        List<HotelRoomOrder> content = findAll.getContent();
        Date currentDate = new Date();
        for(HotelRoomOrder hotelRoomOrder:content){
            int status = hotelRoomOrder.getStatus();
            if(HotelRoomOrder.ORDER_PAY_STATUS_PAYED==status){
                String checkInDate = hotelRoomOrder.getCheckInDate();
                Date inDate = DateUtil.date_sdf.parse(checkInDate);
                if(inDate.compareTo(currentDate)==-1){
                    hotelRoomOrderDao.updateOrderStatus(hotelRoomOrder.getId(),HotelRoomOrder.ORDER_STATUS_USERD);
                }
            }
            /*if(HotelRoomOrder.ORDER_STATUS_USERD==status){
                String checkOutDate = hotelRoomOrder.getCheckOutDate();
                Date outDate = DateUtil.date_sdf.parse(checkOutDate);
                if(outDate.compareTo(currentDate)==-1){
                    hotelRoomOrderDao.updateOrderStatus(hotelRoomOrder.getId(),HotelRoomOrder.ORDER_STATUS_END);
                }
            }*/
        }
        pageBean.setContent(content);
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照酒店预约订单id删除
     *
     * @param id
     */
    public void delete(Long id) {
        hotelRoomOrderItemDao.deleleByOrderId(id);
        hotelRoomOrderDao.deleteById(id);
    }

    /**
     * 返回酒店预约订单总数
     *
     * @return
     */
    public long total() {
        return hotelRoomOrderDao.count();
    }



    @Transactional
    public HotelRoomOrder createOrder(HotelRoomOrder hotelRoomOrder, List<HotelRoomOrderItem> hotelRoomOrderItems){
        HotelRoomOrder save = hotelRoomOrderDao.save(hotelRoomOrder);
        for(HotelRoomOrderItem hotelRoomOrderItem:hotelRoomOrderItems){
            hotelRoomOrderItem.setOrder(save);
            HotelType hotelType = hotelRoomOrderItem.getHotelType();
            if(hotelType.getDefaultNumber()-1<=0){
                hotelTypeDao.updateNumber(hotelType.getId());
                hotelTypeDao.updateStatus(hotelType.getId(),HotelType.HOTEL_STATUS_OFF);
            }else{
                hotelTypeDao.updateNumber(hotelType.getId());
            }
        }
        hotelRoomOrderItemDao.saveAll(hotelRoomOrderItems);
        return save;
    }


    @Transactional
    public void orderPay(Account account,Long oid){
            accountDao.updateBalance(account.getId(),account.getBalance());
            hotelRoomOrderDao.updateOrderStatus(oid,HotelRoomOrder.ORDER_PAY_STATUS_PAYED);
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    public int updateOrderStatus(Long id, int status){
        return hotelRoomOrderDao.updateOrderStatus(id,status);
    }


    /**
     * 取消订单操作
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId){
        HotelRoomOrder hotelRoomOrder = hotelRoomOrderDao.find(orderId);
        BigDecimal totalPrice = hotelRoomOrder.getTotalPrice();
        //更新订单状态
        hotelRoomOrderDao.updateOrderStatus(orderId,HotelRoomOrder.ORDER_STATUS_CANCEL);
        Account account = hotelRoomOrder.getAccount();
        BigDecimal defaultBalance = account.getBalance().add(totalPrice);
        //更新用户余额
        accountDao.updateBalance(account.getId(),defaultBalance);
        //更新客房数量
        List<HotelRoomOrderItem> hotelRoomOrderItems = hotelRoomOrderItemDao.findByOrderId(orderId);
        for (HotelRoomOrderItem hotelRoomOrderItem:hotelRoomOrderItems){
            HotelType hotelType = hotelRoomOrderItem.getHotelType();
            if(hotelType.getStatus()==HotelType.HOTEL_STATUS_OFF){
                hotelTypeDao.updateStatus(hotelType.getId(),HotelType.HOTEL_STATUS_ON);
            }
            int defaultNumber = hotelType.getDefaultNumber();
            hotelTypeDao.updateRoomNumber(hotelType.getId(),defaultNumber+1);
        }
    }


    /**
     * 根据用户id查询订单
     * @param accountId
     * @return
     */
    public int countByAccountId(Long accountId){
        return hotelRoomOrderDao.countByAccountId(accountId);
    }
}
