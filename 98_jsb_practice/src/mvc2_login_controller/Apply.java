package mvc2_login_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc2_loginDao.MyMemberDao;
import mvc2_loginDto.MyMemberDto;

@WebServlet("apply")
public class Apply extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		MyMemberDto myMemberDto = MyMemberDao.getinstance().getOneMemberInfo(id);
		
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/7.apply.jsp");
		dis.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/8.applyAction.jsp");
		dis.forward(request, response);
	}

}
