package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Jacksontemplates.LoginTemplate;
import com.revature.models.Users;
import com.revature.services.UserServices;

/**
 * Servlet implementation class Login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -4415167968525232599L;
	private static final ObjectMapper om = new ObjectMapper();
	private static final UserServices service = new UserServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
			
			}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		/*
		BufferedReader reader = req.getReader();// get reader from server
	 	StringBuilder sb =new StringBuilder();
		String line;//variable to store  each line
		while( (line = reader.readLine()) != null) {//when null body over
			sb.append(line);// appending "line" with what is read - each line in order
			String body = sb.toString();
    	referrence variable    class value passed into jackson and jackson uses constructor and getters and setters*/
			//LoginTemplate lt = om.readValue(body, LoginTemplate.class );
			
	        HttpSession session = req.getSession();
	        
	        Users current =(Users) session.getAttribute("currentUser");
	        
	        if(current != null) {
	        	
	        	res.setStatus(400);
	        	res.getWriter().println("You are already logged in as user" + current.getUsername());
	        	return;
	        }
	        		
	        
			LoginTemplate lt = om.readValue(req.getReader(), LoginTemplate.class);
			System.out.println(lt);
		
			Users u = service.login(lt);
			PrintWriter writer = res.getWriter();
			if(u==null) {
				
				res.setStatus(400);
				writer.println("Username  or password is incorrect");
				return;
			}
			
			
			              // key must be string , value can be anything
			session.setAttribute("currentUser", u);
			
			res.setStatus(200);
			writer.println(om.writeValueAsString(u));
			
			
			
			res.setContentType("application/json");
			}
	
	
	
}