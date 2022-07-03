package mvc2_login_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2_loginDao.MyMemberDao;
import mvc2_loginDto.MyMemberDto;

@WebServlet("/join")
public class Join extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/2_join.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MyMemberDto myMemberDto = new MyMemberDto();
		
		myMemberDto.setId(request.getParameter("id"));
		myMemberDto.setPw(request.getParameter("pw"));
		myMemberDto.setName(request.getParameter("name"));
		myMemberDto.setTel(request.getParameter("tel"));
		myMemberDto.setEmail(request.getParameter("email"));
		
		boolean isJoin = MyMemberDao.getinstance().joinMember(myMemberDto);
		request.setAttribute("isJoin", isJoin);
		
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/2_joinAction.jsp");
		dis.forward(request, response);
	
	}

}
