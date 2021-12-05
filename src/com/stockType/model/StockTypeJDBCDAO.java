package com.stockType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

import java.io.File;

import util.Util;

public class StockTypeJDBCDAO implements StockTypeDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO STOCK_TYPE (typeName, kind) VALUES (?,?)";
	private static final String UPDATE_STMT = "UPDATE STOCK_TYPE SET typeName = ?, kind = ? WHERE typeId = ?";
	private static final String DELETE_STMT = "DELETE FROM STOCK_TYPE WHERE typeId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM STOCK_TYPE WHERE typeId = ?";
	private static final String GET_ALL = "SELECT * FROM STOCK_TYPE";
	private static final String GET_BOOK_TYPE = "SELECT * FROM STOCK_TYPE WHERE kind = '書籍'";
	private static final String GET_MOVIE_TYPE = "SELECT * FROM STOCK_TYPE WHERE kind = '電影'";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(StockTypeVO stockType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, stockType.getTypeName());
			pstmt.setString(2, stockType.getKind());
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
	public void update(StockTypeVO stockType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, stockType.getTypeName());
			pstmt.setString(2, stockType.getKind());
			pstmt.setInt(3, stockType.getTypeId());
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
	public void delete(Integer typeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, typeId);
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
	public StockTypeVO findByTypeId(Integer typeId) {
		StockTypeVO type = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
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
		return type;
	}

	@Override
	public List<StockTypeVO> getAll() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}

	@Override
	public List<StockTypeVO> getBook() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BOOK_TYPE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}

	@Override
	public List<StockTypeVO> getMovie() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_MOVIE_TYPE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}
	
//	public static void main(String[] args) {
//		StockTypeDAO dao = new StockTypeDAOImpl();
//		
//		// 新增
////		StockTypeVO type1 = new StockTypeVO();
////		type1.setTypeName("測試類別");
////		dao.add(type1);
//		
//		// 修改
////		StockTypeVO type2 = new StockTypeVO();
////		type2.setTypeId(18);
////		type2.setTypeName("測試類別二");
////		dao.update(type2);
//		
//		// 刪除
////		dao.delete(18);
//		
//		// 單筆查詢
////		StockTypeVO type3 = dao.findByTypeId(1);
////		out.println("類別編號 : " + type3.getTypeId());
////		out.println("類別編號 : " + type3.getTypeName());
//		
//		// 全部查詢
////		List<StockTypeVO> typeList = dao.getAll();
////		for (StockTypeVO type4 : typeList) {
////			out.println("類別編號 : " + type4.getTypeId());
////			out.println("類別編號 : " + type4.getTypeName());
////		}
//		
//		String bPath = "C:\\博客來資料\\書籍資料";
//		String mPath = "C:\\博客來資料\\電影資料";
//		File bf = new File(bPath);
//		File mf = new File(mPath);
//		String bfdirs[] = bf.list();
//		String mfdirs[] = mf.list();
//		
//		for (String book : bfdirs) {
//			StockTypeVO type = new StockTypeVO();
//			type.setTypeName(book);
//			dao.add(type);
//		}
//		
//		for (String movie : mfdirs) {
//			StockTypeVO type = new StockTypeVO();
//			type.setTypeName(movie);
//			dao.add(type);
//		}
//		
//	}

}
