<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="${project}img/logo_c.png">
	
	<title>Travelers</title>
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" type="text/css"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<!-- Custom styles for this template -->
	<style>
		body {
			min-height: 75rem;
			padding-top: 4.5rem;
		}		
		a.nav-item {
			color: white !important;
		}			
		select { 
		width: 100px;
   	 	height: 30px;
    	padding-left: 10px;
    	font-size: 18px;
    	color: #353535;
    	border: 1px solid #006fff;
    	border-radius: 3px;
		}
	</style>
</head>

<c:if test="${sessionScope.user_id eq null}">
	<body>
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="tripList.go"> <img
				src="${project}img/logo_c.png" width="30" height="30"
				class="d-inline-block align-top"> Travelers
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="tripList.go">${page_main_eng} <span class="sr-only"></span></a>
					</li>
					<li class="nav-item"><a class="nav-link" href="tripList.go">${page_board_eng}</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="album.go">${page_album_eng}</a>
					</li>
				</ul>
				<form class="form-inline mt-2 mt-md-0 login-section" name="serch_trip" method="post" action="searchTrip.go" >
					<select name="search_type">
						<option value="schedule">${search_trip_schedule}</option>
						<option value="trip_writer">${trip_writer}</option>
					</select> &nbsp;
					<input type="text" class="form-control" name="keyword" placeholder="${search_guide}"> &nbsp;
					<button type="submit" style="border: none; background: transparent;"><img alt="" src="${project}img/search-m2-24.png"></button> &nbsp;&nbsp;
					<a href="login.go" class="nav-item">${page_login}</a> &nbsp;|&nbsp;
					<a href="registration.go" class="nav-item">${page_input}</a>
				</form>
			</div>
		</nav>

		<!-- Bootstrap core JavaScript (**Essential for Toggler action)
	================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
	</body>
</c:if>
<c:if test="${sessionScope.user_id ne null}">
	<body>
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="tripList.go"> <img
				src="${project}img/logo_c.png" width="30" height="30"
				class="d-inline-block align-top"> Travelers
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="tripList.go">${page_main_eng} <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item"><a class="nav-link" href="tripList.go">${page_board_eng}</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="album.go">${page_album_eng}</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="myTrip.go">${page_calendar_eng}</a>
					</li>
				</ul>
				<c:if test="${user_level ne 9}">
					<form class="form-inline mt-2 mt-md-0 login-section" name="serch_trip" method="post" action="searchTrip.go" >
						<select name="search_type">
						<option value="schedule">${search_trip_schedule}</option>
						<option value="trip_writer">${trip_writer}</option>
					</select> &nbsp;
					<input type="text" class="form-control" name="keyword" placeholder="${search_guide}"> &nbsp;
					<button type="submit" style="border: none; background: transparent;"><img alt="" src="${project}img/search-m2-24.png"></button> &nbsp;|&nbsp;
						<a href="myPage.go" class="nav-item">${page_mypage2}</a> &nbsp;
					</form>
				</c:if>
				<c:if test="${user_level eq 9}">
					<form class="form-inline mt-2 mt-md-0 login-section" name="serch_trip" method="post" action="searchTrip.go" >
						<select name="search_type">
							<option value="schedule">${search_trip_schedule}</option>
							<option value="trip_writer">${trip_writer}</option>
						</select> &nbsp;
						<input type="text" class="form-control" name="keyword" placeholder="${search_guide}"> &nbsp;
						<button type="submit" style="border: none; background: transparent;"><img alt="" src="${project}img/search-m2-24.png"></button> &nbsp;|&nbsp;
						<a class="nav-item" href="adminTrip.go">${btn_adm}</a> &nbsp;<a
							class="nav-item" href="adminLogout.go">${btn_logout}</a>
					</form>
				</c:if>
			</div>
		</nav>

		<!-- Bootstrap core JavaScript (**Essential for Toggler action)
	================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
	</body>
</c:if>
</html>
