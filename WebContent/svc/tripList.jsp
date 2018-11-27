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
		<!-- search -->
		<div class="container justify-content-end" style="width:25%;border:solid 1px;float:right">
			<form method="post">
				<div class="row"><input type="text" placeholder="장소검색창 위치"></div>
				<div class="row" style="border:solid 1px">지도 상자</div>
				<div class="row">날짜 검색</div>
				<div class="row"><input type="text" placeholder="일정 검색"></div>
				<div class="row">일정 검색</div>
				<div class="row"><input type="text" placeholder="장소검색창 위치"></div>
				<input type="submit" class="btn btn-sm" value="검색">
			</form>
		</div>
		<!-- search -->
		<div class="d-flex justify-content-flex-start">
			<c:if test="${sessionScope.user_id eq null}">
				<a href="login.go"></a>
			</c:if>
			<c:if test="${sessionScope.user_id ne null}">
				<a href="tripWrite.go">
					<img src="${project}img/compose_icon.png" width="120" height="40">
				</a>
			</c:if>
		</div>
		<div class="board-list" id="board-list">
			<c:if test="${postList.size() ne 0}">
				<c:forEach var="post" items="${postList}">
					<div class="row">
						<div class="col-md-6">
							<div class="card flex-md-row mb-3 shadow-sm h-md-250">
								<div class="card-body d-flex flex-column align-items-start">
									<strong class="d-inline-block mb-2"> 
									<c:forEach var="trip" items="${post.tripLists}">
					              		${trip.coord_name}
					              	</c:forEach>
									</strong>
									<h3 class="mb-0">
										<a class="text-dark" href="trip.go?board_no=${post.board_no}">
										<c:if test="${post.board_level eq 1}">
											 ${trip_notice_1}
										</c:if>
											${post.board_title}
										</a>
									</h3>
									<div class="mb-1 text-muted text-right">
										<i><b>With</b></i>&nbsp; ${post.user_name}
									</div>
									<hr size="1px" color="black" noshade>
									<p class="card-text mb-auto">${post.board_content}</p>
									<hr style="width: 100%">
									<div class="d-flex justify-content-center">
										<div class="p-2">조회수:${post.board_view_count}</div>
										&nbsp;
										<div class="p-2">
											<c:forEach var="tag" items="${post.board_tags}">
												<label class="btn btn-sm taglist"> # ${tag.tag_value} </label>
											</c:forEach>
										</div>
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
		<!-- board list -->
		<form name="tripListInfo">
			<input type="hidden" name="next_row" value="${next_row}">
		</form>
		<div id="loading-button">
			<button type="button" class="btn btn-dark col-md-6"
				onclick="loadMoreList(${next_row})">Load more...</button>
		</div>
	</div>
	<!-- body box -->

	<!-- Footer -->
	<footer class="board-footer">
		<p>
			<a href="">Back to top</a>
		</p>
	</footer>
</body>
</html>
