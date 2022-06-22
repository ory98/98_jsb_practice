<%@page import="board.MyBoardDao"%>
<%@page import="board.MyBoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bList</title>
</head>
<body>

	<h1>게시글 보기</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	<%
		ArrayList<MyBoardDto> myBoardList = MyBoardDao.getInstance().myGetAllboard();
		for (MyBoardDto myBoardDto : myBoardList) {
	%>		
			<tr>
				<td><%=myBoardDto.getNum() %></td>
				<td><a href="bInfo.jsp?num=<%=myBoardDto.getNum() %>"><%=myBoardDto.getSubject()%></a></td>
				<td><%=myBoardDto.getWriter() %></td>
				<td><%=myBoardDto.getRegDate() %></td>
				<td><%=myBoardDto.getReadCount() %></td>
			</tr>	
	<%		
		}
	%>	
	
		<tr>
			<td colspan="5">
				<input type="button" value="글쓰기" onclick="location.href='bWrite.jsp'"/>
			</td>
		</tr>
		
		
		
		
		
	</table>

</body>
</html>