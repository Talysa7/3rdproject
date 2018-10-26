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

<title>${page_input}</title>
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
	<div class="container" style="width: 600px">
		<div class="text-center">
			<a href="tripList.go"> <img class="mb-4" src="${project}img/logo_c.png"
				width="100" height="100">
			</a>
			<h2>${page_input}</h2>
			<br>
		</div>
		<form class="form-horizontal" method="post" action="regPro.go"
			name="inputform" onsubmit="return inputcheck()">
			<div class="form-group row">
				<label for="inputID" class="col-sm-2 col-form-label">${str_id}</label>
				<div class="col-sm-8">
					<input type="text" name=user_id class="form-control" id="id_val"
						placeholder="ID" maxlength="20" required autofocus>
					<h6 style="color: red;" id="IdCheckMessagegg"></h6>
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-md btn-secondary"
						onclick="IdCheck()">${btn_confirm}</button>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputPassword" class="col-sm-2 col-form-label">${str_passwd}</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="userPassword1"
						name="passwd" placeholder="비밀번호" maxlength="30" required
						onkeyup="passwordCheckFunction()">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="userPassword2"
						name="repasswd" placeholder="비밀번호 재입력" maxlength="30" required
						onkeyup="passwordCheckFunction()">
					<h6 style="color: red;" id="passwordCheckMessage"></h6>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputNickname" class="col-sm-2 col-form-label">${str_nickname}</label>
				<div class="col-sm-8">
					<input type="text" name="user_name" class="form-control"
						id="name_val" placeholder="Nickname" required>
					<h6 style="color: red;" id="NameCheckMessage"></h6>
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-md btn-secondary"
						onclick="NameCheck()">${btn_confirm}</button>
				</div>
			</div>
			<fieldset class="form-group">
				<div class="row">
					<label class="col-form-label col-sm-2 pt-0">${str_gender}</label>
					<div class="col-sm-10">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender"
								id="gridRadios1" value="1" checked> <label
								class="form-check-label" for="gridRadios1">
								${str_gender_f} </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="gender"
								id="gridRadios2" value="0"> <label
								class="form-check-label" for="gridRadios2">
								${str_gender_m} </label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group">
				<div class="row">
					<label for="email" class="control-label col-sm-2">${str_email}
					</label>
					<div class="col-sm-8">
						<input type="email" name="email1" class="form-control" id="email1"
							placeholder="Enter email" required>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-md btn-secondary" 
						onclick="EmailCheck(inputform.email1.value)">${btn_confirm_smtp}</button>
						<input type="hidden" name="confirm" value="0">
					</div>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="tag" class="control-label col-sm-2" >${str_tag} </label>
				<div class="col-sm-8">				
				<c:forEach var="i" items="${styleTags}">
                	<div class="btn-group btn-group-sm" role="group" aria-label="...">
                		<button type="button" class="btn btn-default"><input type="checkbox" name="user_tag" value="${i.tag_id}">${i.tag_value}</button>
                	</div>
                </c:forEach>
            </div>
                
            </div>
            
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="gridCheck1" name="gridCheck1">
						<label class="form-check-label" for="gridCheck1"> Agree to
							<a href="Conditions.go" target="_blank" class="alert-link">Terms and Conditions</a>
						</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<button type="submit" class="btn btn-lg btn-secondary btn-block">${btn_join}</button>
				</div>
			</div>
		</form>
	</div>
<!-- container -->
</body>
</html>
