<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>UserID</th>
					<th>Subject</th>
					<th>Assignment</th>
					<th>Assignment Type</th>
					<th>Grade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="records" items="${records}">
					<tr>
						<td><c:out value="${records.id}" /></td>
						<td><c:out value="${records.userid}" /></td>
						<td><c:out value="${records.subject}" /></td>
						<td><c:out value="${records.assignment}" /></td>
						<td><c:out value="${records.assignmenttype}" /></td>
						<td><c:out value="${records.grade}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="EditRecord" method="post">
			Enter Record To edit: <br /> <input type="text" name="recordID"> <br />
			<br /> 
			<br /> <input type="submit" value="submit" id="submit">
			<a href="EnterGrade.jsp">Enter new grade</a>
		</form>
	</div>
</body>
</html>