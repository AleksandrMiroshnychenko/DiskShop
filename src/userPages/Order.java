package userPages;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.CartFactory;
import entity.Delivery;
import entity.OrderEntity;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("Order.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// float price = 0;

		float sum = Float.valueOf(request.getParameter("countSum"));

		request.setAttribute("deliveryPrice", Delivery.deliveryPrice);

		request.setAttribute("priceSum", sum);

		request.getRequestDispatcher("Order.jsp").forward(request, response);

		// doGet(request, response);
	}

}
