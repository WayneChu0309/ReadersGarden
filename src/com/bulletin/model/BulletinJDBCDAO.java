package com.bulletin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class BulletinJDBCDAO implements BulletinDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO BULLETIN(memberId, buContent, buDate) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE BULLETIN SET memberId = ?, buContent = ?, buDate = ? "
												+ "WHERE bulletinId = ?";
	private static final String DELETE_STMT = "DELETE FROM BULLETIN WHERE bulletinId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM BULLETIN WHERE bulletinId = ?";
	private static final String GET_ALL = "SELECT * FROM BULLETIN";
	private static final String test = "select bucontent from bulletin b join member m on b.memberid = m.memberid";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(BulletinVO bulletin) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, bulletin.getMemberId());
			pstmt.setString(2, bulletin.getBulletinContent());
			pstmt.setDate(3, bulletin.getBulletinDate());			
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
	public void update(BulletinVO bulletin) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, bulletin.getMemberId());
			pstmt.setString(2, bulletin.getBulletinContent());
			pstmt.setDate(3, bulletin.getBulletinDate());
			pstmt.setInt(4, bulletin.getBulletinId());			
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
	public void delete(Integer bulletinId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, bulletinId);
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
	public BulletinVO findByBulletinId(Integer bulletinId) {
		BulletinVO bulletin = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, bulletinId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bulletin = new BulletinVO();
				bulletin.setBulletinId(rs.getInt("bulletinId"));
				bulletin.setMemberId(rs.getInt("memberId"));
				bulletin.setBulletinContent(rs.getString("buContent"));
				bulletin.setBulletinDate(rs.getDate("buDate"));
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
		return bulletin;
	}

	@Override
	public List<BulletinVO> getAll() {
		List<BulletinVO> bullList = new ArrayList<>();
		BulletinVO bulletin = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bulletin = new BulletinVO();
				bulletin.setBulletinId(rs.getInt("bulletinId"));
				bulletin.setMemberId(rs.getInt("memberId"));
				bulletin.setBulletinContent(rs.getString("buContent"));
				bulletin.setBulletinDate(rs.getDate("buDate"));
				bullList.add(bulletin);
				
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
		return bullList;
	}

	@Override
	public List<String> test() {
		List<String> test1 = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(test);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				test1.add(rs.getString("bucontent"));
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
		return test1;
	}
}
