<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
<script>

	function formValidationCheck(){
		
		var id = document.getElementById("id");
		if (id.value.lengh == 0) {
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}
		
		var pw = document.getElementById("pw");
		if (pw.value.lengh == 0) {
			alert("비밀번호를 입력하세요.");
			pw.focus();
			return false;
		}
		
		var name = document.getElementById("name");
		if (name.value.lengh == 0) {
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}
		
		var tel = document.getElementById("tel");
		if (tel.value.lengh == 0) {
			alert("전화번호를 입력하세요.");
			tel.focus();
			return false;
		}
		
		var email = document.getElementById("email");
		if (email.value.lengh == 0) {
			alert("이메일을 입력하세요.");
			email.focus();
			return false;
		}
		
		return true;
		
	}

</script>
</head>
<body>
	<div align="center">
		<h1>회원가입</h1>
		<p>메가 아카데미를 찾아주셔서 감사합니다.</p>
		<hr>
		<form action="m2join" method="post" onsubmit="formValidationCheck()">
			<h4>로그인 정보</h4>
			<label for="id">아이디 : <input type="text" id="id" name="id" autofucus></label>
			<br><br>
			<label for="pw">비밀번호 : <input type="password" id="pw" name="pw"></label>
			<br><br>
			<label for="name">이름 : <input type="text" id="name" name="name" placeholder="공백없이 입력하세요"></label>
			<br><br>
			<label for="contact">전화번호 : <input type="text" id="tel" name="tel" size="20" placeholder="000-0000-0000"></label>
			<br><br>
			<label for="email">이메일 : <input type="email" id="email" name="email"></label>
			<br><br><br>	
			<div align="center">
				<input type="submit" value="회원가입">
				<input type="reset" value="다시쓰기">
			</div>
		</form>
	</div>
</body>
</html>