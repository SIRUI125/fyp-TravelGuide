package com.yuanlrc.base.entity.common;
/**
 * 旅游景点表
 */

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="ylrc_scenic")
@EntityListeners(AuditingEntityListener.class)
public class Scenic extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final int SCENIC_STATUS_ON=0;
	public static final int SCENIC_STATUS_OFF=1;


	@ValidateEntity(required = true,errorRequiredMsg = "请填写标题")
	@Column(name = "title",length = 64)
	private String title;//标题


	@ValidateEntity(required = true,errorRequiredMsg = "请上传封面图")
	@Column(name = "cover_pic",length = 255)
	private String coverPic;//封面图

	@Lob
	@Column(name = "images",length = 64)
	private String images;//图片



	@Column(name = "price",length = 5)
	private BigDecimal price;//价格

	@Lob
	@ValidateEntity(required = true,errorRequiredMsg = "请填写内容")
	@Column(name = "content",length = 255)
	private String content;//内容

	@ValidateEntity(required = true,errorRequiredMsg = "请填写详细地址")
	@Column(name = "address",length = 64)
	private String address;//详细地址


	@ValidateEntity(required=true,requiredLeng=true,minLength=2,maxLength=50,errorRequiredMsg="开放时间不能为空!",errorMinLengthMsg="开放时间长度需大于2!",errorMaxLengthMsg="开放时间长度不能大于50!")
	@Column(name="open_time",nullable=false,length=50)
	private String openTime;//开放时间

	@ValidateEntity(required=true,requiredLeng=true,minLength=2,maxLength=50,errorRequiredMsg="官方电话不能为空!",errorMinLengthMsg="官方电话长度需大于2!",errorMaxLengthMsg="官方电话长度不能大于50!")
	@Column(name="official_phone",nullable=false,length=50)
	private String officialPhone;//官方电话

	@Column(name = "status")
	private int status=SCENIC_STATUS_ON;//状态

	@Column(name = "view_number",length = 5)
	private int viewNumber=0;//浏览量

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




	public int getOrderByPrice() {
		return orderByPrice;
	}

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

	public void setOrderByPrice(int orderByPrice) {
		this.orderByPrice = orderByPrice;
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

	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getOfficialPhone() {
		return officialPhone;
	}

	public void setOfficialPhone(String officialPhone) {
		this.officialPhone = officialPhone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
