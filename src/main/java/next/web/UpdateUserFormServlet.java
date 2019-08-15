package next.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.dao.UserDAO;
import next.model.User;
import next.utils.MyValidatorFactory;

@WebServlet("/users/updateForm")
public class UpdateUserFormServlet extends HttpServlet{
	private Logger Log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(userId == null) {
			resp.sendRedirect("/");
			return;
		}
		
		System.out.println("User Id :"+userId);
		
		UserDAO userDAO = new UserDAO();
		User user;
		try {
			user = userDAO.findByUserId(userId);
			req.setAttribute("isUpdate", true);
			req.setAttribute("user", user);
			RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
			rd.forward(req,resp);
				
		} catch (SQLException e) {
			
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("user/list");
	}
}
