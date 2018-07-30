<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

   <%@include file="include/header.jsp" %>
   
   <script>
   $(document).ready(function(e){
	   var formObj = $('#login').find("button[class='btn btn-primary']");
	   formObj.on("click", function(){
		  
			   $('#login').attr("action", "/login");
			   $('#login').attr("method", "get");		
			   $('#login').submit();
		});
   });
   </script>
   <section class="content">
      <div class="row">
         <div class="col-md-12">
            <div class="box">
               <div class="box-header with-border">
                  <h3 class="box-title">HOME PAGE</h3>
                  <form id="login" >
                	 <button type="button" class="btn btn-primary">로그인</button>
                 </form>
               </div>
               <div class="box-header with-border">
               	<h3 class="box-title"></h3>
               </div>
            </div>
         </div>
      </div>
   </section>
   <%@include file="include/footer.jsp" %>