package Controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.User;
import Services.IUserService;
import Services.UserServiceImpl;
@WebServlet(urlPatterns = {"/profile","/editProfile"})
public class ProfileController extends HttpServlet{
	IUserService userService = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if(url.contains("profile")) {
			String uid = req.getParameter("userID");
			User user =  userService.findUser(uid);
			req.setAttribute("user", user );
			req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
		}
		else if(url.contains("editProfile")){
//			String uid = req.getParameter("userID");
//			User user =  userService.findUser(uid);
//			req.setAttribute("user", user );
			req.getRequestDispatcher("/views/user/editProfile.jsp").forward(req, resp);
			
		}
		
		
	}
	
}
