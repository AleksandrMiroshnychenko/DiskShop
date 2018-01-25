package userPages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import entity.OrderEntity;

/**
 * Servlet implementation class Success
 */
@WebServlet("/Success")
public class Success extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Success() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OrderEntity order = new OrderEntity().createOrder(request);
		request.getSession().setAttribute("cart", null);
		
		new OrderDao().addOrder(order);
		
		request.getRequestDispatcher("Success.jsp").forward(request, response);

	}

}
