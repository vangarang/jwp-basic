package next;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.db.DataBase;
import next.exception.PasswordMismatchException;
import next.exception.UserNotFoundException;
import next.model.User;

public class UserTest {
	public static User TEST_USER = new User("userId","pw","choi","choi@n.c");
	
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("pw"));
	}
	
	@Test
	public void login() throws Exception{
		User user = TEST_USER;
		DataBase.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExist() throws Exception{
		User.login("hello", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception{
		DataBase.addUser(UserTest.TEST_USER);
		User.login(TEST_USER.getUserId(), "misPw");
	}
}
