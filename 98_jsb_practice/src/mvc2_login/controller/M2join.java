package mvc2_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2_loginDao.M2memberDao;
import mvc2_loginDto.M2memberDto;

@WebServlet("/m2join")
public class M2join extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/2_join.jsp");
		dis.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		M2memberDto m2memberDto = new M2memberDto();
		
		m2memberDto.setId(request.getParameter("id"));
		m2memberDto.setPw(request.getParameter("pw"));
		m2memberDto.setName(request.getParameter("name"));
		m2memberDto.setTel(request.getParameter("tel"));
		m2memberDto.setEmail(request.getParameter("email"));
		
		boolean isJoin = M2memberDao.getInstance().joinMember(m2memberDto);
		request.setAttribute("isJoin", isJoin);
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/3_joinAction.jsp");
		dis.forward(request, response);
	}

}
