<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>

<%@include file="../include/header.jsp"%>

<script>
	$(document).ready(function(e){
		
		if('${error}'=='wrong_bno'){
			alert('잘못된 요청입니다.');
			location.href='/board/listAll';
		}
		
		var result = '${msg}';
	    
	    if(result == 'SUCCESS'){
	    	alert("처리가 완료되었습니다.");
	    }
	    
	    if(result=="UPDATE"){
	    	alert('수정 완료되었습니다.');
	    }
	    
	    $("#write-btn").on("click", function(){
	    	var mySession= $("#hdnSession").data('value');
	    		$('#writing').attr("action", "/board/regist");
				$('#writing').attr("method", "get");		
				$('#writing').submit();
		});
	});
</script>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->

			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">RETRIEVE ALL LIST PAGE</h3>
				</div>
				<!-- /board/register로 이동  -->
				<form id="writing">
				<%-- <c:out value="${sessionScope.login }"></c:out> --%>
				<c:if test="${sessionScope.login !=null }">
					<!-- session 정보 확인 후 글쓰기 사용 -->
					<button id="write-btn">글쓰기</button>
				</c:if>
				</form>
				<div class="box-body">
				
<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>


<c:forEach items="${list}" var="boardVO">

	<tr>
		<td>${boardVO.bno}</td>
		<td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
		<td>${boardVO.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
				value="${boardVO.regdate}" /></td>
		<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
	</tr>

</c:forEach>

</table>

				</div>
				<!-- /.box-body -->
				<div class="box-footer">Footer</div>
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
    
    
    
    </script>

<%@include file="../include/footer.jsp"%>
