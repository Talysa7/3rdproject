<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="setting.jsp"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${page_modify}</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="${project}travelers_style.css">
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
</head>

<body class="usermodify">

	<div class="container" style="width: 600px">
		<div class="text-center">
			<a href="tripList.go"> <img class="mb-4"
				src="${project}img/logo_c.png" alt="" width="100" height="100">
			</a>
			<h4>${msg_passwdform}</h4><br>
		</div>
		<form class="form-horizontal" method="post" action="userLeavePro.go" name="userModPassCheck">
			<div class="form-group row">
				<label for="user_id" class="col-sm-2 col-form-label">${str_id}</label>
				<div class="col-sm-8">&nbsp; ${sessionScope.user_id}</div>
				<input type="hidden" name="user_id" value="${sessionScope.user_id}">
			</div>
			<div class="form-group row">
				<label for="nickname" class="col-sm-2 col-form-label">${str_passwd}</label>
				<div class="col-sm-8"><input type="password" name="passwd" class="form-control"
				maxlength="20" required autofocus><br></div>
			</div>
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<button type="submit" class="btn btn-lg btn-secondary btn-block">${btn_ok}</button>
					<button type="button" class="btn btn-lg btn-secondary btn-block"
						onclick="location='myPage.go'">${btn_cancel}</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
