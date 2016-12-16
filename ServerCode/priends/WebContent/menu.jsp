<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String type = (String)session.getAttribute("type");
	if(type.equals("sitter")){
		request.setAttribute("main","/priends/WebContent/calendar.jsp");
	}else{
	request.setAttribute("main", "/priends/WebContent/body.html");
	}
	/* request.setAttribute("main", "/body.html"); */
%>
<jsp:forward page="/priends/WebContent/join.jsp"/>
<%-- <jsp:forward page="/join.jsp"/> --%>
