<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="islogin.jsp" %>
<%
	pageContext.setAttribute("_path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看车辆信息</title>
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
<a href="finduser.jsp"> 查看用户信息 </a>
	<table align="center" border="1">
		<tr>
			<td colspan="13">手机号：<input name="tel" id="tel" />&nbsp;&nbsp;
			<input value="搜索" type="button" id="searchBtn" /></td>
		</tr>
		<tr>
			<td width="40">序号</td>
			<td width="75">所属人</td>
			<td width="95">所属人电话</td>
			<td width="110">组别</td>
			<td width="200">车队</td>
			<td width="70">车牌</td>
			<td width="95">品牌</td>
			<td width="110">车型</td>
			<td width="50">排量</td>
			<td width="65">是否改装</td>
			<td width="40">状态</td>
			<td width="65">上场次数</td>
			<td width="140">操作</td>
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
	        				<td>所属人：</td>
	        				<td><span id="editUsername"></span></td>
	        			</tr>
	        			<tr>
	        				<td>车牌：</td>
	        				<td><input name="code" id="editCode" required/></td>
	        			</tr>
	        			<tr>
	        				<td>组别：</td>
	        				<td><select name="cargroupId" id="editGroup" style="width: 170px"></select></td>
	        			</tr>
	        			<tr>
	        				<td>车队：</td>
	        				<td><select name="teamId" id="editTeam" style="width: 170px"></select></td>
	        			</tr>
	        			<tr>
	        				<td>品牌：</td>
	        				<td><input name="brand" id="editBrand" required/></td>
	        			</tr>
	        			<tr>
	        				<td>车型：</td>
	        				<td><input name="cartype" id="editCartype" required/></td>
	        			</tr>
	        			<tr>
	        				<td>排量：</td>
	        				<td><input name="displacement" id="editDisplacement" required/></td>
	        			</tr>
	        			<tr>
	        				<td>是否改装：</td>
	        				<td><label><input type="checkbox" id="editIschange"/>是</label></td>
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
        
        //关闭窗口
        $('.cd-popup1').on('click', function(event){
            if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup1') ) {
                event.preventDefault();
                $(this).removeClass('is-visible1');
            }
        });
        //ESC关闭
        $(document).keyup(function(event){
            if(event.which=='27'){
                $('.cd-popup1').removeClass('is-visible1');
            }
        });
        
        $.get("${_path}/phone/group/getAllGroup",function(data){
        	if(data.data){
        		$(data.data).each(function(i,e){
        			$("#editGroup").append("<option value='"+e.id+"'>"+e.name+"</option>");
        		});
        	}
        });
        
        $.get("${_path}/phone/carteam/getAllTeam",function(data){
        	if(data.data){
        		$(data.data).each(function(i,e){
        			$("#editTeam").append("<option value='"+e.id+"'>"+e.name+"</option>");
        		});
        	}
        });
        
        //提交表单
        $("#editCarForm").on("submit",function(e){
        	e.preventDefault();
        	
        	$("#ischange").val($("#editIschange").is(":checked")?1:0);
        	$("#status").val($("#editStatus").is(":checked")?1:0);
        	
        	$.post("${_path}/phone/car/updateCar",$("#editCarForm").serialize(),function(data){
        		if(data.state==1){
        			alert("更新成功~页面将刷新！");
        			$("#searchBtn").click();
        			$(".cd-popup1").removeClass('is-visible1');
        		}
        	});
        });
    });
    function showAddGrade(id){
    	$("#cid").html(id);
        $('.cd-popup').addClass('is-visible');
    }
    function showEditCar(obj){
    	$("#carid").html(obj.id);
        $('.cd-popup1').addClass('is-visible1');
        
        $("#carId").val(obj.id);
        $("#editId").html(obj.id);
        $("#editCode").val(obj.code);
        $("#editUsername").html(obj.username);
        $("#editGroup").val(obj.cargroupId);
        $("#editTeam").val(obj.teamId);
        $("#editBrand").val(obj.brand);
        $("#editCartype").val(obj.cartype);
        $("#editDisplacement").val(obj.displacement);
        $("#editIschange").prop("checked",(obj.ischange=='1'));
        $("#editStatus").prop("checked",(obj.status=='1'));
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