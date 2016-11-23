<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%
PetMomDAO dao = new PetMomDAO();
PetMomDTO dto = dao.selectOne(request.getParameter("chid")); %>
<html>
<body>
	<h3>id 중복 확인</h3>
	<hr color="black">
	<%if(dto!=null){
		%><b><%=request.getParameter("chid")%></b> 은/는 현재 사용 중 입니다.<%
		}
	else{
		%><b><%=request.getParameter("chid")%></b> 은/는 사용 가능한 아이디 입니다.<%
	}
		%>
	
	
	<hr color="black">
	<input type="button" value="확인" onclick="self.close()">
</body>
</html>