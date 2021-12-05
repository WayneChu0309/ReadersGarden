package com.libraryMap.model;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryMapJNDIDAO implements LibraryMapDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO LIBRARY_MAP (libId, floor, floorImg) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE LIBRARY_MAP SET libId = ?, floor = ?, floorImg = ? WHERE mapId = ?";
	private static final String DELETE_STMT = "DELETE FROM LIBRARY_MAP WHERE mapId = ?";
	private static final String FIND_BY_PK = "SELECT * FROM LIBRARY_MAP WHERE mapId = ?";
	private static final String GET_ALL = "SELECT * FROM LIBRARY_MAP";
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
	public void add(LibraryMapVO libraryMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, libraryMap.getLibraryId());
			pstmt.setString(2, libraryMap.getFloor());
			pstmt.setBytes(3, libraryMap.getFloorImg());
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
	public void update(LibraryMapVO libraryMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, libraryMap.getLibraryId());
			pstmt.setString(2, libraryMap.getFloor());
			pstmt.setBytes(3, libraryMap.getFloorImg());
			pstmt.setInt(4, libraryMap.getMapId());
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
	public void delete(Integer mapId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, mapId);
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
	public LibraryMapVO findByMapId(Integer mapId) {
		LibraryMapVO map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, mapId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				map = new LibraryMapVO();
				map.setMapId(rs.getInt("mapId"));
				map.setLibraryId(rs.getInt("libId"));
				map.setFloor(rs.getString("floor"));
				map.setFloorImg(rs.getBytes("floorImg"));
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
		return map;
	}

	@Override
	public List<LibraryMapVO> getAll() {
		List<LibraryMapVO> mapList = new ArrayList<>();
		LibraryMapVO map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				map = new LibraryMapVO();
				map.setMapId(rs.getInt("mapId"));
				map.setLibraryId(rs.getInt("libId"));
				map.setFloor(rs.getString("floor"));
				map.setFloorImg(rs.getBytes("floorImg"));
				mapList.add(map);
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
		return mapList;
	}
}
