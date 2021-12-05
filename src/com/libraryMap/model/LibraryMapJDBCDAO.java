package com.libraryMap.model;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Util;

public class LibraryMapJDBCDAO implements LibraryMapDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO LIBRARY_MAP (libId, floor, floorImg) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LIBRARY_MAP SET libId = ?, floor = ?, floorImg = ? WHERE mapId = ?";
	private static final String DELETE_STMT = "DELETE FROM LIBRARY_MAP WHERE mapId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM LIBRARY_MAP WHERE mapId = ?";
	private static final String GET_ALL = "SELECT * FROM LIBRARY_MAP";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(LibraryMapVO libraryMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, libraryMap.getLibraryId());
			pstmt.setString(2, libraryMap.getFloor());
			pstmt.setBytes(3, libraryMap.getFloorImg());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(LibraryMapVO libraryMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, libraryMap.getLibraryId());
			pstmt.setString(2, libraryMap.getFloor());
			pstmt.setBytes(3, libraryMap.getFloorImg());
			pstmt.setInt(4, libraryMap.getMapId());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer mapId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mapId);
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public LibraryMapVO findByMapId(Integer mapId) {
		LibraryMapVO map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mapId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				map = new LibraryMapVO();
				map.setMapId(rs.getInt("mapId"));
				map.setLibraryId(rs.getInt("libId"));
				map.setFloor(rs.getString("floor"));
				map.setFloorImg(rs.getBytes("floorImg"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return map;
	}

	@Override
	public List<LibraryMapVO> getAll() {
		List<LibraryMapVO> mapList = new ArrayList<>();
		LibraryMapVO map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				map = new LibraryMapVO();
				map.setMapId(rs.getInt("mapId"));
				map.setLibraryId(rs.getInt("libId"));
				map.setFloor(rs.getString("floor"));
				map.setFloorImg(rs.getBytes("floorImg"));
				mapList.add(map);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mapList;
	}
	
//	public static void main(String[] args) {
//		LibraryMapDAO dao = new LibraryMapDAOImpl();
//		
//		// 新增
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
//		
//		// 修改
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
//		
//		// 刪除
//		dao.delete(6);
//	
//		// 單筆查詢
//		LibraryMapVO map3 = dao.findByMapId(1);
//		out.println("地圖編號 : " + map3.getMapId());
//		out.println("圖書館編號 : " + map3.getLibraryId());
//		out.println("樓層 : " + map3.getFloor());
//		out.println("樓層圖 : " + map3.getFloorImg());
//		
//		// 全部查詢
//		List<LibraryMapVO> mapList = dao.getAll();
//		for(LibraryMapVO map4 : mapList) {
//			out.println("地圖編號 : " + map4.getMapId());
//			out.println("圖書館編號 : " + map4.getLibraryId());
//			out.println("樓層 : " + map4.getFloor());
//			out.println("樓層圖 : " + map4.getFloorImg());
//		}
//	}
//
//		public static byte[] getPictureByteArray(String path) throws IOException {
//			FileInputStream fis = new FileInputStream(path);
//			byte[] buffer = new byte[fis.available()];  
//			fis.read(buffer);  // 因為來源為硬碟, 不需使用buffer分段處理  // fis.read 回傳值為int, buffer的byte數量
//			fis.close();
//			return buffer;
//		}
}
