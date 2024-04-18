package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.HotelRoomOrderDao;
import com.yuanlrc.base.dao.home.HotelRoomOrderItemDao;
import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.HotelRoomOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.List;

/**
 * 酒店预约订单项管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class HotelRoomOrderItemService {

    @Autowired
    private HotelRoomOrderDao hotelRoomOrderDao;

    @Autowired
    private HotelRoomOrderItemDao hotelRoomOrderItemDao;


    @Autowired
    private AccountDao accountDao;


    /**
     * 根据酒店预约订单项id查询
     *
     * @param id
     * @return
     */
    public HotelRoomOrderItem find(Long id) {
        return hotelRoomOrderItemDao.find(id);
    }


    /**
     * 酒店预约订单项添加/编辑操作
     *
     * @param hotelRoomOrderItem
     * @return
     */
    public HotelRoomOrderItem save(HotelRoomOrderItem hotelRoomOrderItem) {
        return hotelRoomOrderItemDao.save(hotelRoomOrderItem);
    }

    /**
     * 分页查询酒店预约订单项列表
     *
     * @param hotelRoomOrderItem
     * @param pageBean
     * @return
     */
    public PageBean<HotelRoomOrderItem> findAdminList(HotelRoomOrderItem hotelRoomOrderItem, PageBean<HotelRoomOrderItem> pageBean) {
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize());
        Page<HotelRoomOrderItem> findAll = hotelRoomOrderItemDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * 分页查询酒店预约订单项列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<HotelRoomOrderItem> findHomeList(Long userId, PageBean<HotelRoomOrderItem> pageBean) throws ParseException {
        Specification<HotelRoomOrderItem> specification = new Specification<HotelRoomOrderItem>() {
            @Override
            public Predicate toPredicate(Root<HotelRoomOrderItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<HotelRoomOrderItem> findAll = hotelRoomOrderItemDao.findAll(specification, pageable);
        List<HotelRoomOrderItem> content = findAll.getContent();
        pageBean.setContent(content);
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照酒店预约订单项id删除
     *
     * @param id
     */
    public void delete(Long id) {
        hotelRoomOrderItemDao.deleteById(id);
    }

    /**
     * 返回酒店预约订单项总数
     *
     * @return
     */
    public long total() {
        return hotelRoomOrderItemDao.count();
    }


    /**
     * 根据订单id查询订单项
     * @param oid
     * @return
     */
    public List<HotelRoomOrderItem> findByOrderId(Long oid){
        return hotelRoomOrderItemDao.findByOrderId(oid);
    }
}
