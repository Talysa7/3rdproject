<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@include file="setting.jsp" %>
<h2>평판페이지</h2>

<script type="text/javascript">	
var sel1 = document.getElementById('sel1');
var sel2 = document.getElementById('sel2');
var options2 = sel2.getElementByTagName('option');

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
<form method="post" action="reviewPro.go">
	
		<div>
		<span>일정</span>
		<c:forEach var="memTrip" items="${memberDto}">
		<span><select id="sel1" name="sel1">
			<option value="${memTrip.getTrip_id()}"> ${coord_name} </option>
			</select> </span>
		 <c:forEach var="member" items="memDto">
			<div>평판대상</div>	
			<span><select id="sel2" name="sel2">
			<option value="${member.user_name}" data-option="${memTrip.getTrip_id()}">${member.user_name}</option>
			</select></span>
			</c:forEach>		
		</c:forEach>	 
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
		<div> ※ 동행인에 대한 평판글은 수정 및 삭제가 불가하오니 이점 유의하여 작성해주세요 ※</div>
		<div> <textarea name="textarea" rows="10" cols="50"></textarea> </div>
		<div><input type="submit" value="제출">
			 <input type="reset" value="취소"></div>
</form>