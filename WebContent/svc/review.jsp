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
<body class="review">
<div class="container" style="width:600px">
<form class="form-horizontal" name="review" method="post" action="reviewPro.go">
	<div>			
		<span>일정</span>	
		<span>
			<select id="sel1" name="sel1">	
				<c:forEach var="trip" items="${trip}">	
				<c:set var = "now" value = "<%= new java.util.Date()%>" />	
				<c:if test="${now gt trip.end_date }">			
				<option value="${trip.trip_id}">${trip.coord_name} : ${trip.start_date}~${trip.end_date}</option>	
				</c:if>			
				</c:forEach>									
			</select> 
		</span>		
		<span>평판대상</span>				
		<span>
			<select id="sel2" name="sel2">
				<c:forEach var="trip" items="${trip}">	
				<c:forEach var="member" items="${trip.review_members}">		
				<c:if test="${member.user_id ne user}">		
				<option value="${member.user_id}" class="${trip.trip_id}">${member.user_id}</option>
				</c:if>		
				</c:forEach>
				</c:forEach>		
			</select>
		</span>	
	</div>	
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src='//rawgit.com/tuupola/jquery_chained/master/jquery.chained.min.js'></script>
	<script>
	  $("#sel2").chained("#sel1");
	</script>		
	<div>
		<span>점수</span>
		<span> <input type="checkbox" value="1" name="grade"> ★ </span>
		<span> <input type="checkbox" value="2" name="grade"> ★★</span>
		<span> <input type="checkbox" value="3" name="grade"> ★★★</span>
		<span> <input type="checkbox" value="4" name="grade"> ★★★★</span>	
		<span> <input type="checkbox" value="5" name="grade"> ★★★★★</span>	
	</div>
	<div>내용 </div>
	<div id ="red"> ※ 동행인에 대한 평판글은 수정 및 삭제가 불가하오니 이점 유의하여 작성해주세요 ※</div>
	<div> <textarea name="textarea" rows="10" cols="50"></textarea> </div>
	<div>
		 <button type="submit" class="btn btn-secondary btn-sm">제출</button>
		  <button type="reset" class="btn btn-secondary btn-sm">취소</button>
		 <button type="button" class="btn btn-secondary btn-sm" onclick="goback()">이전페이지로</button>		
	</div>
</form>

</div>	

</body>
</html>