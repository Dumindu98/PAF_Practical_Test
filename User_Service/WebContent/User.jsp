<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="model.User"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/users.js"></script>
</head>
<body>
	<h1>User Management</h1>



	<form id="formUser" name="formUser" method="post" action="User.jsp">
		UserName : <input id="Ruser_name" name="Ruser_name" type="text"
			class="form-control form-control-sm"> <br> User Address : <input
			id="Ruser_address" name="Ruser_address" type="text"
			class="form-control form-control-sm"> <br> Gender : <input
			id="Ruser_gender" name="Ruser_gender" type="text" class="form-control form-control-sm">
		<br> Age : <input id="Ruser_age" name="Ruser_age" type="text"
			class="form-control form-control-sm"> <br> User Notes : <input
			id="Ruser_notes" name="Ruser_notes" type="text"
			class="form-control form-control-sm"> <br> <input
			id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"> <input type="hidden"
			id="hidRuser_IDSave" name="hidRuser_IDSave" value="">
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divUsersGrid">
		<%
			User UserObj = new User();
		out.print(UserObj.readUsers());
		%>
	</div>

</body>
</html>