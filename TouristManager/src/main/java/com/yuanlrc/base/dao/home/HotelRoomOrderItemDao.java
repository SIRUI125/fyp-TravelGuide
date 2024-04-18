package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.HotelRoomOrder;
import com.yuanlrc.base.entity.home.HotelRoomOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 酒店预约订单项数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelRoomOrderItemDao extends JpaRepository<HotelRoomOrderItem, Long>, JpaSpecificationExecutor<HotelRoomOrderItem> {


	/**
	 * 根据酒店预约订单项id查询
	 * @param id
	 * @return
	 */
	@Query("select ho from HotelRoomOrderItem ho where id = :id")
	public HotelRoomOrderItem find(@Param("id") Long id);


	/**
	 * 根据订单id查询订单项
	 * @param oid
	 * @return
	 */
	public List<HotelRoomOrderItem> findByOrderId(@Param("oid")Long oid);


	/**
	 * 根据订单id删除订单项
	 * @param orderId
	 */
	@Transactional
	@Modifying
	@Query("delete from HotelRoomOrderItem where order.id=:orderId")
	public void deleleByOrderId(@Param("orderId")Long orderId);
}
