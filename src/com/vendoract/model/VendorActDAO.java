package com.vendoract.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class VendorActDAO implements VendorActDAO_interface {

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO VENDORACT (NAME, VENDORID, AMOUNT, SITEID, ACTID, RTLSTART, RTLEND, ACTSTART, ACTEND, TKSTART, TKEND, PRICE, ACTCNT, IMG) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE VENDORACT SET NAME = ?, VENDORID = ?, DATE = ?, AMOUNT = ?, SITEID = ?, ACTID = ?, PROGRESS = ?, RTLSTART = ?, RTLEND = ?, ACTSTART = ?, ACTEND = ?, TKSTART = ?, TKEND = ?, PRICE = ?, ACTCNT = ?, IMG = ? WHERE VACTID = ?";
	private static final String DELETE_STMT = "DELETE FROM VENDORACT WHERE VACTID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM VENDORACT WHERE VACTID = ?";
	private static final String GET_ALL = "SELECT * FROM VENDORACT ORDER BY VACTID DESC";
	private static final String UPDATE_IMG = "UPDATE VENDORACT SET IMG = ? WHERE VACTID = ?";
	private static final String FIND_BY_SITEID = "SELECT * FROM VENDORACT WHERE SITEID = ? AND (? BETWEEN YEAR(RTLSTART) AND YEAR(RTLEND) OR ? BETWEEN MONTH(RTLSTART) AND MONTH(RTLEND)) AND PROGRESS NOT IN (99, 100)";
	private static final String FIND_BY_VENDORID = "SELECT * FROM VENDORACT WHERE VENDORID = ? ORDER BY VACTID DESC";
	private static final String FIND_SALE_BEFORE = "SELECT * FROM VENDORACT WHERE VENDORID = ? AND PROGRESS = 3 AND NOW() < TKSTART ORDER BY VACTID DESC";
	private static final String FIND_SALE_AFTER = "SELECT * FROM VENDORACT WHERE VENDORID = ? AND PROGRESS = 3 AND NOW() > TKEND ORDER BY VACTID DESC";
	private static final String FIND_ONSALE = "SELECT * FROM VENDORACT WHERE VENDORID = ? AND PROGRESS = 3 AND NOW() BETWEEN TKSTART AND TKEND ORDER BY VACTID DESC";
	private static final String FIND_LATEST_ACT = "SELECT * FROM VENDORACT WHERE VENDORID = ? ORDER BY VACTID DESC LIMIT 1";
	private static final String FIND_CANCELED_ORDER = "SELECT * FROM VENDORACT WHERE VENDORID = ? AND PROGRESS = 99 ORDER BY VACTID DESC";
	private static final String FIND_ONSALE_ALL = "SELECT * FROM VENDORACT WHERE PROGRESS = 3 AND NOW() BETWEEN TKSTART AND TKEND ORDER BY VACTID DESC";
	private static final String UPDATE_PROGRESS = "UPDATE VENDORACT SET PROGRESS = ? WHERE VACTID = ?";
	private static final String CHECK_PROGRESS = "SELECT PROGRESS FROM VENDORACT WHERE VACTID = ?"; 

	private static final String FIND_BY_NO_CHECK = "SELECT COUNT(*) AS TOTAL FROM VENDORACT WHERE PROGRESS = 0";

	private static final String ADD_NOTE = "UPDATE VENDORACT SET NOTE = ? WHERE VACTID = ?";
	
	private static final String FIND_BY_NEW = "SELECT * FROM VENDORACT WHERE PROGRESS = 3 AND NOW() BETWEEN TKSTART AND TKEND ORDER BY VACTID DESC LIMIT 2";

	
	@Override
	public Integer add(VendorActVO vact) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer vactid = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, vact.getName());
			pstmt.setInt(2, vact.getVendorid());
			pstmt.setInt(3, vact.getAmount());
			pstmt.setInt(4, vact.getSiteid());
			pstmt.setInt(5, vact.getActid());
			pstmt.setDate(6, vact.getRtlstart());
			pstmt.setDate(7, vact.getRtlend());
			pstmt.setDate(8, vact.getActstart());
			pstmt.setDate(9, vact.getActend());
			pstmt.setDate(10, vact.getTkstart());
			pstmt.setDate(11, vact.getTkend());
			pstmt.setInt(12, vact.getPrice());
			pstmt.setString(13, vact.getActcnt());
			pstmt.setBytes(14, vact.getImg());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				vactid = rs.getInt(1);
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
		return vactid;
	}

	@Override
	public void update(VendorActVO vact) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, vact.getName());
			pstmt.setInt(2, vact.getVendorid());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4, vact.getAmount());
			pstmt.setInt(5, vact.getSiteid());
			pstmt.setInt(6, vact.getActid());
			pstmt.setString(7, vact.getProgress());
			pstmt.setDate(8, vact.getRtlstart());
			pstmt.setDate(9, vact.getRtlend());
			pstmt.setDate(10, vact.getActstart());
			pstmt.setDate(11, vact.getActend());
			pstmt.setDate(12, vact.getTkstart());
			pstmt.setDate(13, vact.getTkend());
			pstmt.setInt(14, vact.getPrice());
			pstmt.setString(15, vact.getActcnt());
			pstmt.setBytes(16, vact.getImg());
			pstmt.setInt(17, vact.getVactid());
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
	public void delete(Integer vactid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, vactid);
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
	public VendorActVO findByPK(Integer vactid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		VendorActVO vact = new VendorActVO();
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, vactid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));
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
		return vact;
	}

	@Override
	public List<VendorActVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));
				vactlist.add(vact);
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
		return vactlist;
	}

	@Override
	public void updateImg(Integer vactid, String path) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_IMG);
			
			pstmt.setBytes(1, getPictureByteArray(path));
			pstmt.setInt(2, vactid);

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
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		return buffer;
	}

	@Override
	public List<VendorActVO> findOccupied(Integer siteid, Integer year, Integer month) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SITEID);
			pstmt.setInt(1, siteid);
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vactlist.add(vact);
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
		return vactlist;
	}

	@Override
	public List<VendorActVO> findByVendorid(Integer vendorid) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_VENDORID);
			pstmt.setInt(1, vendorid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));
				vactlist.add(vact);
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
		return vactlist;
	}

	@Override
	public List<VendorActVO> findByTkDate(Integer vendorid, String tkstatus) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		
		if(tkstatus.equals("待上架")) {
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(FIND_SALE_BEFORE);
				pstmt.setInt(1, vendorid);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vact = new VendorActVO();
					vact.setVactid(rs.getInt("VACTID"));
					vact.setName(rs.getString("NAME"));
					vact.setVendorid(rs.getInt("VENDORID"));
					vact.setDate(rs.getDate("DATE"));
					vact.setAmount(rs.getInt("AMOUNT"));
					vact.setSiteid(rs.getInt("SITEID"));
					vact.setActid(rs.getInt("ACTID"));
					vact.setProgress(rs.getString("PROGRESS"));
					vact.setRtlstart(rs.getDate("RTLSTART"));
					vact.setRtlend(rs.getDate("RTLEND"));
					vact.setActstart(rs.getDate("ACTSTART"));
					vact.setActend(rs.getDate("ACTEND"));
					vact.setTkstart(rs.getDate("TKSTART"));
					vact.setTkend(rs.getDate("TKEND"));
					vact.setPrice(rs.getInt("PRICE"));
					vact.setActcnt(rs.getString("ACTCNT"));
					vact.setImg(rs.getBytes("IMG"));
					vact.setNote(rs.getString("NOTE"));
					vactlist.add(vact);
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
		} else if(tkstatus.equals("售票中")) {
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(FIND_ONSALE);
				pstmt.setInt(1, vendorid);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vact = new VendorActVO();
					vact.setVactid(rs.getInt("VACTID"));
					vact.setName(rs.getString("NAME"));
					vact.setVendorid(rs.getInt("VENDORID"));
					vact.setDate(rs.getDate("DATE"));
					vact.setAmount(rs.getInt("AMOUNT"));
					vact.setSiteid(rs.getInt("SITEID"));
					vact.setActid(rs.getInt("ACTID"));
					vact.setProgress(rs.getString("PROGRESS"));
					vact.setRtlstart(rs.getDate("RTLSTART"));
					vact.setRtlend(rs.getDate("RTLEND"));
					vact.setActstart(rs.getDate("ACTSTART"));
					vact.setActend(rs.getDate("ACTEND"));
					vact.setTkstart(rs.getDate("TKSTART"));
					vact.setTkend(rs.getDate("TKEND"));
					vact.setPrice(rs.getInt("PRICE"));
					vact.setActcnt(rs.getString("ACTCNT"));
					vact.setImg(rs.getBytes("IMG"));
					vact.setNote(rs.getString("NOTE"));
					vactlist.add(vact);
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
		} else if(tkstatus.equals("已結束")) {
			try {
				con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
				pstmt = con.prepareStatement(FIND_SALE_AFTER);
				pstmt.setInt(1, vendorid);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vact = new VendorActVO();
					vact.setVactid(rs.getInt("VACTID"));
					vact.setName(rs.getString("NAME"));
					vact.setVendorid(rs.getInt("VENDORID"));
					vact.setDate(rs.getDate("DATE"));
					vact.setAmount(rs.getInt("AMOUNT"));
					vact.setSiteid(rs.getInt("SITEID"));
					vact.setActid(rs.getInt("ACTID"));
					vact.setProgress(rs.getString("PROGRESS"));
					vact.setRtlstart(rs.getDate("RTLSTART"));
					vact.setRtlend(rs.getDate("RTLEND"));
					vact.setActstart(rs.getDate("ACTSTART"));
					vact.setActend(rs.getDate("ACTEND"));
					vact.setTkstart(rs.getDate("TKSTART"));
					vact.setTkend(rs.getDate("TKEND"));
					vact.setPrice(rs.getInt("PRICE"));
					vact.setActcnt(rs.getString("ACTCNT"));
					vact.setImg(rs.getBytes("IMG"));
					vact.setNote(rs.getString("NOTE"));
					vactlist.add(vact);
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
		}
		return vactlist;
	}

	@Override
	public VendorActVO findLatestActivity(Integer vendorid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		VendorActVO vact = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_LATEST_ACT);
			pstmt.setInt(1, vendorid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));

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
	
		return vact;
	}

	@Override
	public List<VendorActVO> findCanceledOrder(Integer vendorid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_CANCELED_ORDER);
			pstmt.setInt(1, vendorid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));

				vactlist.add(vact);
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
	
		return vactlist;
	}

	@Override
	public List<VendorActVO> findByAllOnSale() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_ONSALE_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));

				vactlist.add(vact);
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
		return vactlist;
	}

	@Override
	public void updateProgress(Integer vactid, Integer progress) {
		Connection con = null;
		PreparedStatement pstmt = null;
			
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_PROGRESS);
			pstmt.setInt(1, progress);
			pstmt.setInt(2, vactid);
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
	public String checkProgress(Integer vactid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String progress = "";
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(CHECK_PROGRESS);
			pstmt.setInt(1, vactid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				progress = rs.getString("PROGRESS");
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
		return progress;
	}

	@Override
	public Integer findNoCheck() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer result = 0;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NO_CHECK);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("TOTAL");
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
		return result;
	}

	public void addNote(Integer vactid, String note) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(ADD_NOTE);
			pstmt.setString(1, note);
			pstmt.setInt(2, vactid);
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
	public List<VendorActVO> findByNew() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<VendorActVO> vactlist = new ArrayList<>();
		VendorActVO vact = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NEW);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vact = new VendorActVO();
				vact.setVactid(rs.getInt("VACTID"));
				vact.setName(rs.getString("NAME"));
				vact.setVendorid(rs.getInt("VENDORID"));
				vact.setDate(rs.getDate("DATE"));
				vact.setAmount(rs.getInt("AMOUNT"));
				vact.setSiteid(rs.getInt("SITEID"));
				vact.setActid(rs.getInt("ACTID"));
				vact.setProgress(rs.getString("PROGRESS"));
				vact.setRtlstart(rs.getDate("RTLSTART"));
				vact.setRtlend(rs.getDate("RTLEND"));
				vact.setActstart(rs.getDate("ACTSTART"));
				vact.setActend(rs.getDate("ACTEND"));
				vact.setTkstart(rs.getDate("TKSTART"));
				vact.setTkend(rs.getDate("TKEND"));
				vact.setPrice(rs.getInt("PRICE"));
				vact.setActcnt(rs.getString("ACTCNT"));
				vact.setImg(rs.getBytes("IMG"));
				vact.setNote(rs.getString("NOTE"));

				vactlist.add(vact);
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
		return vactlist;
	}
}