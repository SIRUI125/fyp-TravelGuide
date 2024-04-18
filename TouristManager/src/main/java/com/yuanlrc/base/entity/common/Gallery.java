package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.home.Account;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 照片库信息实体
 */
@Entity
@Table(name = "ylrc_gallery")
@EntityListeners(AuditingEntityListener.class)
public class Gallery extends BaseEntity {

	public static final int PHOTO_STATUS_ON=0;
	public static final int PHOTO_STATUS_OFF=1;


	@ValidateEntity(required = true,errorRequiredMsg = "请上传图片")
	@Column(name = "images",length =255)
	private String images;//照片

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//用户


	@Column(name = "title",length = 16)
	private String title;//标题


	@Column(name = "status",length = 5)
	private int status;//状态


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
