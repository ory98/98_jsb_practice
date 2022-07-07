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
		<c:choose>
			<c:when test="${sessionScope.id eq null }">
				<a href="m2join">회원가입</a><br><br>
				<a href="m2login">로그인</a>
			</c:when>
			<c:otherwise>
				<a href="m2update">입사지원 수정</a><br><br>
				<a href="m2logout">로그아웃</a><br><br>
				<a href="m2delete">탈퇴</a>
			</c:otherwise>
		</c:choose>
	</div>

	<br><br><br><br><br>
	
	<div align="center">
		<a href="m2apply"><img alt="입사지원" src="6_myImg/applyonline.png"></a>
	</div>
	
</body>
</html>