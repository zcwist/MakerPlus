package self.kiwi.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import self.kiwi.config.RootPath;
import self.kiwi.event.AbstractEvent;

public class Event extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241105024897597228L;

	/**
	 * Constructor of the object.
	 */
	public Event() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File file = new File("");
		System.out.println(file.getAbsolutePath());

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (request.getParameter("EventName") == null){
			//return Event list
			out.println("Event List");
			
		} else {
			//return the parameter needed of the event
			try {
				Class<?> event = Class.forName("self.kiwi.event." + request.getParameter("EventName"));
				ArrayList<String> parameterList = ((AbstractEvent) event.newInstance()).getParameterList();
				for (String parameter: parameterList){
					out.println(parameter);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.println("This class doesn't exsit ya!");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (request.getParameter("EventName") == null){
			//return Event list
			out.println("Don't know what to do");
			
		} else {
			//return the parameter needed of the event
			try {
				Class<?> event = Class.forName("self.kiwi.event." + request.getParameter("EventName"));
				HashMap<String, String> hashMap = new HashMap<String, String>();
				AbstractEvent objEvent = (AbstractEvent) event.newInstance();
				ArrayList<String> parameterList = objEvent.getParameterList();
				for (String parameter: parameterList){
					hashMap.put(parameter, request.getParameter(parameter));
				}
				objEvent.runEvent(hashMap);
				out.println("succeed");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		String path = getServletContext().getRealPath("/");
		RootPath.getInstance().setRoot(path);
		
	}

}
