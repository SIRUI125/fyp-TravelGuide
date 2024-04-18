package com.yuanlrc.base.entity.home;

import com.yuanlrc.base.entity.admin.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 酒店评价回复实体类
 * @author SiruiYao 2024/03/06
 */
@Entity
@Table(name = "ylrc_hotel_comment_reply")
@EntityListeners(AuditingEntityListener.class)
public class HotelCommentReply extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;//用户

    @ManyToOne
    @JoinColumn(name = "order_comment")
    private HotelOrderComment hotelOrderComment; //所属评价

    @Column(name = "content",length = 255)
    private String content;//内容

    @ManyToOne
    @JoinColumn(name = "reply_account_id")
    private Account replyAccount;//回复用户


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public HotelOrderComment getHotelOrderComment() {
        return hotelOrderComment;
    }

    public void setHotelOrderComment(HotelOrderComment hotelOrderComment) {
        this.hotelOrderComment = hotelOrderComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getReplyAccount() {
        return replyAccount;
    }

    public void setReplyAccount(Account replyAccount) {
        this.replyAccount = replyAccount;
    }
}
