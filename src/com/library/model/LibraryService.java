package com.library.model;

import java.util.List;

public class LibraryService {
	
	private LibraryDAO_interface dao;
	
	public LibraryService() {
		dao = new LibraryJNDIDAO();
	}
	
	public LibraryVO addLibrary(String libName, String libLoc, String libTime, String libTel, String libEmail ) {
		LibraryVO libVO = new LibraryVO();
		libVO.setLibraryName(libName);
		libVO.setLibraryAddress(libLoc);
		libVO.setLibraryTime(libTime);
		libVO.setLibraryTel(libTel);
		libVO.setLibraryEmail(libEmail);
		dao.add(libVO);
		return libVO;
	}
	
	public LibraryVO updateLibrary(Integer libId, String libName, String libLoc, String libTime, String libTel, String libEmail ) {
		LibraryVO libVO = new LibraryVO();
		libVO.setLibraryId(libId);
		libVO.setLibraryName(libName);
		libVO.setLibraryAddress(libLoc);
		libVO.setLibraryTime(libTime);
		libVO.setLibraryTel(libTel);
		libVO.setLibraryEmail(libEmail);
		dao.add(libVO);
		return libVO;
	}
	
	public void delete(Integer libId) {
		dao.delete(libId);
	}
	
	public LibraryVO getOneLibary(Integer libId) {
		return dao.findByLibraryId(libId);
	}
	
	public List<LibraryVO> getAll() {
		return dao.getAll();
	}
	
}
