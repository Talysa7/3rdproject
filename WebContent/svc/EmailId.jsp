<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="setting.jsp"%>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>${EmailId}</title>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="${project}travelers_style.css">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>
<body class="registration">
		<form class="form-horizontal" method="post" name="inputform" onsubmit="">
			
			<div class="form-group">
				<div class="row">
					<label for="email" class="control-label col-sm-2">${str_email}
					</label>
					<div class="col-sm-8">
						<input type="email" name="email2" class="form-control" id="email2"
							placeholder="Enter email" required>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-md btn-secondary" 
						onclick="EmailIdCheck(inputform.email2.value)">${btn_confirm_smtp}</button>
						<input type="hidden" name="confirm" value="0">
					</div>
				</div>
			</div>
		</form>
<!-- container -->
</body>
</html>
