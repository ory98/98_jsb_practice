<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>joinAction</title>
</head>
<body>

	<c:choose>
		<c:when test="${isJoin eq true }">
			<script>
				alert("회원가입 되었습니다");
				location.href = "m2main";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("중복된 아이디입니다.");
				history.go(-1);	
			</script>
		</c:otherwise>
	</c:choose>
	
</body>
</html>