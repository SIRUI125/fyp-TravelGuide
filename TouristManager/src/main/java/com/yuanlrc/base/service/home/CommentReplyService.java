package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.CommentReplyDao;
import com.yuanlrc.base.dao.home.OrderCommentDao;
import com.yuanlrc.base.dao.home.ScenicOrderDao;
import com.yuanlrc.base.entity.home.CommentReply;
import com.yuanlrc.base.entity.home.OrderComment;
import com.yuanlrc.base.entity.home.ScenicOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 订单评论回复管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class CommentReplyService {

    @Autowired
    private CommentReplyDao commentReplyDao;

    @Autowired
    private AccountDao accountDao;

    /**
     * 根据订单评论回复id查询
     *
     * @param id
     * @return
     */
    public CommentReply find(Long id) {
        return commentReplyDao.find(id);
    }


    /**
     * 订单评论回复添加/编辑操作
     * @param commentReply
     * @return
     */
    public CommentReply save(CommentReply commentReply) {
        return commentReplyDao.save(commentReply);
    }


    /**
     * 按照订单评论回复id删除
     *
     * @param id
     */
    public void delete(Long id) {
        commentReplyDao.deleteById(id);
    }

    /**
     * 返回订单评论回复总数
     *
     * @return
     */
    public long total() {
        return commentReplyDao.count();
    }

}
