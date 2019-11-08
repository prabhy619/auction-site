    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hi <c:out value="${nameUser}"/>

<div>

<c:set var="nameUser" value="${userlog.name}"  />


		<table style="border: 2 px solid ">
		  <tr>
            <th style="border: 1px solid ">Name</th><th style="border: 1px solid ">BiddedPrice</th>
            <th style="border: 1px solid ">Bidder</th>
            </tr>
          
		  <c:forEach var="prod" items="${bidProd}" varStatus="status">​
            <tr>
            	<td style="border: 1px solid ">${prod.prodname}​</td>​​
                 <td style="border: 1px solid ">${prod.biddingPrice}​</td>
                  <%-- <td style="color:green">${names[status.index]}</td> --%>
            	 <c:if test="${names[status.index] eq nameUser}">
               		  <td style="color:green">${names[status.index]}</td>
               	  </c:if>
               	 <c:if  test="${names[status.index] ne nameUser}">
               		  <td style="color:red">${names[status.index]}</td>
						 <form action="confirmBid/${prod.prodId }">
                		 <td style="border: 1px solid ">
                 	<input type="number" name="bidNo">​
                 		</td>
                 	<td style="border: 1px solid ">
                 		<input type="submit" name="SubmitBid" >​
                 	</td>               	
               	  </c:if>  
               	  
               	  
                 
               	  
            </tr>
            </c:forEach>
    	</table>
	</div>
</body>
</html>