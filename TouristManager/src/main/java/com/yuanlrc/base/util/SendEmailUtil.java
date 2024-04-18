package com.yuanlrc.base.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author SiruiYao
 * @date 2024/03/06
 * @info 邮箱发送
 */
public class SendEmailUtil {



    //授权码
    private static String code="yjvweaoddirvbgha";

    private static String sourceEmail="115432031@qq.com"; //发件人\
    private static int qq_type=0;
    private static int wy_type=0;
    private String smtp = "smtp.qq.com"; //smtp服务器地址

    private Session session; //session

    //初始化
    public void init() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", smtp); // smtp服务器地址
        this.session = Session.getInstance(props);
        session.setDebug(true);
    }

    //发送邮件
    public void sendMsg(String title, String content,String email) throws Exception {
        //初始化
        init();
        Message msg = new MimeMessage(session);
        msg.setSubject(title);
        msg.setText(content);
        msg.setFrom(new InternetAddress(sourceEmail));//发件人邮箱(我的163邮箱)
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email)); //收件人邮箱(我的QQ邮箱)
        msg.saveChanges();
        Transport transport = session.getTransport();
        transport.connect(sourceEmail, code);//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }

    public void sendMsg(String email,String content) throws Exception {
        sendMsg("【Travel Platform】Registration Verification Code", content,email);
    }

}
