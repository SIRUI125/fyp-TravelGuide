package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.BlogDao;
import com.yuanlrc.base.dao.common.GalleryDao;
import com.yuanlrc.base.entity.common.Blog;
import com.yuanlrc.base.entity.common.Gallery;
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
 * 照片库管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	/**
	 * 根据照片库id查询
	 * @param id
	 * @return
	 */
	public Gallery find(Long id){
		return galleryDao.find(id);
	}

	/**
	 * 照片库添加/编辑操作
	 * @param gallery
	 * @return
	 */
	public Gallery save(Gallery gallery){
		return galleryDao.save(gallery);
	}

	/**
	 * 分页查询照片库列表
	 * @param gallery
	 * @param pageBean
	 * @return
	 */
	public PageBean<Gallery> findList(Gallery gallery,PageBean<Gallery> pageBean){

		Specification<Gallery> specification = new Specification<Gallery>() {
			@Override
			public Predicate toPredicate(Root<Gallery> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + (gallery.getTitle() == null ? "" : gallery.getTitle()) + "%");
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Gallery> findAll = galleryDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 分页查询照片库列表
	 * @param userId
	 * @param pageBean
	 * @return
	 */
	public PageBean<Gallery> findHomeList(Long userId,PageBean<Gallery> pageBean){

		Specification<Gallery> specification = new Specification<Gallery>() {
			@Override
			public Predicate toPredicate(Root<Gallery> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"),userId);
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Gallery> findAll = galleryDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照照片库id删除
	 * @param id
	 */
	public void delete(Long id){
		galleryDao.deleteById(id);
	}

	/**
	 * 返回照片库总数
	 * @return
	 */
	public long total(){
		return galleryDao.count();
	}

	/**
	 * 根据状态查询照片库
	 * @param status
	 * @return
	 */
	public List<Gallery> findByStatus(int status){
		return galleryDao.findByStatus(status);
	}
}
