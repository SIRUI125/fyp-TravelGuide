package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.admin.Role;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 前台用户实体类
 * @author SiruiYao 2024/03/06
 *
 */
@Entity
@Table(name="ylrc_account")
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final int ACCOUNT_SEX_MAN = 1;//性别男

	private static final int ACCOUNT_SEX_WOMAN = 2;//性别女

	private static final int ACCOUNT_SEX_UNKONW = 0;//性别未知

	public static final int HOME_ACCOUNT_STATUS_ENABLE = 1;//用户状态正常可用
	public static final int HOME_ACCOUNT_STATUS_UNABLE = 0;//用户状态不可用


	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=18,errorRequiredMsg="用户名不能为空!",errorMinLengthMsg="用户名长度需大于4!",errorMaxLengthMsg="用户名长度不能大于18!")
	@Column(name="username",nullable=false,length=18,unique=true)
	private String username;//用户名

	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=32,errorRequiredMsg="密码不能为空！",errorMinLengthMsg="密码长度需大于4!",errorMaxLengthMsg="密码长度不能大于32!")
	@Column(name="password",nullable=false,length=32)
	private String password;//登录密码

	@Column(name="status",length=1)
	private int status = HOME_ACCOUNT_STATUS_ENABLE;//用户状态,默认可用

	@Column(name="head_pic",length=128)
	private String headPic;//用户头像

	@ValidateEntity(required=false)
	@Column(name="sex",length=1)
	private int sex = ACCOUNT_SEX_UNKONW;//用户性别

	@Column(name = "age",length = 16)
	private int age;//年龄

	@ValidateEntity(required=true,errorRequiredMsg = "请填写手机号")
	@Column(name="mobile",length=12)
	private String mobile;//用户手机号

	@ValidateEntity(required=true,errorRequiredMsg = "请填写用户邮箱")
	@Column(name="email",length=32)
	private String email;//用户邮箱


	@Column(name = "card_no",length = 64)
	private String cardNo;//身份证号

	@Column(name = "balance",length = 5)
	private BigDecimal balance;//用户余额

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", status=" + status +
				", headPic='" + headPic + '\'' +
				", sex=" + sex +
				", age=" + age +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", balance=" + balance +
				'}';
	}
}
