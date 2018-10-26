<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp"%>
<link rel="stylesheet" type="text/css" href="${project}style_admin.css">
<script src="${project}script.js"></script>
<h3>|${str_tag_add}</h3>
	<input type="hidden" value="${state}" name="state"/>
	<section>
		<div class="buttonarea">
			<input class="inputbutton" type="button" value="${btn_cancel}" onclick="setCancel()">
			<input class="inputbutton" type="button" value="${btn_check}" onclick="addTag(${state})">
		</div>
	</section>
	<section>
		<table>
			<tr> 
				<th class="check" ><input type="checkbox" disabled="disabled"></th>
				<th >${str_tag}</th>
				<th>${str_type}</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="check1" ></td>
				<td><input class="input" type="text" autofocus="autofocus" maxlength="10"></td>
				<td>
					<select name="tag_type">
						<option value="1" selected="selected">${str_tag_user}</option>
						<option value="2">${str_tag_city}</option>
					</select>
				</td>
			</tr>
			<tbody id="t">
			</tbody>
			<tr>
				<td colspan="3">
						<button onclick="addRow()">
							<img  class="btn_img" src="/Travelers/adm/images/addbutton.png"> 
						</button>
				</td>
			</tr>
		</table>
	</section>