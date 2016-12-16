<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="priends.work.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%

String petMomID = request.getParameter("petMomID");
String petSitterID = request.getParameter("petSitterID");

PetMomDAO dao = new PetMomDAO();
dao.deleteReject_info(petMomID, petSitterID);

%>
<body>
	<div style="height: auto; width: 100%; border-top: 1px solid gold;"></div>
	<h1> Reject Complete </h1>	
	<div style="height: auto; width: 100%; border-top: 1px solid gold;"></div>
	
	<form name="listForm" action="alertForm.jsp" method="get">
	<input type= "submit" value="확인">
	</form>
	
</body>
</html>