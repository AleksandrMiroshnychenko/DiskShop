package Server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import authorizate.Authorization;
import entity.User;
import session.SessionManipulate;

@WebServlet("/RegisterServ")
public class RegisterServ extends HttpServlet implements CookieFactory {
	private static final long serialVersionUID = 1L;

	public RegisterServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("Registration.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		request.getSession().invalidate();
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!Authorization.comparePasswords(password, password2)) {
			request.setAttribute("attention", "Пароль не подтвержден!");
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
			return;
		}

		String login = request.getParameter("login");
		String eMail = request.getParameter("email");

		password = Authorization.md5Crypt(password);
		
		User vasia = new User(login, password, eMail);
		boolean isUnique = vasia.appendToDB();
		
		if (!isUnique) {
			request.setAttribute("attention", "Логин или E-mail уже существует");
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
			return;
		}
		addCookies(login, password, response);
		response.sendRedirect("AuthorizateServlet");
	}

}
