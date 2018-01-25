package diskServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActorDAO;
import dao.DiskDAO;
import dao.GenreDAO;
import entity.Actor;
import entity.Disk;
import entity.Genre;

/**
 * Servlet implementation class DiskRedact
 */
@WebServlet("/Disk_Redacting")
public class DiskRedact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiskRedact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id;
		Disk disk;
		
		try {
			id = Integer.valueOf(request.getParameter("redact"));
			 disk = new DiskDAO().getDiskById(id, true, true);
			 request.setAttribute("redact", disk);
		} catch (NumberFormatException e) {	}
		
		request.getRequestDispatcher("DiskRedact.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			//cover = download(request, response);
			//System.out.println(cover);

		Disk disk = new Disk().createDisk(request);
		
		new DiskDAO().updateDisk(disk);
		
		String send = "Изменения подтверждены";
		request.setAttribute("attention", send);
		request.getRequestDispatcher("DiskRedact.jsp").forward(request, response);
	}
}
