<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Users</title>
</head>
<body>
   <div align="center">
   		<h2>Search Users</h2><br/>
   	 	<form action="profilesearch" method="post">
   	 		<input type="text" name="profilesearch" size="50"
             onfocus="this.value=''"><br/><br/><input type="submit"
             value="Search" />
      	</form>
      
        <br/><br/>
         <table border="1" cellpadding="6">
            <tr>
				<th>User List</th>
            </tr>
            
       		<c:forEach var="users" items="${listUser}">
           	<tr style="text-align:center">
               	<td>
					<form action="viewprofilelist" method="get">
						<input  name="email" type="submit" value="${users.email}" onClick="viewprofilelist"/>
					</form>
				</td>
           	</tr>
     		</c:forEach>
        </table>
        
       	<br/><br/><a href="activitypage.jsp" target="_self">Return to NFT Marketplace</a>
       	<br/><br/><a href="login.jsp"target ="_self" >Logout</a><br><br>      
    </div>   
</body>
</html>