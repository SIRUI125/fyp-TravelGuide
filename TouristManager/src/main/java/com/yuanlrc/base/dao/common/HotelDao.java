package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ManyToOne;
import java.util.List;

/**
 * 酒店数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelDao extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {


	/**
	 * 根据酒店id查询
	 * @param id
	 * @return
	 */
	@Query("select h from Hotel h where id = :id")
	public Hotel find(@Param("id") Long id);

	/**
	 * 根据id更新评论人数和评论分数
	 * @param id
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("update Hotel set rateNumber=rateNumber+1,rateScore=rateScore+:rateScore  where id=:id")
	public int updateRateNumberAndRateScore(@Param("id")Long id,@Param("rateScore")int rateScore);


	/**
	 * 根据状态查询前12条信息
	 * @param status
	 * @return
	 */
	public List<Hotel> findTop12ByStatusOrderByCreateTimeDesc(@Param("status")int status);
}
