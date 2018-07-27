<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 완료 화면</title>
</head>
<body>
	가입 완료<br>
	<hr>
	번호 : ${newMember.bno}<br>
	제목 : ${newMember.title }<br>
	내용 : ${newMember.content}<br>
	작성자 : ${newMember.writer}<br>
	등록일 : ${newMember.regdate}<br>
	조회수 : ${newMember.viewcnt}<br>
		
</body>
</html>