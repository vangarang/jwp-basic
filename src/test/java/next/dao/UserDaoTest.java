package next.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Before;
import org.junit.Test;

import next.UserTest;
import next.model.User;

public class UserDaoTest {
	private UserDAO userDAO;
	private Connection con;

	@Before
	public void setup() {
		userDAO = new UserDAO();
		//userDAO.removeUser(..getUserId());
	}
	
	@Test
	public void selectByUserId() throws Exception{
		User user = userDAO.findByUserId("javajigi");
		assertNotNull(user); 
	}
	
    @Test
    public void crud() throws Exception {
        User expected = new User("userId", "password", "name", "javajigi@email.com");
        userDAO = new UserDAO();
        userDAO.removeUser(expected.getUserId());
        userDAO.addUser(expected);

        User dbUser = userDAO.findByUserId(expected.getUserId());
        assertEquals(expected, dbUser);
        
        User updateUser = new User(expected.getUserId(), "uPassword", "update name", "update@email.com");
        userDAO.updateUser(updateUser);
        dbUser = userDAO.findByUserId(expected.getUserId());
        assertEquals(updateUser, dbUser);
    }

}
