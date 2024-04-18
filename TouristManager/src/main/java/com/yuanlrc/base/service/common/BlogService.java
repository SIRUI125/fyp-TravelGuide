package com.yuanlrc.base.service.common;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.BlogDao;
import com.yuanlrc.base.entity.common.Blog;
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
import java.util.List;

/**
 * 博客管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	/**
	 * 根据博客id查询
	 * @param id
	 * @return
	 */
	public Blog find(Long id){
		return blogDao.find(id);
	}

	/**
	 * 博客添加/编辑操作
	 * @param blog
	 * @return
	 */
	public Blog save(Blog blog){
		return blogDao.save(blog);
	}

	/**
	 * 分页查询博客列表
	 * @param blog
	 * @param pageBean
	 * @return
	 */
	public PageBean<Blog> findList(Blog blog,PageBean<Blog> pageBean){

		Specification<Blog> specification = new Specification<Blog>() {
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + (blog.getTitle() == null ? "" : blog.getTitle()) + "%");
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Blog> findAll = blogDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 分页查询博客列表
	 * @param blog
	 * @param pageBean
	 * @return
	 */
	public PageBean<Blog> findHomeList(Blog blog,PageBean<Blog> pageBean){

		Specification<Blog> specification = new Specification<Blog>() {
			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("title"), "%" + (blog.getTitle() == null ? "" : blog.getTitle()) + "%");
				Predicate status = criteriaBuilder.equal(root.get("status"), Blog.BLOG_STATUS_ON);
				predicate = criteriaBuilder.and(predicate, status);
				return predicate;
			}
		};
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Blog> findAll = blogDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}


	/**
	 * 按照博客id删除
	 * @param id
	 */
	public void delete(Long id){
		blogDao.deleteById(id);
	}

	/**
	 * 返回博客总数
	 * @return
	 */
	public long total(){
		return blogDao.count();
	}


}
