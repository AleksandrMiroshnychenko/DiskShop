package session;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.Disk;
import entity.User;

public class SessionManipulate {

	HttpSession session;
	private List<Disk> disks;
	
	// private static String login = null;
	// private static String password = null;

	public SessionManipulate(User user, HttpServletRequest request) {

		session = request.getSession(true);
		session.setAttribute("user", user);

		session.setMaxInactiveInterval(60 * 60);
	}
	
	public SessionManipulate(HttpServletRequest request){
		session = request.getSession(true);
	}

	public static HttpSession getSession(HttpServletRequest request) {
		try { 
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		return request.getSession();
	}

	public static Date getCreationDate(HttpServletRequest request) {
		return new Date(request.getSession(false).getCreationTime());
	}

	public static void setSessionTime(int which, HttpServletRequest request) {
		request.getSession(true).setMaxInactiveInterval(which);
	}





	public Byte getRole(HttpServletRequest request) {
		try {
			byte brole = (Byte) request.getSession(false).getAttribute("role");
			return brole;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Disk> getBooks() {
		return disks;
	}


	public void setBooks(List<Disk> disk, HttpServletRequest request) {
		try {
		request.getSession(true).setAttribute("disks", disk);
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
	}


}
