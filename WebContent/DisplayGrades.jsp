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

<center><h1>Grades List</h1></center>
	<div class="container">
	<center>
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
		<a href="EnterGrade.jsp">Enter new grade</a><br /><br /></center><hr>
		<input type="text" value="${alert}" size="28" readonly><hr>
		<form action="GetHighestAndLowestScore" method="post">
			Enter Assignment Type to get highest and lowest score: <br /><input type="text" name="assignmentType">&nbsp;
			<input type="submit" value="submit" id="submit"><br />
		</form>
		<input type="text" value="${HighAndLow}" size="40" readonly><br /><br /><hr>
		<form action="ShowStudentRecordServlet" method="post">
			Enter student Roll Number to view grades: <br /><input type="text" name="studentID">&nbsp;
			<input type="submit" value="submit" id="submit"><br /><br />
		</form><hr>
		<form action="ShowAssignmentRecordServlet" method="post">
			Enter assignment type to view grades: <br /><input type="text" name="assignmentType">&nbsp;
			<input type="submit" value="submit" id="submit"><br /><br />
		</form><hr>
		<form action="ShowAssignmentAndStudentRecordServlet" method="post">
			View grades for Roll Number: <br /><input type="text" name="studentID2"><br/>and&nbsp;
			Assignment type: <br /><input type="text" name="assignmentType2">&nbsp;
			<input type="submit" value="submit" id="submit"><br /><br />
		</form><hr>
		<form action="ShowStudentAverage" method="post">
			Enter student Roll Number to view average: <br /><input type="text" name="studentID">&nbsp;
			<input type="submit" value="submit" id="submit"><br />
		</form>
		<form action="ShowStudentAverageByAssignment" method="post">
			View average score of Roll number: <br /><input type="text" name="studentID"><br/>and&nbsp;
			Assignment type: <br/><input type="text" name="assignmentType">&nbsp;
			<input type="submit" value="submit" id="submit"><br />
		</form>
		<input type="text" value="${average}" size="40" readonly><br /><br /><hr>
		<form action="EditRecord" method="post">
			Enter Record ID To edit: <br /><input type="text" name="recordID">&nbsp;
			<input type="submit" value="submit" id="submit"><br /><br /><br />			
		</form>

	</div>
</body>
</html>