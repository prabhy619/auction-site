<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
</head>
<body>
${param.Errormsg}
<h2>Please Enter the form</h2>
	<s:form commandName="user" method="Post" >
		<spring:message code="name"> </spring:message>
		<s:input path="name"/>
		<s:errors path="name"></s:errors>
		Age<s:input path="Age" />
		EmailId<s:input path="emailId"/>
		userName<s:input path="userName"/>
		Password<s:input type="password" path="password"/>
		<br><input type="submit" value="Submit" >
	</s:form>
</body>
</html>