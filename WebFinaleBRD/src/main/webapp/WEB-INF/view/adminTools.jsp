<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>See The List</title>
</head>
<body>
	<div>
	<table align="center" style="border: 2px solid ">
            <tr>
            	<th style="border: 1px solid ">User</th><th style="border: 1px solid ">EmailID</th><th>Function</th>
            </tr>
            
            <c:forEach var="employee" items="${unapproved}">​
            <tr>    ​​
                 <td style="border: 1px solid ">${employee.name}​</td>
                 <td style="border: 1px solid ">${employee.emailId}​</td>
                 <td style="border: 1px solid "><a href="approve/${employee.id}">Approve​</a>
                 <a href="delete/${employee.id}">Delete​</a>
                 </td>
                  </tr>
            </c:forEach>​  
    </table>
    </div>
    

    <a href="prod/1">See all products</a>
    
    
</body>
</html>