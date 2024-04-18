package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 客房类型信息实体
 */
@Entity
@Table(name = "ylrc_hotel_type")
@EntityListeners(AuditingEntityListener.class)
public class HotelType extends BaseEntity {

	public static final int HOTEL_STATUS_ON=0;
	public static final int HOTEL_STATUS_OFF=1;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;//酒店

	@ValidateEntity(required = true,errorRequiredMsg = "请填写名称")
	@Column(name = "name",length = 64)
	private String name;//名称

	@Column(name = "number",length = 5)
	private int number;//单人床数量

	@ValidateEntity(required = true,errorRequiredMsg = "请填写介绍")
	@Column(name = "introduce",length = 255)
	private String introduce;//介绍

	@ValidateEntity(required = true,errorRequiredMsg = "请填写详情")
	@Column(name = "details",length = 255)
	private String details;//详情

	@Column(name = "guest_number",length = 5)
	private int guestNumber;//客人数量

	@Column(name = "price",length = 5)
	private BigDecimal price;//价格

	@ValidateEntity(required = true,errorRequiredMsg = "请填写备注")
	@Column(name = "remark",length = 255)
	private String remark;//备注


	@Column(name = "area",length = 5)
	private Double area;//面积

	@ValidateEntity(required = true,errorRequiredMsg = "请填写景观")
	@Column(name = "landscape",length = 64)
	private String landscape;//景观

	@ValidateEntity(required = true,errorRequiredMsg = "请上传图片")
	@Column(name = "images",length = 255)
	private String images;//图片

	@Column(name = "status",length = 5)
	private int status;//状态


	@Column(name = "room_number",length = 5)
	private int roomNumber;//房间数量

	@Column(name = "default_number",length = 5)
	private int defaultNumber;//剩余数量

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getDefaultNumber() {
		return defaultNumber;
	}

	public void setDefaultNumber(int defaultNumber) {
		this.defaultNumber = defaultNumber;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getLandscape() {
		return landscape;
	}

	public void setLandscape(String landscape) {
		this.landscape = landscape;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
