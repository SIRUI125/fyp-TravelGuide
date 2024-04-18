package com.yuanlrc.base.service.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.ScenicPlanDao;
import com.yuanlrc.base.dao.common.ScenicPriceDao;
import com.yuanlrc.base.entity.common.ScenicPlan;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.util.BigDecimalUtil;
import com.yuanlrc.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 景点门票价格管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class ScenicPriceService {

	@Autowired
	private ScenicPriceDao scenicPriceDao;

	/**
	 * 根据景点门票价格id查询
	 * @param id
	 * @return
	 */
	public ScenicPrice find(Long id){
		return scenicPriceDao.find(id);
	}

	/**
	 * 景点门票价格添加/编辑操作
	 * @param scenicPrice
	 * @return
	 */
	public ScenicPrice save(ScenicPrice scenicPrice){
		return scenicPriceDao.save(scenicPrice);
	}

	/**
	 * 分页查询景点门票价格列表
	 * @param scenicPrice
	 * @param pageBean
	 * @return
	 */
	public PageBean<ScenicPrice> findList(ScenicPrice scenicPrice,PageBean<ScenicPrice> pageBean){

		Specification<ScenicPrice> specification = new Specification<ScenicPrice>() {
			@Override
			public Predicate toPredicate(Root<ScenicPrice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.equal(root.get("scenic").get("id"), scenicPrice.getScenic().getId());
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<ScenicPrice> findAll = scenicPriceDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照景点门票价格id删除
	 * @param id
	 */
	public void delete(Long id){
		scenicPriceDao.deleteById(id);
	}

	/**
	 * 返回景点门票价格总数
	 * @return
	 */
	public long total(){
		return scenicPriceDao.count();
	}

	/**
	 * 根据景点查询最后的门票日期
	 * @param scenicId
	 * @return
	 */
	public ScenicPrice findFirstByScenicIdOrderByProductDateDesc(Long scenicId){
		return scenicPriceDao.findFirstByScenicIdOrderByProductDateDesc(scenicId);
	}


	/**
	 * 批量保存景点门票
	 * @param scenicPrices
	 * @return
	 */
	public List<ScenicPrice> saveAll(List<ScenicPrice> scenicPrices){

		return scenicPriceDao.saveAll(scenicPrices);
	}

	/**
	 * 根据景点id和日期查询门票信息
	 * @param scenicId
	 * @return
	 */
	public JSONArray findByScenicIdAndProductDateGreaterThanEqual(Long scenicId) throws ParseException {
		Date date = DateUtil.ClearDate(new Date());
		List<ScenicPrice> scenicPrices = scenicPriceDao.findByScenicIdAndProductDateGreaterThanEqual(scenicId, date);
		JSONArray jsonArray = new JSONArray();
		for(ScenicPrice scenicPrice:scenicPrices){
			JSONObject priceJson=new JSONObject();
			priceJson.put("date",DateUtil.toString(scenicPrice.getProductDate()));
			BigDecimal price = scenicPrice.getPrice();
			priceJson.put("data",price);
			priceJson.put("psid",scenicPrice.getLimitation());
			priceJson.put("inventory",scenicPrice.getInventory());
			priceJson.put("id",scenicPrice.getId());
			jsonArray.add(priceJson);
		}
		return jsonArray;
	}


	/**
	 * 更新门票库存
	 * @param inventory
	 * @param id
	 * @return
	 */

	public int updateInventoryById(int inventory,Long id){
		return scenicPriceDao.updateInventoryById(inventory,id);
	}
}
