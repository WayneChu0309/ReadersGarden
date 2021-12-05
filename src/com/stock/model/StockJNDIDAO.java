package com.stock.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.Util;

public class StockJNDIDAO implements StockDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO STOCK (typeId, stockName, author, press, issuedDate, "
			+ "stockContent, stockScore, stockImg) " + "VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE STOCK SET typeId = ?, stockName = ?, author = ?, press = ?, "
			+ "issuedDate = ?, stockContent = ?, stockScore = ?, stockImg = ? WHERE stockId = ?";
	private static final String DELETE_STMT = "DELETE FROM STOCK WHERE stockId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM STOCK WHERE stockId = ?";
	private static final String FIND_BY_FK = "SELECT count(*) as total FROM STOCK WHERE typeId = ?";
	private static final String GET_ALL = "SELECT * FROM STOCK WHERE typeId = ? ORDER BY stockId DESC";
	private static final String GET_KEYWORD = "SELECT * FROM STOCK WHERE stockName like ?";
	private static final String GET_PAGE = "SELECT * FROM STOCK WHERE typeId = ? ORDER BY stockId DESC limit ?, 30";
	private static final String GET_LAST = "SELECT COUNT(sl.stockId) as stockLast FROM STOCK s join STOCK_LIST sl "
			+ "where s.stockId = ? and sl.stockId = ?";

	// book [人文史地, 商業理財, 心理勵志, 文學小說, 生活風格, 社會科學, 自然科普, 藝術設計, 醫療保健, ]
	// movie [劇情, 動作冒險, 奇幻&科幻, 幽默喜劇, 浪漫愛情, 犯罪&推理, 親子家庭, 驚悚&恐佈]
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
	public Integer add(StockVO stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer stockId = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, stock.getTypeId());
			pstmt.setString(2, stock.getStockName());
			pstmt.setString(3, stock.getAuthor());
			pstmt.setString(4, stock.getPress());
			pstmt.setDate(5, stock.getIssuedDate());
			pstmt.setString(6, stock.getStockContent());
			pstmt.setDouble(7, stock.getStockScore());
			pstmt.setBytes(8, stock.getStockImg());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				stockId = rs.getInt(1);
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
		return stockId;
	}

	@Override
	public void update(StockVO stock) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, stock.getTypeId());
			pstmt.setString(2, stock.getStockName());
			pstmt.setString(3, stock.getAuthor());
			pstmt.setString(4, stock.getPress());
			pstmt.setDate(5, stock.getIssuedDate());
			pstmt.setString(6, stock.getStockContent());
			pstmt.setDouble(7, stock.getStockScore());
			pstmt.setBytes(8, stock.getStockImg());
			pstmt.setInt(9, stock.getStockId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
	public void delete(Integer stockId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, stockId);
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
	public StockVO findByStockId(Integer stockId) {
		StockVO stock = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, stockId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				stock = new StockVO();
				stock.setStockId(rs.getInt("stockId"));
				stock.setTypeId(rs.getInt("typeId"));
				stock.setStockName(rs.getString("stockName"));
				stock.setAuthor(rs.getString("author"));
				stock.setPress(rs.getString("press"));
				stock.setIssuedDate(rs.getDate("issuedDate"));
				stock.setStockContent(rs.getString("stockContent"));
				stock.setStockScore(rs.getDouble("stockScore"));
				stock.setStockImg(rs.getBytes("stockImg"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
		return stock;
	}

	@Override
	public List<StockVO> getAll(Integer typeId) {
		List<StockVO> reList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StockVO stock = new StockVO();
				stock.setStockId(rs.getInt("stockId"));
				stock.setTypeId(rs.getInt("typeId"));
				stock.setStockName(rs.getString("stockName"));
				stock.setAuthor(rs.getString("author"));
				stock.setPress(rs.getString("press"));
				stock.setIssuedDate(rs.getDate("issuedDate"));
				stock.setStockContent(rs.getString("stockContent"));
				stock.setStockScore(rs.getDouble("stockScore"));
				reList.add(stock);
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
		return reList;
	}

	@Override
	public List<StockVO> getPage(Integer typeId, Integer pageNumber) {
		List<StockVO> reList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PAGE);
			pstmt.setInt(1, typeId);
			pstmt.setInt(2, (pageNumber - 1) * 50);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StockVO stock = new StockVO();
				stock.setStockId(rs.getInt("stockId"));
				stock.setTypeId(rs.getInt("typeId"));
				stock.setStockName(rs.getString("stockName"));
				stock.setAuthor(rs.getString("author"));
				stock.setPress(rs.getString("press"));
				stock.setIssuedDate(rs.getDate("issuedDate"));
				stock.setStockContent(rs.getString("stockContent"));
				stock.setStockScore(rs.getDouble("stockScore"));
				stock.setStockImg(rs.getBytes("stockImg"));
				reList.add(stock);
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
		return reList;
	}

	@Override
	public List<StockVO> getPageInf(Integer typeId, Integer pageNumber) {
		List<StockVO> reList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PAGE);
			pstmt.setInt(1, typeId);
			pstmt.setInt(2, (pageNumber - 1) * 50);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StockVO stock = new StockVO();
				stock.setStockId(rs.getInt("stockId"));
				stock.setStockName(rs.getString("stockName"));
				stock.setAuthor(rs.getString("author"));
				stock.setPress(rs.getString("press"));
				stock.setIssuedDate(rs.getDate("issuedDate"));
				stock.setStockContent(rs.getString("stockContent"));
				reList.add(stock);
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
		return reList;
	}

	@Override
	public Integer getLast(Integer stockId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer last = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LAST);
			pstmt.setInt(1, stockId);
			pstmt.setInt(2, stockId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				last = rs.getInt("stockLast");
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
		return last;
	}

	@Override
	public Integer findByTypeId(Integer typeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer total = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_FK);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt("total");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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
		return total;
	}

	@Override
	public List<StockVO> getKeyword(String keyword) {
		List<StockVO> keywordList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_KEYWORD);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StockVO stock = new StockVO();
				stock.setStockId(rs.getInt("stockId"));
				stock.setStockName(rs.getString("stockName"));
				stock.setAuthor(rs.getString("author"));
				stock.setPress(rs.getString("press"));
				stock.setIssuedDate(rs.getDate("issuedDate"));
				stock.setStockContent(rs.getString("stockContent"));
				keywordList.add(stock);
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
		return keywordList;
	}
}
