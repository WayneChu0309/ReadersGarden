package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import util.Util;

// 此類別實作DAO interface，並將資料庫操作細節封裝起來
public class ArticleDAOImpl implements ArticleDAO {
	private static final String INSERT_STMT = "INSERT INTO ARTICLE(ACID, ACCTID, ANAME, ADESCRIPT) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ARTICLE SET ACID = ?, ACCTID = ?, ANAME = ?, ADESCRIPT = ? WHERE AID = ?";
	private static final String DELETE_STMT = "DELETE FROM ARTICLE WHERE AID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ARTICLE WHERE AID = ?";
	private static final String GET_ALL = "SELECT * FROM ARTICLE";
	
	private static final String GET_PAGE = "SELECT * FROM ARTICLE WHERE AID = ? ORDER BY ACID DESC limit 2";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Article article) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, article.getAID());
			pstmt.setInt(1, article.getACID());
			pstmt.setInt(2, article.getACCTID());
			pstmt.setString(3, article.getANAME());
			pstmt.setString(4, article.getADESCRIPT());

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
	public void update(Article article) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, article.getACID());
			pstmt.setInt(2, article.getACCTID());
			pstmt.setString(3, article.getANAME());
			pstmt.setString(4, article.getADESCRIPT());
			pstmt.setInt(5, article.getAID());


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
	public void delete(int aID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, aID);
			
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
	public Article findByPK(int aID) {
		Article art = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, aID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				art = new Article();
				art.setAID(rs.getInt("AID"));
				art.setACID(rs.getInt("ACID"));
				art.setACCTID(rs.getInt("ACCTID"));
				art.setANAME(rs.getString("ANAME"));
				art.setADESCRIPT(rs.getString("ADESCRIPT"));
				art.setAPD(rs.getDate("APD"));
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

		return art;
	}

	@Override
	public List<Article> getAll() {
		List<Article> artList = new ArrayList<>();
		Article art = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				art = new Article();
				art.setAID(rs.getInt("AID"));
				art.setACID(rs.getInt("ACID"));
				art.setACCTID(rs.getInt("ACCTID"));
				art.setANAME(rs.getString("ANAME"));
				art.setADESCRIPT(rs.getString("ADESCRIPT"));
				art.setAPD(rs.getDate("APD"));
				artList.add(art);
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
		return artList;
	}

}



