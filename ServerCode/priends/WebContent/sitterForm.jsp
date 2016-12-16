<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>펫 시터 정보</title>
<%--  <a href ="/priends/WebContent/testForm.html?id=<%=n_list.get(i).getPetSitter_ID()%>&name=<%=n_list.get(i).getName()%>
&age=<%=n_list.get(i).getAge()%>&address=<%=n_list.get(i).getAddress()%>&day=<%=n_list.get(i).getPrice_day()%>
&night=<%=n_list.get(i).getPrice_night()%>&job=<%=n_list.get(i).getJob()%>"> --%>
<%

request.setCharacterEncoding("UTF-8");
String name = URLDecoder.decode(request.getParameter("name"),"UTF-8");
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age"));
String address = request.getParameter("address");
int price_day = Integer.parseInt(request.getParameter("day"));
int price_night = Integer.parseInt(request.getParameter("night"));
String job = request.getParameter("job");
String image = request.getParameter("image");
String home1 = request.getParameter("home1");
String home2 = request.getParameter("home2");
String home3 = request.getParameter("home3");
String sex = request.getParameter("sex");
String sDate = request.getParameter("sDate");
String eDate = request.getParameter("eDate");

//String param =  new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");


%>
<style type="text/css">
{margin: 0; padding: 0;}
.ps-list div {
	margin: 20px;
}

 ol {
	list-style-type: none;
	width: 500px;
}

.ps-list h2 {
	text-decoration: none;
	font-weight: bold;
	color: #333;
	font: 25px/1 Helvetica, Verdana, sans-serif;
	text-transform: uppercase;
}

.ps-list h3 {
	font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
}

.ps-list h4{
	font: bold 10px/1.5 Helvetica, Verdana, sans-serif;
}

.ps-list li img {
	float: left;
	margin: 0 15px 0 0;
	width: 120px;
	height: 140px;
}

.ps-list li p {
	font: 200 13px/1.5 Georgia, Times New Roman, serif;
}

.ps-list li {
	padding: 10px;
	overflow: auto;
}

.ps-list a{
	color: #444;
	text-decoration: none;
	-webkit-transition: all .3s ease-out;
	-moz-transition: all .3s ease-out;
	-ms-transition: all .3s ease-out;
	-o-transition: all .3s ease-out;
	transition: all .3s ease-out;
}


</style>
</head>
<body>
<div style="width:80%; margin:auto;">
<form action="/priends/WebContent/applyProc.jsp" name="sitterForm" id="sitterForm" method="get">

	<hr>
	
	
	<ol class = "ps-list">
         <li>
            <img src="http://www.priends.co.kr/<%=image%>">
            <h3><%=name%></h3>
            <p>
               직업: <%=job%>, &nbsp;&nbsp; 나이 : <%=age%> <br>
               주소: <%=address%> &nbsp;&nbsp; 성별 : <%=sex%> <br>
             
         </li>
      </ol>
      <div style="margin:auto; width:100%">
      <p>첫 번째 집 사진</p>
      <img style="margin:auto; width:640px; height:400px;"src="http://www.priends.co.kr/<%=home1%>">
      </div>
      <div style="margin:auto; width:100%">
      <p>두 번째 집 사진</p>
      <img style="margin:auto; width:640px; height:400px;"src="http://www.priends.co.kr/<%=home2%>">
      </div>
      <div style="margin:auto; width:100%">
      <p>세 번째 집 사진</p>
      <img style="margin:auto; width:640px; height:400px;"src="http://www.priends.co.kr/<%=home3%>">
      </div>
      <input type="hidden" name="id" value="<%=id%>" >
      <input type="hidden" name="sDate" value="<%=sDate%>">
      <input type="hidden" name="eDate" value="<%=eDate%>">
	<input type="submit" value="신청하기"></form>
	

</div>
</body>
</html>