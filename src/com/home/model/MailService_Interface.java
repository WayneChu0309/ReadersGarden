package com.home.model;

import com.vendor.model.VendorVO;

public interface MailService_Interface {
	
	public abstract String sendVerifyCode(String username, String email);
	
	public abstract String forgetPassword(Integer number);
	
	public abstract Boolean vendorForgetPassword(Integer vendorid, String email, String token);
	
	public abstract Boolean vendorRegisterEmail(Integer vendorid, String email, String token);
	public abstract Boolean memberForgetPassword(Integer number, String email, String token);

}
