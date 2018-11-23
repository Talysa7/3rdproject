<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
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
<link rel="stylesheet" href="${project}travelers_style.css">
<link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body class="mypage">
<div class="container" style="width:600px">
		<div class="text-center">
			<a href="tripList.go">
				<img class="mb-4" src="${project}img/logo_c.png" alt="" width="100" height="100">
			</a>
			<h2>${userDto.user_id} &nbsp; ${msg_main_login}</h2>
			<br>
		</div>
		 <form class="form-horizontal" name="mypage">
			<div class="form-group row">
				<label for="nickname" class="col-sm-2 col-form-label">${str_nickname}</label>
				<div class="col-sm-8">
					&nbsp; ${userDto.user_name}				
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
		    	<label for="email" class="control-label col-sm-2" >${str_email} </label>
		    	<div class="col-sm-8">
		     	 	&nbsp; ${userDto.email}	
		     	 </div>	
			</div>
			<div class="form-group row">
		    	<label for="email" class="control-label col-sm-2" >${str_reg_date} </label>
		    	<div class="col-sm-8">
		     	 	&nbsp; ${userDto.reg_date}	
		     	 </div>	
			</div>
			<div class="form-group row">
		    	<label for="tag" class="control-label col-sm-2" >${str_tag} </label>
		    	<div class="col-sm-8">
		    		<div class="btn-group btn-group-sm disabled" role="group" aria-label="...">
		    			<c:forEach var="tag" items="${userDto.user_tags}">
		    				<button type="button" class="btn btn-default">#${tag.tag_value}&nbsp; </button>
		    			</c:forEach>
					</div>
		     	 </div>	
			</div>
			<div class="form-group row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<button type="button" class="btn btn-lg btn-secondary btn-block" onclick="location='userModPassCheck.go'">${btn_modify}</button>
					<c:if test="${userDto.user_level ne 9}">
					<button type="button" class="btn btn-lg btn-secondary btn-block" onclick="location='userLeave.go'">${btn_user_delete}</button>
					</c:if>
					<button type="button" class="btn btn-lg btn-secondary btn-block" onclick="location='logout.go'">${btn_logout}</button>
				</div>
			</div>
		</form>
		<div id="reputation">
		<span>
		<c:if test="${catchNum ne null}">
			<input type="button" onclick="location='review.go'" value="작성해야 할 평판이 있습니다">
		</c:if> 
		</span>
		&nbsp;&nbsp;
		<span>
			<input type="button" onclick="location='placeWrite.go'" value="여행지를 평가해주세요">
		</span>
		<br> ${userDto.user_name}님의 평점은  		
		<c:if test="${average ne 0 }"> [평균평점 : ${average}점 (5점만점)] </c:if>
		<c:if test="${count ne 0}">[게시글 수 : ${count}개]</c:if>입니다		
		<br>
		<br>
		<c:forEach var="review" items="${reviewDto}">	
		<table border="1">		
		<tr>
			<th>평가자</th> 
			<td>${review.reviewer_id }</td>
		</tr>
		<tr>
			<th>평가점수</th>
			<td>
			<c:choose>
				<c:when test="${review.review_point eq 1}"> ★ </c:when>
				<c:when test="${review.review_point eq 2}"> ★★  </c:when>
				<c:when test="${review.review_point eq 3}"> ★★★  </c:when>
				<c:when test="${review.review_point eq 4}"> ★★★★    </c:when>
				<c:when test="${review.review_point eq 5}"> ★★★★★     </c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th>평가 내용</th>
			<td>${review.review_comment }</td>
		</tr>
		</table>
		<br>
		</c:forEach>
		</div>
</div>	

</body>
</html>