<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	PetSitterDAO dao = new PetSitterDAO();
	PetSitterDTO dto = dao.selectOne(id);
%>
<html>
<head>
<body>
	<%if(dto != null && pass.equals(dto.getPetSitter_Pwd())){
		session.setAttribute("memId", id);
		%>
		<script>
			alert("<%=session.getAttribute("memId")%>님 반갑습니다.");
			location.href="/priends/WebContent/menu.jsp";
		</script>
	<% }else {%>
		<script>
			alert("등록되지 않은 아이디거나 \n비밀번호가 맞지 않습니다.");
			location.href="/priends/WebContent/loginSitter.html";
		
		</script>
	<%}%>

</body>
</head>

</html>