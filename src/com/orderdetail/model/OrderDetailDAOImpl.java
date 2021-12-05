package com.orderdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

// 此類別實作DAO interface，並將資料庫操作細節封裝起來
public class OrderDetailDAOImpl implements OrderDetailDAOJDBC {
	private static final String INSERT_STMT = "INSERT INTO ORDER_DETAIL(TICKETID, ORDERID, ACTNO) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ORDER_DETAIL SET ORDERID = ?, ACTNO = ? WHERE TICKETID = ?";
	private static final String DELETE_STMT = "DELETE FROM ORDER_DETAIL WHERE TICKETID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ORDER_DETAIL WHERE TICKETID = ?";
	private static final String GET_ALL = "SELECT * FROM ORDER_DETAIL";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(OrderDetail order_detail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, order_detail.getTICKETID());
			pstmt.setInt(2, order_detail.getORDERID());
			pstmt.setInt(3, order_detail.getACTNO());
		

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
	public void update(OrderDetail order_detail) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);


			pstmt.setInt(1, order_detail.getTICKETID());
			pstmt.setInt(2, order_detail.getORDERID());
			pstmt.setInt(3, order_detail.getACTNO());


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
	public OrderDetail findByPK(int TICKETID) {
		OrderDetail od = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, TICKETID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				od = new OrderDetail();
				od.setTICKETID(rs.getInt("TICKETID"));
				od.setORDERID(rs.getInt("ORDERID"));
				od.setACTNO(rs.getInt("ACTNO"));

			
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

		return od;
	}

	@Override
	public List<OrderDetail> getAll() {
		List<OrderDetail> odList = new ArrayList<>();
		OrderDetail od = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				od = new OrderDetail();
				od.setTICKETID(rs.getInt("TICKETID"));
				od.setORDERID(rs.getInt("ORDERID"));
				od.setACTNO(rs.getInt("ACTNO"));

				odList.add(od);
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
		return odList;
	}

}

