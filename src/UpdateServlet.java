

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBGrade;
import model.Gbgrade;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String subject = request.getParameter("subject");
		String assignment = request.getParameter("assignment");
		String assType = request.getParameter("assType");
		String grade = request.getParameter("grade");

		HttpSession session = request.getSession();
		
		List<Gbgrade> records = null;
		
		Gbgrade gradeRecord = (Gbgrade) session.getAttribute("grade");
		gradeRecord.setUserid(userID);
		gradeRecord.setSubject(subject);
		gradeRecord.setAssignment(assignment);
		gradeRecord.setAssignmenttype(assType);
		gradeRecord.setGrade(grade);
		DBGrade.update(gradeRecord);
		
		int userID2 = (int) session.getAttribute("userID");
		if(userID2 == 1){
			records = DBGrade.gbPost();
			session.setAttribute("records", records);
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
		else{
			records = DBGrade.gbPostStudent(userID2);
			session.setAttribute("records", records);
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}
		
		
	}

}
