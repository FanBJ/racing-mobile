<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="islogin.jsp"%>
<%
	pageContext.setAttribute("_path", request.getContextPath());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
					$("#showData").append("<tr><td>"+e.id+"</td><td>"+e.username+"</td><td>"+e.sex
							+"</td><td>"+e.tel+"</td><td>"+e.UserID+"</td><td>"+e.area+"</td><td><input type='button' value='编辑'/></td></tr>");
				});
			});
		});
	});
</script>
</head>
<body>
	<a href="findcar.jsp"> 查看/录入成绩 </a>
	<table align="center">
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
</body>
</html>