package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.ScenicPlan;
import com.yuanlrc.base.entity.common.ScenicPrice;
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
 * 景点门票价格数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface ScenicPriceDao extends JpaRepository<ScenicPrice, Long>, JpaSpecificationExecutor<ScenicPrice> {


	/**
	 * 根据景点门票价格id查询
	 * @param id
	 * @return
	 */
	@Query("select s from ScenicPrice s where id = :id")
	public ScenicPrice find(@Param("id") Long id);


	/**
	 * 根据景点查询最后的门票日期
	 * @param scenicId
	 * @return
	 */
	public ScenicPrice findFirstByScenicIdOrderByProductDateDesc(@Param("scenicId") Long scenicId);


	/**
	 * 根据景点id和日期查询门票信息
	 * @param scenicId
	 * @param productDate
	 * @return
	 */
	public List<ScenicPrice> findByScenicIdAndProductDateGreaterThanEqual(@Param("scenicId")Long scenicId,
																		  @Param("productDate") Date productDate);


	/**
	 * 更新门票库存
	 * @param inventory
	 * @param id
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("update ScenicPrice  set inventory=:inventory where id=:id")
	int updateInventoryById(@Param("inventory")int inventory,@Param("id")Long id);
}
