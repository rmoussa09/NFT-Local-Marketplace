import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private nftDAO nftDAO = new nftDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	nftDAO = new nftDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/listUser": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/listNFTs": 
                 System.out.println("The action is: listNFT");
                 listNFTs(request, response);  
        	 case "/profilesearch": 
                 profilesearch(request, response);           	
                 break;
        	 case "/profilesearchroot": 
                 profilesearchroot(request, response);           	
                 break;
        	 case "/nftSearch": 
                 nftSearch(request, response);           	
                 break;
        	 case "/mintNFT":
         		mintNFT(request, response);
         		break;
        	 case "/buy":
        		 buy(request, response);
        		 break;
        	 case "/bigCreator":
        	 		bigCreator(request, response);
        	 break;
        	 case "/bigSeller":
 	 				bigSeller(request, response);
        	 break;
        	 case "/bigBuyer":
 	 				bigBuyer(request, response);
        	 break;
        	 case "/diamondHands":
 	 				diamondHands(request, response);
        	 break;
        	 case "/paperHands":
 	 				paperHands(request, response);
        	 break;
        	 case "/goodBuyers":
 	 		goodBuyers(request, response);
        	 break;
        	 case "/inactiveUsers":
      	 		inactiveUsers(request, response);
             	 break;
        	 case "/stats":
      	 		stats(request, response);
             	 break;
        	 case "/commonNFT":
      	 		commonNFT(request, response);
             	 break;
             case "/viewprofilelist":
        		 viewprofilelist(request, response);
             case "/viewprofileroot":
        		 viewprofileroot(request, response);
             case "/viewnftpage":
        		 viewnftpage(request, response);
        	
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, 1000,0);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    private void profilesearch(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("search started: 0000");

 	        String email = request.getParameter("profilesearch");
 	        List<user> users = userDAO.profilesearch(email);
 	        System.out.println(users);
 	        request.setAttribute("listUser", users);       
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("userSearchPage.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("search finished: 111111111");
 	    }
	    
	    private void viewprofilelist(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("viewprofile started: 00000");
	        String email = request.getParameter("email");
	        System.out.println(email);
	        
	        List<user> users = userDAO.viewprofilelist(email);
	        System.out.println(users);
	        request.setAttribute("listUser", users);     
			RequestDispatcher dispatcher = request.getRequestDispatcher("userProfilePage.jsp");
			dispatcher.forward(request, response);
	        	        
	        
	        System.out.println("viewprofile finished: 11111111111");
	    }
	    
	    private void nftSearch(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("search started: 00000");

 	        String nftName = request.getParameter("nftSearch");
 	        List<nft> NFTs = nftDAO.nftsearch(nftName);
 	        System.out.println(NFTs);
 	        request.setAttribute("listNFT", NFTs);       
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("nftSearchPage.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("search finished: 1111");
 	    }

	     
	    private void viewnftpage(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("viewprofile started: 0000");
	        String nftName = request.getParameter("nftName");
	        System.out.println(nftName);
	        
	        List<nft> NFTs = nftDAO.viewnftpage(nftName);
	        System.out.println(NFTs);
	        request.setAttribute("listNFT", NFTs);     
			RequestDispatcher dispatcher = request.getRequestDispatcher("nftProfilePage.jsp");
			dispatcher.forward(request, response);
	    
	    }
	    
	    private void profilesearchroot(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("search started: 0000");

 	        String email = request.getParameter("profilesearchroot");
 	        List<user> users = userDAO.profilesearch(email);
 	        System.out.println(users);
 	        request.setAttribute("listUser", users);       
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("search finished: 111111111");
 	    }
	    
	    private void viewprofileroot(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("viewprofile started: 00000");
	        String email = request.getParameter("email");
	        System.out.println(email);
	        
	        List<user> users = userDAO.viewprofilelist(email);
	        System.out.println(users);
	        request.setAttribute("listUser", users);     
			RequestDispatcher dispatcher = request.getRequestDispatcher("profilesearchroot.jsp");
			dispatcher.forward(request, response);
	        	        
	        
	        System.out.println("viewprofile finished: 11111111111");
	    }
	         
	    private void listNFTs(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
 	        System.out.println("listNFT started: 00000");

 	     
 	        List<nft> NFTs = nftDAO.listnfts();
 	        System.out.println(NFTs);
 	        request.setAttribute("listNFT", NFTs);  
			

			user user = userDAO.getUser(currentUser);
			request.setAttribute("currentUser", user);
 	        RequestDispatcher dispatcher = request.getRequestDispatcher("nftSearchPage.jsp");       
 	        dispatcher.forward(request, response);
 	     
 	        System.out.println("listNFT finished: 111111");
	 	    }
	    
	    private void mintNFT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String nftName = request.getParameter("nftName");
	   	 	String nftDes  = request.getParameter("nftDes");
	   	 	int price = Integer.parseInt(request.getParameter("price"));
	   	 	String imageURL = request.getParameter("imageURL");
	   	 	String listTime = request.getParameter("listTime");
	   	 	String owner = request.getParameter("owner");
	   	 	if(!nftDAO.checknft(nftName)) {
		   	 		System.out.println("Creation Successful!");
		            nft nfts = new nft(nftName ,nftDes ,imageURL ,owner ,listTime, price);
		   	 		nftDAO.insert(nfts);
		   	 		response.sendRedirect("nftSearchPage.jsp");
	   	 	}
	   	 	else
	   	 	{
	   	 	System.out.println("Failed");
	  		 request.setAttribute("error","Registration failed");
	  		 request.getRequestDispatcher("mintNfTPage.jsp").forward(request, response);
	   	 	}

	    }
	    
	    private void buy(HttpServletRequest request, HttpServletResponse response)
 	            throws SQLException, IOException, ServletException {
	    	System.out.println("buy started: 000");
	    	String nftName = request.getParameter("nftName");
	    	user username = userDAO.getUser(currentUser);
	    	System.out.println(username);
	    	System.out.println(nftName);
	    	nftDAO.transfer(nftName, username.email);
	    	System.out.println(nftName);
	    	System.out.println(username.email);
	    	nftDAO.lowerCash(username.email, nftDAO.getPrice(nftName), nftDAO.getCashBal(username.email));
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("manageNFT.jsp");       
	        dispatcher.forward(request, response);
	        System.out.println("buy finished: 1111");	
	    }
	    
	    private void bigCreator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    
	    
	    private void bigSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void bigBuyer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void diamondHands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void paperHands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void goodBuyers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void inactiveUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void stats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.transfer(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    private void commonNFT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
		{
			
			System.out.println("transfer started: 0000000");
			String nftName = request.getParameter("nftName");
			String owner = request.getParameter("owner");
			
			nftDAO.commonNFT(nftName, owner);
			RequestDispatcher dispatcher = request.getRequestDispatcher("rootView.jsp");
			dispatcher.forward(request, response);
			System.out.println("transfer finished: 11111111111");
		}
	    
	    
	    
	    

	    
	    
}
	        
	        
	    
	        
	        
	        
	    


