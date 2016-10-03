

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
		
		HttpSession session = request.getSession();
		
		List<Gbgrade> records = null;
		int userID2 = (int) session.getAttribute("userID");
		if(userID2 == 1){
			records = DBGrade.gbPost();
			session.setAttribute("records", records);
		}
		else{
			records = DBGrade.gbPostStudent(userID2);
			session.setAttribute("records", records);
		}
		
		int userID = Integer.parseInt(request.getParameter("userID"));
		String subject = request.getParameter("subject");
		String assType = request.getParameter("assType");
		String grade = request.getParameter("grade");
		String assignment = request.getParameter("assNumber");

		Gbgrade record = new Gbgrade();
		record.setAssignment(assignment);
		record.setAssignmenttype(assType);
		record.setGrade(grade);
		record.setSubject(subject);
		record.setUserid(userID);
		
		DBGrade.insert(record);
		
		response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
	}

}
