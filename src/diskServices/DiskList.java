package diskServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import dao.DiskDAO;
import entity.Disk;
import session.SessionManipulate;

@WebServlet("/DiskList")
public class DiskList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DiskList() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchQuery = request.getParameter("searchQuery");
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
		List<Disk> disks = new ArrayList<>();

		disks = new DiskDAO().getFullDiskList(searchQuery, Paginable.getElemCount(buttonEvent));
		int size = new DiskDAO().getDiskCount(searchQuery);

		int pageCount = Paginable.getPagecount(size);
		System.out.println("Дисков в запросе " + size);

		System.out.println("Cтраниц запроса: " + pageCount);

		request.setAttribute("pageCount", pageCount);
		request.setAttribute("currentPage", buttonEvent);

		// request.setAttribute("search", searchQuery);
		request.setAttribute("disks", disks);

		request.getRequestDispatcher("/DiskList.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id;
		try{
		id = Integer.valueOf(request.getParameter("del"));
		new DiskDAO().deleteDisk(id);
		
		request.setAttribute("attention", "Диск удален");
		}
		catch(Exception e){
			request.setAttribute("attention", "У диска существуют заказы, он не может быть удален");
		}
		doGet(request, response);
	}

}
