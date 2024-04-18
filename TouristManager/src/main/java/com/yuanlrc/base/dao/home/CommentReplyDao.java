package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.CommentReply;
import com.yuanlrc.base.entity.home.OrderComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单评论回复数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface CommentReplyDao extends JpaRepository<CommentReply, Long>, JpaSpecificationExecutor<CommentReply> {

	/**
	 * 根据订单评论回复id查询
	 * @param id
	 * @return
	 */
	@Query("select c from CommentReply c where id = :id")
	public CommentReply find(@Param("id") Long id);


	/**
	 * 根据评论 查询 回复信息
	 * @param orderId
	 * @return
	 */
	public List<CommentReply> findByOrderCommentId(@Param("orderId")Long orderId);


	/**
	 * 根据评论id删除回复信息
	 * @param commentId
	 */
	@Modifying
	@Transactional
	@Query("delete from CommentReply  where orderComment.id=:commentId")
	public void deleteByCommentId(@Param("commentId")Long commentId);

}
