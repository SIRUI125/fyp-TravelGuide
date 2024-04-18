package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.home.ScenicFavorites;
import com.yuanlrc.base.entity.home.ScenicOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 景点收藏数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface ScenicFavoritesDao extends JpaRepository<ScenicFavorites, Long>, JpaSpecificationExecutor<ScenicFavorites> {


	/**
	 * 根据景点收藏id查询
	 * @param id
	 * @return
	 */
	@Query("select s from ScenicFavorites s where id = :id")
	public ScenicFavorites find(@Param("id") Long id);


	/**
	 * 根据用户id查询收藏
	 * @param accountId
	 * @return
	 */
	public ScenicFavorites findByAccountIdAndScenicId(@Param("accountId")Long accountId,@Param("scenicId")Long scenicId);
}
