package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.annotion.ValidateEntity;
import com.yuanlrc.base.entity.admin.BaseEntity;
import com.yuanlrc.base.entity.common.Hotel;
import com.yuanlrc.base.entity.common.Scenic;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * 酒店订单评论信息实体
 */
@Entity
@Table(name = "ylrc_hotel_order_comment")
@EntityListeners(AuditingEntityListener.class)
public class HotelOrderComment extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;//酒店

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;//前台用户

	@ValidateEntity(required = true,errorRequiredMsg = "请填写评论内容")
	@Column(name = "content",length = 255)
	private String content;//内容

	@ManyToOne
	@JoinColumn(name = "hotel_room_order_id")
	private HotelRoomOrder hotelRoomOrder;//评论订单

	@Column(name = "score",length = 5)
	private int score;//评分

	@Transient
	private List<HotelCommentReply> commentReplies;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HotelRoomOrder getHotelRoomOrder() {
		return hotelRoomOrder;
	}

	public void setHotelRoomOrder(HotelRoomOrder hotelRoomOrder) {
		this.hotelRoomOrder = hotelRoomOrder;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<HotelCommentReply> getCommentReplies() {
		return commentReplies;
	}

	public void setCommentReplies(List<HotelCommentReply> commentReplies) {
		this.commentReplies = commentReplies;
	}
}
