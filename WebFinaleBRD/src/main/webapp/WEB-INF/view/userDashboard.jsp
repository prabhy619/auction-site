<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>​
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserDashboard</title>
</head>
<body>
<h4>${userlog.name}</h4>
	<div>
	<ul>
		<li ><a href="newBid">New Bid</a></li>
		<li>Products Bid On</li>
		<li><a href="seeMy">See Bought Products</a></li>
		<li><a href="newProd">Upload New Product</a></li>
	</ul>
	</div>
	
	<div>
		<table style="border: 2 px solid ">
		  <c:forEach var="prod" items="${myProd}" varStatus="status">​
          <tr>
            <th style="border: 1px solid ">Name</th><th style="border: 1px solid ">YourPrice</th><th style="border: 1px solid ">BidderId</th>
            <th style="border: 1px solid ">Approve Bid</th>
            </tr>
            <tr>
            	<td style="border: 1px solid ">${prod.prodname}​</td>​​
                 <td style="border: 1px solid ">${prod.biddingPrice}​</td>
                 <td style="color:green;border: 1px solid  ">${names[status.index]}</td>  
              <td style="border: 1px solid "><c:if test="${fn:length(names[status.index])>1}">
               <a href="approveBid/${prod.prodId}"><p>Approve</p></a></c:if></td>
            </tr>
            </c:forEach>
    	</table>
	</div>
	
	
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
            	 <c:if test="${names2[status.index] eq nameUser}">
               		  <td style="color:green">${names2[status.index]}</td>
               	  </c:if>
               	 <c:if  test="${names2[status.index] ne nameUser}">
               		  <td style="color:red">${names2[status.index]}</td>
						 <form action="confirmBid/${prod.prodId }"> 
                		 <td style="border: 1px solid ">
                 			<input type="number" name="bidNo">​
                 		</td>
                 	<td style="border: 1px solid ">
                 			<input type="submit" value="submit">
                 	</td>               	
                 	</form>
               	  </c:if>         	  
            </tr>
            </c:forEach>
    	</table>
	</div>
	
	
	
	
</body>
</html>