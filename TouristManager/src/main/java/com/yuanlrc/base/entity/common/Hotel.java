package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 酒店信息实体
 */
@Entity
@Table(name = "ylrc_hotel")
@EntityListeners(AuditingEntityListener.class)
public class Hotel extends BaseEntity {


	public static final int HOTEL_STATUS_ON=0;

	public static final int HOTEL_STATUS_OFF=1;

	@ValidateEntity(required = true,errorRequiredMsg = "请填写名称")
	@Column(name = "name",length = 64)
	private String name;//名称

	@ValidateEntity(required = true,errorRequiredMsg = "请填写地址")
	@Column(name = "address",length = 128)
	private String address;//地址



	@ValidateEntity(required = true,errorRequiredMsg = "请上传封面图")
	@Column(name = "cover_pic",length = 64)
	private String coverPic;//封面图

	@ValidateEntity(required = true,errorRequiredMsg = "请上传图片")
	@Column(name = "images",length = 255)
	private String images;//图片

	@ValidateEntity(required = true,errorRequiredMsg = "请填写服务")
	@Column(name = "service",length = 255)
	private String service;//服务

	@Lob
	@ValidateEntity(required = true,errorRequiredMsg = "请填写内容")
	@Column(name = "content",length = 255)
	private String content;//内容

	@Column(name = "status",length = 5)
	private int status;//状态

	@Column(name = "avg_price",length = 5)
	private BigDecimal avgPrice;//平均价格

	@Column(name = "phone",length = 64)
	private String phone;//电话


	@Column(name = "remark",length = 255)
	private String remark;//备注

	@Column(name = "rate_number",length = 5)
	private int rateNumber=0;//评论数量

	@Column(name = "rate_score",length = 5)
	private int rateScore=0;//评论分数


	@Transient
	private int minPrice;//最小值

	@Transient
	private int maxPrice;//最大值

	@Transient
	private int orderByPrice;//排序

	public int getRateNumber() {
		return rateNumber;
	}

	public void setRateNumber(int rateNumber) {
		this.rateNumber = rateNumber;
	}

	public int getRateScore() {
		return rateScore;
	}

	public void setRateScore(int rateScore) {
		this.rateScore = rateScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getOrderByPrice() {
		return orderByPrice;
	}

	public void setOrderByPrice(int orderByPrice) {
		this.orderByPrice = orderByPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
