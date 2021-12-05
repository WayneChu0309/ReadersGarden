package com.ticketbackend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;


public class Ticket_BackendDAOImpl implements Ticket_BackendDAO {
	private static final String INSERT_STMT = "INSERT INTO TICKET_BACKEND (TICKETID, TOTALNUMT, TLEFT, TPRICE, ACTID) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE TICKET_BACKEND SET TOTALNUMT = ?, TLEFT = ?, TPRICE = ?, ACTID = ? WHERE TICKETID = ?";
	private static final String DELETE_STMT = "DELETE FROM TICKET_BACKEND WHERE TICKETID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM TICKET_BACKEND WHERE TICKETID = ?";
	private static final String GET_ALL = "SELECT * FROM TICKET_BACKEND";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Ticket_Backend ticket_backend) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ticket_backend.getTICKETID());
			pstmt.setInt(2, ticket_backend.getTOTALNUMT());
			pstmt.setInt(3, ticket_backend.getTLEFT());
			pstmt.setInt(4, ticket_backend.getTPRICE());
			pstmt.setInt(5, ticket_backend.getACTID());
		

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
	public void update(Ticket_Backend ticket_backend) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, ticket_backend.getTICKETID());
			pstmt.setInt(2, ticket_backend.getTOTALNUMT());
			pstmt.setInt(3, ticket_backend.getTLEFT());
			pstmt.setInt(4, ticket_backend.getTPRICE());
			pstmt.setInt(5, ticket_backend.getACTID());

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
	public void delete(int TICKETID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, TICKETID);
			
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
	public Ticket_Backend findByPK(int TICKETID) {
		Ticket_Backend tb = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, TICKETID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tb = new Ticket_Backend();
				tb.setTICKETID(rs.getInt("TICKETID"));
				tb.setTOTALNUMT(rs.getInt("TOTALNUMT"));
				tb.setTLEFT(rs.getInt("TLEFT"));
				tb.setTPRICE(rs.getInt("TPRICE"));
				tb.setACTID(rs.getInt("ACTID"));
				
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

		return tb;
	}

	@Override
	public List<Ticket_Backend> getAll() {
		List<Ticket_Backend> tbList = new ArrayList<>();
		Ticket_Backend tb = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tb = new Ticket_Backend();
				tb.setTICKETID(rs.getInt("TICKETID"));
				tb.setTOTALNUMT(rs.getInt("TOTALNUMT"));
				tb.setTLEFT(rs.getInt("TLEFT"));
				tb.setTPRICE(rs.getInt("TPRICE"));
				tb.setACTID(rs.getInt("ACTID"));
				tbList.add(tb);
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
		return tbList;
	}

}

