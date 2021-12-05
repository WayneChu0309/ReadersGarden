package com.library.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LibraryJNDIDAO implements LibraryDAO_interface{
	private static final String INSERT_STMT = "INSERT INTO LIBRARY (libName, libLoc, libTime,"
												+ " libTel, libEmail) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LIBRARY SET libName = ?, libLoc = ?, libTime = ?, "
												+ "libTel = ?, libEmail = ? WHERE libId = ?";
	private static final String DELETE_STMT = "DELETE FROM LIBRARY WHERE libId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM LIBRARY WHERE LibraryId = ?";
	private static final String GET_ALL = "SELECT * FROM LIBRARY";
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void add(LibraryVO library) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, library.getLibraryName());
			pstmt.setString(2, library.getLibraryAddress());
			pstmt.setString(3, library.getLibraryTime());
			pstmt.setString(4, library.getLibraryTel());
			pstmt.setString(5, library.getLibraryEmail());
			
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
	

	public void update(LibraryVO library) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, library.getLibraryName());
			pstmt.setString(2, library.getLibraryAddress());
			pstmt.setString(3, library.getLibraryTime());
			pstmt.setString(4, library.getLibraryTel());
			pstmt.setString(5, library.getLibraryEmail());
			pstmt.setInt(6, library.getLibraryId());
			
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
	
	public void delete(Integer libId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, libId);
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
	
	public LibraryVO findByLibraryId(Integer libId) {
		LibraryVO lib = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, libId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lib = new LibraryVO();
				lib.setLibraryId(rs.getInt("libId"));
				lib.setLibraryName(rs.getString("libName"));
				lib.setLibraryAddress(rs.getString("libLoc"));
				lib.setLibraryTime(rs.getString("libTime"));
				lib.setLibraryTel(rs.getString("libTel"));
				lib.setLibraryEmail(rs.getString("libEmail"));
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
		return lib;
	}

	public List<LibraryVO> getAll() {
		List<LibraryVO> libList = new ArrayList<>();
		LibraryVO lib = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lib = new LibraryVO();
				lib.setLibraryId(rs.getInt("libId"));
				lib.setLibraryName(rs.getString("libName"));
				lib.setLibraryAddress(rs.getString("libLoc"));
				lib.setLibraryTime(rs.getString("libTime"));
				lib.setLibraryTel(rs.getString("libTel"));
				lib.setLibraryEmail(rs.getString("libEmail"));
				libList.add(lib);
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
		return libList;
	}
	
//	public static void main(String[] args) {
//		LibraryDAO dao = new LibraryDAOImpl();
//		
//		// 新增
//		LibraryVO lib1 = new LibraryVO();
//		lib1.setLibraryName("Reader's Garden");
//		lib1.setLibraryAddress("台北市中山區南京東路三段219號5樓");
//		lib1.setLibraryTime("全年無休");
//		lib1.setLibraryTel("02-2712-0589");
//		lib1.setLibraryEmail("tibame@gmail.com");
//		dao.add(lib1);
//		
//		// 修改
//		LibraryVO lib2 = new LibraryVO();
//		lib2.setLibraryId(2);
//		lib2.setLibraryName("TFA103 第一組");
//		lib2.setLibraryAddress("富士山");
//		lib2.setLibraryTime("見紅休");
//		lib2.setLibraryTel("0987-654-321");
//		lib2.setLibraryEmail("TFA103@gmail.com");
//		dao.update(lib2);
//		
//		// 刪除
////		dao.delete(2);
//		
//		// 單筆查詢
//		LibraryVO lib3 = dao.findByLibraryId(1);
//		out.println("圖書館編號 : " + lib3.getLibraryId());
//		out.println("圖書館名稱 : " + lib3.getLibraryName());
//		out.println("圖書館地址 : " + lib3.getLibraryAddress());
//		out.println("圖書館開放時間 : " + lib3.getLibraryTime());
//		out.println("圖書館聯絡電話 : " + lib3.getLibraryTel());
//		out.println("圖書館聯絡信箱 : " + lib3.getLibraryEmail());
//
//		// 全部查詢
//		List<LibraryVO> libList = dao.getAll();
//		for(LibraryVO lib4 : libList) {
//			out.println("圖書館編號 : " + lib4.getLibraryId());
//			out.println("圖書館名稱 : " + lib4.getLibraryName());
//			out.println("圖書館地址 : " + lib4.getLibraryAddress());
//			out.println("圖書館開放時間 : " + lib4.getLibraryTime());
//			out.println("圖書館聯絡電話 : " + lib4.getLibraryTel());
//			out.println("圖書館聯絡信箱 : " + lib4.getLibraryEmail());
//		}
//	}
//	
}
