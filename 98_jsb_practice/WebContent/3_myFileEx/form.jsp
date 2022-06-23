<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form</title>
</head>
<body>

	<form action="upload.jsp" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>파일 업로드</legend>
			<p>작성자 : <input type="text" name="userName"></p>
			<p>파일 : <input type="file" name="file"></p>
			<p><input type="submit" value="업로드"></p>
		</fieldset>
	</form>
	
	<fieldset>
		<legend>파일 다운로드</legend>
		<table border="1">
			<tr>
				<td><img src="../6_myImg/404.png" width="70" height="70"></td>
				<td>404 에러 메세지</td>
				<td><a href="download.jsp=fileName=404.png">다운로드</a></td>
			</tr>
			<tr>
				<td><img src="../6_myImg/500.webp" width="70" height="70"></td>
				<td>500 에러 메세지</td>
				<td><a href="download.jsp=fileName=500.webp">다운로드</a></td>
			</tr>
		</table>
	</fieldset>

</body>
</html>