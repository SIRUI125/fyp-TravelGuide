package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 景点数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface ScenicDao extends JpaRepository<Scenic, Long>, JpaSpecificationExecutor<Scenic> {

	/**
	 * 按照景点名查找景点信息
	 * @param title
	 * @return
	 */
	public Scenic findByTitle(String title);

	/**
	 * 根据景点id查询
	 * @param id
	 * @return
	 */
	@Query("select s from Scenic s where id = :id")
	public Scenic find(@Param("id") Long id);


	/**
	 * 根据id更新浏览量
	 * @param id
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("update Scenic set viewNumber=viewNumber+1 where id=:id")
	public int updateViewNumber(@Param("id")Long id);


	/**
	 * 根据id更新评论人数和评论分数
	 * @param id
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("update Scenic set rateNumber=rateNumber+1,rateScore=rateScore+:rateScore  where id=:id")
	public int updateRateNumberAndRateScore(@Param("id")Long id,@Param("rateScore")int rateScore);


	/**
	 * 查询出最新的12条景点
	 * @return
	 */
	public List<Scenic> findTop12ByOrderByCreateTime();

}
