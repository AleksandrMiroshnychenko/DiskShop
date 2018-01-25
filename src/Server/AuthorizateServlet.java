package Server;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import authorizate.Authorization;
import dao.UserDAO;
import session.SessionManipulate;


@WebServlet("/AuthorizateServlet")
public class AuthorizateServlet extends HttpServlet implements CookieFactory {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//RequestDispatcher disp = request.getRequestDispatcher("Interact.jsp");
		//disp.include(request, response);
		response.sendRedirect("DiskList");
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.getSession(false).invalidate();
	
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	password = Authorization.md5Crypt(password);
	
	addCookies(login, password, response);
	
	response.sendRedirect("DiskList");
}

}