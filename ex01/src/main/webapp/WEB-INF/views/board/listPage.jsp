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
			location.href='/board/listPage';
		}
		
		var result = '${msg}';
	    
	    if(result == 'SUCCESS'){
	    	alert("처리가 완료되었습니다.");
	    }
	    
	    if(result=="UPDATE"){
	    	alert('수정 완료되었습니다.');
	    }
	    
	    $("#write-btn").on("click", function(){
	    	
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
		<th style="width: 40px">FILE</th>
	</tr>


<c:forEach items="${list}" var="boardVO">

	<tr>
		<td>${boardVO.bno}</td>
		<%-- <td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td> --%>
		<td>
			<a
			href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)
			 }&bno=${boardVO.bno }'>
			 ${boardVO.title}</a>
		</td>
		<td>${boardVO.writer}</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
				value="${boardVO.regdate}" /></td>
		<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
		<td><span class="badge bg-red">${boardVO.oldname } <img src="${thumb}"></span></td>
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
	
<div class="text-center">
	<ul class="pagination">
		<c:if test="${pageMaker.prev }">
			<li><a href="listPage?page=${pageMaker.startPage -1 }">&laquo;</a></li>
		</c:if>
	
		<c:forEach begin="${pageMaker.startPage}"
			end="${pageMaker.endPage }" var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
				<a href="listPage?page=${idx }">${idx }</a>
			</li>	
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
			<li><a href="listPage?page=${pageMaker.endPage +1 }">&raquo;</a></li>
		</c:if>
		
	</ul>
</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>
    
    
    
    </script>

<%@include file="../include/footer.jsp"%>
