package com.member.model;

import java.sql.Date;
import java.util.List;



public interface MemberDAO_interface {
	
	public	void add(MemberVO member);
	public	void update(MemberVO member);
	public	void delete(Integer number);
	public MemberVO findByPK(Integer number);
	public MemberVO findByEmail(String email);
	public MemberVO login(String email, String password);
	public void  register(MemberVO member);
	public void  updateMember2(MemberVO member);
	List<MemberVO> getAll();
}