package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.ScenicPrice;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 酒店预约订单信息实体
 */
@Entity
@Table(name = "ylrc_hotel_room_order")
@EntityListeners(AuditingEntityListener.class)
public class HotelRoomOrder extends BaseEntity {


	public static final int ORDER_STATUS_USERD=2;//已使用
	public static final int ORDER_STATUS_CANCEL=3;//已取消
	public static final int ORDER_STATUS_COMMENT=4;//已评论
	public static final int ORDER_STATUS_END=5;//已结束

	public static final int ORDER_PAY_STATUS_NO_PAY=0;//未支付
	public static final int ORDER_PAY_STATUS_PAYED=1;//已支付

	@Column(name = "sn",length = 255,nullable = false)
	private String sn;//订单编号

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//前台用户

	@Column(name = "total_price",length = 5)
	private BigDecimal totalPrice;//总价

	@Column(name = "num",length = 5)
	private int num;//数量

	@Column(name = "status",length = 5)
	private int status=ORDER_PAY_STATUS_NO_PAY;//状态

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;//所属酒店


	@ValidateEntity(required = true,errorRequiredMsg = "请填写入住日期")
	@Column(name = "check_in_date",length = 16)
	private String checkInDate;//入住日期

	@ValidateEntity(required = true,errorRequiredMsg = "请填写退房日期")
	@Column(name = "check_out_date",length = 16)
	private String checkOutDate;//退房日期


	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
