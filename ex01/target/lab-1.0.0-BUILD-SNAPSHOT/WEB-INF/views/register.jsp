<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 화면</title>
</head>
<body>
	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">number</label>
				<input type="text"
				name='bno' class="form-control" placeholder="Enter Title">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label>
				<input type="text"
				name='title' class="form-control" placeholder="Enter Title">
			</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3"
			placeholder="Enter ..."></textarea>
		</div>
		
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label>
			<input type="text"
			name="writer" class="form-control" placeholder="Enter Writer">
		</div>
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="submit" class="btn btn-primary">Submit</button>
		</div>
	</form>
</body>
</html>