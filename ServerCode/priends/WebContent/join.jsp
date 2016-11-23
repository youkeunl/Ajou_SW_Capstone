<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String top = "/priends/WebContent/top.jsp";
	//String top = "/top.jsp";
	String main = (String)request.getAttribute("main");
	

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트</title>
</head>
<body>
	<table width="100%" height="100%">
		<tr>
			<td><jsp:include page="<%=top%>"/></td>
		</tr>
		
		<tr height="700px" valign="top">
			
			<td><jsp:include page="<%=main %>"/></td>
			
		</tr>

	</table>

</body>
</html>