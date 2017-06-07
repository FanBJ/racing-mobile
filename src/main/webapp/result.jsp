<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比赛排名</title>
<script type="text/javascript" src="${_path }/resources/js/jquery.js"></script>
<link rel="stylesheet" href="${_path }/resources/css/divstyle.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchBtn").click(function() {
			$("#showData").html("");
			$.getJSON("${_path}/phone/car/findCarByTel", {
				tel : $("#tel").val()
			}, function(data) {
				$(data.data).each(function(i, e) {
					$("#showData").append("<tr><td>"+e.id+"</td><td>"+e.username+"</td><td>"+e.tel+"</td><td>"+e.groupname
							+"</td><td>"+e.teamname+"</td><td>"+e.code+"</td><td>"+e.brand+"</td><td>"+e.cartype+"</td><td>"+
							e.displacement+"</td><td>"+e.ischange+"</td><td>"+e.status+"</td><td>"+
							e.runCount+"</td><td><input id='btn"+i+"' type='button' value='编辑'/>&nbsp;<a href='javascript:showAddGrade(\""+e.id+"\");'>添加成绩</a></td></tr>");
					$("#btn"+i).on("click",function(){
						showEditCar(e);
					});
				});
			});
		});
	});
</script>
</head>
<body>
<select></select>
<table>
	<thead>
		<tr>
			<th>排名</th>
			<th>所属人</th>
			<th>性别</th>
			<th>组别</th>
			<th>车队</th>
			<th>品牌</th>
			<th>车型</th>
			<th>排量</th>
			<th>成绩</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody></tbody>
</table>
</body>
</html>