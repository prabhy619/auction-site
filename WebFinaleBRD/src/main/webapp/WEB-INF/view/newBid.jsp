<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>You can bid only on these products</h3>
	<div>
		<table style="border: 2 px solid ">
		  <c:forEach var="prod" items="${prod}">​
          <tr>
          	  <th style="border: 1px solid ">Name</th><th style="border: 1px solid ">YourPrice</th><th>YourBid</th>
            </tr>
            <tr>    
            <td style="border: 1px solid ">${prod.prodname}​</td>​​
                 <td style="border: 1px solid ">${prod.biddingPrice}​</td>
                 <form action="confirmBid/${prod.prodId }">
                 <td style="border: 1px solid ">
                 	<input type="number" name="bidNo">​
                 </td>
                 <td style="border: 1px solid ">
                 	<input type="submit" name="SubmitBid" >​
                 </td>
                 </form>
            </td>
		</tr>
            </c:forEach>
    	</table>
	</div>
</body>
</html>