package next.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import core.db.DataBase;
import next.dao.UserDAO;
import next.model.User;
import next.utils.MyValidatorFactory;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1); 
		}
		
		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
		if(constraintViolations.size() > 0) {
			req.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(req,resp,errorMessage);
			return;
		}
		
		UserDAO dao = new UserDAO();
		try {
			dao.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/");
	}
	
	private void forwardJSP(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
		rd.forward(req,resp);
	}
}
