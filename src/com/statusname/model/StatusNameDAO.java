package com.statusname.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siteact.model.SiteActVO;

import util.Util;

public class StatusNameDAO implements StatusNameDAO_Interface{

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static final String FIND_BY_PK = "SELECT * FROM STATUSNAME WHERE STATUSID = ?";
	private static final String GET_ALL = "SELECT * FROM STATUSNAME";

	@Override
	public StatusNameVO findByPK(Integer statusid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		StatusNameVO snVO = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, statusid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				snVO = new StatusNameVO();
				snVO.setStatusid(rs.getInt("statusid"));
				snVO.setStatus(rs.getString("status"));
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
		return snVO;
	}

	@Override
	public List<StatusNameVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<StatusNameVO> snlist = new ArrayList<>();
		StatusNameVO snVO = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				snVO = new StatusNameVO();
				snVO.setStatusid(rs.getInt("statusid"));
				snVO.setStatus(rs.getString("status"));
				snlist.add(snVO);
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
		return snlist;	
	}

}
