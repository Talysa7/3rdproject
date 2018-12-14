<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Custom styles for boarAlbum template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">
<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
	<div class="container">
		<c:choose>
			<c:when test="${myTrips.size() eq 0}">
				<center>
					<h6>${search_no_result}</h6>
					<br>
					<img src="${project}img/paori.png">
				</center>
			</c:when>
			<c:otherwise>
				<c:forEach var="trip" items="${myTrips}">
						<label>게시물 보기 : </label><a href="/3rdProject/trip.go?board_no=${trip.board_no}">link</a><br>
						<label>가는 곳 : </label> ${trip.coord_name}<br>
						<label>날짜 : </label>${trip.start_date}~${trip.end_date}<br>
						<label>평가 : </label><a href="/3rdProject/coordReview.go?coord_id=${trip.coord_id}">link</a><br>
						<c:set var = "now" value = "<%= new java.util.Date()%>" />	
						<c:if test="${now lt trip.end_date }">					
						<label>평판보기 : </label><a href="/3rdProject/memberReview.go?trip_id=${trip.trip_id}">link</a><br>
						</c:if>
					<hr size="1px" color="black" noshade>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>