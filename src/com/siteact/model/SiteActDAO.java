package com.siteact.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.activity.model.ActivityVO;
import com.site.model.SiteVO;

import util.Util;

public class SiteActDAO implements SiteActDAO_interface{
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SITEACT (SITEID, ACTID) VALUES (?, ?)";
	private static final String UPDATE_STMT = "UPDATE SITEACT SET SITEID = ?, ACTID = ? WHERE LISTID = ?";
	private static final String DELETE_STMT = "DELETE FROM SITEACT WHERE LISTID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM SITEACT WHERE LISTID = ?";
	private static final String GET_ALL = "SELECT * FROM SITEACT";
	private static final String FIND_ONE_CATEGORY = "SELECT * FROM SITEACT SA JOIN SITE S ON S.SITEID = SA.SITEID WHERE ACTID = ?";
//	private static final String FIND_SITE_ACTIVITY = "SELECT * FROM SITEACT SA JOIN ACTIVITY A ON SA.ACTID = A.ACTID";
	
	@Override
	public void add(SiteActVO sa) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, sa.getSiteid());
			pstmt.setInt(2, sa.getActid());

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
	public void update(SiteActVO sa) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, sa.getSiteid());
			pstmt.setInt(2, sa.getActid());
			pstmt.setInt(3, sa.getListid());

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
	public SiteActVO findByPK(Integer listid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SiteActVO sa = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, listid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sa = new SiteActVO();
				sa.setListid(rs.getInt("LISTID"));
				sa.setSiteid(rs.getInt("SITEID"));
				sa.setActid(rs.getInt("ACTID"));
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
		return sa;
	}

	@Override
	public List<SiteActVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<SiteActVO> salist = new ArrayList<>();
		SiteActVO sa = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sa = new SiteActVO();
				sa.setListid(rs.getInt("LISTID"));
				sa.setSiteid(rs.getInt("SITEID"));
				sa.setActid(rs.getInt("ACTID"));
				salist.add(sa);
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
		return salist;
	
	}

	@Override
	public List<SiteVO> getOneCategory(Integer actid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SiteVO> sitelist = new ArrayList<>();
		SiteVO siteVO = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_ONE_CATEGORY);
			pstmt.setInt(1, actid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				siteVO = new SiteVO();
				siteVO.setSiteid(rs.getInt("SITEID"));
				siteVO.setName(rs.getString("NAME"));
				siteVO.setArea(rs.getInt("AREA"));
				siteVO.setDaycost(rs.getInt("DAYCOST"));
				siteVO.setCapacity(rs.getInt("CAPACITY"));
				siteVO.setImg(rs.getBytes("IMG"));
				sitelist.add(siteVO);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
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
		
		return sitelist;
	}	
}
