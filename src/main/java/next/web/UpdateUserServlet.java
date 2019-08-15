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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import next.dao.UserDAO;
import next.model.User;
import next.utils.MyValidatorFactory;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 보안 이슈 해결 (현재 회원이 로그인한 상태인지 체크)
		HttpSession session = req.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(sessionUserId == null) {
			resp.sendRedirect("/");
			return;
		}
		
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1); 
		}
		
		// req.getParameter로 넘어오지 않기 떄문에 어쩔수 없이 추가..
		if(user.getUserId() == null) {
			user.setUserId(sessionUserId);
		}
		
		if(!user.isSameUser(sessionUserId)) {
			System.out.println("UpdateUserServlet userId :"+user.getUserId());
			resp.sendRedirect("/");
			return;
		}
		
		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
		if(constraintViolations.size() > 0) {
			req.setAttribute("isUpdate", true);
			req.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(req,resp,errorMessage);
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		resp.sendRedirect("/");
	}
	
	private void forwardJSP(HttpServletRequest req, HttpServletResponse resp, String errorMessage) throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
		rd.forward(req,resp);
	}
}
