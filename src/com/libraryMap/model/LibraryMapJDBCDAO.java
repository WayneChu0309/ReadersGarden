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
//		// ??????
//		LibraryMapVO map1 = new LibraryMapVO();
//		map1.setLibraryId(1);
//		map1.setFloor("??????");
//		try {
//			byte[] pic = getPictureByteArray("src/LibraryMap/test.jpg");
//			map1.setFloorImg(pic);
//			dao.add(map1);
//		} catch (IOException e) {
//			e.printStackTrace(System.err);
//		}
//		
//		// ??????
//		LibraryMapVO map2 = new LibraryMapVO();
//		map2.setMapId(1);
//		map2.setLibraryId(1);
//		map2.setFloor("??????");
//		try {
//			byte[] pic2 = getPictureByteArray("src/LibraryMap/test2.jpg");
//			map2.setFloorImg(pic2);
//			dao.update(map2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// ??????
//		dao.delete(6);
//	
//		// ????????????
//		LibraryMapVO map3 = dao.findByMapId(1);
//		out.println("???????????? : " + map3.getMapId());
//		out.println("??????????????? : " + map3.getLibraryId());
//		out.println("?????? : " + map3.getFloor());
//		out.println("????????? : " + map3.getFloorImg());
//		
//		// ????????????
//		List<LibraryMapVO> mapList = dao.getAll();
//		for(LibraryMapVO map4 : mapList) {
//			out.println("???????????? : " + map4.getMapId());
//			out.println("??????????????? : " + map4.getLibraryId());
//			out.println("?????? : " + map4.getFloor());
//			out.println("????????? : " + map4.getFloorImg());
//		}
//	}
//
//		public static byte[] getPictureByteArray(String path) throws IOException {
//			FileInputStream fis = new FileInputStream(path);
//			byte[] buffer = new byte[fis.available()];  
//			fis.read(buffer);  // ?????????????????????, ????????????buffer????????????  // fis.read ????????????int, buffer???byte??????
//			fis.close();
//			return buffer;
//		}
}
