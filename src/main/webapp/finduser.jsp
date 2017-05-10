<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="islogin.jsp"%>
<%
	pageContext.setAttribute("_path", request.getContextPath());
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看用户信息</title>
<script type="text/javascript" src="${_path }/resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchBtn").click(function() {
			$("#showData").html("");
			$.getJSON("${_path}/phone/user/findUser", {
				username : $("#username").val(),
				tel : $("#tel").val()
			}, function(data) {
				$(data).each(function(i, e) {
					$("#showData").append("<tr><td>"+e.uid+"</td><td>"+e.username+"</td><td>"+e.sex
							+"</td><td>"+e.tel+"</td><td>"+e.userID+"</td><td>"+e.area+"</td><td><input type='button' value='编辑'/></td></tr>");
				});
			});
		});
	});
</script>
</head>
<body>
	<a href="findcar.jsp"> 查看/录入成绩 </a>
	<table align="center" border="1">
		<tr>
			<td colspan="7">姓名：<input name="username" id="username" />&nbsp;&nbsp;手机号：<input
				name="tel" id="tel" />&nbsp;&nbsp;<input value="搜索" type="button"
				id="searchBtn" /></td>
		</tr>
		<tr>
			<td>序号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>电话</td>
			<td>驾驶证号</td>
			<td>归属地</td>
			<td>操作</td>
		</tr>
		<tbody id="showData">
		</tbody>
	</table>
	
	<div class="cd-popup1">
	    <div class="cd-popup-container1">
	        <p>编辑序号（<span id="carid"></span>）的数据</p>
	        <div class="cd-buttons">
	        	<form id="editCarForm" action="#" method="post">
	        	<input type="hidden" name="id" id="carId"/>
	        	<input type="hidden" name="ischange" id="ischange"/>
	        	<input type="hidden" name="status" id="status"/>
	        		<table>
	        			<tr>
	        				<td>序号：</td>
	        				<td><span id="editId"></span></td>
	        			</tr>
	        			<tr>
	        				<td>姓名：</td>
	        				<td><span id="editUsername"></span></td>
	        			</tr>
	        			<tr>
	        				<td>性别：</td>
	        				<td><input name="sex" id="editSexMan" type="radio"/></td>
	        			</tr>
	        			<tr>
	        				<td>电话：</td>
	        				<td><select name="cargroupId" id="editTel" style="width: 170px" required></select></td>
	        			</tr>
	        			<tr>
	        				<td>驾驶证号：</td>
	        				<td><select name="teamId" id="editUserId" style="width: 170px" required></select></td>
	        			</tr>
	        			<tr>
	        				<td>归属地：</td>
	        				<td><input name="brand" id="editArea" required/></td>
	        			</tr>
	        			<tr>
	        				<td>状态：</td>
	        				<td><label><input type="checkbox" id="editStatus"/>启用</label></td>
	        			</tr>
	        			<tr>
	        				<td colspan="2" align="center"><input style="height: 30px;width: 60px" id="btnUpdate" value="保存" type="submit"/></td>
	        			</tr>
	        		</table>
	        	</form>
	        </div>
	        <a href="#0" class="cd-popup-close">close</a>
	    </div>
	</div>
</body>
</html>