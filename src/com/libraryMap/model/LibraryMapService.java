package com.libraryMap.model;

import java.util.List;

public class LibraryMapService {
	
	private LibraryMapDAO_interface dao;
	
	public LibraryMapService() {
		dao = new LibraryMapJNDIDAO();
	}
	
	public LibraryMapVO addLibraryMap(Integer libId, String floor, byte[] floorImg) {
		LibraryMapVO libMapVO = new LibraryMapVO();
		libMapVO.setLibraryId(libId);
		libMapVO.setFloor(floor);
		libMapVO.setFloorImg(floorImg);
		dao.add(libMapVO);
		return libMapVO;
	}
	
	public LibraryMapVO updateLibraryMap(Integer mapId, Integer libId, String floor, byte[] floorImg) {
		LibraryMapVO libMapVO = new LibraryMapVO();
		libMapVO.setMapId(mapId);
		libMapVO.setLibraryId(libId);
		libMapVO.setFloor(floor);
		libMapVO.setFloorImg(floorImg);
		dao.add(libMapVO);
		return libMapVO;
	}
	
	public void deleteLibMap(Integer mapId) {
		dao.delete(mapId);
	}
	
	public LibraryMapVO getOneLibMap(Integer mapId) {
		return dao.findByMapId(mapId);
	}
	
	public List<LibraryMapVO> getAll() {
		return dao.getAll();
	}
}
