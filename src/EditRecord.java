

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
 * Servlet implementation class EditRecord
 */
@WebServlet("/EditRecord")
public class EditRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRecord() {
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

		List<Gbgrade> records = null;
		
		int recordID = Integer.parseInt(request.getParameter("recordID"));
		Gbgrade grade = DBGrade.getRecordById(recordID);
		session.setAttribute("grade", grade);
		
		int userID = (int) session.getAttribute("userID");
		if(userID == 1){
			records = DBGrade.gbPost();
			session.setAttribute("records", records);
			response.sendRedirect(request.getContextPath()+"/EditRecord.jsp");
		}
		else{
			//records = DBGrade.gbPostStudent(userID);
			//session.setAttribute("records", records);
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}			
	}

}
