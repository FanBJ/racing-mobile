<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="islogin.jsp" %>
<%
	pageContext.setAttribute("_path", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
					$("#showData").append("<tr><td>"+e.id+"</td><td>"+e.username+"</td><td>"+e.groupname
							+"</td><td>"+e.teamname+"</td><td>"+e.code+"</td><td>"+e.brand+"</td><td>"+e.cartype+"</td><td>"+
							e.displacement+"</td><td>"+e.ischange+"</td><td>"+e.status+"</td><td><input type='button' value='编辑'/>&nbsp;<a href='javascript:showAddGrade(\""+e.id+"\");'>添加成绩</a></td></tr>");
				});
			});
		});
	});
</script>
</head>
<body>
<a href="finduser.jsp"> 查看用户信息 </a>
	<table align="center" border="1" width="1000">
		<tr>
			<td colspan="11">手机号：<input name="tel" id="tel" />&nbsp;&nbsp;
			<input value="搜索" type="button" id="searchBtn" /></td>
		</tr>
		<tr>
			<td>序号</td>
			<td>所属人</td>
			<td>组别</td>
			<td>车队</td>
			<td>车牌</td>
			<td>品牌</td>
			<td>车型</td>
			<td>排量</td>
			<td>是否改装</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<tbody id="showData">
		</tbody>
	</table>
	
	<div class="cd-popup">
	    <div class="cd-popup-container">
	        <p>添加序号（<span id="cid"></span>）比赛成绩</p>
	        <div class="cd-buttons">
	        	<select id="round">
	        		<option value="1">海选成绩</option>
	        		<option value="2">半决赛</option>
	        		<option value="3">决赛</option>
	        	</select>
	        	<input id="minute" size="1" maxlength="2"/>分<input size="1" maxlength="2" id="second" size="2"/>秒<input size="2" maxlength="3" id="millisecond" size="2"/>毫秒
	        	<br/><br/><input type="button" value="确定" onclick="addGrade()"/>
	        </div>
	        <a href="#0" class="cd-popup-close">close</a>
	    </div>
	</div>
	<script type="text/javascript">
    /*弹框JS内容*/
    jQuery(document).ready(function($){
        //关闭窗口
        $('.cd-popup').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
                event.preventDefault();
                $(this).removeClass('is-visible');
            }
        });
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup').removeClass('is-visible');
            }
        });
    });
    function showAddGrade(id){
    	$("#cid").html(id);
        $('.cd-popup').addClass('is-visible');
    }
    function addGrade(){
    	var carid = $("#cid").html();
    	if($("#minute").val()=='' || isNaN($("#minute").val())){
    		alert("请正确填写分钟");
    		return;
    	}
    	if($("#second").val()=='' || isNaN($("#second").val())){
    		alert("请正确填写秒种");
    		return;
    	}
    	if($("#millisecond").val()=='' || isNaN($("#millisecond").val())){
    		alert("请正确填写秒种");
    		return;
    	}
    	if($("#minute").val().length==1){
    		$("#minute").val("0"+$("#minute").val());
    	}
    	if($("#second").val().length==1){
    		$("#second").val("0"+$("#second").val());
    	}
    	if($("#millisecond").val().length==1){
    		$("#millisecond").val("00"+$("#millisecond").val());
    	}
    	if($("#millisecond").val().length==2){
    		$("#millisecond").val("0"+$("#millisecond").val());
    	}
    	var speed = $("#minute").val()+":"+$("#second").val()+":"+$("#millisecond").val();
    	
    	$.post("${_path}/phone/game/addGameLog",{roundId:$("#round").val(),carId:carid,speed:speed},
    		function(data){
    			if(data){
    				alert("添加成功！");
    				$('.cd-popup').removeClass('is-visible');
    			}else{
    				alert("失败咯，请看看数据是否正确！");
    			}
    		}
    	);
    }
</script>
</body>
</html>