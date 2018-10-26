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
				${search_no_result}
			</c:when>
			<c:otherwise>
				<c:forEach var="trip" items="${myTrips}">
						<label>게시물 보기 : </label><a href="/trip.go?tb_no=${trip.tb_no}">link</a><br>
						<label>가는 곳 : </label> ${trip.country_name}<br>
						<label>날짜 : </label>${trip.cal_start_date}~${trip.cal_end_date}<br>
					<hr size="1px" color="black" noshade>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>