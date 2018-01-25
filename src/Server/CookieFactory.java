package Server;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

interface CookieFactory {
	
	
	default void addCookies(String login, String password, HttpServletResponse response){
		
		java.util.Date date = new java.util.Date();
		String now = date.toString();
		
		Cookie loginC = new Cookie("login", login);
		Cookie passwordC = new Cookie("password", password);
		Cookie timestamp = new Cookie("stamp",now );
		
		int year = 365 * 24 * 60 * 60;
		
		loginC.setMaxAge(year);
		passwordC.setMaxAge(year);
		timestamp.setMaxAge(year);
		
		response.addCookie(loginC);
		response.addCookie(passwordC);
		response.addCookie(timestamp);
		
	}
	
	public default void removeCookie(HttpServletResponse response) {
		Cookie loginC = new Cookie("login", "0");
		Cookie passwordC = new Cookie("password", "0");
		Cookie timestamp = new Cookie("stamp","0" );


		loginC.setMaxAge(0);
		passwordC.setMaxAge(0);
		timestamp.setMaxAge(0);
		response.addCookie(loginC);
		response.addCookie(passwordC);
		response.addCookie(timestamp);
	}
	

}
