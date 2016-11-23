<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>
	function logincheck(){
		if(document.loginForm.id.value==""){
			alert("아이디를 입력하세요");
			document.loginForm.id.focus();
		} else if(document.loginForm.pass.value==""){
			alert("비밀번호를 입력하세요")
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
			
			<b>아이디</b> <input type="text" name="id" size="10">
			<b>비밀번호</b> <input type="password" name="pass" size="10">
			&nbsp;&nbsp;
			<input class="btn btn-default" type="button" value="로그인" onclick="javascript:logincheck()">
			<input class="btn btn-default" type="button" value="회원가입" onclick="location.href='/priends/member/reg.jsp'">
		
		</form>
	
	
	</div>
	<%} else { %>
	<div align="right">
		<h1 align="left"><b>WebReview</b></h1>
		<b><%=id %></b>님 환영합니다.<br>
		
		<input class="btn btn-default" type="button" value="로그아웃"
		onclick="location.href='/priends//log/logoutProc.jsp'">
		
		<input class="btn btn-default" type="button" value="리뷰보기"
		onclick="location.href='/priends/menu/menu.jsp'">
		
			<input class="btn btn-default" type="button" value="처음으로"
		onclick="location.href='/priends/index.jsp'">
		
		
		
	</div>
<%} %>

</body>
</html>