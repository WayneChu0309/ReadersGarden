package com.vactstatus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siteact.model.SiteActVO;

import util.Util;

public class VactStatusDAO implements VactStatusDAO_Interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO VACTSTATUS (VACTID, STATUSID) VALUES (?, ?)";
	private static final String UPDATE_STMT = "UPDATE VACTSTATUS SET DATE = ?, VACTID = ?, STATUSID = ? WHERE LISTID = ?";
	private static final String DELETE_STMT = "DELETE FROM VACTSTATUS WHERE LISTID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM VACTSTATUS WHERE LISTID = ?";
	private static final String GET_ALL = "SELECT * FROM VACTSTATUS";

	
	@Override
	public void add(VactStatusVO vs) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, vs.getVactid());
			pstmt.setInt(2, vs.getStatusid());

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
	public void update(VactStatusVO vs) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setTimestamp(1, vs.getDate());
			pstmt.setInt(2, vs.getVactid());
			pstmt.setInt(3, vs.getStatusid());
			pstmt.setInt(4, vs.getListid());

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
	public void delete(Integer listid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, listid);
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
	public VactStatusVO findByPK(Integer listid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		VactStatusVO vsVO = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, listid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vsVO = new VactStatusVO();
				vsVO.setListid(rs.getInt("LISTID"));
				vsVO.setDate(rs.getTimestamp("DATE"));
				vsVO.setVactid(rs.getInt("VACTID"));
				vsVO.setStatusid(rs.getInt("STATUSID"));
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
		return vsVO;
	}

	@Override
	public List<VactStatusVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<VactStatusVO> vslist = new ArrayList<>();
		VactStatusVO vsVO = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vsVO = new VactStatusVO();
				vsVO.setListid(rs.getInt("LISTID"));
				vsVO.setDate(rs.getTimestamp("DATE"));
				vsVO.setVactid(rs.getInt("VACTID"));
				vsVO.setStatusid(rs.getInt("STATUSID"));
				vslist.add(vsVO);
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
		return vslist;
	}
}
