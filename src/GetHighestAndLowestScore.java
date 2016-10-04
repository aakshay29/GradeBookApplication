

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBGrade;

/**
 * Servlet implementation class GetHighestAndLowestScore
 */
@WebServlet("/GetHighestAndLowestScore")
public class GetHighestAndLowestScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHighestAndLowestScore() {
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
		
		request.getSession().setAttribute("alert", "");
		String assignmentType = request.getParameter("assignmentType");
		if(DBGrade.isValidAssignment(assignmentType) == true){
			int highestScore = DBGrade.gbPostHighestScore(assignmentType);
			int lowestScore = DBGrade.gbPostLowestScore(assignmentType);
			request.getSession().setAttribute("HighAndLow", highestScore + " is the highest score and " + lowestScore + " is the lowest score in " + assignmentType);
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
		else{
			request.getSession().setAttribute("alert", "Assignment Type does not exist");
			response.sendRedirect(request.getContextPath()+"/DisplayGrades.jsp");
		}
	}

}
