<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload page</title>
</head>
<body>
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data">
		아이디 : <input type="text" name="userid"><br>
		사진 : <input type="file" name="file"><br>
		 <input type="submit">
	</form>
	
	
</body>
</html>