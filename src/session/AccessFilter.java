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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.ERROR }, urlPatterns = {
		"/Orders", "/Disk_Administration" })
public class AccessFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AccessFilter() {
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
		HttpServletResponse httpRes = (HttpServletResponse) response;

		try {
			User user = (User) httpReq.getSession(false).getAttribute("user");
						
			if (user.getRole() != 1) {
				httpRes.sendError(404);
				return;
			}
		} catch (NullPointerException e) {
			httpRes.sendError(404);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
