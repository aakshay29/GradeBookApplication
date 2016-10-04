<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Grades</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
<center>
<h1>Grades List</h1>
	<div class="container">
		<table class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>Record ID</th>
					<th>Roll Number</th>
					<th>Subject</th>
					<th>Assignment</th>
					<th>Assignment Type</th>
					<th>Score</th>
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
	</div>
</center>
</body>
</html>