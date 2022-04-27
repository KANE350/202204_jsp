package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EventService;


@WebServlet("*.event")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EventService eventService = new EventService(); 
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.contains("list")){
			String codename = request.getParameter("findkey");
			String title = request.getParameter("findvalue");
			
			List<Map<String, Object>> elist = eventService.selectList;
			System.out.println(elist);
			request.setAttribute("elist", elist);
			request.getRequestDispatcher("/view/eventlist.jsp")
				.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
