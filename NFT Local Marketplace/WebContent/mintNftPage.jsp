<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mint NFT</title>
</head>
<body>
   <div align="center">
   		<h2>Create a New NFT</h2><br/>
        <form action="mintNFT">
        <table border="1" cellpadding="6">    
            <tr>
				<th>Name</th>
					<td>
						<input type="text" name="nftName" size="50">
					</td>
            </tr>
             <tr>
				<th>Description</th>
					<td>
						<input type="text" name="nftDes" size="50">
					</td>
            </tr>
           	<tr>
               	<th>Image URL</th>
					<td>
						<input type="text" name="imageURL" size="50">
					</td>
            </tr>
            
            <tr>
				<th>Price</th>
					<td>
						<input type="text" name="price" size="50">
					</td>
            </tr>
            
            <tr>
				<th>Owner</th>
					<td>
						<input type="text" name="owner" size="50">
					</td>
            </tr>
            
            <tr>
            	<td colspan = "2" align="center">
            		<input type="submit" value="mintNFT">
            	</td>
            </tr>
        </table>
        </form>
        
        <br/><br/><a href="nftSearchPage.jsp" target="_self">Return to NFT Search</a>
       	<br/><br/><br/><br/><a href="activitypage.jsp" target="_self">Return to NFT Marketplace</a>
       	<br/><br/><a href="login.jsp"target ="_self" >Logout</a><br><br>      
    </div>   
</body>
</html>