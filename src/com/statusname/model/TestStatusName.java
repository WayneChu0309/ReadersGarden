package com.statusname.model;

import java.util.ArrayList;
import java.util.List;

public class TestStatusName {
	
	public static void main(String[] args) {
	
		StatusNameDAO_Interface dao = new StatusNameDAO();
		
		//find_By_PK
		StatusNameVO snVO = new StatusNameVO();
		snVO = dao.findByPK(1);
		System.out.println(snVO.toString());
		System.out.println("=======================================");
		
		//getAll
		List<StatusNameVO> snlist = new ArrayList<>();
		snlist = dao.getAll();
		for(StatusNameVO snVO1 : snlist) {
			System.out.println(snVO1);
		}
		
	}
	
}
