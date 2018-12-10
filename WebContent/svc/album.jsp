<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="setting.jsp" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Album</title>
	<script src="${project}script.js"></script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${project}travelers_style.css">
    <link href="https://fonts.googleapis.com/css?family=Work+Sans" rel="stylesheet">
  </head>
  <body id="album">
   <main role="main">
      <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">
           <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="mr-2"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>
          	Travelers Album
          </h1>
          <p class="lead text-muted">${moments}</p>
        </div>
      </section>

      <div class="album py-5 bg-light">
        <div class="container">
			<c:if test="${photoCount gt 0}">
	          <div class="row">
		          <c:forEach var="photo" items="${album}">

		           <div class="col-md-4" id="photoArea">
		           <input type="checkbox" name='check1' style="display:none">
		              <div class="card mb-4 shadow-sm">
		                <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" 
		                alt="Card image cap" src="${photo.photo_url}" data-toggle="modal" data-target="#myModal_${photo.photo_id}">
					    <div id="myModal_${photo.photo_id}" class="modal fade">
					        <div class="modal-dialog">
					            <div class="modal-content">
					                <div class="modal-header">
					                	${photo.photo_reg_date}
					                    <button type="button" class="close" data-dismiss="modal">&times;</button>
					                </div>
					                <div class="modal-body">
					                    <img src="${photo.photo_url}" class="img-fluid">
					                </div>
					            </div>
					        </div>
					    </div>
		                <div class="card-body">
		                	<c:set var="done" value="false"/>
		                	<c:forEach var='eachPhoto' items="${photoInfos}">
			                  	<c:if test="${eachPhoto.this_board_no eq photo.board_no and not done}">
				                  	<p class="card-text">
				                  		${eachPhoto.photoLoc}
				                  		<c:set var="done" value="true"/>
				                  	 </p>
			                  	</c:if>
		                  </c:forEach>
		                  <div class="d-flex justify-content-between align-items-center">
		                    <div class="btn-group">
		                    	<c:forEach var="tags" items="${photoTags}">
		                    		<c:if test="${tags.this_board_no eq photo.board_no}">
		                    		<button type="button" class="btn btn-sm btn-outline-secondary" disabled>#${tags.tag_value}</button>
		                    		</c:if>
		                    	</c:forEach>
		                    </div>
		                    <small class="text-muted"><fmt:formatDate value="${photo.photo_reg_date}" pattern="yyyy-MM-dd HH:mm"/></small>
		                  </div>
		                </div>
		              </div>
		            </div>
	             </c:forEach>
	          </div>
	         
          </c:if>
        </div>
      </div>

    </main>

    <footer class="text-muted">
      <div class="container">
        <p class="float-right">
          <a href="#">Back to top</a>
        </p>
        <p>New to Travelers? <a href="login.go">Join us</a></p>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
  </body>
</html>
 
