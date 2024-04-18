package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景点门票价格信息实体
 */
@Entity
@Table(name = "ylrc_scenic_price")
@EntityListeners(AuditingEntityListener.class)
public class ScenicPrice extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "scenic_id")
	private Scenic scenic;//所属景点

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "product_date")
	private Date productDate; //日期

	@ValidateEntity(required = true,errorRequiredMsg = "价格不能为空",requiredMinValue = true,minValue = 0,errorMinValueMsg = "价格最低为0")
	@Column(name = "price")
	private BigDecimal price;//该日期实际价格

	@Column(name = "inventory")
	private int inventory;//库存

	@ValidateEntity(required = true,requiredMinValue = true,minValue = 0,errorRequiredMsg = "放票数不能为空",errorMinValueMsg = "放票数最低为0")
	@Column(name = "ticket")
	private int ticket;//放票数

	@ValidateEntity(required = true,requiredMinValue = true,requiredMaxValue = true,minValue = 1,maxValue = 10,errorRequiredMsg = "单人限购数量不能为空",errorMinValueMsg = "单人限购数量最小为1",errorMaxValueMsg = "单人限购数量最大为10")
	@Column(name = "limitation")
	private int limitation;//单人购买限制


	public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getLimitation() {
		return limitation;
	}

	public void setLimitation(int limitation) {
		this.limitation = limitation;
	}


	@Override
	public String toString() {
		return "ScenicPrice{" +
				"scenic=" + scenic +
				", productDate=" + productDate +
				", price=" + price +
				", inventory=" + inventory +
				", ticket=" + ticket +
				", limitation=" + limitation +
				'}';
	}
}
