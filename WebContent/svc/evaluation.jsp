<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<h2>평판페이지</h2>
<script type="text/javascript">	
var sel1 = document.querySelector('#sel1');
var sel2 = document.querySelector('#sel2');
var options2 = sel2.querySelectorAll('option');

function giveSelection(selValue) {
  sel2.innerHTML = '';
  for(var i = 0; i < options2.length; i++) {
    if(options2[i].dataset.option === selValue) {
      sel2.appendChild(options2[i]);
    }
  }
}

giveSelection(sel1.value);

</script>
<form method="post" action="evaluation.go">
	<table>
		<tr>일정</tr>
		<c:forEach var="trip" items="${tripDto}">
		<td><select id="sel1" name="sel1" onchange="giveSelect(this.value)">
			<option value="${trip.trip_id}"> ${trip.coord_name} </option>
			</select> </td>
			<c:forEach var="member" items="memberList">
			<tr>평판대상</tr>	
			<td><select id="sel2" name="sel2">
			<option data-option="${trip.trip_id}">${member.user_name}</option>
			</select></td>
			</c:forEach>				
		</c:forEach>		
		<tr>점수</tr>
		<td> <input type="checkbox" value="1" name="grade"> ★ </td>
		<td> <input type="checkbox" value="2" name="grade"> ★★</td>	
		<td> <input type="checkbox" value="3" name="grade"> ★★★</td>	
		<td> <input type="checkbox" value="4" name="grade"> ★★★★</td>	
		<td> <input type="checkbox" value="5" name="grade"> ★★★★★</td>		
		<tr>내용 </tr>
		<td> <textarea name="textarea" rows="10" cols="50"></textarea> </td>
	</table>
</form>