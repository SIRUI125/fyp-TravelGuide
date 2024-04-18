package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.ScenicDao;
import com.yuanlrc.base.dao.home.*;
import com.yuanlrc.base.entity.home.CommentReply;
import com.yuanlrc.base.entity.home.OrderComment;
import com.yuanlrc.base.entity.home.ScenicFavorites;
import com.yuanlrc.base.entity.home.ScenicOrder;
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
 * 订单评论管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class OrderCommentService {

    @Autowired
    private OrderCommentDao orderCommentDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ScenicOrderDao scenicOrderDao;

    @Autowired
    private CommentReplyDao commentReplyDao;
    @Autowired
    private ScenicDao scenicDao;

    /**
     * 根据订单评论id查询
     *
     * @param id
     * @return
     */
    public OrderComment find(Long id) {
        return orderCommentDao.find(id);
    }


    /**
     * 订单评论添加/编辑操作
     * @param orderComment
     * @return
     */
    public OrderComment save(OrderComment orderComment) {
        return orderCommentDao.save(orderComment);
    }

    /**
     * 分页查询订单评论列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<OrderComment> findHomeList(Long userId, PageBean<OrderComment> pageBean){
        Specification<OrderComment> specification = new Specification<OrderComment>() {
            @Override
            public Predicate toPredicate(Root<OrderComment> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<OrderComment> findAll = orderCommentDao.findAll(specification, pageable);
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
        commentReplyDao.deleteByCommentId(id);
        orderCommentDao.deleteById(id);
    }

    /**
     * 返回订单评论总数
     *
     * @return
     */
    public long total() {
        return orderCommentDao.count();
    }


    /**
     * 根据景点查询评论
     * @param scenicId
     * @return
     */
    public List<OrderComment> findByScenicId(Long scenicId){
        List<OrderComment> orderComments = orderCommentDao.findByScenicId(scenicId);
        for(OrderComment orderComment:orderComments){
            Long id = orderComment.getId();
            List<CommentReply> commentReplies = commentReplyDao.findByOrderCommentId(id);
            orderComment.setCommentReplies(commentReplies);
        }
        return orderComments;
    }


    /**
     * 评分成功
     * @param orderComment
     */
    @Transactional
    public void commentSuccess(OrderComment orderComment){
        orderCommentDao.save(orderComment);
        scenicOrderDao.updateOrderStatus(orderComment.getScenicOrder().getId(), ScenicOrder.ORDER_STATUS_COMMENT);
        scenicDao.updateRateNumberAndRateScore(orderComment.getScenic().getId(),orderComment.getScore());
    }

    /**
     * 根据用户id查询评论数
     * @param accountId
     * @return
     */
    public int countByAccountId(Long accountId){
        return orderCommentDao.countByAccountId(accountId);
    }

}
