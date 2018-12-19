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


<script src="//code.jquery.com/jquery.js"></script>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- Timepicker -->
<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css"/>

<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Work+Sans"
	rel="stylesheet">
<link rel="stylesheet" href="${project}travelers_style.css">

<script src="${project}script.js"></script>

</head>

<body>
	<!-- body box start -->
	<div class="body-box">
		<!-- container area start -->
		<div class="container">
			<!-- top area start -->
			<div id="top">
				<!-- write button -->
				<div class="row justify-content-end">
					<c:if test="${sessionScope.user_id ne null}">
						<a href="tripWrite.go">
							<img src="${project}img/compose_icon.png" width="120" height="40">
						</a>
					</c:if>
				</div>
				<!-- write button -->
			</div>
			<!-- top area end -->
			
			<!-- mid area row start -->
			<div class="row">
			
				<!-- left area start -->
				<div id="left">
					<!-- board list start -->
					<div class="board-list" id="board-list">
						<c:if test="${postList.size() ne 0}">
							<c:forEach var="post" items="${searchReceive}">
								<!-- left row start -->
								<div class="row">
									<!-- col-md-11 start -->
									<div class="col-md-11">
										<!-- left card start -->
										<div class="card flex-md-row mb-3 shadow-sm h-md-250">
											<!-- card body start -->
											<div class="card-body">
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
												<hr style="width: 100%" noshade>
												<p class="card-text mb-auto">${post.board_content}</p>
												<hr style="width: 100%" noshade>
											</div>
											<!-- card body end -->
											<!-- card center start -->
											<div class="card-center justify-content-center">
												<div class="p-2">
													조회수:${post.board_view_count}
												</div>
												<div class="p-2">
													<c:forEach var="tag" items="${post.board_tags}">
														<label class="btn btn-sm taglist"> # ${tag.tag_value} </label>
													</c:forEach>
												</div>
											</div>
											<!-- card center end-->
										</div>
										<!-- left card end -->
									</div>
									<!-- col-md-11 end -->
								</div>
								<!-- left row end -->
							</c:forEach>
						</c:if>
					</div>
					<!-- board list end -->
				</div>
				<!-- left area end -->
				
				<!-- <!-- aside area start -->
				<aside id="right">
					<div class="col-sm-15">
						<form method="post" action="advanceSearch.go">
							<!-- pac-card start -->
							<div class="pac-card" id="pac-card">
							<!-- GOOGLEMAP RADIO BUTTON  -->	
								<div id="type-selector" class="pac-controls">
									<input type="radio" name="type" id="changetype-all" checked="checked">
									<label for="changetype-all">All</label>
								</div>	
							<!-- 	GOOGLEMAP LOCATION TEXTAREA  -->  
								<div id="pac-container">
									<input id="pac-input" name="searchSite" type="text" placeholder="Enter a location">
								</div>
							</div>
							<!-- pac card end -->
							<div id="map"></div>
							<div id="infowindow-content" display="none">
								 <span id="place-name"  class="title"></span><br>
								 <span id="place-address"></span>
							</div>
							<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbvvT_kUPxmLL9PHcM9gp2qibpr8sThVQ&libraries=places&callback=initMap" async defer></script>
							<label>날짜</label>
							<div class="row" style="padding: 0px 0px 10px 14px;">
								<input id="fromDate" name="fromDate" width="45%"/>
								<script>
							        $('#fromDate').datepicker({
							            uiLibrary: 'bootstrap4'
							        });
								</script>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input id="toDate" name="toDate" width="45%"/>
								<script>
							        $('#toDate').datepicker({
							            uiLibrary: 'bootstrap4'
							        });
							    </script>
							</div>
							<label>기간</label>
							<div  class="row" style="padding: 0px 0px 10px 14px;">
								<input type="text" class="form-control col-md-9" name="searchPeriod" placeholder="일 단위로 입력하세요">	&nbsp;	
								<input type="submit" class="btn btn-secondary" value="검색">
							</div>
							<label>태그</label>
							<div class="row" style="padding: 0px 0px 10px 14px;">
								<input type="text" class="form-control col-md-9" name="searchTag" placeholder="검색할 태그를 입력하세요">&nbsp;
							 	<input type="submit" class="btn btn-secondary" value="검색">
							</div>
						</form>
					</div>
				</aside>
				<!-- right area end -->
			
			</div>
			<!-- mid area row end -->
			
			<!-- loading button start -->
			<div id="loading-button">
				<!-- board data -->
				<form name="tripListInfo">
					<input type="hidden" name="next_row" value="${next_row}">
				</form>
				<!-- board data -->
			    <c:if test="${count ne 0}">
					<c:if test="${pageCount gt endPage}">
						<button type="button" class="btn btn-dark btn-block"
				onclick="loadList(${startPage+pageBlock})">Load more...</button>
					</c:if>	
				</c:if>				
			</div>
				
		</div>
			<!-- loading button end -->
			
		</div>
		<!-- container area end -->
		
		<div class="mx-auto">
				<button type="button" class="btn btn-secondary btn-sm" onclick="goback()">이전페이지로</button>
		</div>
	<!-- body box end-->
</body>

<!-- footer area start-->
<div id="footer">
	<footer class="board-footer">
		<p>
			<a href="">Back to top</a>
		</p>
	</footer>
</div>
<!-- footer area end-->
		
</html>