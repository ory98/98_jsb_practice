<%@page import="board.MyBoardDao"%>
<%@page import="board.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bDelete</title>
</head>
<body>

	<%
		int num = Integer.parseInt(request.getParameter("num"));
		MyBoardDto myBoardDto = MyBoardDao.getInstance().myGetOneBoard(num);
	%>
	
	<form method="post" action="bDeletePro.jsp">
		<h2>게시글 삭제하기</h2>
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
				<td><%=myBoardDto.getSubject() %></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="num" value="<%=myBoardDto.getNum()%>">
					<input type="submit" value="삭제하기">
					<input type="button" value="목록보기" onclick="location.href='bList.jsp'">
				</td>
			</tr>	
		</table>
	</form>
	

</body>
</html>