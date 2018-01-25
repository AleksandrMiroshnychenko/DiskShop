package diskServices;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiskDAO;
import entity.Disk;
import entity.CostConstants;

/**
 * Servlet implementation class DiskView
 */
@WebServlet("/DiskView")
public class DiskView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiskView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = request.getParameter("diskId");
		int diskId = Integer.valueOf(index);

		Disk disk = new DiskDAO().getDiskById(diskId, true, true);

		request.setAttribute("perMonth", CostConstants.pricePerDay);

		request.setAttribute("disk", disk);
		request.setAttribute("diskInd", index);

		request.getRequestDispatcher("DiskLook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
