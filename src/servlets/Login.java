package servlets;


import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import main.Server;

/**
 * Servlet implementation class Login
 */
	@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
	     */
	    public Login() {
        // TODO Auto-generated constructor stub
	    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Server server = new Server();
		String username=request.getParameter("username"); 
		String password=request.getParameter("password");
		String preference=request.getParameter("preference");
		try {
		if (preference != null){
			
			String userCookie;
			userCookie = server.login(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("cookie", userCookie);
			System.out.println(userCookie+ " login");
			response.sendRedirect("index.html");
		} else{
			server.loginNoCookie(username, password);

			response.sendRedirect("indexNoCookie.html");
			}
		}
		catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		
	}

}
