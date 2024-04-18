package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.Blog;
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
 * 博客数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

	/**
	 * 根据博客id查询
	 * @param id
	 * @return
	 */
	@Query("select b from Blog b where id = :id")
	public Blog find(@Param("id") Long id);


}
