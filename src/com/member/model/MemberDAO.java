package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Util;


public class MemberDAO implements MemberDAO_interface {
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
	
	private static final String INSERT_STMT = "INSERT INTO MEMBER(email, name, birthday, phoneNumber, address, ID, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE MEMBER SET  email = ?, name = ?, birthday = ?, phoneNumber = ?, address = ?, ID = ?, password = ?, status = ? WHERE number = ?";
	private static final String DELETE_STMT = "DELETE FROM MEMBER WHERE number = ?";
	private static final String FIND_BY_PK = "SELECT * FROM MEMBER WHERE number = ?";
	private static final String FIND_BY_EMAIL = "SELECT * FROM MEMBER WHERE EMAIL = ?";
	private static final String GET_ALL = "SELECT * FROM MEMBER";
	private static final String login="SELECT * FROM MEMBER WHERE EMAIL=? AND password= ?";
	private static final String register = "INSERT INTO MEMBER(email, name, birthday, phoneNumber, address, ID, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE= "UPDATE MEMBER SET  email = ?, name = ?,  phoneNumber = ?, address = ?, ID = ?, password = ? WHERE number = ?";

	@Override
	public void add(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

	
			pstmt.setString(1, memberVO.getEmail());
			pstmt.setString(2, memberVO.getName());
			pstmt.setDate(3, memberVO.getBirthday());
			pstmt.setString(4, memberVO.getPhoneNumber());
			pstmt.setString(5, memberVO.getAddress());
			pstmt.setString(6, memberVO.getID());
			pstmt.setString(7, memberVO.getPassword());
			pstmt.setString(8, memberVO.getStatus());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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

	
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setString(1, memberVO.getEmail());
			pstmt.setString(2, memberVO.getName());
			pstmt.setDate(3, memberVO.getBirthday());
			pstmt.setString(4, memberVO.getPhoneNumber());
			pstmt.setString(5, memberVO.getAddress());
			pstmt.setString(6, memberVO.getID());
			pstmt.setString(7, memberVO.getPassword());
			pstmt.setString(8, memberVO.getStatus());
			pstmt.setInt(9, memberVO.getNumber());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer number) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, number);
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MemberVO findByEmail(String email) {
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mem = new MemberVO();			
				mem.setEmail(rs.getString("email"));
				mem.setNumber(rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
				} 
			catch (SQLException e) {		
									}
		}
		return mem;
	}
				

	
	@Override
	public MemberVO findByPK(Integer number) {
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mem = new MemberVO();
				mem.setNumber(rs.getInt("number"));
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setBirthday(rs.getDate("birthday"));
				mem.setPhoneNumber(rs.getString("phoneNumber"));
				mem.setAddress(rs.getString("address"));
				mem.setID(rs.getString("ID"));
				mem.setPassword(rs.getString("password"));
				mem.setStatus(rs.getString("status"));
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

		return mem;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> memList = new ArrayList<>();
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mem = new MemberVO();
				mem.setNumber(rs.getInt("number"));
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setBirthday(rs.getDate("birthday"));
				mem.setPhoneNumber(rs.getString("phoneNumber"));
				mem.setAddress(rs.getString("address"));
				mem.setID(rs.getString("ID"));
				mem.setPassword(rs.getString("password"));
				mem.setStatus(rs.getString("status"));
				memList.add(mem);
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
		return memList;
	}
	
	public void register(MemberVO memberVO) { 
		Connection con = null;
		PreparedStatement pstmt = null;
	
	try {

		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(register);


		pstmt.setString(1, memberVO.getEmail());
		pstmt.setString(2, memberVO.getName());
		pstmt.setString(3, null);
		pstmt.setString(4, memberVO.getPhoneNumber());
		pstmt.setString(5, memberVO.getAddress());
		pstmt.setString(6, memberVO.getID());
		pstmt.setString(7, memberVO.getPassword());
		pstmt.setString(8, memberVO.getStatus());
		
		pstmt.executeUpdate();

		// Handle any driver errors
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
	public MemberVO login(String email, String password) {
		MemberVO mem = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(login);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				mem = new MemberVO();
				mem.setNumber(rs.getInt("number"));
				mem.setEmail(rs.getString("email"));
				mem.setName(rs.getString("name"));
				mem.setBirthday(rs.getDate("birthday"));
				mem.setPhoneNumber(rs.getString("phoneNumber"));
				mem.setAddress(rs.getString("address"));
				mem.setID(rs.getString("ID"));
				mem.setPassword(rs.getString("password"));
				mem.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mem;
	}
	
	@Override
	public void updateMember2(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, memberVO.getEmail());
			pstmt.setString(2, memberVO.getName());
			
			pstmt.setString(3, memberVO.getPhoneNumber());
			pstmt.setString(4, memberVO.getAddress());
			pstmt.setString(5, memberVO.getID());
			pstmt.setString(6, memberVO.getPassword());
			
			pstmt.setInt(7, memberVO.getNumber());
			pstmt.executeUpdate();

			// Handle any driver errors
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




}