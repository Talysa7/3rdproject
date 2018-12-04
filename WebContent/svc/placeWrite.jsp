<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<h2>여행지 평가페이지</h2>

<form method="post" action="placeWritePro.go">
	<div>	
	<span>일정</span>	
	<span><select id="sel1" name="sel1">	
	<c:forEach var="trip" items="${trip}">	
	<option value="${trip.coord_id}">${trip.coordinate.coord_name}</option>
	</c:forEach>			
	</select> </span>			
	</div>	
	<div>
	<span>점수</span>
	<span> <input type="checkbox" value="1" name="grade"> ★ </span>
	<span> <input type="checkbox" value="2" name="grade"> ★★</span>
	<span> <input type="checkbox" value="3" name="grade"> ★★★</span>
	<span> <input type="checkbox" value="4" name="grade"> ★★★★</span>	
	<span> <input type="checkbox" value="5" name="grade"> ★★★★★</span>	
	</div>
	<div>내용 </div>
	<div> ※ 여행지에 대한 평가는 수정 및 삭제가 불가하오니 신중하게 작성해주세요 ※</div>
	<div> <textarea name="textarea" rows="10" cols="50"></textarea> </div>
	<div>
		<button type="submit" class="btn btn-secondary btn-sm">제출</button>
		<button type="reset" class="btn btn-secondary btn-sm">취소</button>
		<button type="button" class="btn btn-secondary btn-sm" onclick="goback()">이전페이지로</button>
	</div>
</form>