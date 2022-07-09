package mvc2_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc2_loginDao.M2memberDao;
import mvc2_loginDto.M2memberDto;

@WebServlet("/m2apply")
public class M2apply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		M2memberDto m2memberDto = M2memberDao.getInstance().getOneMemberInfo(id);
		
		request.setAttribute("m2memberDto", m2memberDto);
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/7_apply.jsp");
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String field = request.getParameter("field");
		String major = request.getParameter("major");
		String[] temp = request.getParameterValues("skill");
		
		String skill = "";
		for (int i = 0; i < temp.length; i++) {
			skill += temp[i];
			if (i != temp.length -1) {
				skill += ",";
			}
		}
		
		M2memberDto m2memberDto = new M2memberDto();
		m2memberDto.setField(field);
		m2memberDto.setSkill(skill);
		m2memberDto.setMajor(major);
		
		HttpSession session = request.getSession();
		m2memberDto.setId((String)session.getAttribute("id"));
		
		M2memberDao.getInstance().apply(m2memberDto);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/8_applyAction.jsp");
		dis.forward(request, response);
	}

}
