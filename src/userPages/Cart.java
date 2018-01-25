package userPages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.CartFactory;


@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();

	}

	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<Integer, CartFactory> cart;
		
		cart =  (HashMap<Integer, CartFactory>) request.getSession(false).getAttribute("cart");
		List<CartFactory> cartList = new ArrayList<>();
		try{
		cartList = new ArrayList<CartFactory>(cart.values());
		}
		catch(NullPointerException e){}
		request.setAttribute("cart", cartList);
		
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			HashMap<Integer, CartFactory> cart;
			Integer cancelId;
			
			try{
			cancelId = Integer.valueOf(request.getParameter("cancel"));
			}
			catch(NullPointerException | NumberFormatException e){
				cancelId = null;
			}
			
			cart =  (HashMap<Integer, CartFactory>) request.getSession(false).getAttribute("cart");
			
			if(cart == null||cart.isEmpty()){
				cart = new HashMap<>();
			}
			else if (cancelId != null) {
				System.out.println(cancelId);
				cart.remove(cancelId);
			}
			
			if(cancelId == null) {
					CartFactory selected = new CartFactory().addCart(request);
					int id = selected.getId();
					cart.put( id, selected);
			}

			
			List<CartFactory> cartList = new ArrayList<CartFactory>(cart.values());
			

			request.getSession(false).setAttribute("cart", cart);
			
			request.setAttribute("cart", cartList);
			
			request.getRequestDispatcher("Cart.jsp").forward(request, response);

		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("attention", "Простите, произошла ошибка с отправкой данных, попробуйте снова");
			response.sendRedirect(request.getContextPath());
		}
	}

}
