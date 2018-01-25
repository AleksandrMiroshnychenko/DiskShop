package session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class SessionAttr implements HttpSessionAttributeListener {


    public SessionAttr() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  {
        System.out.println("Added new session attribute : " + arg0.getName()+ ' ' + arg0.getValue());
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  {
    	//arg0.getSession().invalidate();
    	System.out.println("Removed session attribute : " + arg0.getName()+ ' ' + arg0.getValue());
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  {
    	System.out.println("Replaced session attribute : " + arg0.getName()+ ' ' + arg0.getValue());
    }
	
}
