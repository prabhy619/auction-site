<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div>
		header
	</div>
		
	<div>
		<a href="reg1">Register as new User</a>
		<a href="login">Login as new Users</a>
		<hr>
		<a href="adminLogin">Admintools</a>	
	</div>
	<div>
${user.name } , ${message} 
	</div>
</body>
</html>
