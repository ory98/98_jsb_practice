<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
	
		String id = (String)session.getAttribute("id");
	
		if (id == null) {
	%>
		<fieldset>
			<legend>홈페이지에 오신 것을 환영합니다.</legend>
			<p><a href="insert.jsp">1. 회원가입</a></p>
			<p><a href="login.jsp">2. 로그인</a></p>
		</fieldset>
	<%		
		}
		else {
	%>
		<fieldset>
			<legend>홈페이지에 오신 것을 환영합니다.</legend>
			<p><a href="logout.jsp">1. 로그아웃</a></p>
			<p><a href="delete.jsp">2. 탈퇴</a></p>
			<p><a href="update.jsp">3. 수정</a></p>
		</fieldset>
	<%		
		}
	%>

</body>
</html>