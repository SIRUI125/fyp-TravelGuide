package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.common.ScenicPriceDao;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.dao.home.ScenicFavoritesDao;
import com.yuanlrc.base.dao.home.ScenicOrderDao;
import com.yuanlrc.base.entity.common.ScenicPrice;
import com.yuanlrc.base.entity.home.Account;
import com.yuanlrc.base.entity.home.ScenicFavorites;
import com.yuanlrc.base.entity.home.ScenicOrder;
import com.yuanlrc.base.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
 * 景点收藏管理service
 *
 * @author SiruiYao 2024/03/06
 */
@Service
public class ScenicFavoritesService {

    @Autowired
    private ScenicFavoritesDao scenicFavoritesDao;

    @Autowired
    private AccountDao accountDao;

    /**
     * 根据景点收藏id查询
     *
     * @param id
     * @return
     */
    public ScenicFavorites find(Long id) {
        return scenicFavoritesDao.find(id);
    }


    /**
     * 景点收藏添加/编辑操作
     *
     * @param scenicFavorites
     * @return
     */
    public ScenicFavorites save(ScenicFavorites scenicFavorites) {
        return scenicFavoritesDao.save(scenicFavorites);
    }

    /**
     * 分页查询景点收藏列表
     *
     * @param userId
     * @param pageBean
     * @return
     */
    public PageBean<ScenicFavorites> findHomeList(Long userId, PageBean<ScenicFavorites> pageBean){
        Specification<ScenicFavorites> specification = new Specification<ScenicFavorites>() {
            @Override
            public Predicate toPredicate(Root<ScenicFavorites> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.equal(root.get("account").get("id"), userId);
                return predicate;
            }
        };
        Sort createTime = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize(), createTime);
        Page<ScenicFavorites> findAll = scenicFavoritesDao.findAll(specification, pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }


    /**
     * 按照景点收藏id删除
     *
     * @param id
     */
    public void delete(Long id) {
        scenicFavoritesDao.deleteById(id);
    }

    /**
     * 返回景点收藏总数
     *
     * @return
     */
    public long total() {
        return scenicFavoritesDao.count();
    }
    /**
     * 根据用户id查询收藏
     * @param accountId
     * @return
     */
    public ScenicFavorites findByAccountIdAndScenicId(Long accountId,Long scenicId){
        return scenicFavoritesDao.findByAccountIdAndScenicId(accountId,scenicId);
    }
}
