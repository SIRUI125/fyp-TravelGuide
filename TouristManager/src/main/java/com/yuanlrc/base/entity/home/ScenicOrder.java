package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Scenic;
import com.yuanlrc.base.entity.common.ScenicPrice;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景点预约订单信息实体
 */
@Entity
@Table(name = "ylrc_scenic_order")
@EntityListeners(AuditingEntityListener.class)
public class ScenicOrder extends BaseEntity {


	public static final int ORDER_STATUS_PAST_DUE=0;//未过期
	public static final int ORDER_STATUS_NOT_PAST_DUE=1;//过期
	public static final int ORDER_STATUS_USERD=2;//使用中
	public static final int ORDER_STATUS_CANCEL=3;//已取消
	public static final int ORDER_STATUS_COMMENT=4;//已评论


	public static final int ORDER_PAY_STATUS_NO_PAY=0;//未支付
	public static final int ORDER_PAY_STATUS_PAYED=1;//已支付

	@JoinColumn(name = "account_id")
	@ManyToOne
	private Account account;  //用户

	@JoinColumn(name = "scenic_price_id")
	@ManyToOne
	private ScenicPrice scenicPrice; //景点安排

	@Column(name="count")
	private Integer count; //购买数量

	@Column(name = "price")
	private BigDecimal price; //支付金额

	@Column(name = "start_date")
	private Date startDate; //预定日期

	@Column(name = "details")
	private String details; //购票详情

	@Column(name = "status")
	private Integer status = ORDER_STATUS_PAST_DUE;

	@Column(name = "pay_status")
	private Integer payStatus = ORDER_PAY_STATUS_NO_PAY;//支付状态 默认未支付

	@Transient
	private Long pid;//景点安排id

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ScenicPrice getScenicPrice() {
		return scenicPrice;
	}

	public void setScenicPrice(ScenicPrice scenicPrice) {
		this.scenicPrice = scenicPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
}
