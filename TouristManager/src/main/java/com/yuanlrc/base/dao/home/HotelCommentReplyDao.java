package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.CommentReply;
import com.yuanlrc.base.entity.home.HotelCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 酒店订单评论回复数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelCommentReplyDao extends JpaRepository<HotelCommentReply, Long>, JpaSpecificationExecutor<HotelCommentReply> {

	/**
	 * 根据酒店订单评论回复id查询
	 * @param id
	 * @return
	 */
	@Query("select h from HotelCommentReply h where id = :id")
	public HotelCommentReply find(@Param("id") Long id);


	/**
	 * 根据评论 查询 回复信息
	 * @param orderCommentId
	 * @return
	 */
	public List<HotelCommentReply> findByHotelOrderCommentId(@Param("orderCommentId") Long orderCommentId);


	/**
	 * 删除回复信息 根据评论
	 * @param commentId
	 */
	@Modifying
	@Transactional
	@Query("delete from  HotelCommentReply where hotelOrderComment.id=:commentId")
	public void deleteByCommentId(@Param("commentId")Long commentId);

}
