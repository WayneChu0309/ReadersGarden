package com.vendor.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class VendorDAO implements VendorDAO_interface {

//	private static DataSource ds = null;
//	
//	static {
//		
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ReaderGarden");
//			
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO VENDOR (COMPANY, TAXID, NAME, EMAIL, PASSWORD, TEL, MOBILE, ADDR) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE VENDOR SET COMPANY = ?, TAXID = ?, NAME = ?, EMAIL = ?, PASSWORD = ?, TEL = ?, MOBILE = ?, ADDR = ? WHERE VENDORID = ?";
	private static final String DELETE_STMT = "DELETE FROM VENDOR WHERE VENDORID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM VENDOR WHERE VENDORID = ?";
	private static final String GET_ALL = "SELECT * FROM VENDOR";
	private static final String FIND_BY_KEYWORD = "SELECT * FROM VENDOR WHERE COMPANY LIKE ?";
	private static final String UPDATE_STATUS = "UPDATE VENDOR SET STATUS = ? WHERE VENDORID = ?";
	private static final String FIND_BY_EMAIL = "SELECT * FROM VENDOR WHERE EMAIL = ?";
	
	@Override
	public void add(VendorVO vendorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, vendorVO.getCompany());
			pstmt.setInt(2, vendorVO.getTaxid());
			pstmt.setString(3, vendorVO.getName());
			pstmt.setString(4, vendorVO.getEmail());
			pstmt.setString(5, vendorVO.getPassword());
			pstmt.setString(6, vendorVO.getTel());
			pstmt.setString(7, vendorVO.getMobile());
			pstmt.setString(8, vendorVO.getAddr());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(VendorVO vendorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, vendorVO.getCompany());
			pstmt.setInt(2, vendorVO.getTaxid());
			pstmt.setString(3, vendorVO.getName());
			pstmt.setString(4, vendorVO.getEmail());
			pstmt.setString(5, vendorVO.getPassword());
			pstmt.setString(6, vendorVO.getTel());
			pstmt.setString(7, vendorVO.getMobile());
			pstmt.setString(8, vendorVO.getAddr());
			pstmt.setInt(9, vendorVO.getVendorid());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer vendorid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, vendorid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
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
	public VendorVO findByPK(Integer vendorid) {

		Connection con = null;
		PreparedStatement pstmt = null;
		VendorVO vd = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, vendorid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vd = new VendorVO();
				vd.setVendorid(rs.getInt("VENDORID"));
				vd.setCompany(rs.getString("COMPANY"));
				vd.setStatus(rs.getString("STATUS"));
				vd.setTaxid(rs.getInt("TAXID"));
				vd.setName(rs.getString("NAME"));
				vd.setEmail(rs.getString("EMAIL"));
				vd.setPassword(rs.getString("PASSWORD"));
				vd.setTel(rs.getString("TEL"));
				vd.setMobile(rs.getString("MOBILE"));
				vd.setAddr(rs.getString("ADDR"));
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
		return vd;
	}

	@Override
	public List<VendorVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VendorVO> vdl = new ArrayList<>();
		VendorVO vd = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vd = new VendorVO();
				vd.setVendorid(rs.getInt("VENDORID"));
				vd.setCompany(rs.getString("COMPANY"));
				vd.setStatus(rs.getString("STATUS"));
				vd.setTaxid(rs.getInt("TAXID"));
				vd.setName(rs.getString("NAME"));
				vd.setEmail(rs.getString("EMAIL"));
				vd.setPassword(rs.getString("PASSWORD"));
				vd.setTel(rs.getString("TEL"));
				vd.setMobile(rs.getString("MOBILE"));
				vd.setAddr(rs.getString("ADDR"));
				vdl.add(vd);

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
		return vdl;
	}
	
	public List<VendorVO> findByKeyword(String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VendorVO> venlist = new ArrayList<>();
		VendorVO venVO = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_KEYWORD);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				venVO = new VendorVO();
				venVO.setVendorid(rs.getInt("VENDORID"));
				venVO.setCompany(rs.getString("COMPANY"));
				venVO.setStatus(rs.getString("STATUS"));
				venVO.setTaxid(rs.getInt("TAXID"));
				venVO.setName(rs.getString("NAME"));
				venVO.setEmail(rs.getString("EMAIL"));
				venVO.setPassword(rs.getString("PASSWORD"));
				venVO.setTel(rs.getString("TEL"));
				venVO.setMobile(rs.getString("MOBILE"));
				venVO.setAddr(rs.getString("ADDR"));
				venlist.add(venVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			if(rs!=null) {
				try {
					
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return venlist;
		
	}

	@Override
	public void updateStatus(Integer vendorid, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, status);
			pstmt.setInt(2, vendorid);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public VendorVO findByEmail(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		VendorVO vd = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vd = new VendorVO();
				vd.setVendorid(rs.getInt("VENDORID"));
				vd.setCompany(rs.getString("COMPANY"));
				vd.setStatus(rs.getString("STATUS"));
				vd.setTaxid(rs.getInt("TAXID"));
				vd.setName(rs.getString("NAME"));
				vd.setEmail(rs.getString("EMAIL"));
				vd.setPassword(rs.getString("PASSWORD"));
				vd.setTel(rs.getString("TEL"));
				vd.setMobile(rs.getString("MOBILE"));
				vd.setAddr(rs.getString("ADDR"));
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
		return vd;
	}


}
