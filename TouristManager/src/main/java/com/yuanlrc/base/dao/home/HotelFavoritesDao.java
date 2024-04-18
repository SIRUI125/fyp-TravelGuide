package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.HotelFavorites;
import com.yuanlrc.base.entity.home.ScenicFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 酒店收藏数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface HotelFavoritesDao extends JpaRepository<HotelFavorites, Long>,
		JpaSpecificationExecutor<HotelFavorites> {


	/**
	 * 根据酒店收藏id查询
	 * @param id
	 * @return
	 */
	@Query("select h from HotelFavorites h where id = :id")
	public HotelFavorites find(@Param("id") Long id);


	/**
	 * 根据用户id查询收藏
	 * @param accountId
	 * @return
	 */
	public HotelFavorites findByAccountIdAndHotelId(@Param("accountId") Long accountId,
													  @Param("hotelId") Long hotelId);
}
