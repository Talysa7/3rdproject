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
	<!------------------------------------------------------- RIGHT COLUMN ------------------------------------------------------>
	<!-- Category & Contents Box -->
	<div class="body-box">
		<div class="right">
			<div>
					<div>
						<form method="post">
						<div class="pac-card" id="pac-card">
						  		<!-- GOOGLEMAP TITLE -->
							    <div id="title">
							      	여행 장소 검색
							    </div>
						    	<!-- GOOGLEMAP RADIO BUTTON -->
							    <div id="type-selector" class="pac-controls">
							      <input type="radio" name="type" id="changetype-all" checked="checked">
							      <label for="changetype-all">All</label>
							
							      <input type="radio" name="type" id="changetype-establishment">
							      <label for="changetype-establishment">Establishments</label>
							
							      <input type="radio" name="type" id="changetype-address">
							      <label for="changetype-address">Addresses</label>
							
							      <input type="radio" name="type" id="changetype-geocode">
							      <label for="changetype-geocode">Geocodes</label>
							    </div>
							    <div id="strict-bounds-selector" class="pac-controls">
							      <input type="checkbox" id="use-strict-bounds" value="">
							      <label for="use-strict-bounds">Strict Bounds</label>
							    </div>
						  	<!-- GOOGLEMAP LOCATION TEXTAREA -->
								  <div id="pac-container">
								    <input id="pac-input" type="text"
								        placeholder="Enter a location">
								  </div>
						</div>
								<div id="map"></div>
								<div id="infowindow-content">
									  <img src="" width="16" height="16" id="place-icon">
									  <span id="place-name"  class="title"></span><br>
									  <span id="place-address"></span>
								</div>
								<!-- Replace the value of the key parameter with your own API key. -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDbvvT_kUPxmLL9PHcM9gp2qibpr8sThVQ&libraries=places&callback=initMap"async defer></script>
						
									<div class="row" style="padding: 0px 0px 10px 14px;">
										 	<button type="button" class="btn btn-secondary col-md-1">날짜</button>&nbsp;
										 <input id="fromDate" width="160"/>
											    <script>
											        $('#fromDate').datepicker({
											            uiLibrary: 'bootstrap4'
											        });
											    </script>
												&nbsp;
										 <input id="toDate" width="160"/>
											    <script>
											        $('#toDate').datepicker({
											            uiLibrary: 'bootstrap4'
											        });
											    </script>
									</div>
									<div  class="row" style="padding: 0px 0px 10px 14px;">
										 	<button type="button" class="btn btn-secondary col-md-1">기간</button>&nbsp;
											<input type="text" class="form-control col-md-4" placeholder="일 단위로 입력하세요">	&nbsp;	
											<input type="submit" class="btn btn-secondary" value="검색">
										</div>
									</div>
									<div class="row" style="padding: 0px 0px 0px 14px;">
									 	<button type="button" class="btn btn-secondary col-md-1">태그</button>&nbsp;
										<input type="text" class="form-control col-md-4" placeholder="검색할 태그를 입력하세요">&nbsp;
									 	<input type="submit" class="btn btn-secondary" value="검색">
									</div>
							</form>
						</div>	
					</div>
				</div>
			
		<!------------------------------------------------------- LEFT COLUMN ------------------------------------------------------>
			<div class="left" style = "padding: 0px 0px 0px 100px;">
				<div class="d-flex" name="board_list">
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
						<div class="col-md-11">
							<div class="card flex-md-row mb-3 shadow-sm h-md-250">
								<div class="card-body d-flex flex-column align-items-start">
									<strong class="d-inline-block mb-2"> <c:forEach
											var="trip" items="${post.tripLists}">
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
			<button type="button" class="btn btn-dark col-md-11"
				onclick="loadMoreList(${next_row})">Load more...</button>
		</div>
	</div>
</div>
	<!-- body box -->
	
	<!-- Park Jun-kyu : I disabled 'Footer' because of alignment. As soon as I find solution, I'll fix it. -->
	
	<!-- Footer
			<footer class="board-footer">
				<p>
					<a href="">Back to top</a>
				</p>
			</footer>
			
	 -->
</body>
</html>
