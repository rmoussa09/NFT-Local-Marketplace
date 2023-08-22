<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search NFTs</title>
</head>
<body>
   <div align="center">
   		<h2>Search NFTs</h2><br/>
   	 	<form action="nftSearch" method="post">
   	 		<input type="text" name="nftSearch" size="50"
             onfocus="this.value=''"><br/><br/><input type="submit"
             value="Search" />
      	</form>
      
        <br/><br/>
        <table border="1" cellpadding="6">
            
       		<table border="1" cellpadding="6">
            <caption><h2>All available NFTs</h2></caption>
            <tr>
              	<th>NFT Id</th>
                <th>NFT Name</th>
                <th>Description</th>
                <th>Listing Price</th>
                <th>Image URL</th>
                <th>Listing Time Duration</th>
                <th>Owner</th>
            </tr>
            <c:forEach var="NFTs" items="${listNFT}">
                <tr style="text-align:center">
               	    <td>${NFTs.nftID} </td>         	    
					<td>
					<form action="viewnftpage" method="get">
                    <input name="nftName" type="submit" value="${NFTs.nftName}" onClick="viewnftpage"/>
                    </form>
                    </td>                    
                    <td>${NFTs.nftDes}</td>
                    <td>${NFTs.price}</td>
                    <td><img src="${NFTs.imageURL}" width= "200" height="200"></td>
                    <td>${NFTs.listTime} days</td>
                    <td>${NFTs.owner}</td> 
                 </tr>
            </c:forEach>
          </table>
        </table>
        
        <br><br><a href="mintNftPage.jsp"target ="_self" >Mint NFT</a>        
        <br><br><a href="nftSummaryPage.jsp"target ="_self" >User NFT Summary</a>   
       	<br/><br/><br/><br/><a href="activitypage.jsp" target="_self">Return to NFT Marketplace</a>
       	<br/><br/><a href="login.jsp"target ="_self" >Logout</a><br><br>      
    </div>   
</body>
</html>