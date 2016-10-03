<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UpdateServlet" method="post">
	
	<%
		model.Gbgrade u = (model.Gbgrade) session.getAttribute("grade");
	%>
	
		<label id="id">User ID: <%=u.getId()%>
		</label> <br /> 
		<label>User ID:</label> <br /> 
		<input name="userID" id="userID" type="text" value=<%=u.getUserid()%> /> <br />
				<label>Subject:</label> <br /> 
		<input name="subject" id="subject" type="text" value=<%=u.getSubject()%> /> <br />
				<label>Assignment:</label> <br /> 
		<input name="assignment" id="assignment" type="text" value=<%=u.getAssignment()%> /> <br />
				<label>Assignment Type:</label> <br /> 
		<input name="assType" id="assType" type="text" value=<%=u.getAssignmenttype()%> /> <br />
				<label>Grade:</label> <br /> 
		<input name="grade" id="grade" type="text" value=<%=u.getGrade()%> /> <br />
		<br /> <input type="submit" value="Update" />
		<br /> <a href="EnterGrade.jsp">Enter new grade</a>

	</form>
</body>
</html>