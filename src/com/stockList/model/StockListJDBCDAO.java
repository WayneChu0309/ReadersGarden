package com.stockList.model;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Util;

public class StockListJDBCDAO implements StockListDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO STOCK_LIST (stockId, listStates) VALUES (?, ?)";
	private static final String UPDATE_STMT = "UPDATE STOCK_LIST SET stockId = ?, listStates = ? WHERE listId = ?";
	private static final String DELETE_STMT = "DELETE FROM STOCK_LIST WHERE listId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM STOCK_LIST WHERE listId = ?";
	private static final String GET_ALL = "SELECT * FROM STOCK_LIST";
	private static final String GET_AVAIL = "SELECT COUNT(*) AS COUNT FROM STOCK_LIST WHERE stockId = ? AND (liststates = 1)";
	// book [人文史地, 商業理財, 心理勵志, 文學小說, 生活風格, 社會科學, 自然科普, 藝術設計, 醫療保健, ]
	// movie [劇情, 動作冒險, 奇幻&科幻, 幽默喜劇, 浪漫愛情, 犯罪&推理, 親子家庭, 驚悚&恐佈]
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(StockListVO stockList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, stockList.getStockId());
			pstmt.setInt(2, stockList.getListStates());
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
	public void update(StockListVO stockList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, stockList.getStockId());
			pstmt.setInt(2, stockList.getListStates());
			pstmt.setInt(3, stockList.getListId());
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
	public void delete(Integer listId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, listId);
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
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
	public StockListVO findByListId(Integer listId) {
		StockListVO list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setInt(1, listId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list = new StockListVO();
				list.setListId(rs.getInt("listId"));
				list.setStockId(rs.getInt("stockId"));
				list.setListStates(rs.getInt("listStates"));
			}
			
		}catch (SQLException se) {
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
		return list;
	}

	@Override
	public List<StockListVO> getAll() {
		List<StockListVO> reList = new ArrayList<>();
		StockListVO list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list = new StockListVO();
				list.setListId(rs.getInt("listId"));
				list.setStockId(rs.getInt("stockId"));
				list.setListStates(rs.getInt("listStates"));
				reList.add(list);
			}
			
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
		return reList;
	}

	@Override
	public Integer availStock(Integer stockId) {
		Integer avail = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_AVAIL);
			pstmt.setInt(1, stockId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				avail = rs.getInt("count");
			} 
		
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
		return avail;
	}
	
//	public static void main(String[] args) {
//		StockListDAO dao = new StockListDAOImpl();
		
		// 新增
//		StockListVO st1 = new StockListVO();
//		st1.setStockId(1);
//		st1.setListStates(1);
//		dao.add(st1);
		
		// 修改
//		StockListVO st2 = new StockListVO();
//		st2.setListId(1);
//		st2.setStockId(1);
//		st2.setListStates(1);
//		dao.update(st2);
		
		// 刪除
//		dao.delete(10);
		
		// 單筆查詢
//		StockListVO st3 = dao.findByTypeId(1);
//		out.println("listId : " + st3.getListId());
//		out.println("stockId : " + st3.getStockId());
//		out.println("listStates" + st3.getListStates());
		
		// 全部查詢
//		List<StockListVO> stList = dao.getAll();
//		for (StockListVO st4 : stList) {
//			out.println("listId : " + st4.getListId());
//			out.println("stockId : " + st4.getStockId());
//			out.println("listStates" + st4.getListStates());
//		}
		
//	}

}
