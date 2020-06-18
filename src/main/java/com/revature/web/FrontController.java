package com.revature.web;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Controllers.AccountController;
import com.revature.Controllers.UserController;
import com.revature.Exceptions.AuthorizationException;
import com.revature.Jacksontemplates.MessageTemplate;
import com.revature.Jacksontemplates.PassTimeTemplate;
import com.revature.Jacksontemplates.TransferTemplate;
import com.revature.Jacksontemplates.WithdrawalDepositTemplate;
import com.revature.authorization.AuthorizationService;
import com.revature.models.Account;
import com.revature.models.Users;


public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = -8708258405811685396L;
	private static final UserController userController = new UserController();
	private static final AccountController accountController = new AccountController();
	
	private static final ObjectMapper om = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException,IOException{
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
		String[] portions =URI.split("/");
		//System.out.println(Arrays.toString(portions));
		
		try {
			switch(portions [0]) {
			case "users":
				if(portions.length == 2) {
					
					int id =Integer.parseInt(portions[1]);
					AuthorizationService.guard(req.getSession(false),id,"Employee","Admin");
					Users u = userController.FindByUserId(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(u));
				}
				else { //delegate to controller method to handle all users
					AuthorizationService.guard(req.getSession(false), "Employee","Admin");
					List<Users> all = userController.findAllUsers();
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(all));
				}
				
					break;
//				}
			case "accounts":
				if(portions.length ==3 ) {
					if(portions[2].equals("status") ) {
						//delegate to controller method to get by status
						int id =Integer.parseInt(portions[2]);			
						AuthorizationService.guard(req.getSession(false),"Employee","Admin");		
						List<Account> as = accountController.findByAccount_Status(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(as));
					}
					else  if(portions[1].equals("owner") ){ //delegate to controller method to handle find by owner
						int id =Integer.parseInt(portions[2]);
						HttpSession session = req.getSession(false);
						Users u = (Users) session.getAttribute("currentUser");
						AuthorizationService.guard(session, u.getId(),"Employee","Admin");
						List<Account> ownersAccounts = accountController.findAccountByOwner(id);
						
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(ownersAccounts));
					}
					
				
						
					}
					else if(portions.length ==2 ) {
						//delegate to controller method to get by account id
						int id =Integer.parseInt(portions[1]);
						AuthorizationService.guard(req.getSession(false),id,"Employee","Admin");
						Account a = accountController.findAccountById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
					else if(portions[0].equals("accounts")){ //delegate to controller method to handle all users
						AuthorizationService.guard(req.getSession(false), "Employee","Admin");
						List<Account> all = accountController.findAllAccounts();
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(all));
					}
					
						break;}
				
					
				}catch(AuthorizationException e ) {
					
					res.setStatus(401);
					MessageTemplate message = new MessageTemplate("The incoming token has expired");
					res.getWriter().println(om.writeValueAsString(message));}
			       return;
				}
			
	    	
				@Override
				protected void doPost(HttpServletRequest req, HttpServletResponse res)
						throws ServletException,IOException{
					res.setContentType("application/json");
					res.setStatus(404);
					final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
					String[] portions =URI.split("/");
					System.out.println(Arrays.toString(portions));
						
					try {
					switch(portions[0]) {
					case "accounts":
						if(portions.length ==1) {
						HttpSession session = req.getSession(false);
						Users u = (Users) session.getAttribute("currentUser");
						AuthorizationService.guard(session,u.getId(),"Employee","Admin");			
						if(portions.length ==1) { //submit new account	
					    Account a =om.readValue(req.getReader(),Account.class);
					    accountController.submitAccount(a);
						System.out.println(a);
						
						res.setStatus(201);
						res.getWriter().println("Created");
						
						}break;
						
					 }  else if(portions.length ==1) {
							HttpSession session = req.getSession(false);
							AuthorizationService.guard(session,"Admin");			
						    PassTimeTemplate ptt=om.readValue(req.getReader(),PassTimeTemplate.class);
						    accountController.PassTime(0);
							System.out.println(ptt);
							
							res.setStatus(201);
							MessageTemplate message = new MessageTemplate("{numOfMonths} of interest has been accrued for all your  Savings accounts ") ;
							res.getWriter().println(om.writeValueAsString(message));
							break;
					 }
					
					 if (portions.length ==2) {
						if(portions[1].equals("deposit")) {
						HttpSession session = req.getSession(false);
						Users u = (Users) session.getAttribute("currentUser");
						AuthorizationService.guard(session,u.getId(),"Admin");		
						if(portions.length ==1) { 	
						WithdrawalDepositTemplate wdt =om.readValue(req.getReader(), WithdrawalDepositTemplate.class);
						accountController.deposit(wdt);
						System.out.println(wdt);
						
						res.setStatus(201);
						MessageTemplate message = new MessageTemplate("${amount} has been deposited into your account #{account id}") ;
						res.getWriter().println(om.writeValueAsString(message));}
				       return;
					}
					else if (portions.length == 1) {
						if(portions[1].equals("withdraw")) {
					}
						HttpSession session = req.getSession(false);
						Users u = (Users) session.getAttribute("currentUser");
						AuthorizationService.guard(session,u.getId(),"Admin");			
						if(portions.length ==1) { //withdraw	
					    WithdrawalDepositTemplate wdt =om.readValue(req.getReader(), WithdrawalDepositTemplate.class);
					    accountController.withdraw(wdt);
						System.out.println(wdt);
						
						res.setStatus(201);
						MessageTemplate message = new MessageTemplate("${amount} has been withdrawn from your account #{account id}") ;
						res.getWriter().println(om.writeValueAsString(message));}
				       return;
					}
					else  {
						if(portions.length == 1 && portions[1].equals("transfer")) {
					}
						HttpSession session = req.getSession(false);
						Users u = (Users) session.getAttribute("currentUser");
						AuthorizationService.guard(session,u.getId(),"Admin");				
					    TransferTemplate tt =om.readValue(req.getReader(),TransferTemplate.class);
					    accountController.transfer(tt);
						System.out.println(tt);
						
						res.setStatus(201);
						MessageTemplate message = new MessageTemplate("The amount of ${getAmount} has been transferred from Acvcount #{getSourceAccountId}"
						+ " to Account #{getTargetAccountId}");
						res.getWriter().println(om.writeValueAsString(message));}
					       return;
					}
					
					case "logout":
						if(userController.logOut(req.getSession(false))) {
							res.setStatus(200);
							res.getWriter().println("You have been successfully logged out");
						} else {
							res.setStatus(400);
							res.getWriter().println("You were not logged in to begin with");
						}
						break;
					}
				} catch(AuthorizationException e ) {
					
					res.setStatus(401);
					MessageTemplate message = new MessageTemplate("The incoming token has expired");
					res.getWriter().println(om.writeValueAsString(message));
			     }
					
				} protected void doPut (HttpServletRequest req, HttpServletResponse res)
						throws ServletException,IOException{
					res.setContentType("application/json");
					res.setStatus(404);
					final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
					String[] portions =URI.split("/");
					System.out.println(Arrays.toString(portions));
					
					try {
						switch(portions[0]) {
					case "users":
						
						//update user
							HttpSession session = req.getSession(false);
							Users u = (Users) session.getAttribute("currentUser");
							AuthorizationService.guard(session,u.getId(),"Admin");			 
						    Users u1 =om.readValue(req.getReader(),Users.class);
						    userController.UpdateUser(u1);
							System.out.println(u1);
							res.setStatus(202);
							res.getWriter().println(om.writeValueAsString(u));
							
						break;
					
					//update accounts
						case "accounts"://update account	
							session = req.getSession(false);
							AuthorizationService.guard(session,"Admin");			 
						    Account a =om.readValue(req.getReader(),Account.class);
						    accountController.UpdateAccount(a);
							System.out.println(a);
							
							res.setStatus(202);
							res.getWriter().println(om.writeValueAsString(a));
							
							break;
					}
				
						
						
					} catch(AuthorizationException e ) {
						
						res.setStatus(401);
						MessageTemplate message = new MessageTemplate("The incoming token has expired");
						res.getWriter().println(om.writeValueAsString(message));
				     }
					}
					protected void doDelete (HttpServletRequest req, HttpServletResponse res)
							throws ServletException,IOException{
						res.setContentType("application/json");
						res.setStatus(404);
						final String URI = req.getRequestURI().replace("/rocp-project", "").replaceFirst("/", "");
						String[] portions =URI.split("/");
						System.out.println(Arrays.toString(portions));
			}
			}