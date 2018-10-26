<%@page import="handler.SvcProHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@include file="setting.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="${project}style_loginForm.css">
<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body class="registration">

<c:if test="${result eq 0}"> <!-- 이메일이 없을때-->
	<form method="post" action="" name="emailCheck" class="form-horizontal">
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">${email_auth_guide}</label>
				<div class="col-sm-8">
					<input name="input" class="form-control" type="text" name="emailconfirm" id="EmailVlaue" maxlength="5">
				</div>
				<div class="col-sm-2">
					<input name="inputbutton" class="btn btn-md btn-secondary" type="button" value="${btn_auth}" 
                 	onclick="confirmeMail('${authNum}')">
                </div>
                 	<input type="hidden" name="confirm" value="0">	<!-- 인증성공시 1로변함 -->
			</div>
	</form>
</c:if>

<c:if test="${result eq 1}"><!-- 이메일이 존재할때 -->
	<form class="form-horizontal">
	<div class="form-group row">
			<div class="col-sm-8">
					<label for="inputPassword" class="col-sm-2 col-form-label">${email}${email_used}</label>
			</div>
			<div class="col-sm-2">
					<input class="inputbutton" type="button" class="btn btn-md btn-secondary" value="${btn_ok}"
						onclick="EmailClose()">
			</div>
	</div>
	</form>
</c:if>
</body>