package com.yuanlrc.base.service.home;

import com.yuanlrc.base.bean.PageBean;
import com.yuanlrc.base.dao.admin.UserDao;
import com.yuanlrc.base.dao.home.AccountDao;
import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.home.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台用户管理service
 * @author SiruiYao 2024/03/06
 *
 */
@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	/**
	 * 根据前台用户id查询
	 * @param id
	 * @return
	 */
	public Account find(Long id){
		return accountDao.find(id);
	}

	/**
	 * 按照前台用户名查找前台用户
	 * @param username
	 * @return
	 */
	public Account findByUsername(String username){
		return accountDao.findByUsername(username);
	}

	/**
	 * 前台用户添加/编辑操作
	 * @param account
	 * @return
	 */
	public Account save(Account account){
		return accountDao.save(account);
	}

	/**
	 * 分页查询前台用户列表
	 * @param account
	 * @param pageBean
	 * @return
	 */
	public PageBean<Account> findList(Account account,PageBean<Account> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("username", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status","sex","balance");
		Example<Account> example = Example.of(account, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Account> findAll = accountDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}

	/**
	 * 判断前台用户名是否存在，添加和编辑均可判断
	 * @param username
	 * @param id
	 * @return
	 */
	public boolean isExistUsername(String username,Long id){
		Account account = accountDao.findByUsername(username);
		if(account != null){
			//表示前台用户名存在，接下来判断是否是编辑前台用户的本身
			if(account.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}

	/**
	 * 按照前台用户id删除
	 * @param id
	 */
	public void delete(Long id){
		accountDao.deleteById(id);
	}

	/**
	 * 返回前台用户总数
	 * @return
	 */
	public long total(){
		return accountDao.count();
	}

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
	public Account findByMobile(String mobile){
		return  accountDao.findByMobile(mobile);
	}


	/**
	 * 根据邮箱查询用户
	 * @param email
	 * @return
	 */
	public Account findByEmail(String email){
		return accountDao.findByEmail(email);
	}

	/**
	 * 根据身份证号码查询用户
	 * @param cardNo
	 * @return
	 */
	public Account findByCardNo(String cardNo){
		return accountDao.findByCardNo(cardNo);
	}


	/**
	 * 更新用户密码
	 * @param id
	 * @param password
	 * @return
	 */

	public int updatePassword(Long id, String password){
		return accountDao.updatePassword(id,password);
	}
}
