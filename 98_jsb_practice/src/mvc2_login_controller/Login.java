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

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/4_login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyMemberDto myMemberDto = new MyMemberDto();
		
		myMemberDto.setId(request.getParameter("id"));
		myMemberDto.setPw(request.getParameter("pw"));
		
		boolean isLogin = MyMemberDao.getinstance().login(myMemberDto);
		
		if (isLogin == true) {
			
			HttpSession session = request.getSession();
			session.setAttribute("id", myMemberDto.getId());
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("1_login_mvc2/5_loginAction.jsp");
		dis.forward(request, response);
	}

}
