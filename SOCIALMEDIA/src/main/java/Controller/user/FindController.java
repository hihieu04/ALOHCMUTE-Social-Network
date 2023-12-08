package Controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/timkiem"})
public class FindController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword= req.getParameter("keyword");
		///webapp/views/user/resultFind.jsp
		req.getRequestDispatcher("/views/user/resultFind.jsp").forward(req, resp);
	}
}