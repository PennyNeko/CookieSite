package servlets;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Server;
import util.Utils;

/**
 * Servlet implementation class CookieHacking
 */
@WebServlet("/CookieHacking")
public class CookieHacking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieHacking() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: meowget").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				Server server = new Server();
				String userCookie;
				HttpSession session = request.getSession();
				userCookie = (String) session.getAttribute("cookie");
				
				// Let's hack it to say we are an admin
				// we know the format of the cookie and we know the data of the user we logged in as
				String pennyPlainTextFirstBlock = "username=penny&f";
				// we want to be logged in as "admin"
				String adminPlainTextFirstBlock = "username=admin&f";
				// To hack it we need to calculate
				// iv xor originalPlainText xor desiredPlainText
				byte[] originalIv = Utils.extractIv(userCookie);
				byte[] xorIvAndOriginalPlainText = Utils.xor(originalIv, pennyPlainTextFirstBlock.getBytes());
				byte[] adminIv = Utils.xor(xorIvAndOriginalPlainText, adminPlainTextFirstBlock.getBytes());

				// now use the newly calculated adminIv with the originalEncryptedText
				byte[] originalEncryptedText = Utils.extractMessage(userCookie);
				String adminCookie = Utils.createEncryptedCookie(adminIv, originalEncryptedText);

				// See the hacked username
				String admin = "";
				try {
					admin = server.getUsername(adminCookie);
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Hacked in as " + admin);
				response.sendRedirect("indexNoCookie.html");
				
	}

}
