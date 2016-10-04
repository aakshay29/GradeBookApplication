

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

/**
 * Servlet implementation class EnterGradeServlet
 */
@WebServlet("/EnterGradeServlet")
public class EnterGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterGradeServlet() {
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
		
		HttpSession session = request.getSession();
		String nextURL = null;
		List<Gbgrade> records = null;
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String subject = request.getParameter("subject");
		String assType = request.getParameter("assType");
		String grade = request.getParameter("grade");
		String assignment = request.getParameter("assNumber");
		
		if(DBUser.isValidRollNumber(userID) == true){
			Gbgrade record = new Gbgrade();
			record.setAssignment(assignment);
			record.setAssignmenttype(assType);
			record.setGrade(grade);
			record.setSubject(subject);
			record.setUserid(userID);			
			DBGrade.insert(record);		
			nextURL = "/DisplayGrades.jsp";
		}
		else{
			request.getSession().setAttribute("alert", "Student Roll Number does not exist");
			nextURL = "/EnterGrade.jsp";
		}
		
		int userID2 = (int) session.getAttribute("userID");
		if(userID2 == 1){
			records = DBGrade.gbPost();
			session.setAttribute("records", records);
		}
		else{
			nextURL = "/Login.jsp";
		}
		
		response.sendRedirect(request.getContextPath()+nextURL);

	}

}
