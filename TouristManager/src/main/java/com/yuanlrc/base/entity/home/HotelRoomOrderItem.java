package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.HotelType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单子项实体
 * @author SiruiYao 2024/03/06
 *
 */
@Entity
@Table(name="ylrc_hotel_room_order_item")
@EntityListeners(AuditingEntityListener.class)
public class HotelRoomOrderItem extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="order_id")
	private HotelRoomOrder order;//所属订单

	@ManyToOne
	@JoinColumn(name="hotel_type_id")
	private HotelType hotelType;//所属客房

	@Column(name="money",nullable=false,length=12)
	private BigDecimal money;//金额


	@Column(name = "personal_number",length = 15)
	private int personalNumber;//入住人数

	@Column(name = "day",length = 5)
	private int day;//天数


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}


	public int getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}

	public HotelRoomOrder getOrder() {
		return order;
	}

	public void setOrder(HotelRoomOrder order) {
		this.order = order;
	}

	public HotelType getHotelType() {
		return hotelType;
	}

	public void setHotelType(HotelType hotelType) {
		this.hotelType = hotelType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}
