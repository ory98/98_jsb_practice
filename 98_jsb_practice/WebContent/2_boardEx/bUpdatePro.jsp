<%@page import="board.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bUpdatePro</title>
</head>
<body>

	<% request.setCharacterEncoding("utf-8");%>
	
	<jsp:useBean id="myBoardDto" class="board.MyBoardDto"> 
		<jsp:setProperty property="*" name="myBoardDto"/>
	</jsp:useBean>
	
	<%
	
		boolean myIsUpdate = MyBoardDao.getInstance().myUpdateBoard(myBoardDto);
	
		if(myIsUpdate) {
	%>		
			<script>
				alert("수정되었습니다.");
				location.href="bList.jsp";
			</script>
	<%		
		}
		else {
	%>	
			<script>
				alert("패스워드가 일치하지 않습니다. 확인해주세요.");
				history.go(-1);
			</script>
			
	<%		
		}
	
	%>
	
	
</body>
</html>