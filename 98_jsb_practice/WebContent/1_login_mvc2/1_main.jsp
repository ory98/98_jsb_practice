<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>

	<div align="center">
		<c:when test="">
			<a href="join">회원가입</a><br><br>
			<a href="login">로그인</a>	
		</c:when>
		<c:otherwise>
			<a href="update">입사지원정보 수정</a><br><br>
			<a href="logout">로그아웃</a><br><br>
			<a href="delete">탈퇴</a>
		</c:otherwise>
	</div>
	
	<div align="center">
		<a href="apply"><img scr="6_myImg/applyonline.png" alt="입사지원"></a>
	</div>

</body>
</html>