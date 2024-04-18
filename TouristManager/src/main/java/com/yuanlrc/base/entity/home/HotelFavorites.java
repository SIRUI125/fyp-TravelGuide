package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 酒店收藏信息实体
 */
@Entity
@Table(name = "ylrc_hotel_favorites")
@EntityListeners(AuditingEntityListener.class)
public class HotelFavorites extends BaseEntity {


	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;//酒店


	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//前台用户


	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
