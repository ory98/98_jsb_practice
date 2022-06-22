<%@page import="board.MyBoardDao"%>
<%@page import="board.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bWritePro</title>
</head>
<body>

	<% request.setCharacterEncoding("utf-8"); %>
	
	<jsp:useBean id="myBoardDto" class="board.MyBoardDto">
		<jsp:setProperty property="*" name="myBoardDto"/>
	</jsp:useBean>
	
	<%
		MyBoardDao.getInstance().myInsertBoard(myBoardDto);
	%>
	
	<script>
		alert("등록되었습니다.");
		location.href = "bLIst.jsp";
	</script>
</body>
</html>