package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.HotelOrderComment;
import com.yuanlrc.base.entity.home.OrderComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 酒店订单评论数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelOrderCommentDao extends JpaRepository<HotelOrderComment, Long>,
		JpaSpecificationExecutor<HotelOrderComment> {


	/**
	 * 根据订单评论id查询
	 * @param id
	 * @return
	 */
	@Query("select o from HotelOrderComment o where id = :id")
	public HotelOrderComment find(@Param("id") Long id);


	/**
	 * 根据酒店查询评论
	 * @param hotelId
	 * @return
	 */
	public List<HotelOrderComment> findByHotelId(@Param("hotelId") Long hotelId);

	/**
	 * 根据用户id查询评论
	 * @param accountId
	 * @return
	 */
	public int countByAccountId(@Param("accountId")Long accountId);
}
