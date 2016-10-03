<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enter Grade</title>
</head>
<body>
<center>
		<h1>Enter Grades Here</h1>
		<form action="EnterGradeServlet" method="post">
			User ID: <br /> <input type="text" id="userID" name="userID"> <br />
			Subject: <br /> <input type="text" id="subject" name="subject"> <br />
			Assignment: <br /> <input type="text" id="assNumber" name="assNumber"> <br />
			Assignment Type: <br /> <input type="text" id="assType" name="assType"> <br />
			Grade: <br /> <input type="text" name="grade" id="grade"> <br />
			<br /> <input type="submit" value="submit" id="submit">
		</form>
	</center>
</body>
</html>