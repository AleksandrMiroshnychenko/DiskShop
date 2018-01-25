package adminPages;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiskDAO;
import dao.OrderDao;
import diskServices.Paginable;
import entity.OrderEntity;
import entity.User;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/Orders")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer buttonEvent = 1;
		try {
			String temp = request.getParameter("pageButton");

			if (temp != null) {
				buttonEvent = Integer.valueOf(temp);
			}

		} catch (NullPointerException | NumberFormatException e) {
			e.printStackTrace();
			buttonEvent = 1;
		}

		boolean notConfirmed = true;

		try {
			notConfirmed = Boolean.valueOf(request.getParameter("notConfirmed"));
		} catch (NumberFormatException e) {
			notConfirmed = false;
		}

		int size = new OrderDao().getOrderCount(notConfirmed);
		List<OrderEntity> entities = new OrderDao().getFullOrderList(notConfirmed, Paginable.getElemCount(buttonEvent));

		int pageCount = Paginable.getPagecount(size);

		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", buttonEvent);
		request.setAttribute("notConfirmed", notConfirmed);
		request.setAttribute("orders", entities);
		request.getRequestDispatcher("OrderCon.jsp").forward(request, response);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.valueOf(request.getParameter("orderId"));
			byte option = Byte.valueOf(request.getParameter("option"));
			new OrderDao().changeOrderStatus(id, option);
		} catch (NullPointerException | NumberFormatException e) {}

		try {
			int cartId = Integer.valueOf(request.getParameter("cartId"));
			new OrderDao().checkReturnDate(id, cartId);
		} catch (NullPointerException | NumberFormatException e) {

		}
		doGet(request, response);

	}
	

}
