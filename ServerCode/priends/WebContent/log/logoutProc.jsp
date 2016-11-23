<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("memId");
	
	session.invalidate();
%>
<script>
	alert("<%=id%>님, 로그아웃 되었습니다.");
	location.href="/priends/WebContent/login.html";
</script>