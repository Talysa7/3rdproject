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

<body class="placeWrite">
<div class="container" style="width:600px">
<form class="form-horizontal" name="review" method="post" action="placeWritePro.go">
	<div>	
	<span>일정</span>	
	<span><select id="sel1" name="sel1">	
	<c:forEach var="trip" items="${trip}">	
	<option value="${trip.coord_id}">${trip.coord_name}#${trip.start_date}~${trip.end_date}</option>
	</c:forEach>			
	</select> </span>			
	</div>	
	<div>
	<span>점수</span>
	<span> <input type="checkbox" value="1" name="grade"> ★ </span>
	<span> <input type="checkbox" value="2" name="grade"> ★★</span>
	<span> <input type="checkbox" value="3" name="grade"> ★★★</span>
	<span> <input type="checkbox" value="4" name="grade"> ★★★★</span>	
	<span> <input type="checkbox" value="5" name="grade"> ★★★★★</span>	
	</div>
	<div>내용 </div>
	<div> ※ 여행지에 대한 평가는 수정 및 삭제가 불가하오니 신중하게 작성해주세요 ※</div>
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

