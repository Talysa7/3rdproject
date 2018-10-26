<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@include file="setting.jsp" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	 <!-- Custom style for this template -->
	<link rel="stylesheet" href="${project}travelers_style.css">
	<script src="${project}script.js"></script>

  </head>
  <c:if test="${sessionScope.user_id eq null}">	    
  <body class="login">
  	<form class="form-signin" method="post" action="loginPro.go" name="loginform" onsubmit="return logincheck()" >
  		<a href="tripList.go">
  			<img class="pt-0 mt-0 mb-4" src="${project}img/logo.jpg" alt="logo" width="400" height="250">
  		</a>
     	<input type="text" id="inputId" name="user_id" class="form-control" 
     		placeholder="${str_id}" required autofocus>
 	 	
 	 	<input type="password" id="inputPassword" name="passwd" class="form-control" 
 	 		placeholder="${str_passwd}" required>
  		
  		<button class="btn btn-lg btn-secondary btn-block" type="submit">${btn_login}</button>
  		<a href="registration.go"><u>${page_input}</u></a>
  		<a onclick="window.open('EmailId.go','아이디 찾기','width=400,height=400,location=no,status=no,scrollbars=no');"><u>${EmailId}</u></a>
  		<a onclick="window.open('EmailPasswd.go','비밀번호 찾기','width=400,height=400,location=no,status=no,scrollbars=no');"><u>${EmailPasswd}</u></a>
  		<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>	
  
  	</form>
  </body>
</c:if>
 <c:if test="${sessionScope.user_id ne null}">
	<c:redirect url="myPage.go"/>		
</c:if>
</html>
   