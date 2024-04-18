package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.HotelFavoritesDao;
import com.yuanlrc.base.dao.home.ScenicFavoritesDao;
import com.yuanlrc.base.entity.home.HotelFavorites;
import com.yuanlrc.base.entity.home.ScenicFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 酒店收藏管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class HotelFavoritesService {

    @Autowired
    private HotelFavoritesDao hotelFavoritesDao;

    @Autowired
    private AccountDao accountDao;

    /**
     * 根据酒店收藏id查询
     *
     * @param id
     * @return
     */
    public HotelFavorites find(Long id) {
        return hotelFavoritesDao.find(id);
    }


    /**
     * 酒店收藏添加/编辑操作
     *
     * @param hotelFavorites
     * @return
     */
    public HotelFavorites save(HotelFavorites hotelFavorites) {
        return hotelFavoritesDao.save(hotelFavorites);
    }

    /**
     * 分页查询酒店收藏列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<HotelFavorites> findHomeList(Long userId, PageBean<HotelFavorites> pageBean){
        Specification<HotelFavorites> specification = new Specification<HotelFavorites>() {
            @Override
            public Predicate toPredicate(Root<HotelFavorites> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<HotelFavorites> findAll = hotelFavoritesDao.findAll(specification, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照酒店收藏id删除
     *
     * @param id
     */
    public void delete(Long id) {
        hotelFavoritesDao.deleteById(id);
    }

    /**
     * 返回酒店收藏总数
     *
     * @return
     */
    public long total() {
        return hotelFavoritesDao.count();
    }
    /**
     * 根据用户id查询收藏
     * @param accountId
     * @return
     */
    public HotelFavorites findByAccountIdAndHotelId(Long accountId,Long hotelId){
        return hotelFavoritesDao.findByAccountIdAndHotelId(accountId,hotelId);
    }
}
