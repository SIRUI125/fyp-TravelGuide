package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.ScenicDao;
import com.yuanlrc.base.dao.common.ScenicPlanDao;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 景点攻略管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class ScenicPlanService {

	@Autowired
	private ScenicPlanDao scenicPlanDao;

	/**
	 * 根据景点攻略id查询
	 * @param id
	 * @return
	 */
	public ScenicPlan find(Long id){
		return scenicPlanDao.find(id);
	}

	/**
	 * 按照景点攻略名查找景点攻略
	 * @param name
	 * @return
	 */
	public ScenicPlan findByName(String name){
		return scenicPlanDao.findByName(name);
	}

	/**
	 * 景点攻略添加/编辑操作
	 * @param scenicPlan
	 * @return
	 */
	public ScenicPlan save(ScenicPlan scenicPlan){
		return scenicPlanDao.save(scenicPlan);
	}

	/**
	 * 分页查询景点攻略列表
	 * @param scenicPlan
	 * @param pageBean
	 * @return
	 */
	public PageBean<ScenicPlan> findList(ScenicPlan scenicPlan,PageBean<ScenicPlan> pageBean){

		Specification<ScenicPlan> specification = new Specification<ScenicPlan>() {
			@Override
			public Predicate toPredicate(Root<ScenicPlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.equal(root.get("scenic").get("id"), scenicPlan.getScenic().getId());
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<ScenicPlan> findAll = scenicPlanDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照景点攻略id删除
	 * @param id
	 */
	public void delete(Long id){
		scenicPlanDao.deleteById(id);
	}

	/**
	 * 返回景点攻略总数
	 * @return
	 */
	public long total(){
		return scenicPlanDao.count();
	}

	/**
	 * 根据景点id查询景点攻略
	 * @param scenicId
	 * @return
	 */
	public List<ScenicPlan> findByScenicId(Long scenicId){
		return scenicPlanDao.findByScenicId(scenicId);
	}
}
