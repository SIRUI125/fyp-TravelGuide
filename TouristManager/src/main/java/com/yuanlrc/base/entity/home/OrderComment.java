package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * 订单评论信息实体
 */
@Entity
@Table(name = "ylrc_order_comment")
@EntityListeners(AuditingEntityListener.class)
public class OrderComment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "scenic_id")
	private Scenic scenic;//景点

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//前台用户

	@ValidateEntity(required = true,errorRequiredMsg = "请填写评论内容")
	@Column(name = "content",length = 255)
	private String content;//内容

	@ManyToOne
	@JoinColumn(name = "scenic_order_id")
	private ScenicOrder scenicOrder;//评论订单

	@Column(name = "score",length = 5)
	private int score;//评分

	@Transient
	private List<CommentReply> commentReplies;

	public List<CommentReply> getCommentReplies() {
		return commentReplies;
	}

	public void setCommentReplies(List<CommentReply> commentReplies) {
		this.commentReplies = commentReplies;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ScenicOrder getScenicOrder() {
		return scenicOrder;
	}

	public void setScenicOrder(ScenicOrder scenicOrder) {
		this.scenicOrder = scenicOrder;
	}

	public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
