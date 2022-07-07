package mvc2_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2_loginDto.M2memberDto;

@WebServlet("/m2login")
public class M2login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/4_login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		M2memberDto m2memberDto = new M2memberDto();
		
		m2memberDto.setId(request.getParameter("id"));
		m2memberDto.setPw(request.getParameter("pw"));
		
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/5_loginAction.jsp");
		dis.forward(request, response);
	}

}
