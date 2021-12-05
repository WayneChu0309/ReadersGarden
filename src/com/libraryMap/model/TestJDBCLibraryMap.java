package com.libraryMap.model;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TestJDBCLibraryMap {
	
	public static void main(String[] args) {
		LibraryMapJDBCDAO dao = new LibraryMapJDBCDAO();
		
		// 新增
//		LibraryMapVO map1 = new LibraryMapVO();
//		map1.setLibraryId(1);
//		map1.setFloor("四樓");
//		try {
//			byte[] pic = getPictureByteArray("src/LibraryMap/test.jpg");
//			map1.setFloorImg(pic);
//			dao.add(map1);
//		} catch (IOException e) {
//			e.printStackTrace(System.err);
//		}
		
		// 修改
//		LibraryMapVO map2 = new LibraryMapVO();
//		map2.setMapId(1);
//		map2.setLibraryId(1);
//		map2.setFloor("五樓");
//		try {
//			byte[] pic2 = getPictureByteArray("src/LibraryMap/test2.jpg");
//			map2.setFloorImg(pic2);
//			dao.update(map2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 刪除
//		dao.delete(6);
	
		// 單筆查詢
		LibraryMapVO map3 = dao.findByMapId(1);
		out.println("地圖編號 : " + map3.getMapId());
		out.println("圖書館編號 : " + map3.getLibraryId());
		out.println("樓層 : " + map3.getFloor());
		out.println("樓層圖 : " + map3.getFloorImg());
		
		// 全部查詢
		List<LibraryMapVO> mapList = dao.getAll();
		for(LibraryMapVO map4 : mapList) {
			out.println("地圖編號 : " + map4.getMapId());
			out.println("圖書館編號 : " + map4.getLibraryId());
			out.println("樓層 : " + map4.getFloor());
			out.println("樓層圖 : " + map4.getFloorImg());
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];  
		fis.read(buffer);  // 因為來源為硬碟, 不需使用buffer分段處理  // fis.read 回傳值為int, buffer的byte數量
		fis.close();
		return buffer;
	}
	
}
