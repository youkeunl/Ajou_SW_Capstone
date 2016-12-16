<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="priends.work.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>신청 내역 확인</title>

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
<%
request.setCharacterEncoding("UTF-8");
String petMomID = request.getParameter("petMomID");
String petSitterID = request.getParameter("petSitterID");

PetMomDAO dao = new PetMomDAO();
PetMomDTO dto = dao.selectOne(petMomID);

ArrayList<PetDTO> petList = dao.getPets_info(petMomID);

%>
<body>
<div style="width:80%;margin:auto;">
   <h2> 신청한 펫 맘 상세 정보 </h2>
   &nbsp;&nbsp;&nbsp;&nbsp; <h4> 위탁 요청 기간 </h4>
   <h4> <%=petList.get(0).getStart_Date() %> ~ <%= petList.get(0).getEnd_Date() %> </h4>

   <ol class = "ps-list">
      <li>
         <img src="http://www.priends.co.kr/<%= dto.getImage()%>">
         <h3><%= dto.getName() %></h3>
         <p>
            직업 : <%= dto.getJob()%>, &nbsp;&nbsp; 나이 : <%= dto.getAge() %> <br>
            주소 : <%= dto.getAddress() %> <br>
            특징 : <%= dto.getCharacter() %> <br>
            주의사항 : <%= dto.getCaution() %>
         </p>
      </li>
      <%
      for(int i = 0; i < petList.size(); i++){
      %>
      <ol class = "ps-list">
         <li>
            <img src="http://www.priends.co.kr/<%= petList.get(i).getImage()%>">
            <h3><%= petList.get(i).getName() %></h3>
            <p>
               종 : <%= petList.get(i).getSpecies() %>, &nbsp;&nbsp; 나이 : <%= petList.get(i).getAge() %> <br>
               건강상태 : <%= petList.get(i).getHealth() %> &nbsp;&nbsp; 성별 : <%= petList.get(i).getSex() %> <br>
               특징 : <%= petList.get(i).getCharacter() %> <br>
               주의사항 : <%= petList.get(i).getCaution()%>
         </li>
      </ol>
      <%
       }
       %>
   </ol>

	<table align="center">
	<tr><td>
   <form name="listForm" action="accept.jsp" method="get">

   <input type= "hidden" name="petMomID" value="<%=petMomID %>">
   <input type= "hidden" name="petSitterID" value="<%=petSitterID %>">
   <input type= "submit" value="수락">
   </form>
</td><td>
   <form name="listForm" action="reject.jsp" method="get">

   <input type= "hidden" name="petMomID" value="<%=petMomID %>">
   <input type= "hidden" name="petSitterID" value="<%=petSitterID %>">
   <input type= "submit" value="거절"></form>
   </td>
   </tr>
   </table>
   </div>

</body>
</html>
