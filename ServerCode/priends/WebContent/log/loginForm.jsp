<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>
	function logincheck(){
		if(document.loginForm.id.value==""){
			alert("���̵� �Է��ϼ���");
			document.loginForm.id.focus();
		} else if(document.loginForm.pass.value==""){
			alert("��й�ȣ�� �Է��ϼ���")
			document.loginForm.pass.focus();
		} else{
			document.loginForm.submit();
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<title>Insert title here</title>
</head>
<body>
<% String id = (String)session.getAttribute("memId");
	if(session.getAttribute("memId") == null) {%>
	<div align="right">
		<h1 align="left"><b>WebReview</b></h1>
		<form name="loginForm" action="/WebReview/log/loginProc.jsp" method="post">
			
			<b>���̵�</b> <input type="text" name="id" size="10">
			<b>��й�ȣ</b> <input type="password" name="pass" size="10">
			&nbsp;&nbsp;
			<input class="btn btn-default" type="button" value="�α���" onclick="javascript:logincheck()">
			<input class="btn btn-default" type="button" value="ȸ������" onclick="location.href='/priends/member/reg.jsp'">
		
		</form>
	
	
	</div>
	<%} else { %>
	<div align="right">
		<h1 align="left"><b>WebReview</b></h1>
		<b><%=id %></b>�� ȯ���մϴ�.<br>
		
		<input class="btn btn-default" type="button" value="�α׾ƿ�"
		onclick="location.href='/priends//log/logoutProc.jsp'">
		
		<input class="btn btn-default" type="button" value="���亸��"
		onclick="location.href='/priends/menu/menu.jsp'">
		
			<input class="btn btn-default" type="button" value="ó������"
		onclick="location.href='/priends/index.jsp'">
		
		
		
	</div>
<%} %>

</body>
</html>