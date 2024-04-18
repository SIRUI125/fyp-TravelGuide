package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.GalleryDao;
import com.yuanlrc.base.dao.common.RechargeRecordDao;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.entity.common.Gallery;
import com.yuanlrc.base.entity.common.RechargeRecord;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

/**
 * 充值金额记录管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class RechargeRecordService {

	@Autowired
	private RechargeRecordDao rechargeRecordDao;

	@Autowired
	private AccountDao accountDao;
	/**
	 * 根据充值金额记录id查询
	 * @param id
	 * @return
	 */
	public RechargeRecord find(Long id){
		return rechargeRecordDao.find(id);
	}

	/**
	 * 充值金额记录添加/编辑操作
	 * @param rechargeRecord
	 * @return
	 */
	public void save(RechargeRecord rechargeRecord){
		Account  account= accountDao.find(rechargeRecord.getAccount().getId());
		rechargeRecord.setAccount(account);
		rechargeRecord.setOldBalance(account.getBalance());
		BigDecimal add = account.getBalance().add(rechargeRecord.getPrice());
		rechargeRecord.setBalance(add);
		rechargeRecordDao.save(rechargeRecord);
		accountDao.updateBalance(account.getId(),add);
	}

	/**
	 * 分页查询充值金额记录列表
	 * @param userId
	 * @param pageBean
	 * @return
	 */
	public PageBean<RechargeRecord> findList(Long userId,PageBean<RechargeRecord> pageBean){

		Specification<RechargeRecord> specification = new Specification<RechargeRecord>() {
			@Override
			public Predicate toPredicate(Root<RechargeRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<RechargeRecord> findAll = rechargeRecordDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 分页查询充值金额记录列表
	 * @param rechargeRecord
	 * @param pageBean
	 * @return
	 */
	public PageBean<RechargeRecord> findAdminList(RechargeRecord rechargeRecord,PageBean<RechargeRecord> pageBean){

		Specification<RechargeRecord> specification = new Specification<RechargeRecord>() {
			@Override
			public Predicate toPredicate(Root<RechargeRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("account").get("username"),"%"+(rechargeRecord.getAccount()==null?"":rechargeRecord.getAccount().getUsername())+"%");
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<RechargeRecord> findAll = rechargeRecordDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}

	/**
	 * 按照充值金额记录id删除
	 * @param id
	 */
	public void delete(Long id){
		rechargeRecordDao.deleteById(id);
	}

	/**
	 * 返回充值金额记录总数
	 * @return
	 */
	public long total(){
		return rechargeRecordDao.count();
	}

}
