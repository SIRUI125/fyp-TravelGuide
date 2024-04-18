package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景点收藏信息实体
 */
@Entity
@Table(name = "ylrc_scenic_favorites")
@EntityListeners(AuditingEntityListener.class)
public class ScenicFavorites extends BaseEntity {


	@ManyToOne
	@JoinColumn(name = "scenic_id")
	private Scenic scenic;//景点


	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//前台用户


	public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
