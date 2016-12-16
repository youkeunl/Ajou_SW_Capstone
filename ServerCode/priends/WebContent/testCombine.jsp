<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setAttribute("main", "/test.jsp");
	
	/* request.setAttribute("main", "/body.html"); */
%>
<jsp:forward page="/priends/WebContent/join.jsp"/>
<%-- <jsp:forward page="/join.jsp"/> --%>