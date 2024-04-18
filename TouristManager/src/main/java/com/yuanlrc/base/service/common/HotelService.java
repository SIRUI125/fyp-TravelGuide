package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.HotelDao;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.Scenic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 酒店管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class HotelService {

	@Autowired
	private HotelDao hotelDao;

	/**
	 * 根据酒店id查询
	 * @param id
	 * @return
	 */
	public Hotel find(Long id){
		return hotelDao.find(id);
	}

	/**
	 * 酒店添加/编辑操作
	 * @param hotel
	 * @return
	 */
	public Hotel save(Hotel hotel){
		return hotelDao.save(hotel);
	}

	/**
	 * 分页查询酒店列表
	 * @param hotel
	 * @param pageBean
	 * @return
	 */
	public PageBean<Hotel> findList(Hotel hotel,PageBean<Hotel> pageBean){
		Specification<Hotel> specification = new Specification<Hotel>() {
			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (hotel.getName() == null ? "" : hotel.getName()) + "%");
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Hotel> findAll = hotelDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 分页查询酒店列表
	 * @param hotel
	 * @param pageBean
	 * @return
	 */
	public PageBean<Hotel> findHomeList(Hotel hotel,PageBean<Hotel> pageBean){
		Specification<Hotel> specification = new Specification<Hotel>() {
			@Override
			public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (hotel.getName() == null ? "" : hotel.getName()) + "%");
				Predicate status = criteriaBuilder.equal(root.get("status"),Hotel.HOTEL_STATUS_ON);
				predicate = criteriaBuilder.and(predicate, status);
				if(hotel.getMaxPrice()!=0 || hotel.getMinPrice()!=0){
					Predicate price = criteriaBuilder.between(root.get("avgPrice"), hotel.getMinPrice(), hotel.getMaxPrice());
					predicate = criteriaBuilder.and(predicate, price);
				}

				return predicate;
			}
		};
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(),sort);
		Page<Hotel> findAll = hotelDao.findAll(specification, pageable);
		List<Hotel> content = findAll.getContent();
		for(Hotel hote:content){
			int rateScore = hote.getRateScore();
			int rateNumber = hote.getRateNumber();
			if(rateScore!=0){
				hote.setRateScore(rateScore/rateNumber);
			}
		}
		pageBean.setContent(content);
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照酒店id删除
	 * @param id
	 */
	public void delete(Long id){
		hotelDao.deleteById(id);
	}

	/**
	 * 返回酒店总数
	 * @return
	 */
	public long total(){
		return hotelDao.count();
	}

	/**
	 * 根据状态查询前12条信息
	 * @param status
	 * @return
	 */
	public List<Hotel> findTop12ByStatusOrderByCreateTimeDesc(int status){
		List<Hotel> hotels = hotelDao.findTop12ByStatusOrderByCreateTimeDesc(status);
		for(Hotel hote:hotels){
			int rateScore = hote.getRateScore();
			int rateNumber = hote.getRateNumber();
			if(rateScore!=0){
				hote.setRateScore(rateScore/rateNumber);
			}
		}
		return hotels;
	}
}
