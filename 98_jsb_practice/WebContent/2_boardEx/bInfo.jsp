<%@page import="board.MyBoardDao"%>
<%@page import="board.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bInfo</title>
</head>
<body>

	<%
		int num = Integer.parseInt(request.getParameter("num"));
		MyBoardDto myBoardDto = MyBoardDao.getInstance().myGetOneBoard(num);
	%>
	
	<h2>게시글 보기</h2>
	<br>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td><%=myBoardDto.getNum() %></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><%=myBoardDto.getReadCount() %></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=myBoardDto.getWriter() %></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><%=myBoardDto.getRegDate() %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=myBoardDto.getEmail()%></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><%=myBoardDto.getSubject() %></td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><%=myBoardDto.getContent()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정하기" onclick="location.href='bUpdate.jsp?num=<%=myBoardDto.getEmail()%>'">
				<input type="button" value="삭제하기" onclick="location.href='bDelete.jsp?num=<%=myBoardDto.getNum()%>'">
				<input type="button" value="목록가기" onclick="location.href='bList.jsp'">
			</td>
		</tr>
		
	</table>

</body>
</html>