<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	PetSitterDAO dao = new PetSitterDAO();
	PetSitterDTO dto = new PetSitterDTO();

	
	dto.setPetSitter_ID(request.getParameter("id"));
	dto.setPetSitter_Pwd(request.getParameter("pass"));
	dto.setName(request.getParameter("name"));
	dto.setAge(Integer.parseInt(request.getParameter("age")));
	dto.setJob(request.getParameter("job"));
	dto.setSex(request.getParameter("sex"));
	dto.setAddress(request.getParameter("address"));
	dto.setPrice_day(Integer.parseInt(request.getParameter("price_day")));
	dto.setPrice_night(Integer.parseInt(request.getParameter("price_night")));
	
	int n = dao.insertOne(dto);
	
	
	if(n > 0) {
%>
	<script>
		alert("회원 가입 완료~");
		location.href="/priends/WebContent/loginSitter.html";
	</script>
<%} else { %>
	<script>
		alert("가입 실패...");
		location.href="/priends/WebContent/regFormSitter.html";
	</script>
<%} %>	