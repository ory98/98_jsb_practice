<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>

	<div align="center">
		<h1>로그인</h1>
		<p>입사지원을 위해서는 로그인이 필요합니다.</p>
		<hr>
		<form action="login" method="post">
			<p><label>아이디 : <input type="id" name="id" autofocus></label></p>
			<p><label>비밀번호 : <input type="password" name="pw" ></label></p>
			<input type="submit" value="로그인">
		</form>
	</div>

</body>
</html>