    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <c:forEach var="prod" items="${listPg}">​
          <table>
          <tr>
            <th style="border: 1px solid ">Name</th><th style="border: 1px solid ">YourPrice</th><th style="border: 1px solid ">Buyer</th><th style="border: 1px solid ">Sold</th>
            </tr>
            <tr>
            	<td style="border: 1px solid ">${prod.prodname}​</td>​​
                <td style="border: 1px solid ">${prod.biddingPrice}​</td>
                <td style="border: 1px solid ">${prod.user.name}​</td>
                <td style="border: 1px solid ">${prod.sold}​</td>
            </tr>
            </c:forEach>
            </table>
            <a href="${no-1}"><</a>
            <a href="${no+1}">></a>
            <br>
            <a href="/WebFinaleBRD/">Return</a>
</body>
</html>