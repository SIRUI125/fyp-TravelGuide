package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 照片库数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface GalleryDao extends JpaRepository<Gallery, Long>, JpaSpecificationExecutor<Gallery> {

	/**
	 * 根据照片库id查询
	 * @param id
	 * @return
	 */
	@Query("select g from Gallery g where id = :id")
	public Gallery find(@Param("id") Long id);


	/**
	 * 根据状态查询照片库
	 * @param status
	 * @return
	 */
	public List<Gallery> findByStatus(@Param("status")int status);

}
