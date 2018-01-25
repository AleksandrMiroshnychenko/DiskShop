package userPages;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import entity.OrderEntity;
import entity.User;


@WebServlet("/UserOrders")
public class UserOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id;
		try {
			User user =  (User) request.getSession(false).getAttribute("user");
			if (user.getRole() != 0){
				response.sendError(404);
				return;
			}
			List<OrderEntity> Orders = new OrderDao().getOrderById(user.getUserId());
			request.setAttribute("orders", Orders);
			request.getRequestDispatcher("UserOrders.jsp").forward(request, response);
		} catch (NumberFormatException | NullPointerException e) {
			e.printStackTrace();
			response.sendError(404);
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		
		try{
		id = Integer.valueOf(request.getParameter("orderId"));
		OrderDao dao = new OrderDao();
		System.out.println(id);
		byte error = dao.checkOrderById(id);
		if(error !=0){
			request.setAttribute("attent", "Этот заказ не ваш");
		}
		else new OrderDao().changeOrderStatus(id, (byte)2);
		}
		catch(NumberFormatException | NullPointerException e){
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

		

	
}
