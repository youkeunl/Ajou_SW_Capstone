<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int type = Integer.parseInt(request.getParameter("type"));
String service2 = request.getParameter("service2");
//request.setAttribute("main", "/test.jsp");

%>
<input type="hidden" name="type" value="<%=type%>">
<input type="hidden" name="service2" value="<%=service2%>">
<jsp:forward page="/priends/WebContent/list.jsp" />
</body>

