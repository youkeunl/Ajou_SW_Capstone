<!DOCTYPE html>
<html lang="en">
  
  <head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>회원가입 페이지</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- font awesome -->
    <link rel="stylesheet" href="css/font-awesome.min.css" media="screen" title="no title" charset="UTF-8">
    <!-- Custom style -->
    <link rel="stylesheet" href="css/style.css" media="screen" title="no title" charset="UTF-8">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

      <article class="container">
       
        <div class="col-md-12">
        <div class="page-header">
    	    <h1>펫 맘 회원가입 </h1>
        </div>
        <form name="regform" class="form-horizontal" action="/priends/WebContent/member/regProc.jsp" method="post">
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputName">이름</label>
          <div class="col-sm-6">
            <input name="name" class="form-control" id="inputName" type="text" placeholder="이름">
          </div>
        </div>
        
        
			
			 <div class="form-group">
            <label class="col-sm-3 control-label" for="inputName">아이디</label>
          <div class="col-sm-6">
            <input name="id" class="form-control" id="inputName" type="text" placeholder="아이디">
            
          </div>
          <input type="button" value="중복체크" onclick="javascript:checkId()">
        </div>
       
        <div class="form-group">
          <label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
        <div class="col-sm-6">
          <input name="pass" class="form-control" id="inputPassword" type="password" placeholder="비밀번호">
        
        </div>
        </div>
          <div class="form-group">
              <label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호 확인</label>
             <div class="col-sm-6">
              <input name="repass" class="form-control" id="inputPasswordCheck" type="password" placeholder="비밀번호 확인">
                <p class="help-block">비밀번호를 한번 더 입력해주세요.</p>
             </div>
          </div>
          
          <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumber">성별</label>
              <div class="col-sm-6">
                <div class="input-group">
                  <input type="radio" name="sex" value="남"/>남
                  <input type="radio" name="sex" value="여" checked="checked"/>여
                </div>
              </div>
        </div>
       
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumber">직업</label>
              <div class="col-sm-6">
                <div class="input-group">
                  <input name = "job" type="text" class="form-control" id="inputNumber" placeholder="직업" />
                </div>
              </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">나이</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "age" type="text" class="form-control" id="inputNumber" placeholder="숫자로 입력 하세요." />
       
            </div>
      
          </div>
        </div>
        
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputName">주소</label>
          <div class="col-sm-6">
            <input name="address" class="form-control" id="inputName" type="text" placeholder="주소를 입력 하세요.">
          </div>
        </div>
        
         <div class="page-header">
    	    <h1>펫 정보 </h1>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">펫 이름</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "pet_name" type="text" class="form-control" id="inputNumber" placeholder="펫 이름" />
            </div>
      
          </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">펫 종류</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "pet_species" type="text" class="form-control" id="inputNumber" placeholder="펫 종류" />
            </div>
      
          </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">펫 나이</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "pet_age" type="text" class="form-control" id="inputNumber" placeholder="펫 나이 (숫자로 입력)" />
            </div>
      
          </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">펫 특징</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "pet_character" type="text" class="form-control" id="inputNumber" placeholder="펫 특징" />
            </div>
      
          </div>
        </div>
        
        <div class="form-group">
            <label class="col-sm-3 control-label" for="inputNumberCheck">펫 건강 상태</label>
          <div class="col-sm-6">
            <div class="input-group">
                <input name = "pet_health" type="text" class="form-control" id="inputNumber" placeholder="펫 건강 상태" />
            </div>
      
          </div>
        </div>
          
        <div class="form-group">
          <div class="col-sm-12 text-center">
            <button class="btn btn-primary" type="button" value="회원가입" 
			onclick="javascript:checking()">회원가입<i class="fa fa-check spaceLeft"></i></button>
            <input class="btn btn-default" type="reset" value="다시작성">
			<!-- <button class="btn btn-primary" type="submit">회원가입<i class="fa fa-check spaceLeft"></i></button> -->
            <button class="btn btn-danger" type="button" onclick="location.href='/wp/'">가입취소<i class="fa fa-times spaceLeft"></i></button>
          </div>
        </div>
        </form>
          </div>
      </article>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
