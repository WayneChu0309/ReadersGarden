package com.replyrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

// 此類別實作DAO interface，並將資料庫操作細節封裝起來
public class ReplyRecordDAOImpl implements ReplyRecordDAO {
	private static final String INSERT_STMT = "INSERT INTO Reply_Record(SENUM, ACCTID, AID, REPDESCRIPT) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Reply_Record SET ACCTID = ?, AID = ?, REPDESCRIPT = ? WHERE SENUM = ?";
	private static final String DELETE_STMT = "DELETE FROM Reply_Record WHERE SENUM = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Reply_Record WHERE SENUM = ?";
	private static final String GET_ALL = "SELECT * FROM Reply_Record";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Reply_Record replyrecord) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, replyrecord.getSENUM());
			pstmt.setInt(2, replyrecord.getACCTID());
			pstmt.setInt(3, replyrecord.getAID());
			pstmt.setString(4, replyrecord.getREPDESCRIPT());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
	public void update(Reply_Record replyrecord) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(4, replyrecord.getSENUM());
			pstmt.setInt(1, replyrecord.getACCTID());
			pstmt.setInt(2, replyrecord.getAID());
			pstmt.setString(3, replyrecord.getREPDESCRIPT());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
	public void delete(int SENUM) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, SENUM);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
	public Reply_Record findByPK(int SENUM) {
		Reply_Record rr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, SENUM);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rr = new Reply_Record();
				rr.setSENUM(rs.getInt("SENUM"));
				rr.setACCTID(rs.getInt("ACCTID"));
				rr.setAID(rs.getInt("AID"));
				rr.setREPDESCRIPT(rs.getString("REPDESCRIPT"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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

		return rr;
	}

	@Override
	public List<Reply_Record> getAll() {
		List<Reply_Record> rrList = new ArrayList<>();
		Reply_Record rr = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rr = new Reply_Record();
				rr.setSENUM(rs.getInt("SENUM"));
				rr.setACCTID(rs.getInt("ACCTID"));
				rr.setAID(rs.getInt("AID"));
				rr.setREPDESCRIPT(rs.getString("REPDESCRIPT"));
				rrList.add(rr);
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
		return rrList;
	}

}


