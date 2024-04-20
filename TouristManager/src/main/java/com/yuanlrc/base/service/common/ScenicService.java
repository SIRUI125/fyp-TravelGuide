package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.admin.UserDao;
import com.yuanlrc.base.dao.common.ScenicDao;
import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.common.Scenic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 景点管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class ScenicService {

	@Autowired
	private ScenicDao scenicDao;

	/**
	 * 根据景点id查询
	 * @param id
	 * @return
	 */
	public Scenic find(Long id){
		return scenicDao.find(id);
	}

	/**
	 * 按照景点名查找景点
	 * @param title
	 * @return
	 */
	public Scenic findByTitle(String title){
		return scenicDao.findByTitle(title);
	}

	/**
	 * 景点添加/编辑操作
	 * @param scenic
	 * @return
	 */
	public Scenic save(Scenic scenic){
		return scenicDao.save(scenic);
	}

	/**
	 * 分页查询景点列表
	 * @param scenic
	 * @param pageBean
	 * @return
	 */
	public PageBean<Scenic> findList(Scenic scenic,PageBean<Scenic> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("title", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status","price","viewNumber","rateNumber","rateScore");
		Example<Scenic> example = Example.of(scenic, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Scenic> findAll = scenicDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 分页查询景点列表
	 * @param scenic
	 * @param pageBean
	 * @return
	 */
	public PageBean<Scenic> findHomeList(Scenic scenic,PageBean<Scenic> pageBean){

		Specification<Scenic> specification = new Specification<Scenic>() {
			@Override
			public Predicate toPredicate(Root<Scenic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + (scenic.getTitle() == null ? "" : scenic.getTitle()) + "%");
				Predicate status = criteriaBuilder.equal(root.get("status"), Scenic.SCENIC_STATUS_ON);
				predicate = criteriaBuilder.and(predicate, status);
				if(scenic.getMaxPrice()!=0 || scenic.getMinPrice()!=0){
					Predicate price = criteriaBuilder.between(root.get("price"), scenic.getMinPrice(), scenic.getMaxPrice());
					predicate = criteriaBuilder.and(predicate, price);
				}
				return predicate;
			}
		};
		int orderByPrice = scenic.getOrderByPrice();
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		if(orderByPrice==1){
			sort = Sort.by(Sort.Direction.DESC, "price");
		}
		if(orderByPrice==2){
			sort = Sort.by(Sort.Direction.ASC, "price");
		}
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(),sort);
		Page<Scenic> findAll = scenicDao.findAll(specification, pageable);
		List<Scenic> content = findAll.getContent();
		for(Scenic scenics:content){
			int rateScore = scenics.getRateScore();
			int rateNumber = scenics.getRateNumber();
			if(rateScore!=0){
				scenics.setRateScore(rateScore/rateNumber);
			}
		}
		pageBean.setContent(content);
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}

	/**
	 * 判断景点名是否存在，添加和编辑均可判断
	 * @param title
	 * @param id
	 * @return
	 */
	public boolean isExistTitle(String title,Long id){
		Scenic scenic = scenicDao.findByTitle(title);
		if(scenic != null){
			//表示景点名存在，接下来判断是否是编辑景点的本身
			if(scenic.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 按照景点id删除
	 * @param id
	 */
	public void delete(Long id){
		scenicDao.deleteById(id);
	}

	/**
	 * 返回景点总数
	 * @return
	 */
	public long total(){
		return scenicDao.count();
	}

	/**
	 * 根据id更新浏览量
	 * @param id
	 * @return
	 */
	public int updateViewNumber(Long id){
		return scenicDao.updateViewNumber(id);
	}


	/**
	 * 根据id更新评论人数和评论分数
	 * @param id
	 * @return
	 */
	public int updateRateNumberAndRateScore(Long id,int rateScore){
		return scenicDao.updateRateNumberAndRateScore(id,rateScore);
	}


	/**
	 * 查询出最新的12条景点
	 * @return
	 */
	public List<Scenic> findTop12ByOrderByCreateTime(){
		List<Scenic> scenics = scenicDao.findTop12ByOrderByCreateTime();
		for(Scenic scenic:scenics){
			int rateNumber = scenic.getRateNumber();
			if(rateNumber!=0){
				scenic.setRateScore(scenic.getRateScore()/rateNumber);
			}
		}
		return scenics;
	}

}
