package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.ScenicDao;
import com.yuanlrc.base.dao.common.ScenicPriceDao;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.HotelRoomOrderDao;
import com.yuanlrc.base.dao.home.ScenicOrderDao;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.entity.home.ScenicOrder;
import com.yuanlrc.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
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
 * 景点预约订单管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class ScenicOrderService {

    @Autowired
    private ScenicOrderDao scenicOrderDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ScenicPriceDao scenicPriceDao;



    /**
     * 根据景点预约订单id查询
     *
     * @param id
     * @return
     */
    public ScenicOrder find(Long id) {
        return scenicOrderDao.find(id);
    }


    /**
     * 景点预约订单添加/编辑操作
     *
     * @param scenicOrder
     * @return
     */
    public ScenicOrder save(ScenicOrder scenicOrder) {
        return scenicOrderDao.save(scenicOrder);
    }

    /**
     * 分页查询景点预约订单列表
     *
     * @param scenicOrder
     * @param pageBean
     * @return
     */
    public PageBean<ScenicOrder> findAdminList(ScenicOrder scenicOrder, PageBean<ScenicOrder> pageBean) {

        Specification<ScenicOrder> specification = new Specification<ScenicOrder>() {
            @Override
            public Predicate toPredicate(Root<ScenicOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.like(root.get("account").get("mobile"), "%" + (scenicOrder.getAccount() == null ? "" : scenicOrder.getAccount().getMobile()) + "%");
                return predicate;
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(),sort);
        Page<ScenicOrder> findAll = scenicOrderDao.findAll(specification,pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 分页查询景点预约订单列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<ScenicOrder> findHomeList(Long userId, PageBean<ScenicOrder> pageBean) throws ParseException {
        Specification<ScenicOrder> specification = new Specification<ScenicOrder>() {
            @Override
            public Predicate toPredicate(Root<ScenicOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<ScenicOrder> findAll = scenicOrderDao.findAll(specification, pageable);
        List<ScenicOrder> content = findAll.getContent();
        List<ScenicOrder> scenicOrders = checkStatus(content);
        pageBean.setContent(scenicOrders);
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照景点预约订单id删除
     *
     * @param id
     */
    public void delete(Long id) {
        scenicOrderDao.deleteById(id);
    }

    /**
     * 返回景点预约订单总数
     *
     * @return
     */
    public long total() {
        return scenicOrderDao.count();
    }


    @Transactional
    public void paySuccess(ScenicOrder scenicOrder, Account account, ScenicPrice scenicPrice) {
        scenicOrderDao.save(scenicOrder);
        accountDao.updateBalance(account.getId(), account.getBalance());
        scenicPriceDao.updateInventoryById(scenicPrice.getInventory(), scenicPrice.getId());
    }

    /**
     * 查询当天景点 用户购买数量
     *
     * @param accountId
     * @param scenicPriceId
     * @return
     */
    public int countByAccountIdAndScenicPriceId(Long accountId, Long scenicPriceId) {
        return scenicOrderDao.countByAccountIdAndScenicPriceId(accountId, scenicPriceId);
    }


    /**
     * 检查状态
     *
     * @param scenicOrders
     * @return
     * @throws ParseException
     */
    public List<ScenicOrder> checkStatus(List<ScenicOrder> scenicOrders) throws ParseException {
        Date date = new Date();
        String format = DateUtil.date_sdf.format(date);
        Date currentDate = DateUtil.date_sdf.parse(format);
        for (ScenicOrder scenicOrder : scenicOrders) {
            Date startDate = scenicOrder.getStartDate();
            int i = startDate.compareTo(currentDate);
            Integer status = scenicOrder.getStatus();
            if (i == 0) {
                if (status == ScenicOrder.ORDER_STATUS_PAST_DUE) {
                    //表示使用中
                    scenicOrderDao.updateOrderStatus(scenicOrder.getId(), ScenicOrder.ORDER_STATUS_USERD);
                    scenicOrder.setStatus(ScenicOrder.ORDER_STATUS_USERD);
                }
            }
            if (i == -1) {
                if (status == ScenicOrder.ORDER_STATUS_USERD) {
                    //表示过期
                    scenicOrderDao.updateOrderStatus(scenicOrder.getId(), ScenicOrder.ORDER_STATUS_NOT_PAST_DUE);
                    scenicOrder.setStatus(ScenicOrder.ORDER_STATUS_NOT_PAST_DUE);
                }
            }

        }
        return scenicOrders;
    }


    /**
     * 取消订单操作
     * @param scenicOrder
     */
    @Transactional
    public void cancelOrder(ScenicOrder scenicOrder){
        scenicOrderDao.updateOrderStatus(scenicOrder.getId(),ScenicOrder.ORDER_STATUS_CANCEL);
        Integer count = scenicOrder.getCount();
        ScenicPrice scenicPrice = scenicOrder.getScenicPrice();
        int inventory = scenicPrice.getInventory();
        int defaultInventory = inventory + count;
        scenicPriceDao.updateInventoryById(defaultInventory,scenicPrice.getId());
        Account account = scenicOrder.getAccount();
        BigDecimal price = scenicOrder.getPrice();
        BigDecimal balance = account.getBalance();
        BigDecimal add = balance.add(price);
        accountDao.updateBalance(account.getId(),add);
    }


    /**
     * 根据用户id查询订单数
     * @param accountId
     * @return
     */
    public int countByAccountId(Long accountId){
        return scenicOrderDao.countByAccountId(accountId);
    }
}
