package com.markRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;


public class MarkRecordDAO implements MarkRecordDAO_interface {
	
	
	private static final String INSERT_STMT = "INSERT INTO marks_record(number, stockId, VACTID) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE marks_record SET  number = ?, stockId = ?, VACTID = ? WHERE SERIALNUMBER = ?";
	private static final String DELETE_STMT = "DELETE FROM marks_record WHERE SERIALNUMBER = ?";
	private static final String FIND_BY_PK = "SELECT * FROM marks_record WHERE SERIALNUMBER = ?";
	private static final String GET_ALL = "SELECT * FROM marks_record";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(MarkRecordVO markRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

	
			pstmt.setInt(1, markRecordVO.getNumber());
			pstmt.setInt(2, markRecordVO.getStockID());
			pstmt.setInt(3, markRecordVO.getVacID());
		
			
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

	
	public void update(MarkRecordVO markRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			
			pstmt.setInt(1, markRecordVO.getNumber());
			pstmt.setInt(2, markRecordVO.getStockID());
			pstmt.setInt(3, markRecordVO.getVacID());
			pstmt.setInt(4, markRecordVO.getSerialNumber());
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
	public void delete(Integer serialNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, serialNumber);
			
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
	public MarkRecordVO findByPK(Integer serialNumber) {
		MarkRecordVO markRecord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, serialNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				markRecord = new MarkRecordVO();
				markRecord.setSerialNumber(rs.getInt("serialNumber"));
				markRecord.setNumber(rs.getInt("number"));
				markRecord.setStockID(rs.getInt("stockID"));
				markRecord.setVacID(rs.getInt("vactID"));
				
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

		return markRecord;
	}

	@Override
	public List<MarkRecordVO> getAll() {
		List<MarkRecordVO> markrecordList = new ArrayList<>();
		MarkRecordVO markrecord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				markrecord = new MarkRecordVO();
				markrecord.setSerialNumber(rs.getInt("serialNumber"));
				markrecord.setNumber(rs.getInt("number"));
				markrecord.setStockID(rs.getInt("stockID"));
				markrecord.setVacID(rs.getInt("vactID"));
				markrecordList.add(markrecord);
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
		return markrecordList;
	}

	

}
