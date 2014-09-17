package self.kiwi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import self.kiwi.config.RootPath;
import self.kiwi.event.AbstractEvent;
import self.kiwi.event.DefaultEvent;
import self.kiwi.util.Transformer;
import self.kiwi.util.XMLUtil;

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
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("EventName") == null){
			//return Event list
			out.println(Transformer.array2Json(XMLUtil.getEventList()));
			
			
		} else {
			//return the parameter needed of the event
			
			String eventName = request.getParameter("EventName");
			ArrayList<String> parameterList = XMLUtil.getParamListByEventName(eventName);
			out.println(Transformer.array2Json(parameterList));
			
			
			
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

		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("EventName") == null){
			out.println("Don't know what to do");
			
		} else {
			//return the parameter needed of the event
			try {
				JSONObject jsonObj = new JSONObject(request.getParameter("dict"));
				String eventName = request.getParameter("EventName");
				ArrayList<String> parameterList = XMLUtil.getParamListByEventName(eventName);
				HashMap<String, String> hashMap = new HashMap<String, String>();
				for (String parameter: parameterList){
					hashMap.put(parameter, jsonObj.getString(parameter));
				}
				try { //For a special class that has a special model class
					Class<?> c = Class.forName(RootPath.packagePath + eventName);
					AbstractEvent event = (AbstractEvent) c.newInstance();
					event.runEvent(hashMap);
					out.println(event.getUserID());
				} catch (Exception e) { // For a default class
					// TODO: handle exception
					AbstractEvent event = new DefaultEvent(eventName,hashMap.get("UserId"),XMLUtil.getAddExpByEventName(eventName));;
					event.registerEvent();
					
				}
				out.println("{\"succeed\"}");
			} catch (JSONException e) {
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
