package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.HotelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客房类型数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelTypeDao extends JpaRepository<HotelType, Long>, JpaSpecificationExecutor<HotelType> {


	/**
	 * 根据客房类型id查询
	 * @param id
	 * @return
	 */
	@Query("select h from HotelType h where id = :id")
	public HotelType find(@Param("id") Long id);


	/**
	 * 根据酒店id查询客房
	 * @param hotelId
	 * @return
	 */
	public List<HotelType> findByHotelIdAndStatus(@Param("hotelId")Long hotelId,@Param("status")int status);


	/**
	 * 根据id更新客房状态
	 * @param id
	 * @param status
	 */
	@Modifying
	@Transactional
	@Query("update HotelType set status=:status where id=:id")
	public void updateStatus(@Param("id")Long id,@Param("status")int status);


	/**
	 * 根据id更新客房剩余数量
	 * @param id
	 */
	@Modifying
	@Transactional
	@Query("update HotelType set defaultNumber=defaultNumber-1 where id=:id")
	public void updateNumber(@Param("id")Long id);


	/**
	 * 根据id更新客房剩余数量
	 * @param id
	 */
	@Modifying
	@Transactional
	@Query("update HotelType set defaultNumber=:defaultNumber where id=:id")
	public void updateRoomNumber(@Param("id")Long id,@Param("defaultNumber")int defaultNumber);
}
