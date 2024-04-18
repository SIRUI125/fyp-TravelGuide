package com.yuanlrc.base.service.home;

import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.CommentReplyDao;
import com.yuanlrc.base.dao.home.HotelCommentReplyDao;
import com.yuanlrc.base.entity.home.CommentReply;
import com.yuanlrc.base.entity.home.HotelCommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 酒店订单评论回复管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class HotelCommentReplyService {

    @Autowired
    private HotelCommentReplyDao hotelCommentReplyDao;

    @Autowired
    private AccountDao accountDao;

    /**
     * 根据酒店订单评论回复id查询
     *
     * @param id
     * @return
     */
    public HotelCommentReply find(Long id) {
        return hotelCommentReplyDao.find(id);
    }


    /**
     * 酒店订单评论回复添加/编辑操作
     * @param hotelCommentReply
     * @return
     */
    public HotelCommentReply save(HotelCommentReply hotelCommentReply) {
        return hotelCommentReplyDao.save(hotelCommentReply);
    }


    /**
     * 按照酒店订单评论回复id删除
     *
     * @param id
     */
    public void delete(Long id) {
        hotelCommentReplyDao.deleteById(id);
    }

    /**
     * 返回酒店订单评论回复总数
     *
     * @return
     */
    public long total() {
        return hotelCommentReplyDao.count();
    }



    /**
     * 删除回复信息 根据评论
     * @param commentId
     */
    public void deleteByCommentId(Long commentId){
        hotelCommentReplyDao.deleteByCommentId(commentId);
    }

}
