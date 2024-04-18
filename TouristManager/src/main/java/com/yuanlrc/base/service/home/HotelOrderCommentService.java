package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.HotelDao;
import com.yuanlrc.base.dao.home.*;
import com.yuanlrc.base.entity.home.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 酒店订单评论管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class HotelOrderCommentService {

    @Autowired
    private HotelOrderCommentDao hotelOrderCommentDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private HotelRoomOrderDao hotelRoomOrderDao;

    @Autowired
    private HotelCommentReplyDao hotelCommentReplyDao;

    @Autowired
    private HotelDao hotelDao;


    /**
     * 根据订单评论id查询
     *
     * @param id
     * @return
     */
    public HotelOrderComment find(Long id) {
        return hotelOrderCommentDao.find(id);
    }


    /**
     * 订单评论添加/编辑操作
     * @param orderComment
     * @return
     */
    public HotelOrderComment save(HotelOrderComment orderComment) {
        return hotelOrderCommentDao.save(orderComment);
    }

    /**
     * 分页查询订单评论列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<HotelOrderComment> findHomeList(Long userId, PageBean<HotelOrderComment> pageBean){
        Specification<HotelOrderComment> specification = new Specification<HotelOrderComment>() {
            @Override
            public Predicate toPredicate(Root<HotelOrderComment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<HotelOrderComment> findAll = hotelOrderCommentDao.findAll(specification, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照订单评论id删除
     *
     * @param id
     */
    public void delete(Long id) {
        hotelCommentReplyDao.deleteByCommentId(id);
        hotelOrderCommentDao.deleteById(id);
    }

    /**
     * 返回订单评论总数
     *
     * @return
     */
    public long total() {
        return hotelOrderCommentDao.count();
    }


  /**
     * 根据酒店查询评论
     * @param hotelId
     * @return
     */
    public List<HotelOrderComment> findByHotelId(Long hotelId){
        List<HotelOrderComment> orderComments = hotelOrderCommentDao.findByHotelId(hotelId);
        for(HotelOrderComment orderComment:orderComments){
            Long id = orderComment.getId();
            List<HotelCommentReply> commentReplies = hotelCommentReplyDao.findByHotelOrderCommentId(id);
            orderComment.setCommentReplies(commentReplies);
        }
        return orderComments;
    }

    /**
     * 评分成功
     * @param hotelOrderComment
     */
    @Transactional
    public void commentSuccess(HotelOrderComment hotelOrderComment){
        hotelOrderCommentDao.save(hotelOrderComment);
        hotelRoomOrderDao.updateOrderStatus(hotelOrderComment.getHotelRoomOrder().getId(),
                HotelRoomOrder.ORDER_STATUS_COMMENT);
        hotelDao.updateRateNumberAndRateScore(hotelOrderComment.getHotel().getId(),hotelOrderComment.getScore());
    }


    /**
     * 根据用户id查询评论
     * @param accountId
     * @return
     */
    public int countByAccountId(Long accountId){
        return hotelOrderCommentDao.countByAccountId(accountId);
    }

}
