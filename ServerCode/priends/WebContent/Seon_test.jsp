<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>insert title here</title>

</head>
<body>
<h1>Hello~</h1>

<%

ArrayList<PetSitterDTO> b_list = new ArrayList<PetSitterDTO>();
%> 3 <%
PetMomDAO call = new PetMomDAO();

b_list = call.getPetSitterAllData();
%> 4 <%
int i = 0;
for(i = 0; i < b_list.size(); i++){
	%> <%= b_list.get(i).getAddress() %>
	<% 
}
%>

</body>
</html>