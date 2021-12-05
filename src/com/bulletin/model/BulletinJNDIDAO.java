package com.bulletin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BulletinJNDIDAO implements BulletinDAO_interface{
	private static final String INSERT_STMT = "INSERT INTO BULLETIN(memberId, buContent, buDate) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE BULLETIN SET memberId = ?, buContent = ?, buDate = ? "
												+ "WHERE bulletinId = ?";
	private static final String DELETE_STMT = "DELETE FROM BULLETIN WHERE bulletinId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM BULLETIN WHERE bulletinId = ?";
	private static final String GET_ALL = "SELECT * FROM BULLETIN";
	private static final String test = "select bucontent from bulletin b join member m on b.memberid = m.memberid";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void add(BulletinVO bulletin) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	
	public static void main(String[] args) {
		BulletinJNDIDAO dao = new BulletinJNDIDAO();
		List<String> test = dao.test();
		for (String t : test) {
			System.out.println(t);
		}
	}
	
//	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
////		cal.set(2020, cal.SEPTEMBER, 5); // 月份需減一, 帶8為9月
////		Date date = new Date(cal.getTimeInMillis()); // 以設定的日期轉為毫秒參數
////		System.out.println(date); // 2020-09-05
////		System.out.println(new Date(cal.getTimeInMillis()));
//		
//		BulletinDAO dao = new BulletinDAOImpl();
//		
//		// 新增
//		BulletinVO bul1 = new BulletinVO();
//		bul1.setMemberId(1);
//		bul1.setBulletinContent("PETER個人展即將結束!!!");
//		cal.set(2021, cal.AUGUST, 5);
//		bul1.setBulletinDate(new Date(cal.getTimeInMillis()));
//		dao.add(bul1);
//		
//		// 修改
//		BulletinVO bul2 = new BulletinVO();
//		bul2.setBulletinId(1);
//		bul2.setMemberId(1);
//		bul2.setBulletinContent("PETER個人展已經結束, 下次請早!!!");
//		cal.set(2021, cal.SEPTEMBER, 5);
//		bul2.setBulletinDate(new Date(cal.getTimeInMillis()));
//		dao.update(bul2);
//		
//		// 刪除
//		dao.delete(6);
//		
//		// 單筆查詢
//		BulletinVO bul3 = dao.findByBulletinId(1);
//		out.println("公告編號 : " + bul3.getBulletinId());
//		out.println("管理員編號 : " + bul3.getMemberId());
//		out.println("公告內容 : " + bul3.getBulletinContent());
//		out.println("公告日期 : " + bul3.getBulletinDate());
//		
//		// 全部查詢
//		List<BulletinVO> bulList = dao.getAll();
//		for (BulletinVO bul4 : bulList) {
//			out.println("公告編號 : " + bul4.getBulletinId());
//			out.println("管理員編號 : " + bul4.getMemberId());
//			out.println("公告內容 : " + bul4.getBulletinContent());
//			out.println("公告日期 : " + bul4.getBulletinDate());
//		}
//		
//	}

}
