<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<%@ include file="header.jsp" %>

<link rel="stylesheet" type="text/css" href="${project}style_member.css">
<script src="${project}script.js"></script>

<h2> ${page_main} </h2>

<c:if test="${sessionScope.memid eq null}">	
	<body onload="mainfocus()">
		<form name="mainform" method="post" action="memberloginPro.go" onsubmit="return maincheck()">
			<table>
				<tr>
					<th colspan="2">
						${msg_main}
					</th>
				</tr>
				<tr>
					<th> ${str_id} </th>
					<td>
						<input class="input" type="text" name="user_id" maxlength="15"> 
					</td>
				</tr>
				<tr>
					<th> ${str_passwd} </th>
					<td>
						<input class="input" type="password" name="passwd" maxlength="15">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="inputbutton" type="submit" value="${btn_login}">
						<input class="inputbutton" type="reset" value="${btn_cancel}">
						<input class="inputbutton" type="button" value="${btn_join}"
							onclick="location='memberinputForm.go'">
					</th>
				</tr>
			</table>
		</form>		
	</body>	
</c:if>		
<c:if test="${sessionScope.memid ne null}">
	<table>
		<tr>
			<td align="center">
				<span>${sessionScope.memid}</span>${msg_main_login}
			</td>
		</tr>
		<tr>
			<th>
				<input class="inputbutton" type="button" value="${btn_modify}"
					onclick="location='membermodifyForm.go'">
				<input class="inputbutton" type="button" value="${btn_delete}"
					onclick="location='memberdeleteForm.go'">
				<input class="inputbutton" type="button" value="${btn_logout}"
					onclick="location='memberlogout.go'">
			</th>
		</tr>		
	</table>		
</c:if>