<%@page import="board.MyBoardDao"%>
<%@page import="board.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bUpdate</title>
</head>
<body>

	<%
		int num = Integer.parseInt("num");
		MyBoardDto myBoardDto = MyBoardDao.getInstance().myGetOneBoard(num);
	%>
	
	<form action="bUpdatePro.jsp" method="post">
		<h1>게시글 수정하기</h1>
		<table border="1">
			<tr>
				<td>작성자</td>
				<td><%=myBoardDto.getWriter() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=myBoardDto.getRegDate() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="<%=myBoardDto.getSubject()%>"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td><input type="textarea" cols="60" rows="10" value="<%=myBoardDto.getContent()%>"></td>
			</tr>
		</table>
		<p>
			<input type="hidden" name="num" value="<%=myBoardDto.getNum()%>">
			<input type="submit" value="수정하기">
			<input type="button" value="목록보기" onclick="location.href='bList.jsp'">
		</p>
	</form>

</body>
</html>