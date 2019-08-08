package next.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDAO {
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/jsp_dev?serverTimezone=UTC";
		String id = "testuser";
		String pw = "1111";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			return DriverManager.getConnection(url,id,pw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
    public void addUser(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            
            pstmt = getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();
        } finally {
        	if(pstmt != null) {
        		pstmt.close();
        	}
        	if(con != null) {
        		con.close();
        	}
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void removeUser(String userId) throws SQLException {
    	String sql = "delete from USERS where userId = ?";
    	PreparedStatement pstmt = getConnection().prepareStatement(sql);
    	pstmt.setString(1, userId);
    	
    	pstmt.executeUpdate();
    }
    
    public void updateUser(User user) throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
            
            pstmt = getConnection().prepareStatement(sql);
            
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUserId());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}
