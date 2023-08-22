<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>NFT View</title>
</head>
<body>
<div align="center">
   <h2>NFT Page</h2>
      <table border="1" cellpadding="6">
      	<tr><th colspan="6">NFT</th></tr>
        <tr>
        	<th>NFT ID</th>
        	<th>Name</th>
        	<th>Description</th>
        	<th>Image</th>
        	<th>Cost</th>
        	<th>Owner</th>
        </tr>
        <c:forEach var="NFTs" items="${listNFT}">
     	<tr style="text-align:center">
        	<td>"${NFTs.nftID}" </td>
			<td>"${NFTs.nftName}</td>
        	<td>"${NFTs.nftDes}"</td>
            <td><img src="${NFTs.imageURL}" width= "200" height="200"></td>
            <td>"${NFTs.price}"</td>            
            <td>"${NFTs.owner}"</td> 
        </tr>
        </c:forEach>
        </table>
    <br/><br/><a href="nftSearchPage.jsp" target="_self">Return to NFTs</a><br/><br/>
   	<br/><br/><a href="activitypage.jsp" target="_self">Return to Main Menu</a>
	<br/><br/><a href="login.jsp"target ="_self" >Logout</a><br><br> 
</div>   
</body>
</html>