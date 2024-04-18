package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 景点攻略数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface ScenicPlanDao extends JpaRepository<ScenicPlan, Long>, JpaSpecificationExecutor<ScenicPlan> {

	/**
	 * 按照景点攻略名查找景点攻略信息
	 * @param name
	 * @return
	 */
	public ScenicPlan findByName(String name);

	/**
	 * 根据景点攻略id查询
	 * @param id
	 * @return
	 */
	@Query("select s from ScenicPlan s where id = :id")
	public ScenicPlan find(@Param("id") Long id);


	/**
	 * 根据景点id查询景点攻略
	 * @param scenicId
	 * @return
	 */
	public List<ScenicPlan> findByScenicId(@Param("scenicId")Long scenicId);

}
