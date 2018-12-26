<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">

<script src="//code.jquery.com/jquery.js"></script>
<script src="${project}script.js"></script>
</head>

<body>
	<!-- Category & Contents Box -->
	<div class="body-box">
		<div class="d-flex justify-content-end">
			<c:if test="${sessionScope.user_id eq null}">
				<a href="login.go"></a>
			</c:if>
		</div>
		<div class="review-list" id="review-list">
			<c:if test="${reviewDto.size() eq 0 }">
			 	<b>작성된 평판이 없습니다</b>
			</c:if>
			<c:if test="${reviewDto.size() ne 0}">
				<c:forEach var="review" items="${reviewDto}">
					<div class="row">
						<div class="col-md-12">
							<div class="card flex-md-row mb-3 shadow-sm h-md-250">
								<div class="card-body d-flex flex-column align-items-start">
									<div class="mb-1 text-muted text-right">
										<i><b>평가자</b></i>&nbsp; ${review.reviewer_id}
									</div>
									<hr size="1px" color="black" noshade>
									<p class="card-text mb-auto">${review.review_comment}</p>
									<hr style="width: 100%">
									<div class="d-flex justify-content-center">
										<div class="p-2">별점 : 
										<c:choose>
											<c:when test="${review.review_point eq 1}"> ★ </c:when>
											<c:when test="${review.review_point eq 2}"> ★★  </c:when>
											<c:when test="${review.review_point eq 3}"> ★★★  </c:when>
											<c:when test="${review.review_point eq 4}"> ★★★★    </c:when>
											<c:when test="${review.review_point eq 5}"> ★★★★★     </c:when>
										</c:choose>
										</div>
										&nbsp;
									</div>
									<!-- card-footer -->
								</div>
								<!-- card-body -->
							</div>
						</div>
						<!-- col-md-12 -->
					</div>
					<!-- 1 row -->
				</c:forEach>
			</c:if>
		</div>
		<div id="trace">
		</div>
		<div id="page">
				<c:if test="${count ne 0}">
					<c:if test="${startPage gt pageBlock}">
						<a href="reviewPage.go">[◀◀] </a>
						<a href="reviewPage.go?pageNum=${startPage-pageBlock}">[◀] </a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i eq currentPage}">
							<p>[${i}]<p>
						</c:if>
						<c:if test="${i ne currentPage}">					
							<a href="reviewPage.go?pageNum=${i}">[${i}] </a>
						</c:if>	
					</c:forEach>
					<c:if test="${pageCount gt endPage}">
						<a href ="reviewPage.go?pageNum=${startPage+pageBlock}">[▶]</a>
						<a href ="reviewPage.go?pageNum=${pageCount}">[▶▶]</a>
					</c:if>	
				</c:if>
			<div class="mx-auto">
				<button type="button" class="btn btn-secondary btn-sm" onclick="goback()">이전페이지로</button>
			</div>
		</div>
	<!-- body box -->
	
			</div>
	<!-- Footer -->
	<footer class="board-footer">
		<p>
			<a href="">Back to top</a>
		</p>
	</footer>
</body>
</html>
