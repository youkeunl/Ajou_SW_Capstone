<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	PetMomDAO dao = new PetMomDAO();
	PetMomDTO dto = new PetMomDTO();
	PetDTO p_dto = new PetDTO();
	
	dto.setPetMom_ID(request.getParameter("id"));
	dto.setPetMom_Pwd(request.getParameter("pass"));
	dto.setName(request.getParameter("name"));
	dto.setAge(Integer.parseInt(request.getParameter("age")));
	dto.setJob(request.getParameter("job"));
	dto.setSex(request.getParameter("sex"));
	dto.setAddress(request.getParameter("address"));
	
	p_dto.setName(request.getParameter("pet_name"));
	p_dto.setAge(Integer.parseInt(request.getParameter("pet_age")));
	p_dto.setCharacter(request.getParameter("pet_character"));
	p_dto.setSpecies(request.getParameter("pet_species"));
	p_dto.setHealth(request.getParameter("pet_health"));
	
	
	
	int n = dao.insertOne(dto,p_dto);
	
	
	if(n > 0) {
%>
	<script>
		alert("회원 가입 완료~");
		location.href="/login.html";
	</script>
<%} else { %>
	<script>
		alert("가입 실패...");
		location.href="/priends/WebContent/member/regForm.jsp";
	</script>
<%} %>	