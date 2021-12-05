package com.event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.partiinf.model.PartiinfService;

import util.Util;

public class EventDAO implements EventDAO_interface {
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EVENTINF (eventCateID, memberID, capacity, eventName, eventDescription, eventStart, eventEnd, eventappStart, eventappEnd, eventStatus) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE EVENTINF SET eventCateID = ?, memberID = ?, capacity = ?, eventName = ?, eventDescription = ?, eventStart = ?, eventEnd = ?, eventappStart = ?, eventappEnd = ?, eventStatus = ? WHERE eventID = ? ";
	private static final String DELETE_STMT = "DELETE FROM EVENTINF WHERE eventID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM EVENTINF WHERE eventID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM EVENTINF WHERE eventappend >= NOW()";
	private static final String GET_ALL_BY_EVTCID = "SELECT * FROM EVENTINF where eventCateID = ?";
	private static final String GET_BY_MEMBER = "SELECT * FROM EVENTINF WHERE MEMBERID = ? ORDER BY EVENTSTART ASC";
	private static final String GET_ALL_ACTIVE_DESC = "SELECT * FROM EVENTINF WHERE eventappend >= NOW() ORDER BY EVENTEND DESC";
	private static final String GET_ALL_ACTIVE_ASC = "SELECT * FROM EVENTINF WHERE eventappend >= NOW() ORDER BY EVENTEND ASC";

	
	@Override
	public void add(Event event) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, event.getEventcateid());
			pstmt.setInt(2, event.getMemberid());
			pstmt.setInt(3, event.getCapacity());
			pstmt.setString(4, event.getEventname ());
			pstmt.setString(5, event.getEventdescription());
			pstmt.setTimestamp(6, event.getEventstart());
			pstmt.setTimestamp(7, event.getEventend());
			pstmt.setTimestamp(8, event.getEventappstart());
			pstmt.setTimestamp(9, event.getEventappend());
			pstmt.setInt(10, event.getEventstatus());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Event event) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, event.getEventcateid());
			pstmt.setInt(2, event.getMemberid());
			pstmt.setInt(3, event.getCapacity());
			pstmt.setString(4, event.getEventname());
			pstmt.setString(5, event.getEventdescription());
			pstmt.setTimestamp(6, event.getEventstart());
			pstmt.setTimestamp(7, event.getEventend());
			pstmt.setTimestamp(8, event.getEventappstart());
			pstmt.setTimestamp(9, event.getEventappend());
			pstmt.setInt(10, event.getEventstatus());
			pstmt.setInt(11, event.getEventid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(Integer eventid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, eventid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Event findByPK(Integer eventid) {

		Event event = null;
		event = new Event();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, eventid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));				

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return event;
	}

	@Override
	public List<Event> getAllByCate(Integer eventcateid) {
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BY_EVTCID);
			
			pstmt.setInt(1, eventcateid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));		
				list.add(event); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	

	@Override
	public List<Event> getAll() {
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// event �]�٬� Domain objects
				event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));		
				list.add(event); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	@Override
	public List<Event> getAllActiveDesc() {
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_ACTIVE_DESC);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// event �]�٬� Domain objects
				event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));		
				list.add(event); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	@Override
	public List<Event> getAllActiveAsc() {
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_ACTIVE_ASC);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// event �]�٬� Domain objects
				event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));		
				list.add(event); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	@Override
	public List<Event> findByNew() {
		PartiinfService partSvc = new PartiinfService();
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_ACTIVE_ASC);
			rs = pstmt.executeQuery();
			int count = 0;

			while (rs.next()) {
				// event �]�٬� Domain objects
				event = new Event();
				int eventid = rs.getInt("eventid");
				event.setEventid(eventid);
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				int capacity = rs.getInt("capacity");
				event.setCapacity(capacity);
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));
				if (partSvc.findByJoin(eventid) < capacity ) {
					list.add(event); // Store the row in the list
					count++;
				}
				if (count == 2) {
					break;
				}
			} 

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	@Override
	public List<Event> findByMember(Integer memberid) {
		List<Event> list = new ArrayList<Event>();
		Event event = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_BY_MEMBER);
			
			pstmt.setInt(1, memberid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				event = new Event();
				event.setEventid(rs.getInt("eventid"));
				event.setEventcateid(rs.getInt("eventcateid"));
				event.setMemberid(rs.getInt("memberid"));
				event.setCapacity(rs.getInt("capacity"));
				event.setEventname(rs.getString("eventname"));
				event.setEventdescription(rs.getString("eventdescription"));
				event.setEventstart(rs.getTimestamp("eventstart"));
				event.setEventend(rs.getTimestamp("eventend"));
				event.setEventappstart(rs.getTimestamp("eventappstart"));
				event.setEventappend(rs.getTimestamp("eventappend"));
				event.setEventstatus(rs.getInt("eventstatus"));		
				list.add(event); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}


}