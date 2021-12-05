package com.ticket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

// 此類別實作DAO interface，並將資料庫操作細節封裝起來
public class TicketDAOImpl implements TicketDAO {
	private static final String INSERT_STMT = "INSERT INTO TICKET1(ORDERID, CUSTOMERID, TOTALCOST, ACTNO, ORDERDATE) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE TICKET1 SET CUSTOMERID = ?, TOTALCOST = ?, ACTNO = ?, ORDERDATE = ? WHERE ORDERID = ?";
	private static final String DELETE_STMT = "DELETE FROM TICKET1 WHERE ORDERID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM TICKET1 WHERE ORDERID = ?";
	private static final String GET_ALL = "SELECT * FROM TICKET1";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(Ticket ticket1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ticket1.getORDERID());
			pstmt.setInt(2, ticket1.getCUSTOMERID());
			pstmt.setInt(3, ticket1.getTOTALCOST());
			pstmt.setInt(4, ticket1.getACTNO());
			pstmt.setInt(5, ticket1.getORDERDATE());


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
	public void update(Ticket ticket1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, ticket1.getORDERID());
			pstmt.setInt(2, ticket1.getCUSTOMERID());
			pstmt.setInt(3, ticket1.getTOTALCOST());
			pstmt.setInt(4, ticket1.getACTNO());
			pstmt.setInt(5, ticket1.getORDERDATE());

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
	public void delete(int ORDERID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, ORDERID);
			
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
	public Ticket findByPK(int ORDERID) {
		Ticket tk = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, ORDERID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tk = new Ticket();
				tk.setORDERID(rs.getInt("ORDERID"));
				tk.setCUSTOMERID(rs.getInt("CUSTOMERID"));
				tk.setTOTALCOST(rs.getInt("TOTALCOST"));
				tk.setACTNO(rs.getInt("ACTNO"));
				tk.setORDERDATE(rs.getInt("ORDERDATE"));

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

		return tk;
	}

	@Override
	public List<Ticket> getAll() {
		List<Ticket> tkList = new ArrayList<>();
		Ticket tk = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tk = new Ticket();
				tk.setORDERID(rs.getInt("ORDERID"));
				tk.setCUSTOMERID(rs.getInt("CUSTOMERID"));
				tk.setTOTALCOST(rs.getInt("TOTALCOST"));
				tk.setACTNO(rs.getInt("ACTNO"));
				tk.setORDERDATE(rs.getInt("ORDERDATE"));
				tkList.add(tk);
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
		return tkList;
	}

}

