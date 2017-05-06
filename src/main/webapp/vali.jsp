<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String pwd = request.getParameter("pwd");
if(StringUtils.isBlank(pwd)){
	out.println("<script>alert('滚！！！');window.location.href='login.jsp';</script>");
	return;
}
if(!"Fanbj123".equals(pwd)){
	out.println("<script>alert('滚滚滚！！！');window.location.href='login.jsp';</script>");
	return;
}
if("Fanbj123".equals(pwd)){
	session.setAttribute("isLogin", "1");
	response.sendRedirect("findcar.jsp");
	return;
}
%>