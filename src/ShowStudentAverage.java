

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBGrade;
import customTools.DBUser;

/**
 * Servlet implementation class ShowStudentAverage
 */
@WebServlet("/ShowStudentAverage")
public class ShowStudentAverage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStudentAverage() {
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
		
		int average = 0;
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		//List<Gbgrade> records = null;
		//HttpSession session = request.getSession();
		if(DBUser.isValidRollNumber(studentID) == true){
			average = DBGrade.gbPostStudentAverage(studentID);
			request.getSession().setAttribute("average", "Student("+studentID+") average: " + average);
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
		else{
			request.getSession().setAttribute("alert", "Student Roll Number does not exist");
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
	}

}
