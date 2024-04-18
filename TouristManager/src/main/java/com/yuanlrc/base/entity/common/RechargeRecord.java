package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.home.Account;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 充值金额记录信息实体
 */
@Entity
@Table(name = "ylrc_recharge_record")
@EntityListeners(AuditingEntityListener.class)
public class RechargeRecord extends BaseEntity {


	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//用户


	@Column(name = "price",length = 5)
	private BigDecimal price;//金额

	@Column(name = "old_balance",length = 5)
	private BigDecimal oldBalance;//原金额

	@Column(name = "balance",length = 5)
	private BigDecimal balance;//最新金额

	public BigDecimal getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(BigDecimal oldBalance) {
		this.oldBalance = oldBalance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
