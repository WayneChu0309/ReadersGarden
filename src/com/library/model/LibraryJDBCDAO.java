package com.library.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

import static java.lang.System.out;

public class LibraryJDBCDAO implements LibraryDAO_interface{
	private static final String INSERT_STMT = "INSERT INTO LIBRARY (libName, libLoc, libTime,"
												+ " libTel, libEmail) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LIBRARY SET libName = ?, libLoc = ?, libTime = ?, "
												+ "libTel = ?, libEmail = ? WHERE libId = ?";
	private static final String DELETE_STMT = "DELETE FROM LIBRARY WHERE libId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM LIBRARY WHERE LibId = ?";
	private static final String GET_ALL = "SELECT * FROM LIBRARY";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public void add(LibraryVO library) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
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
}
