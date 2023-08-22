import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/nftDAO")
public class nftDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public nftDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public List<nft> listnfts() throws SQLException {
        List<nft> nfts = new ArrayList<nft>();        
        String sql = "SELECT * FROM NFT WHERE active = 1"; 
        try {
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int nftID = resultSet.getInt("nftID");
            String nftName = resultSet.getString("nftName");
            String nftDes = resultSet.getString("nftDes");
            int price = resultSet.getInt("price");
            String imageURL = resultSet.getString("imageURL");
            String listTime = resultSet.getString("listTime");
            String owner = resultSet.getString("owner"); 
            int active = resultSet.getInt("active");
             
            nfts.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));

        }        
        resultSet.close();
    } catch(SQLException e) {
    	System.out.println(e.toString());
    }   
  
        return nfts;
    }
    
    public boolean checknft(String nftName) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM NFT WHERE nftName = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, nftName);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public List<nft> nftsearch(String nftsearch) throws SQLException {
        List<nft> nfts = new ArrayList<nft>();        
        String sql = "SELECT * FROM NFT WHERE nftName LIKE '%"+nftsearch+"%' AND active = 1"; 
        System.out.println(sql);
        try {
        connect_func();
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        System.out.println(resultSet);
        while (resultSet.next()) {
        	int nftID = resultSet.getInt("nftID");
            String nftName = resultSet.getString("nftName");
            String nftDes = resultSet.getString("nftDes");
            int price = resultSet.getInt("price");
            String imageURL = resultSet.getString("imageURL");
            String listTime = resultSet.getString("listTime");
            String owner = resultSet.getString("owner"); 
            int active = resultSet.getInt("active");
             
            nfts.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));
        }        
        resultSet.close();
        disconnect();
    } catch(SQLException e) {
    	System.out.println(e.toString());
    }   
  
        return nfts;
    }
    
    public List<nft> viewnftpage(String name) throws SQLException {
    	List<nft> nfts = new ArrayList<nft>();
    	String sql = "SELECT * FROM NFT WHERE nftName = '"+name+"'";
    	   System.out.println(sql);
    	   try {
    		   connect_func();      
    	         statement = (Statement) connect.createStatement();
    	         ResultSet resultSet = statement.executeQuery(sql);
    	         
    	         while (resultSet.next()) {
    	        	 int nftID = resultSet.getInt("nftID");
    	             String nftName = resultSet.getString("nftName");
    	             String nftDes = resultSet.getString("nftDes");
    	             int price = resultSet.getInt("price");
    	             String imageURL = resultSet.getString("imageURL");
    	             String listTime = resultSet.getString("listTime");
    	             String owner = resultSet.getString("owner"); 
    	             int active = resultSet.getInt("active");
    	              
    	             nfts.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));
    	         }     
    	   resultSet.close(); 	 
    	   }
		 catch(SQLException e) {
		System.out.println(e.toString());
		 }  
    	   return nfts;
    	   
    }
    
    public void insert(nft nfts) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into NFT(nftName ,nftDes ,imageURL ,owner ,listTime, price, active) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, nfts.getNftName());
			preparedStatement.setString(2, nfts.getNftDes());
			preparedStatement.setString(3, nfts.getImageURL());
			preparedStatement.setString(4, nfts.getOwner());	
			preparedStatement.setString(5, nfts.getListTime());		
			preparedStatement.setInt(6, nfts.getPrice());
			preparedStatement.setInt(7, 1);
			
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public int getPrice(String nftName) throws SQLException {
    	String sql = "select listingPrice from NFT WHERE nftName = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	nftName = resultSet.getString("nftName");
    	int price  = resultSet.getInt("price");
    	preparedStatement.close();
    	
    	return price;
    }
    
    public int getCashBal(String email) throws SQLException{
    	String sql = "select cash_bal from USER WHERE email = ? ";
    	connect_func();
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	ResultSet resultSet1 = preparedStatement.executeQuery();
    	int cash_bal = resultSet1.getInt("cash_bal");
    	preparedStatement.close();
    	
    	return cash_bal;
    }
    
    public boolean lowerCash(String owner, int price, int cash_bal) throws SQLException {
    	String sql2 = "UPDATE User SET cash_bal = ? WHERE email = ?";
    	connect_func();
 
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
    	int difference = price - cash_bal;
    	preparedStatement.setInt(1, difference);
    	preparedStatement.setString(2, owner);

    	
    	boolean rowUpdated = preparedStatement.executeUpdate() > 0;
    	preparedStatement.close();
    	return rowUpdated;
    	
    }
    
    public boolean transfer(String nftName, String owner) throws SQLException {
        String sql = "update NFT set nftOwner = ? where nftName = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, owner);
        preparedStatement.setString(2, nftName);

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }
    
    public List<nft> hotNFT(String name) throws SQLException {
    	List<nft> nfts = new ArrayList<nft>();
    	String sql = "SELECT * FROM NFT WHERE nftName = '"+name+"'";
    	   System.out.println(sql);
    	   try {
    		   connect_func();      
    	         statement = (Statement) connect.createStatement();
    	         ResultSet resultSet = statement.executeQuery(sql);
    	         
    	         while (resultSet.next()) {
    	        	 int nftID = resultSet.getInt("nftID");
    	             String nftName = resultSet.getString("nftName");
    	             String nftDes = resultSet.getString("nftDes");
    	             int price = resultSet.getInt("price");
    	             String imageURL = resultSet.getString("imageURL");
    	             String listTime = resultSet.getString("listTime");
    	             String owner = resultSet.getString("owner"); 
    	             int active = resultSet.getInt("active");
    	              
    	             nfts.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));
    	         }     
    	   resultSet.close(); 	 
    	   }
		 catch(SQLException e) {
		System.out.println(e.toString());
		 }  
    	   return nfts;
    	   
    }
    
    public List<nft> commonNFT(String name1, String name2) throws SQLException {
    	List<nft> nfts1 = new ArrayList<nft>();
    	List<nft> nfts2 = new ArrayList<nft>();
    	List<nft> nfts = new ArrayList<nft>();


    	String sql = "SELECT * FROM NFT WHERE nftName = '"+name1+"'";
    	String sq2 = "SELECT * FROM NFT WHERE nftName = '"+name2+"'";
    	   System.out.println(sql);
    	   try {
    		   connect_func();      
    	         statement = (Statement) connect.createStatement();
    	         ResultSet resultSet1 = statement.executeQuery(sql);
    	         ResultSet resultSet2 = statement.executeQuery(sq2);

    	         
    	         while (resultSet1.next()) {
    	        	 int nftID = resultSet.getInt("nftID");
    	             String nftName = resultSet.getString("nftName");
    	             String nftDes = resultSet.getString("nftDes");
    	             int price = resultSet.getInt("price");
    	             String imageURL = resultSet.getString("imageURL");
    	             String listTime = resultSet.getString("listTime");
    	             String owner = resultSet.getString("owner"); 
    	             int active = resultSet.getInt("active");
    	              
    	             nfts1.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));
    	         }
    	         
    	         while (resultSet2.next()) {
    	        	 int nftID = resultSet.getInt("nftID");
    	             String nftName = resultSet.getString("nftName");
    	             String nftDes = resultSet.getString("nftDes");
    	             int price = resultSet.getInt("price");
    	             String imageURL = resultSet.getString("imageURL");
    	             String listTime = resultSet.getString("listTime");
    	             String owner = resultSet.getString("owner"); 
    	             int active = resultSet.getInt("active");
    	              
    	             nfts2.add(new nft(nftID, nftName, nftDes, imageURL, owner, listTime, price, active));
    	         }
    	         
    	   resultSet.close(); 	 
    	   }
		 catch(SQLException e) {
		System.out.println(e.toString());
		 }  
    	   return nfts;
    	   
    }
    
}