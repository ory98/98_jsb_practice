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

@WebServlet("/m2update")
public class M2update extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		M2memberDto m2memberDto = M2memberDao.getInstance().getOneMemberInfo((String) session.getAttribute("id"));

		if (m2memberDto.getField() != null) { // 지원분야가 없으면 > 최초지원이면

			String[] skills = m2memberDto.getSkill().split(","); // ',' 로 나눈다.
			for (String skill : skills) {

				if (skill.equals("html"))
					request.setAttribute("html", true);
				if (skill.equals("css"))
					request.setAttribute("css", true);
				if (skill.equals("javascript"))
					request.setAttribute("javascript", true);
				if (skill.equals("java"))
					request.setAttribute("java", true);
				if (skill.equals("jsp"))
					request.setAttribute("jsp", true);
				if (skill.equals("spring"))
					request.setAttribute("spring", true);
			}

			request.setAttribute("m2memberDto", m2memberDto);
			request.setAttribute("isFirstApply", false);
		} else {
			request.setAttribute("isFirstApply", true);
		}

		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/10_update.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		M2memberDto m2memberDto = new M2memberDto();

		m2memberDto.setId(request.getParameter("id"));
		m2memberDto.setPw(request.getParameter("pw"));
		m2memberDto.setName(request.getParameter("name"));
		;
		m2memberDto.setTel(request.getParameter("tel"));
		m2memberDto.setEmail(request.getParameter("email"));
		m2memberDto.setField(request.getParameter("field"));
		m2memberDto.setMajor(request.getParameter("major"));

		String[] temp = request.getParameterValues("skill");
		String skill = "";
		for (int i = 0; i < temp.length; i++) {
			skill += temp[i];
			if (i != temp.length - 1) {
				skill += ",";
			}
		}

		m2memberDto.setSkill(skill);

		M2memberDao.getInstance().updateMember(m2memberDto);

		RequestDispatcher dis = request.getRequestDispatcher("1_mvc2_loginEx/11_updateAction.jsp");
		dis.forward(request, response);
	}

}
