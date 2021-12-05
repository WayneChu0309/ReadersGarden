package com.home.model;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import connectionpool.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MailServiceDAO implements MailService_Interface{
	private static final String MEMBER_REGISTER = "【Reader's Garden 註冊通知】";
	private static final String MEMBER_REGISTER_TEXT = "點擊這裡提交驗證";
	private static final String MEMBER_FORGETPASSWORD = "【Reader's Garden 重設密碼通知】";
	private static final String MEMBER_FORGETPASSWORD_TEXT = "點擊這裡修改密碼";
	private static final String VENDOR_REGISTER = "【Reader's Garden 廠商註冊通知】";
	private static final String VENDOR_REGISTER_TEXT = "點擊這裡啟用帳戶";
	private static final String VENDOR_FORGETPASSWORD = "【Reader's Garden 重設密碼通知】";
	private static final String VENDOR_FORGETPASSWORD_TEXT = "點擊這裡修改密碼";

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public boolean sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "ReadersGarden.tfa@gmail.com";
			final String myGmail_password = "rgtfa103";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setContent(messageText, "text/html; charset=UTF-8");

			Transport.send(message);
			System.out.println("傳送成功!");
			return true;
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public String sendVerifyCode(String username, String email) {
		String verifyCode = getAuthCode();
		StringBuilder sb = new StringBuilder();
		MimeBodyPart textPart = new MimeBodyPart();
		sb.append("<a href=\"http://10.2.5.85:8081/TFA103_01/ReadersGarden?action?email=");
		sb.append(email);
		sb.append("&VerifyCode=");
		sb.append(verifyCode);
		sb.append("\">");
		sb.append(" <FONT  face=\"MS UI Gothic\"  size=\"3\"><b>");
		sb.append(MEMBER_REGISTER_TEXT);
		sb.append("</b></FONT>");
		sb.append("</a>");
		boolean ss = sendMail(email, MEMBER_REGISTER, sb.toString());
		return null;
	}

	@Override
	public String forgetPassword(Integer number) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getAuthCode() {
		int[] arr = new int[62];
		String code = "";
		
		for (int i = 0; i < 10; i++) {
			arr[i] = i;
		}
		
		for (int i = (int)'a'; i <= (int)'z'; i++) {
			arr[i-'a'+10] = i;
		}
		
		for (int i = (int)'A'; i <= (int)'Z'; i++) {
			arr[i-'A'+36] = i;
		}
		
		for (int i = 1; i <= 8; i++) {
			int j = (int)(Math.random() * arr.length);
			if ( j <= 9 ) {
				code += arr[j];
			} else {
				code += (char)arr[j];
			}
		}
		
		return code;
	}
	
	
	public static void main(String[] args) {
		MailServiceDAO dao = new MailServiceDAO();
//		dao.sendVerifyCode("123", "spoonyduck@gmail.com");
		dao.sendVerifyCode("123", "amy840115@gmail.com");
	}


	@Override
	public Boolean vendorForgetPassword(Integer vendorid, String email, String token) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"http://34.81.128.226/TFA103_01/vendor/VendorForgetPassword?action=resetAvaliable&vendorid=");
		sb.append(vendorid);
		sb.append("&token=");
		sb.append(token);
		sb.append("\">");
		sb.append(" <FONT  face=\"MS UI Gothic\"  size=\"3\"><b>");
		sb.append(VENDOR_FORGETPASSWORD_TEXT);
		sb.append("</b></FONT>");
		sb.append("</a>");
		boolean ss = sendMail(email, VENDOR_FORGETPASSWORD, sb.toString());
		return ss;
	}


	@Override
	public Boolean vendorRegisterEmail(Integer vendorid, String email, String token) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"http://34.81.128.226/TFA103_01/vendor/VendorServlet?action=tokenCheck&vendorid=");
		sb.append(vendorid);
		sb.append("&token=");
		sb.append(token);
		sb.append("\">");
		sb.append(" <FONT  face=\"MS UI Gothic\"  size=\"3\"><b>");
		sb.append(VENDOR_REGISTER_TEXT);
		sb.append("</b></FONT>");
		sb.append("</a>");
		boolean ss = sendMail(email, VENDOR_REGISTER, sb.toString());
		return ss;
	}
	
	@Override
	public Boolean memberForgetPassword(Integer number, String email, String token) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<a href=\"http://34.81.128.226/TFA103_01/login/memberForgetPassword?action=resetAvaliable&number=");
		sb.append(number);
		sb.append("&token=");
		sb.append(token);
		sb.append("\">");
		sb.append(" <FONT  face=\"MS UI Gothic\"  size=\"3\"><b>");
		sb.append(MEMBER_FORGETPASSWORD_TEXT);
		sb.append("</b></FONT>");
		sb.append("</a>");
		boolean ss = sendMail(email, MEMBER_FORGETPASSWORD, sb.toString());
		return ss;
	}

}
