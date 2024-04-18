package com.yuanlrc.base.dao.home;

import com.yuanlrc.base.entity.admin.User;
import com.yuanlrc.base.entity.home.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 前台用户数据库处理层
 * @author SiruiYao 2024/03/06
 *
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Long>{

	/**
	 * 按照前台用户名查找前台用户信息
	 * @param username
	 * @return
	 */
	public Account findByUsername(String username);

	/**
	 * 根据前台用户id查询
	 * @param id
	 * @return
	 */
	@Query("select a from Account a where id = :id")
	public Account find(@Param("id") Long id);


	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
	public Account findByMobile(@Param("mobile")String mobile);


	/**
	 * 根据邮箱查询用户
	 * @param email
	 * @return
	 */
	public Account findByEmail(@Param("email")String email);


	/**
	 * 更新用户余额
	 * @param id
	 * @param balance
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("update Account set balance=:balance where id=:id")
	int updateBalance(@Param("id")Long id, @Param("balance")BigDecimal balance);


	/**
	 * 根据身份证号码查询用户
	 * @param cardNo
	 * @return
	 */
	public Account findByCardNo(@Param("cardNo")String cardNo);


	/**
	 * 更新用户密码
	 * @param id
	 * @param password
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("update Account set password=:password where id=:id")
	int updatePassword(@Param("id")Long id, @Param("password")String password);




}
