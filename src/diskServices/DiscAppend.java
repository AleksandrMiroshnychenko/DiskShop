package diskServices;

//import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ActorDAO;
import dao.DiskDAO;
import dao.GenreDAO;
import entity.Actor;
import entity.Disk;
import entity.Genre;

@WebServlet("/Disk_Administration")

public class DiscAppend extends HttpServlet {
	private static final long serialVersionUID = -10257028L;

	public DiscAppend() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Actor> names = new ActorDAO().getActorNames();
		ArrayList<Genre> genres = new GenreDAO().getGenreNames();
		request.setAttribute("actors", names);
		request.setAttribute("genres", genres);

		request.getRequestDispatcher("DiskAddition.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*String cover = null;
		try {

		} catch (NullPointerException e) {
			e.printStackTrace();
		}*/

		try {
			
			//cover = download(request, response);
			//System.out.println(cover);

			Genre genre = new Genre();

			try {
				genre.setGenreBatch(Arrays.asList(request.getParameterValues("genre")));
			} catch (NullPointerException e) {
				//e.printStackTrace();
				System.out.println("Новых жанров нет");
			}
				

			
			Actor actor = new Actor();
			try{
			actor = new Actor(Arrays.asList(request.getParameterValues("name")));
			}
			catch(NullPointerException e){
				System.out.println("Новых актеров нет");
			}
			
			String[] existedGenre = request.getParameterValues("chosenGenre");
			String[] existedActor = request.getParameterValues("chosenActor");

			DiskDAO current = new DiskDAO();
			Disk disk = new Disk().createDisk(request);

			try{
			disk.setActors(actor);
			}
			catch(NullPointerException e){}
			try{
			disk.setGenre(genre);
			}catch(NullPointerException e){}
			// Disk disk = new Disk(title, sellable, price, stored, isbn,
			// release, description, genre, actor);

			List<Integer>exActor = stringToDisk(existedActor);
			List<Integer>exGenre = stringToDisk(existedGenre);


			byte error;
			//System.out.println(error);
			
				error = current.addDisk(disk,exActor,exGenre);
				if(error != 0){
					request.setAttribute("attention", "Такой диск уже существует");
				System.out.println("Added a disk: " + disk.toString());
			} else {
				request.setAttribute("attention", "Диск добавлен");
			}

		} catch (NumberFormatException | NullPointerException | SQLException e) {
			System.out.println("mimo");
			e.printStackTrace();
		}

		doGet(request, response);
	}

	 private List<Integer> stringToDisk(String[] var) {
		List<Integer> convert = new ArrayList<>();
		int iter = 0;
		try {
			for (String string : var) {
				convert.add(Integer.valueOf(string));
				System.out.println("Success " + convert.get(iter));
				iter++;
			}

		} catch (NullPointerException e) {
		}
		return convert;
	}
	
	
	/*private String download(HttpServletRequest request, HttpServletResponse response) {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			System.out.println("is not multipart");
			return null;
		}

		// Создаём класс фабрику
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Максимальный буфера данных в байтах,
		// при его привышении данные начнут записываться на диск во временную
		// директорию
		// устанавливаем один мегабайт
		factory.setSizeThreshold(1024 * 1024);

		// устанавливаем временную директорию
		java.io.File tempDir = (java.io.File) getServletContext().getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(tempDir);

		// Создаём сам загрузчик
		ServletFileUpload upload = new ServletFileUpload(factory);

		// максимальный размер данных который разрешено загружать в байтах
		// по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
		upload.setSizeMax(1024 * 1024 * 10);

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					// если принимаемая часть данных является полем формы
					processFormField(item);
				} else {
					// в противном случае рассматриваем как файл
					processUploadedFile(item);
				}
			}
			return "covers/" + items.get(0).getName();
		} catch (Exception e) {
			e.printStackTrace();
			// response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
	}

	@SuppressWarnings("null")
	private void processUploadedFile(FileItem item) throws Exception {
		File uploadedFile = null;
		// выбираем файлу имя пока не найдём свободное
		if (!uploadedFile.exists()) {
			String path = getServletContext().getRealPath("/WebContent/covers/" + item.getName());
			uploadedFile = new File(path);
		}

		// создаём файл
		uploadedFile.createNewFile();
		// записываем в него данные
		item.write(uploadedFile);
	}


	private void processFormField(FileItem item) {
		System.out.println(item.getFieldName() + "=" + item.getString());
	}*/

}
