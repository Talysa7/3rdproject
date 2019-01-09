<%@page import="db.ReviewDataBean"%>
<%@page import="java.util.List"%>
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
			<c:forEach var="bean" items="${users}">
				<c:forEach var="best" items="${bean}" varStatus="status">
				<c:choose>
					<c:when test="${status.first}">
						<h6> ${best.user_id}의 최고 평판</h6>	
					</c:when>
					<c:when test="${status.last }">
						<h6> ${best.user_id}의 최저 평판</h6>
					</c:when>
					<c:otherwise>
						<h6> ${best.user_id}의 최신 평판</h6>
					</c:otherwise>
				</c:choose>
				    	<label for="comment" class="control-label col-sm-2" >평가자 : </label> ${best.reviewer_id }<br>
						<label for="comment" class="control-label col-sm-2" >점수 : </label><c:choose>
									<c:when test="${best.review_point eq 1}"> ★ </c:when>
									<c:when test="${best.review_point eq 2}"> ★★  </c:when>
									<c:when test="${best.review_point eq 3}"> ★★★  </c:when>
									<c:when test="${best.review_point eq 4}"> ★★★★    </c:when>
									<c:when test="${best.review_point eq 5}"> ★★★★★     </c:when>
							   </c:choose><br>
						<label for="comment" class="control-label col-sm-2" >평판내용 : </label>${best.review_comment}<br>								
				</c:forEach>
				<hr size="1px" color="black" noshade>		
				</c:forEach>															
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>