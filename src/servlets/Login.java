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
		// TODO Auto-generated method stub
		
		Server server = new Server();
		doGet(request, response);
		String username=request.getParameter("username"); 
		String password=request.getParameter("password");
		String preference=request.getParameter("preference");
		
		if (preference != null){
			Cookie userCookie = new Cookie(username, password);
		}
		
		response.sendRedirect("index.html");
		
		/*try {
			String userCookie = server.login(username, password);
			if(userCookie !=null){
				Cookie myCookie =
						  new Cookie("authcookie", userCookie);
						  response.addCookie(myCookie);
							response.sendRedirect("Admin");

			}else{
				response.sendRedirect("login.jsp");
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
