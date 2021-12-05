package com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ActivityDAO implements ActivityDAO_interface{
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ACTIVITY (ACTTYPE) VALUES (?)";
	private static final String UPDATE_STMT = "UPDATE ACTIVITY SET ACTTYPE = ? WHERE ACTID = ?";
	private static final String DELETE_STMT = "DELETE FROM ACTIVITY WHERE ACTID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM ACTIVITY WHERE ACTID = ?";
	private static final String GET_ALL = "SELECT * FROM ACTIVITY ORDER BY ACTID";
	
	
	@Override
	public void add(ActivityVO act) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, act.getActtype());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public void update(ActivityVO act) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, act.getActtype());
			pstmt.setInt(2, act.getActid());

			pstmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public void delete(Integer actid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, actid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public ActivityVO findByPK(Integer actid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ActivityVO act = new ActivityVO();
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, actid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				act.setActid(rs.getInt("ACTID"));
				act.setActtype(rs.getString("ACTTYPE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return act;
	}
	@Override
	public List<ActivityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<ActivityVO> actlist = new ArrayList<>();
		ActivityVO act = null;
		ResultSet rs = null;
		
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				act = new ActivityVO();
				act.setActid(rs.getInt("ACTID"));
				act.setActtype(rs.getString("ACTTYPE"));
				actlist.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!= null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return actlist;
	}
}
