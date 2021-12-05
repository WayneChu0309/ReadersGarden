package com.site.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class SiteDAO implements SiteDAO_interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SITE (NAME, AREA, DAYCOST, CAPACITY, IMG) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE SITE SET NAME = ?, AREA = ?, DAYCOST = ?, CAPACITY = ?, IMG = ? WHERE SITEID = ?";
	private static final String DELETE_STMT = "DELETE FROM SITE WHERE SITEID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM SITE WHERE SITEID = ?";
	private static final String GET_ALL = "SELECT * FROM SITE";
	private static final String UPDATE_IMG = "UPDATE SITE SET IMG = ? WHERE SITEID = ?";
	private static final String FIND_OPTIONS = "SELECT * FROM SITE WHERE AREA >= ? AND DAYCOST <= ? AND CAPACITY >= ?";
	
	@Override
	public void add(SiteVO siteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, siteVO.getName());
			pstmt.setInt(2, siteVO.getArea());
			pstmt.setInt(3, siteVO.getDaycost());
			pstmt.setInt(4, siteVO.getCapacity());
			pstmt.setBytes(5, siteVO.getImg());

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
	public void update(SiteVO siteVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, siteVO.getName());
			pstmt.setInt(2, siteVO.getArea());
			pstmt.setInt(3, siteVO.getDaycost());
			pstmt.setInt(4, siteVO.getCapacity());
			pstmt.setBytes(5, siteVO.getImg());
			pstmt.setInt(6, siteVO.getSiteid());
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
	public void delete(Integer siteid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, siteid);
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
	public SiteVO findByPK(Integer siteid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SiteVO siteVO = new SiteVO();
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, siteid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				siteVO.setSiteid(rs.getInt("SITEID"));
				siteVO.setName(rs.getString("NAME"));
				siteVO.setArea(rs.getInt("AREA"));
				siteVO.setDaycost(rs.getInt("DAYCOST"));
				siteVO.setCapacity(rs.getInt("CAPACITY"));
				siteVO.setImg(rs.getBytes("IMG"));
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
		return siteVO;
	}

	@Override
	public List<SiteVO> getAll() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		List<SiteVO> sitelist = new ArrayList<>();
		SiteVO siteVO = null;
		ResultSet rs = null;
		
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
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
		return sitelist;
	}

	@Override
	public void updateImg(Integer siteid, String path) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_IMG);
			
			pstmt.setBytes(1, getPictureByteArray(path));
			pstmt.setInt(2, siteid);

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
	
	public static byte[] getPictureByteArray(String path) {
			
			FileInputStream fis = null;
			byte[] buffer = null;
			
			try {
				fis = new FileInputStream(path);
				buffer = new byte[fis.available()];
				fis.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return buffer;
		}

	@Override
	public List<SiteVO> findOptions(Integer area, Integer daycost, Integer capacity) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SiteVO> sitelist = new ArrayList<>();
		SiteVO siteVO = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_OPTIONS);
			
			pstmt.setInt(1, area);
			pstmt.setInt(2, daycost);
			pstmt.setInt(3, capacity);
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
		return sitelist;
	}
}
