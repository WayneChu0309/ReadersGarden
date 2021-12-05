package com.stockType.model;

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

public class StockTypeJNDIDAO implements StockTypeDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO STOCK_TYPE (typeName, kind) VALUES (?,?)";
	private static final String UPDATE_STMT = "UPDATE STOCK_TYPE SET typeName = ?, kind = ? WHERE typeId = ?";
	private static final String DELETE_STMT = "DELETE FROM STOCK_TYPE WHERE typeId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM STOCK_TYPE WHERE typeId = ?";
	private static final String GET_ALL = "SELECT * FROM STOCK_TYPE";
	private static final String GET_BOOK_TYPE = "SELECT * FROM STOCK_TYPE WHERE kind = '書籍'";
	private static final String GET_MOVIE_TYPE = "SELECT * FROM STOCK_TYPE WHERE kind = '電影'";
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
	public void add(StockTypeVO stockType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, stockType.getTypeName());
			pstmt.setString(2, stockType.getKind());
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
	public void update(StockTypeVO stockType) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, stockType.getTypeName());
			pstmt.setString(2, stockType.getKind());
			pstmt.setInt(3, stockType.getTypeId());
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
	public void delete(Integer typeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, typeId);
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
	public StockTypeVO findByTypeId(Integer typeId) {
		StockTypeVO type = null; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, typeId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
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
		return type;
	}

	@Override
	public List<StockTypeVO> getAll() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}

	@Override
	public List<StockTypeVO> getBook() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BOOK_TYPE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}

	@Override
	public List<StockTypeVO> getMovie() {
		List<StockTypeVO> typeList = new ArrayList<>(); 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MOVIE_TYPE);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				StockTypeVO type = new StockTypeVO();
				type.setTypeId(rs.getInt("typeId"));
				type.setTypeName(rs.getString("typeName"));
				type.setKind(rs.getString("kind"));
				typeList.add(type);
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
		return typeList;
	}
}
