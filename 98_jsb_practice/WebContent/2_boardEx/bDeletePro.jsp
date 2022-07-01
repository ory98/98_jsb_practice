<%@page import="board.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bDelete</title>
</head>
<body>

	<% request.setCharacterEncoding("utf-8"); %>
	
	<jsp:useBean id="myBoardDto" class="board.MyBoardDto">
		<jsp:setProperty property="*" name="myBoardDto"/>
	</jsp:useBean>
	
	<%
		boolean isDelete = MyBoardDao.getInstance().myDeleteBoard(myBoardDto);
	
		if (isDelete) {
	%>
			<script>
				alert("삭제되었습니다.");
				location.href="bList.jsp";
			</script>	
	<%			
		}
		else {
	%>		
		<script>
			alert("비밀번호가 맞지 않습니다. 확인해주세요.");
			history.go(-1);
		</script>	
	<%		
		}
	
	%>
</body>
</html>