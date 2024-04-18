package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.HotelTypeDao;
import com.yuanlrc.base.entity.common.HotelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 客房类型管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class HotelTypeService {

	@Autowired
	private HotelTypeDao hotelTypeDao;

	/**
	 * 根据客房类型id查询
	 * @param id
	 * @return
	 */
	public HotelType find(Long id){
		return hotelTypeDao.find(id);
	}

	/**
	 * 客房类型添加/编辑操作
	 * @param hotelType
	 * @return
	 */
	public HotelType save(HotelType hotelType){
		return hotelTypeDao.save(hotelType);
	}

	/**
	 * 分页查询客房类型列表
	 * @param hotelType
	 * @param pageBean
	 * @return
	 */
	public PageBean<HotelType> findList(HotelType hotelType,PageBean<HotelType> pageBean){

		Specification<HotelType> specification = new Specification<HotelType>() {
			@Override
			public Predicate toPredicate(Root<HotelType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (hotelType.getName() == null ? "" : hotelType.getName()) + "%");
				Predicate equal = criteriaBuilder.equal(root.get("hotel").get("id"), hotelType.getHotel().getId());
				predicate = criteriaBuilder.and(predicate, equal);
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<HotelType> findAll = hotelTypeDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照客房类型id删除
	 * @param id
	 */
	public void delete(Long id){
		hotelTypeDao.deleteById(id);
	}

	/**
	 * 返回客房类型总数
	 * @return
	 */
	public long total(){
		return hotelTypeDao.count();
	}

	/**
	 * 根据酒店id查询客房
	 * @param hotelId
	 * @return
	 */
	public List<HotelType> findByHotelIdAndStatus(Long hotelId,int status){
		return hotelTypeDao.findByHotelIdAndStatus(hotelId,status);
	}
}
