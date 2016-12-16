<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String type = (String)session.getAttribute("type");
	if(type.equals("sitter")){
	request.setAttribute("main", "/priends/WebContent/calendarForm.jsp");
	}else{
		%>
		<script>
		alert("펫 맘은 볼 수 없습니다.");
		</script><%
		request.setAttribute("main","/priends/WebContent/body.html");
	}

%>
<jsp:forward page="/priends/WebContent/join.jsp"/>
