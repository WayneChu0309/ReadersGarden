package com.member.model;

import java.sql.Date;
import java.util.List;

import com.member.model.MemberVO;
import com.member.model.*;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String email, String name, Date birthday, String phoneNumber, String address, String ID,
			String password, String status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setEmail(email);
		memberVO.setName(name);
		memberVO.setBirthday(birthday);
		memberVO.setPhoneNumber(phoneNumber);
		memberVO.setAddress(address);
		memberVO.setID(ID);
		memberVO.setPassword(password);
		memberVO.setStatus(status);
		// memberVO.setNumber(number);
		dao.add(memberVO);

		return memberVO;
	}
	
	public MemberVO updateMember(MemberVO memberVO) {
		dao.update(memberVO);
		return memberVO;
	}
	
	public MemberVO updateMember(Integer number, String email, String name, Date birthday, String phoneNumber,
			String address, String ID, String password, String status) {

		MemberVO memberVO = new MemberVO();

		memberVO.setEmail(email);
		memberVO.setName(name);
		memberVO.setBirthday(birthday);
		memberVO.setPhoneNumber(phoneNumber);
		memberVO.setAddress(address);
		memberVO.setID(ID);
		memberVO.setPassword(password);
		memberVO.setStatus(status);
		memberVO.setNumber(number);
		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer number) {
		dao.delete(number);
	}

	public MemberVO getOneMember(Integer number) {
		return dao.findByPK(number);
	}

	public MemberVO findEmail(String email) {
		return dao.findByEmail(email);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}

	public MemberVO login(String email, String password) {
		return dao.login(email, password);
	}

	public MemberVO register(String email, String name, String phoneNumber, String address, String ID,
			String password) {

		MemberVO memberVO = new MemberVO();

		memberVO.setEmail(email);
		memberVO.setName(name);

		memberVO.setPhoneNumber(phoneNumber);
		memberVO.setAddress(address);
		memberVO.setID(ID);
		memberVO.setPassword(password);
		memberVO.setStatus("正常");

		dao.register(memberVO);

		return memberVO;
	}

	public MemberVO updateMember2(Integer number, String email, String name, String phoneNumber, String address,
			String ID, String password) {

		MemberVO memberVO = new MemberVO();

		memberVO.setEmail(email);
		memberVO.setName(name);
		memberVO.setPhoneNumber(phoneNumber);
		memberVO.setAddress(address);
		memberVO.setID(ID);
		memberVO.setPassword(password);
		memberVO.setNumber(number);
		dao.updateMember2(memberVO);

		return memberVO;
	}

	public void updatePassword(String password) {
		MemberVO memberVO = new MemberVO();
		memberVO.setPassword(password);
		dao.update(memberVO);
	}


}