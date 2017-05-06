<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("isLogin")==null || !"1".equals(session.getAttribute("isLogin"))){
	response.sendRedirect("login.jsp");
	return;
}
%>