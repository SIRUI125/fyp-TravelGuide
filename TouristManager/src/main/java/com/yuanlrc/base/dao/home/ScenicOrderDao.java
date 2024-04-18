package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.entity.home.ScenicOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 景点预约订单数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface ScenicOrderDao extends JpaRepository<ScenicOrder, Long>, JpaSpecificationExecutor<ScenicOrder> {


	/**
	 * 根据景点预约订单id查询
	 * @param id
	 * @return
	 */
	@Query("select s from ScenicOrder s where id = :id")
	public ScenicOrder find(@Param("id") Long id);


	/**
	 * 查询当天景点 用户购买数量
	 * @param accountId
	 * @param scenicPriceId
	 * @return
	 */
	public int  countByAccountIdAndScenicPriceId(@Param("accountId")Long accountId,@Param("scenicPriceId")Long scenicPriceId);




	/**
	 * 更新景点订单状态
	 * @param id
	 * @param status
	 */
	@Modifying
	@Transactional
	@Query("update ScenicOrder set status=:status where id=:id")
	void updateOrderStatus(@Param("id")Long id,@Param("status")int status);


	/**
	 * 根据用户id查询订单数
	 * @param accountId
	 * @return
	 */
	public int countByAccountId(@Param("accountId")Long accountId);
}
