package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.OrderComment;
import com.yuanlrc.base.entity.home.ScenicFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单评论数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface OrderCommentDao extends JpaRepository<OrderComment, Long>, JpaSpecificationExecutor<OrderComment> {


	/**
	 * 根据订单评论id查询
	 * @param id
	 * @return
	 */
	@Query("select o from OrderComment o where id = :id")
	public OrderComment find(@Param("id") Long id);


	/**
	 * 根据景点查询评论
	 * @param scenicId
	 * @return
	 */
	public List<OrderComment> findByScenicId(@Param("scenicId")Long scenicId);

	/**
	 * 根据用户id查询评论数
	 * @param accountId
	 * @return
	 */
	public int countByAccountId(@Param("accountId")Long accountId);
}
