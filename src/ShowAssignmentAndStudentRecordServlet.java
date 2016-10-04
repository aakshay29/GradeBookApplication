

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
 * Servlet implementation class ShowAssignmentAndStudentRecordServlet
 */
@WebServlet("/ShowAssignmentAndStudentRecordServlet")
public class ShowAssignmentAndStudentRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAssignmentAndStudentRecordServlet() {
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
		
		int studentID = Integer.parseInt(request.getParameter("studentID2"));
		String assignmentType = request.getParameter("assignmentType2");
		List<Gbgrade> records = null;
		HttpSession session = request.getSession();
		if(DBUser.isValidRollNumber(studentID) == true && DBGrade.isValidAssignment(assignmentType) == true){
			records = DBGrade.gbPostStudentAndAsstype(studentID,assignmentType);
			session.setAttribute("records", records);
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
		else{
			request.getSession().setAttribute("alert", "Student Roll Number / Assignment Type does not exist");
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
	}

}
