<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setAttribute("main", "/priends/WebContent/menu.jsp");
/* request.setAttribute("main", "/menu.jsp"); */

%>
<jsp:forward page="/priends/WebContent/join.jsp"/>
<%-- <jsp:forward page="/join.jsp"/> --%>