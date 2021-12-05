package com.borrow.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import util.Util;

public class BorrowJDBCDAO implements BorrowDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO BORROW (memberId, stockId, listId, preBoDate, actBoDate,"
												+ "preReDate, actReDate, scoreContent, scoreDate, score, boStates) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE BORROW SET memberId = ?, stockId = ?, listId = ?, preBoDate = ?, "
												+ "actBoDate = ?, preReDate = ?, actReDate = ?, scoreContent = ?, "
												+ "scoreDate = ?, score = ?, boStates = ? WHERE borrowId = ? ";
	private static final String DELETE_STMT = "DELETE FROM BORROW WHERE borrowId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM BORROW WHERE borrowId = ?";
	private static final String GET_ALL = "SELECT * FROM BORROW";
	private static final String GET_NOT_ORDER_NUMBER = "SELECT COUNT(*) AS COUNT FROM BORROW WHERE stockId = ? and bostates != 3 and bostates != 5"; 
	private static final String GET_ORDER_STOCK_BY_NUMBER = "SELECT * FROM BORROW WHERE memberid = ? and stockId = ? and BOSTATES = 1";
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(BorrowVO borrow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, borrow.getMemberId());
			pstmt.setInt(2, borrow.getStockId());
			if (borrow.getListId() == null) {
				pstmt.setNull(3, Types.NULL);
			} else {
				pstmt.setInt(3, borrow.getListId());
			}
			pstmt.setDate(4, borrow.getPreBoDate());
			pstmt.setDate(5, borrow.getActBoDate());
			pstmt.setDate(6, borrow.getPreReDate());
			pstmt.setDate(7, borrow.getActReDate());
			pstmt.setString(8, borrow.getScoreContent());
			pstmt.setDate(9, borrow.getScoreDate());
			if (borrow.getScore() == null) {
				pstmt.setNull(10, Types.NULL);
			} else {
				pstmt.setInt(10, borrow.getScore());				
			}
			pstmt.setInt(11, borrow.getBoStates());
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
	public void update(BorrowVO borrow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, borrow.getMemberId());
			pstmt.setInt(2, borrow.getStockId());
			pstmt.setInt(3, borrow.getListId());
			pstmt.setDate(4, borrow.getPreBoDate());
			pstmt.setDate(5, borrow.getActBoDate());
			pstmt.setDate(6, borrow.getPreReDate());
			pstmt.setDate(7, borrow.getActReDate());
			pstmt.setString(8, borrow.getScoreContent());
			pstmt.setDate(9, borrow.getScoreDate());
			pstmt.setInt(10, borrow.getScore());
			pstmt.setInt(11, borrow.getBoStates());
			pstmt.setInt(12, borrow.getBorrowId());
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
	public void delete(Integer borrowId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, borrowId);
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
	public BorrowVO findByBorrowId(Integer borrowId) {
		BorrowVO borrow = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, borrowId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				borrow = new BorrowVO();
				borrow.setBorrowId(rs.getInt("borrowId"));
				borrow.setMemberId(rs.getInt("memberId"));
				borrow.setMemberId(rs.getInt("stockId"));
				borrow.setListId(rs.getInt("listId"));
				borrow.setPreBoDate(rs.getDate("preBoDate"));
				borrow.setActBoDate(rs.getDate("actBoDate"));
				borrow.setPreReDate(rs.getDate("preReDate"));
				borrow.setActReDate(rs.getDate("actReDate"));
				borrow.setScoreContent(rs.getString("scoreContent"));
				borrow.setScoreDate(rs.getDate("scoreDate"));
				borrow.setScore(rs.getInt("score"));
				borrow.setBoStates(rs.getInt("boStates"));
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
		return borrow;
	}

	@Override
	public List<BorrowVO> getAll() {
		List<BorrowVO> bwList = new ArrayList<>();
		BorrowVO borrow = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				borrow = new BorrowVO();
				borrow = new BorrowVO();
				borrow.setBorrowId(rs.getInt("borrowId"));
				borrow.setMemberId(rs.getInt("memberId"));
				borrow.setMemberId(rs.getInt("stockId"));
				borrow.setListId(rs.getInt("listId"));
				borrow.setPreBoDate(rs.getDate("preBoDate"));
				borrow.setActBoDate(rs.getDate("actBoDate"));
				borrow.setPreReDate(rs.getDate("preReDate"));
				borrow.setActReDate(rs.getDate("actReDate"));
				borrow.setScoreContent(rs.getString("scoreContent"));
				borrow.setScoreDate(rs.getDate("scoreDate"));
				borrow.setScore(rs.getInt("score"));
				borrow.setBoStates(rs.getInt("boStates"));
				bwList.add(borrow);
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
		return bwList;
	}

	@Override
	public Integer notOrder(Integer stockId) {
		Integer avail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_NOT_ORDER_NUMBER);
			pstmt.setInt(1, stockId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				avail = rs.getInt("count");
			} 
		
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
		return avail;
	}

	@Override
	public BorrowVO findByNumberPreOrder(Integer memberId, Integer stockId) {
		Integer borrowId = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BorrowVO borrow = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ORDER_STOCK_BY_NUMBER);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, stockId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				borrow = new BorrowVO();
				borrow.setBorrowId(rs.getInt("borrowId"));
				borrow.setMemberId(rs.getInt("memberId"));
				borrow.setMemberId(rs.getInt("stockId"));
				borrow.setListId(rs.getInt("listId"));
				borrow.setPreBoDate(rs.getDate("preBoDate"));
				borrow.setActBoDate(rs.getDate("actBoDate"));
				borrow.setPreReDate(rs.getDate("preReDate"));
				borrow.setActReDate(rs.getDate("actReDate"));
				borrow.setScoreContent(rs.getString("scoreContent"));
				borrow.setScoreDate(rs.getDate("scoreDate"));
				borrow.setScore(rs.getInt("score"));
				borrow.setBoStates(rs.getInt("boStates"));
			} 
		
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
		return borrow;
	}
	
	
}
