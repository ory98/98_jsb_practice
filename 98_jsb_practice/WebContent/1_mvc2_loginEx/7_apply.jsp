<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>7_apply</title>
</head>
<body>

	<c:if test="${sessionScope.id eq null }">
		<script>
			alert("로그인을 먼저 진행해주세요.");
			location.href = "m2main";
		</script>
	</c:if>
	
	<div align="center">
		<h1>자바 개발자 지원서</h1>
		<p>Java, JSP, Spring에 대한 기술적 이해와 경험이 있는 분을 찾습니다.</p>
		<hr>
		<form action="m2apply" method="post">
			<h4>개인 정보</h4>
			<label>이름 : </label> <input type="text" name="id" value="${m2memberDto.name}" readonly>
			<br>
			<label>연락처 : </label> <input type="text" name="tel" value="${m2memberDto.tel }" readonly>
			<br>
			<label>이메일 : </label> <input type="email" name="email" value="${m2memberDto.email }" readonly>
			<br>
			<h4>지원 분야</h4>
			<label> <input type="radio" name="field" value="publishing">웹 퍼블리싱</label>
			<label> <input type="radio" name="field" value="frontend">프런트앤드</label>
			<label> <input type="radio" name="field" value="application">애플리케이션</label>
			<br>
			<h4>기술 능력</h4>
			<label> <input type="checkbox" name="skill" value="html">HTML</label>
			<label> <input type="checkbox" name="skill" value="css">CSS</label>
			<label> <input type="checkbox" name="skill" value="javascript">JVASCRIPT</label>
			<label> <input type="checkbox" name="skill" value="java">JAVA</label>
			<label> <input type="checkbox" name="skill" value="jsp">JSP</label>
			<label> <input type="checkbox" name="skill" value="spring">SPRING</label>
			<br>
			<h4>전공 분야</h4>
			<label>학과</label> <select id="major" name="major"> 
				<option value="computer">컴퓨터공학과</option>
				<option value="elec">전기전자공학과</option>
				<option value="mechanic">기계공학과</option>
				<option value="indust">산업공학과</option>
			</select> <br> 
			<br>
			<hr>
			<div>
				<input type="submit" value="지원하기"> <input type="reset" value="다시쓰기">
			</div>
		</form>
	</div>

</body>
</html>