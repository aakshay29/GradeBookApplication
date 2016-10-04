

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBGrade;
import customTools.DBUser;
import model.Gbgrade;
import model.Gbuser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
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
		request.getSession().setAttribute("alert", "");
		request.getSession().setAttribute("average", "");
		request.getSession().setAttribute("HighAndLow", "");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nextUrl;
		List<Gbgrade> records = null;
		
		if(DBUser.isValidUser(username, password)){
			Gbuser user = null;		
			user = DBUser.getUserByEmail(username);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			int userID = user.getId();
			session.setAttribute("userID", userID);
			
			if(userID == 1){
				records = DBGrade.gbPost();
				session.setAttribute("records", records);
				nextUrl = "/DisplayGrades.jsp";	
			}
			else{
				records = DBGrade.gbPostStudent(userID);
				session.setAttribute("records", records);
				nextUrl = "/YourGrades.jsp";	
			}
			
			
		}
		else{	
			request.getSession().setAttribute("alert", "Incorrect username or password");
			nextUrl = "/Login.jsp";
		}
		response.sendRedirect(request.getContextPath()+nextUrl);
	}

}
