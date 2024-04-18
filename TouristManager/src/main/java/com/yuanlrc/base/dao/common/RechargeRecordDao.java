package com.yuanlrc.base.dao.common;

import com.yuanlrc.base.entity.common.Gallery;
import com.yuanlrc.base.entity.common.RechargeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 充值金额记录数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface RechargeRecordDao extends JpaRepository<RechargeRecord, Long>, JpaSpecificationExecutor<RechargeRecord> {

	/**
	 * 根据充值金额记录id查询
	 * @param id
	 * @return
	 */
	@Query("select r from RechargeRecord r where id = :id")
	public RechargeRecord find(@Param("id") Long id);

}
