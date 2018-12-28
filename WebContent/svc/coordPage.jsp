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
				<c:when test="${coord.size() eq 0}">
					<center>
						<h6>${search_no_result}</h6>
						<br>
						<img src="${project}img/paori.png">
					</center>
				</c:when>
				<c:otherwise>
				<div class="row">
				<c:forEach var="coord" items="${coord}" varStatus="var">
					<div class="col-md-3">
						<div class="card mb-3 shadow-sm">
							<img class="card-img-top" src="${trip.thumbnail}">
							<div class="card-body">				
								<strong class="d-inline-block mb-2">						
						  			<label>장소 : </label>${coord.coord_name}<br>
						  			&nbsp;&nbsp; ㄴ<label>나라 : </label>${coord.country_name}<br>
								</strong>
								<h6 class="mb-0">
								<c:if test="${coord.average ne 'NaN'}">
									<label>평균평점 : </label>${coord.average} <br>
								</c:if>									
								<c:forEach var="entry" items="${coord.map}" begin="0" end="4">
									<label class="btn btn-sm taglist"># ${entry.key}</label>
								</c:forEach>	
								</h6>												
								<button type="button" class="btn btn-sm btn-secondary btn-block" onclick="location='coordReview.go?coord_id=${coord.coord_id}'">평판보기</button>								
								
							</div>
						</div>
					</div>								
			</c:forEach>
			</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
