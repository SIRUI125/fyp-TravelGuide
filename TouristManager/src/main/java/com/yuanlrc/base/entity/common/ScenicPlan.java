package com.yuanlrc.base.entity.common;
/**
 * 景点攻略表
 */

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name="ylrc_scenic_plan")
@EntityListeners(AuditingEntityListener.class)
public class ScenicPlan extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ValidateEntity(required = true,errorRequiredMsg = "名称不能为空")
	@Column(name = "name",length = 64)
	private String name;//名称

	@ValidateEntity(required = true,errorRequiredMsg = "请填写简单介绍")
	@Column(name = "intro",length = 128)
	private String intro;//简单介绍

	@ValidateEntity(required = true,errorRequiredMsg = "请填写内容")
	@Column(name = "content",length = 255)
	private String content;//内容

	@ManyToOne
	@JoinColumn(name = "scenic_id")
	private Scenic scenic;//所属景点

	@Column(name = "images",length = 255)
	private String images;//图片

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}
}
