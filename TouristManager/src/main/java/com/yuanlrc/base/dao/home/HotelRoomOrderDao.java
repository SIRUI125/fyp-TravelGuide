package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.ScenicOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 酒店预约订单数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelRoomOrderDao extends JpaRepository<HotelRoomOrder, Long>, JpaSpecificationExecutor<HotelRoomOrder> {


	/**
	 * 根据酒店预约订单id查询
	 * @param id
	 * @return
	 */
	@Query("select ho from HotelRoomOrder ho where id = :id")
	public HotelRoomOrder find(@Param("id") Long id);


	/**
	 * 更新订单状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("update HotelRoomOrder set status=:status where id=:id")
	int updateOrderStatus(@Param("id")Long id, @Param("status")int status);


	/**
	 * 根据用户id查询订单
	 * @param accountId
	 * @return
	 */
	public int countByAccountId(@Param("accountId")Long accountId);
}
