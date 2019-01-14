<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${project}travelers_style.css">
<script src="${project}script.js"></script>
	<body>
		<form>
		<h1 class="separator" id="container">사이트 이용 정보</h1>
		<div class="separator1">
			<table class="table">
				  <thead class="thead-dark">
				    <tr>
				      <td scope="col" align="center">순위</th>
				      <td scope="col" align="center">회원들이 많이 여행한 도시</th>
				      <td scope="col" align="center">회원들이 많이 사용한 여행 태그</th>
				      <td scope="col" align="center">
					     	<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		${user_id}님을 위한 여행지 추천
					      	</c:if>
					  </td>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td scope="row" align="center">1</th>
				      <td align="center">${convertRst1}</td>
				      <td align="center">${tconvertRst1}</td>
				      	<td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		${coord1}
					      	</c:if>
						</td>
				    </tr>
				    <tr>
				      <td scope="row" align="center">2</th>
				      <td align="center">${convertRst2}</td>
				      <td align="center">${tconvertRst2}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		${coord2}
					      	</c:if>
						</td>
				    </tr>
				    <tr>
				      <td scope="row" align="center">3</th>
				      <td align="center">${convertRst3}</td>
				      <td align="center">${tconvertRst3}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		${coord3}
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">4</th>
				      <td align="center">${convertRst4}</td>
				      <td align="center">${tconvertRst4}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord4 ne null}">	
					      			${coord4}
					      		</c:if>
					      		<c:if test="${coord4 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">5</th>
				      <td align="center">${convertRst5}</td>
				      <td align="center">${tconvertRst5}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord5 ne null}">	
					      			${coord5}
					      		</c:if>
					      		<c:if test="${coord5 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">6</th>
				      <td align="center">${convertRst6}</td>
				      <td align="center">${tconvertRst6}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord6 ne null}">	
					      			${coord6}
					      		</c:if>
					      		<c:if test="${coord6 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">7</th>
				      <td align="center">${convertRst7}</td>
				      <td align="center">${tconvertRst7}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord7 ne null}">	
					      			${coord7}
					      		</c:if>
					      		<c:if test="${coord7 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">8</th>
				      <td align="center">${convertRst8}</td>
				      <td align="center">${tconvertRst8}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord8 ne null}">	
					      			${coord8}
					      		</c:if>
					      		<c:if test="${coord8 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">9</th>
				      <td align="center">${convertRst9}</td>
				      <td align="center">${tconvertRst9}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord9 ne null}">	
					      			${coord9}
					      		</c:if>
					      		<c:if test="${coord9 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				     <tr>
				      <td scope="row" align="center">10</th>
				      <td align="center">${convertRst10}</td>
				      <td align="center">${tconvertRst10}</td>
				      <td align="center">
				     		<c:if test="${sessionScope.user_id eq null}">	
					      		로그인하면 여행지 추천
					      	</c:if>
					      	<c:if test="${sessionScope.user_id ne null}">	
					      		<c:if test="${coord10 ne null}">	
					      			${coord10}
					      		</c:if>
					      		<c:if test="${coord10 eq null}">	
					      			
					      		</c:if>
					      	</c:if>
						</td>
				    </tr>
				    
				  </tbody>
				</table>
			</div>
		</form>		
	</body>	

<!--
<c:if test="${sessionScope.user_id ne null}">	
	<c:forEach items="${coords}" var="coord" >  
	  <c:out value="${coord.coord_name}"/> 
	</c:forEach> 
</c:if>
<c:if test="${sessionScope.user_id eq null}">	
NONE
</c:if>
-->
	