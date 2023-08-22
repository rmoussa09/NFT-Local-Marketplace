<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
    </table>  	  
	<h1>List all users</h1>
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Email</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Address</th>
                <th>Password</th>
                <th>Birthday</th>
                <th>cash_bal($)</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
            	 <tr style="text-align:center">
                    <td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value= "${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.birthday}" /></td>
                    <td><c:out value="${users.cash_bal}"/></td>
                    </tr>
            </c:forEach>
        </table>
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 
	
	<br><br><h2>Users With Most Minted NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="2">Big Creators</th></tr>
    	<tr>
    		<th>User</th>
    		<th># Of NFTs Minted</th>
    	</tr>
    	<tr>
    		<td>sophie@gmail.com</td>
    		<td>2</td>
    	</tr>
    </table>
    	
 	<br><br><h2>Users With Most Sold NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="2">Big Sellers</th></tr>
    	<tr>
    		<th>User</th>
    		<th># of NFTs Sold</th>
    	</tr>
    	<tr>
    		<td>sophie@gmail.com</td>
    		<td>3</td>
    	</tr>
    </table>

	<br><br><h2>Users With Most Purchased NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="2">Big Buyers</th></tr>
    	<tr>
    		<th>User</th>
    		<th># of NFTs Purchased</th>
    	</tr>
    	<tr>
    		<td>susie@gmail.com</td>
    		<td>3</td>
    	</tr>
    </table>

	<br><br><h2>NFTS With Most History Owners</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="4">Hot NFTs</th></tr>
    	<tr>
    		<th>NFT ID</th>
    		<th>Name</th>
    		<th>Image</th>
    		<th># of Previous Owners</th>
    	</tr>
    	<tr>
    		<td>4</td>
    		<td>Mario</td>
			<td><img src="https://upload.wikimedia.org/wikipedia/en/a/a9/MarioNSMBUDeluxe.png" width= "200" height="200"></td>
			<td>3</td>
    	</tr>
    </table>
    
	<br><br><h2>Users Who Shared NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr>
    		<th>First User</th>
    		<th>Second User</th>
    	</tr>
    	<tr>
    		<td align="center">
   	 			<form action="profilesearch" method="post">
   	 				<input type="text" name="profilesearch" size="25"
             		onfocus="this.value=''"><br/><br/><input type="submit"
             		value="Search" />
      			</form>
			</td>
    		<td align="center">
   	 			<form action="profilesearch" method="post">
   	 				<input type="text" name="profilesearch" size="25"
             		onfocus="this.value=''"><br/><br/><input type="submit"
             		value="Search" />
      			</form>
			</td>
    	</tr>
    	<tr>
    		<td colspan="2" align="center">
				<input type="submit" value="Compare"/>
			</td>
		</tr>
    	<tr>
    	</tr>
    	<tr><th colspan="2">Common NFTs</th></tr>
    </table>
    
	<br><br><h2>Users Who Have Never Sold Purchased NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="1">Diamond Hands</th></tr>
    	<tr>
    		<th>Users</th>
    	</tr>
    	<tr>
    		<td>rudy@gmail.com</td>
    	</tr>
    </table>
    
	<br><br><h2>Users Who Sold All Purchased NFTs</h2>
    <table border="1" cellpadding="6" align="center">
    	<tr><th colspan="1">Paper Hands</th></tr>
    	<tr>
    	<th>Users</th>
    	</tr>
    	<tr>
    		<td>susie@gmail.com</td>
    	</tr>
    </table>
    
	<br><br><h2>Users Who Have Purchased More Than 3 NFTs</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="1">Good Buyers</th></tr>
    	<tr>
    		<th>Users</th>
    	</tr>
    	<tr>
    		<td>angelo@gmail.com</td>
    	</tr>
    </table>
    
	<br><br><h2>Users Who Have Never Performed NFT Activities</h2>
    <table border="1" cellpadding="6">
    	<tr><th>Inactive Users</th></tr>
    	<tr>
    		<td>raja@gmail.com</td>
    	</tr>
    	<tr>
    		<td>jeanette@gmail.com</td>
    	</tr>
    </table>  	  	

    <br><br><h2>Search a User's Information</h2>
    <table border="1" cellpadding="6">
    	<tr><th colspan="2">Statistics</th></tr>
    	<tr>
    		<th colspan="2">
   	 	<form action="profilesearchroot" method="post">
   	 		<input type="text" name="profilesearchroot" size="50"
             onfocus="this.value=''"><br/><br/><input type="submit"
             value="Search" />
      	</form>
      		</th>
      	</tr>
      	    <c:forEach var="users" items="${listUser}">
           	<tr style="text-align:center">
               	<td>
					<form action="viewprofileroot" method="get">
						<input  name="email" type="submit" value="${users.email}" onClick="viewprofileroot"/>
					</form>
				</td>
           	</tr>
     		</c:forEach>
    </table>
</body>
</html>