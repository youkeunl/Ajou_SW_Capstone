<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<title>회원가입</title>

<script>
	function checkId() {
		var id = document.regformsitter.id.value;
		
		if(id.length == 0) {
			alert("중복 체크할 아이디를 입력하세요");
			document.regform.id.focus();
		} else {
			var url = "/priends/WebContent/member/idCheckSitter.jsp?chid="+id;
			window.open(url,"chid","width=450, height=250");
		}
	}
	
	function checking() {
		if(document.regformsitter.name.value=="")
			alert("이름을 입력하세요");
		else if(document.regformsitter.id.value=="")
			alert("아이디를 입력하세요");
		else if(document.regformsitter.pass.value=="")
			alert("비밀번호를 입력하세요");
		else if(document.regformsitter.pass.value != document.regformsitter.repass.value)
			alert("비밀번호가 맞지 않네요");
		else
			document.regformsitter.submit();
	}
</script>

</head>
<body>
	<form name="regformsitter" method="get" action="/priends/WebContent/member/regProcSitter.jsp">
	<h4 align="center">펫 시터 회원가입</h4>
	<hr color="black">
	<table border="2" cellpadding="5" align="center">
		<tr>
			<td align="center">이름</td>
			<td>
			<input type="text"  name="name">
			</td>
		</tr>
		
		<tr>
			<td align="center">아이디</td>
			<td>
			<input type="text" name="id">
			<input type="button" value="중복체크" 
			onclick="javascript:checkId()">
			</td>
		</tr>
		
		<tr>
			<td align="center">비밀번호</td>
			<td>
			<input type="password" name="pass">
			</td>
		</tr>
		
		<tr>
			<td align="center">비밀번호 재확인</td>
			<td>
			<input type="password" name="repass">
			</td>
		</tr>
		
		<tr>
			<td align="center">성별</td>
			<td>
			<input type="text" name="sex">
			</td>
		</tr>
		
		<tr>
			<td align="center">직업</td>
			<td>
			<input type="text" name="job">
			</td>
		</tr>
		
		<tr>
			<td align="center">나이</td>
			<td>
			<input type="text" name="age">
			</td>
		</tr>
		
		<tr>
			<td align="center">주소</td>
			<td>
			<input type="text" name="address">
			</td>
		</tr>
		
		<tr>
			<td align="center">Price (Day)</td>
			<td>
			<input type="text" name="price_day">
			</td>
		</tr>
		
		<tr>
			<td align="center">Price (1 Night)</td>
			<td>
			<input type="text" name="price_night">
			</td>
		</tr>

		
		<tr>
			<td colspan="2" align="center">
			<input class="btn btn-default" type="button" value="회원가입" 
			onclick="javascript:checking()">
			<input class="btn btn-default" type="reset" value="다시작성">
			<input class="btn btn-default" type="button" onclick="location.href='/index.html'"value="메인으로">
			</td>
		</tr>

	</table>
	</form>
</body>
</html>