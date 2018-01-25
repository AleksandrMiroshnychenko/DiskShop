package session;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = {
		"/*" })
public class SessionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SessionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;

		try {

			if (httpReq.getSession(true).isNew()) {

				Cookie[] cookies = httpReq.getCookies();

				String login = null;
				String password = null;

				for (Cookie cookie : cookies) {

					if (cookie.getName().equals("login")) {
						login = cookie.getValue();
						System.out.println("Login reached from cookie: " + login);
					} else if (cookie.getName().equals("password")) {
						password = cookie.getValue();
						System.out.println("Password reached from cookie: " + password);
					}
					else if (cookie.getName().equals("stamp")){
						
						System.out.println("reached from cookie: " + cookie.getValue());
					}
				}
				User user = new UserDAO().confirmAuthor(login, password);
				if (user != null) {
					System.out.println("user " + user.getNickname());
					new SessionManipulate(user, httpReq);
				}
			}
		} catch (NullPointerException e) {}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
