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
		<!-- mid area row start -->
			<div class="row">
			
				<!-- left area start -->
				<div id="left">
				<div class="coordReview-list" id="coordReview-list">
				<c:choose>
				<c:when test="${coord.size() eq 0}">
					<center>
						<h6>${search_no_result}</h6>
						<br>
						<img src="${project}img/paori.png">
					</center>
				</c:when>
				<c:otherwise>
				<c:forEach var="coord" items="${coord}" varStatus="var">
					<!-- left row start -->
				<div class="row">
					<!-- col-md-11 start -->
					<div class="col-md-5">
						<!-- left card start -->
						<div class="card flex-row mb-2 shadow-sm h-md-250">
							<!-- card body start -->
							<div class="card-body">								
								<strong class="d-inline-block mb-2">						
						  			<label>장소 : </label>${coord.coord_name}<br>
						  			&nbsp;&nbsp; ㄴ<label>나라 : </label>${coord.country_name}<br>
								</strong>
								<h6 class="mb-0"></h6>
								<c:if test="${coord.average ne 'NaN' or coord.average ne null }">
									<label>평균평점 : </label>${coord.average} <br>
								</c:if>
									
									<c:forEach var="entry" items="${coord.map}">
										<label class="btn btn-sm taglist">${entry.key} # ${entry.value}번  사용됨</label>
									</c:forEach>
									<c:forEach var="review" items="${coord.coordReview}" varStatus="status">					
										<button type="button" class="btn btn-sm btn-secondary btn-block" onclick="location='coordReview.go?coord_id=${coord.coord_id}'">평판보기</button>								
									</c:forEach>
								</h6>
							</div>
						</div>
					</div>
					<!-- thumbnail start -->
					<div class="thumbnail">
						<img src="${post.thumbnail}" class="img-fluid">
					</div>
					<!-- thumbnail end -->
				</div>		
								
			</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

	<!-- left area end -->
	</div>
	</div>
	
			</div>
	<!-- Footer -->
	<footer class="board-footer">
		<p>
			<a href="">Back to top</a>
		</p>
	</footer>
</body>
</html>
