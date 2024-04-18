package com.yuanlrc.base.entity.common;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 博客信息实体
 */
@Entity
@Table(name = "ylrc_blog")
@EntityListeners(AuditingEntityListener.class)
public class   Blog extends BaseEntity {

	public static final int BLOG_STATUS_ON=0;
	public static final int BLOG_STATUS_OFF=1;

	@ValidateEntity(required = true,errorRequiredMsg = "请填写标题")
	@Column(name = "title",length = 64)
	private String title;//标题

	@ValidateEntity(required = true,errorRequiredMsg = "请上传封面图")
	@Column(name = "cover_pic",length = 64)
	private String coverPic;//封面图


	@ValidateEntity(required = true,errorRequiredMsg = "请填写类型")
	@Column(name = "type",length = 16)
	private String type;//类型


	@Column(name = "tags",length = 64)
	private String tags;//标签


	@ValidateEntity(required = true,errorRequiredMsg = "请填写内容")
	@Lob
	@Column(name = "content",length = 255)
	private String content;//内容

	@Column(name = "status",length = 5)
	private int status;//状态

	@ValidateEntity(required = true,errorRequiredMsg = "请填写作者")
	@Column(name = "author",length = 64)
	private String author;//作者

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
