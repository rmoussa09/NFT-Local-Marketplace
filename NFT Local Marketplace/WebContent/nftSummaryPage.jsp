<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NFT Summary Page</title>
</head>
<body>
	<div align = "center">
		<h1>Farid's NFT Summary</h1>
        <h2>NFTs Minted</h2>
        <table border="1" cellpadding="6">
           	<tr>
               	<th>NFT ID</th>
               	<th>Name</th>
               	<th>Description</th>
               	<th>Image</th>
               	<th>Price</th>
               	<th>Sell NFT</th>
           	</tr>
               	<tr style="text-align:center">
                   	<td>7</td>
                   	<td>dog</td>
                   	<td>This is a dog</td>
                   	<td><img src="https://welovedoodles.com/wp-content/uploads/2022/02/walter-dog-meme-original-790x1024.jpeg" width= "200" height="200"></td>
                   	<td>3</td>
                   	<td>
                   		<form action = "initialize">
							<input type = "submit" value = "Sell NFT"/>
						</form>
                   	</td>
       	</table>
        
       	<br><br><h2>NFTs Purchased</h2>
       	<table border="1" cellpadding="6">
           	<tr>
               	<th>NFT ID</th>
               	<th>Name</th>
               	<th>Image</th>
               	<th>Price</th>
               	<th>Date</th>
           	</tr>
       	</table>
       
       	<br><br><h2>NFTs Sold</h2>
       	<table border="1" cellpadding="6">
           	<tr>
               	<th>NFT ID</th>
               	<th>Name</th>
               	<th>Image</th>
               	<th>Price</th>
               	<th>Date</th>
            </tr>
        </table>
        <br/><br/><a href="nftSearchPage.jsp" target="_self">Return to NFT Search</a><br/><br/>
		<a href="login.jsp"target ="_self" >Logout</a><br><br> 
	</div>
</body>
</html>