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
		var id = document.regform.id.value;
		
		if(id.length == 0) {
			alert("중복 체크할 아이디를 입력하세요");
			document.regform.id.focus();
		} else {
			var url = "/priends/WebContent/member/idCheck.jsp?chid="+id;
			window.open(url,"chid","width=450, height=250");
		}
	}
	
	function checking() {
		if(document.regform.name.value=="")
			alert("이름을 입력하세요");
		else if(document.regform.id.value=="")
			alert("아이디를 입력하세요");
		else if(document.regform.pass.value=="")
			alert("비밀번호를 입력하세요");
		else if(document.regform.pass.value != document.regform.repass.value)
			alert("비밀번호가 맞지 않네요");
		else
			document.regform.submit();
	}
</script>

</head>
<body>
	<form name="regform" method="post" action="/priends/WebContent/member/regProc.jsp">
	<h4 align="center">펫 맘 회원가입</h4>
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
		<!--펫 정보  -->
		<tr>
			<td align="center">펫 이름</td>
			<td>
			<input type="text" name="pet_name">
			</td>
		</tr>
		<tr>
			<td align="center">펫 종류</td>
			<td>
			<input type="text" name="pet_species">
			</td>
		</tr>
			<tr>
			<td align="center">펫 나이</td>
			<td>
			<input type="text" name="pet_age">
			</td>
		</tr>
			<tr>
			<td align="center">펫 특징</td>
			<td>
			<input type="text" name="pet_character">
			</td>
		</tr>
		
			<tr>
			<td align="center">펫 건강 상태</td>
			<td>
			<input type="text" name="pet_health">
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
	<div class="ufbl-form-wrapper ufbl-default-template" style="width:100%;">
    <form method="post" action="" class="ufbl-front-form">
		<div class="ufbl-form-title">회원 가입</div>				<input type="hidden" name="form_id" value="1" class="form-id"/>
								<div class="ufbl-form-field-wrap " >
							<label>이름</label>
							<div class="ufbl-form-field">
								<input type="text" name="ufbl_field_1" class="ufbl-form-textfield " data-max-chars="" data-min-chars="" data-error-message="" placeholder="이름을 입력해주세요." value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_1"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap id" id="id">
							<label>아이디</label>
							<div class="ufbl-form-field">
								<input type="text" name="ufbl_field_17" class="ufbl-form-textfield ufbl-required" data-max-chars="" data-min-chars="" data-error-message="" placeholder="사용 가능한 아이디를 입력해주세요." value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_17"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap pass" id="pass">
							<label>비밀번호</label>
							<div class="ufbl-form-field">
								<input type="password" name="ufbl_field_24" class="ufbl-form-password ufbl-required" data-max-chars="" data-min-chars="" data-error-message="" placeholder="비밀번호를 입력하세요"/>
								<div class="ufbl-error"  data-error-key="ufbl_field_24"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap email" >
							<label>이메일</label>
							<div class="ufbl-form-field">
								<input type="email" name="ufbl_field_13" class="ufbl-email-field " data-error-message="" placeholder="이메일 주소를 입력해주세요." value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_13"></div>
							</div>
						</div>
												<div class="ufbl-form-field-wrap repass" id="repass">
							<label>비밀번호 중복확인</label>
							<div class="ufbl-form-field">
								<input type="password" name="ufbl_field_25" class="ufbl-form-password ufbl-required" data-max-chars="" data-min-chars="" data-error-message="" placeholder="비밀번호를 입력하세요."/>
								<div class="ufbl-error"  data-error-key="ufbl_field_25"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap sex" id="sex">
							<label>성별</label>
							<div class="ufbl-form-field">
																		<div class="ufbl-sub-field-wrap"><input type="radio" value="남" name="ufbl_field_29" class="ufbl-form-radio ufbl-required" id="1-ufbl_field_29-0"/><label for="1-ufbl_field_29-0">남</label></div>
																				<div class="ufbl-sub-field-wrap"><input type="radio" value="여" name="ufbl_field_29" class="ufbl-form-radio ufbl-required" id="1-ufbl_field_29-1"/><label for="1-ufbl_field_29-1">여</label></div>
																		<div class="ufbl-error"  data-error-key="ufbl_field_29"></div>
							</div>
						</div>
												<div class="ufbl-form-field-wrap job" id="job">
							<label>직업</label>
							<div class="ufbl-form-field">
								<input type="text" name="ufbl_field_27" class="ufbl-form-textfield ufbl-required" data-max-chars="" data-min-chars="" data-error-message="" placeholder="ex)학생, 회사원" value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_27"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap age" id="age">
							<label>나이</label>
							<div class="ufbl-form-field">
								<input type="text" name="ufbl_field_26" class="ufbl-form-textfield ufbl-required" data-max-chars="3" data-min-chars="1" data-error-message="숫자를 입력 하세요." placeholder="나이를 입력하세요" value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_26"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap address" id="address">
							<label>주소</label>
							<div class="ufbl-form-field">
								<input type="text" name="ufbl_field_28" class="ufbl-form-textfield ufbl-required" data-max-chars="100" data-min-chars="" data-error-message="주소를 입력하세요." placeholder="주소를 입력하세요." value=""/>
								<div class="ufbl-error"  data-error-key="ufbl_field_28"></div>
							</div>
						</div>	
												<div class="ufbl-form-field-wrap " >
							<div class="ufbl-form-field">
								<input type="submit" class="ufbl-form-submit" name="ufbl_field_15" value="회원가입"/>
																<span class="ufbl-form-loader" style="display:none"></span>
							</div>
						</div>
								<div class="ufbl-form-message" style="display: none;"></div>

			</form>

</div>

</body>
</html>