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
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom style for this template -->
<link rel="stylesheet" href="${project}travelers_style.css">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<c:if test="${result eq 1}">
	<body class="usermodify">
		<div class="container" style="width: 600px">
			<div class="text-center">
				<a href="tripList.go"> <img class="mb-4"
					src="${project}img/logo_c.png" alt="" width="100" height="100">
				</a>
				<h4>${page_modify}</h4>
				<br>
			</div>
			<form class="form-horizontal" action="userModPro.go" name="userMod">
				<div class="form-group row">
					<label for="nickname" class="col-sm-2 col-form-label">${str_id}</label>
					<div class="col-sm-8">&nbsp; ${userDto.user_id}</div>
					<input type="hidden" name="user_id" value="${user_id}">
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
				<div class="form-group row">
					<label for="gender" class="col-sm-2 col-form-label">${str_gender}</label>
					<div class="col-sm-8">
						&nbsp;
						<c:if test="${userDto.gender eq 0}">
						${str_gender_m}
					</c:if>
						<c:if test="${userDto.gender eq 1}">
						${str_gender_f}
					</c:if>
					</div>
				</div>
				<div class="form-group row">
					<label for="email" class="control-label col-sm-2">${str_email}
					</label>
					<div class="col-sm-8">&nbsp; ${userDto.email}</div>
				</div>
				<div class="form-group row">
					<label for="tag" class="control-label col-sm-2" >${str_tag}
					</label>
					<div class="col-sm-8">
							<c:forEach var="i" items="${tagList}">
								<c:set var="count" value="0"/>
								<c:set var="size" value="${userTags.size()}"/>
								<c:choose>
									<c:when test="${size eq 0}">
										<button type="button" class="btn btn-default"><input type="checkbox" name ="tags" value="${i.tag_id}"># ${i.tag_value}</button>
									</c:when>
									<c:otherwise>
										<c:forEach var="j" items="${userTags}">
											<c:choose>
												<c:when test="${i.tag_value==j.tag_value}">
												 	<div class="btn-group btn-group-sm" role="group" aria-label="...">
												 	<button type="button" class="btn btn-default"><input type="checkbox" name ="tags" value="${i.tag_id}" checked># ${i.tag_value}</button>
												 	</div>
												 </c:when>
												 <c:otherwise>
												 	<c:choose>
												 		<c:when test="${count<size-1}">
												 			<c:set var="count" value="${count=count+1}"/>
												 		</c:when>
												 		<c:otherwise>
												 			<div class="btn-group btn-group-sm" role="group" aria-label="...">
												 			<button type="button" class="btn btn-default"><input type="checkbox" name ="tags" value="${i.tag_id}"># ${i.tag_value}</button>
												 			</div>
												 		</c:otherwise>
												 	</c:choose>
												 </c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				<div class="form-group row">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<button type="submit" class="btn btn-lg btn-secondary btn-block">${btn_modify}</button>
						<button type="button" class="btn btn-lg btn-secondary btn-block"
							onclick="location='myPage.go'">${btn_cancel}</button>
					</div>
				</div>
			</form>
		</div>
	</body>
</c:if>
<c:if test="${result eq 0}">
	<script type="text/javascript">
		erroralert("${modError_passwd}");
	</script>
</c:if>
<c:if test="${result eq -1}">
	<script type="text/javascript">
		erroralert("${modError_noUser}");
	</script>
</c:if>
