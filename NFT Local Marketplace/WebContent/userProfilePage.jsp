<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Profile View</title>
</head>
<body>
<div align="center">
   <h2>Profile Page</h2>
      <table border="1" cellpadding="6">
      	<tr><th colspan="4">Profile</th></tr>
        <tr>
        	<th>User</th>
        	<th>First Name</th>
        	<th>NFTs Owned</th>
        	<th>ETH balance<th>
        </tr>
        <c:forEach var="users" items="${listUser}">
     	<tr style="text-align:center">
        	<td>"${users.email}" </td>
        	<td>"${users.firstName}" </td> 
        	<td>2</td> 
        	<td>4.2</td>
        </tr>
        </c:forEach>
        </table>
    <br/><br/><a href="userSearchPage.jsp" target="_self">Return to Users</a><br/><br/>
   	<br/><br/><a href="activitypage.jsp" target="_self">Return to Main Menu</a>
	<br/><br/><a href="login.jsp"target ="_self" >Logout</a><br><br> 
</div>   
</body>
</html>