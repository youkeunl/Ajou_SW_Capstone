<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="priends.work.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신청내역</title>
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

.ps-list li:hover {
	background: #eee;
	cursor: pointer;
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
Object id = session.getAttribute("memId");
String UserID = (String)id;

String type = (String)session.getAttribute("type");

boolean isPetSitter = false;

if(type.equals("sitter")){
	isPetSitter = true;
}else{
	isPetSitter = false;
}

PetMomDAO m_dao = new PetMomDAO();

//String petSitterID = (String)session.getAttribute("memID");

if(isPetSitter == true){
	PetSitterDAO dao = new PetSitterDAO();
	ArrayList<String> petmomList = dao.getRequest_info(UserID);
	%>
	<body>
	<div style="width:80%; margin:auto;">
	<h2>신청한 펫 맘 정보</h2>
	<%
	if(petmomList.size()!=0){
	%>
	  <ol class = "ps-list">
		<%
		for(int i = 0; i < petmomList.size(); i++){
			PetMomDTO dto = m_dao.selectOne(petmomList.get(i));
			%>
			<li>
				<a href = "alertPet.jsp?petMomID=<%=petmomList.get(i) %>&petSitterID=<%=UserID %>">
				<img src="http://www.priends.co.kr/<%=dto.getImage()%>">
				<h3><%= dto.getName() %></h3>
				<p>
					직업 : <%= dto.getJob() %>, &nbsp;&nbsp; 나이 : <%= dto.getAge() %> <br>
					주소 : <%= dto.getAddress() %> <br>
					특징 : <%= dto.getCharacter() %> <br>
					주의사항 : <%= dto.getCaution() %>
				</p>
			</li>
		</a>
 <%
		}%>

	</ol>
		<%
	}else{
		%>위탁 신청 내역이 없습니다.<%
	}
	%>

	<%
}else{
	PetMomDAO dao = new PetMomDAO();
	ArrayList<PetDTO> respondList = dao.getRespond_info(UserID);
%>

	<div style="height: auto; width: 100%; border-top: 1px solid gold;"></div>
	<h1>PetMom 신청 내역</h1>
	<%=respondList.size() %>
	<%
	if(respondList.size()!=0){
		for(int i = 0; i < respondList.size(); i++){
			%> <div style="height: auto; width: 100%; border-top: 1px solid gold;"></div><p>
			<%if(respondList.get(i).getState() == 0){
				%> PetSitterID : <%= respondList.get(i).getMatch_PetSitterID() %> , 응답 대기 상태 입니다.</p> <%
			}else if(respondList.get(i).getState() == 1){
				%> PetSitterID : <%= respondList.get(i).getMatch_PetSitterID() %> , 위탁이 수락되었습니다.
				<form name="listForm" action="/priends/WebContent/paymentProc.jsp" method="get"><p>

				<input type= "hidden" name="petSitterID" value="<%=respondList.get(i).getMatch_PetSitterID() %>">
				<input type= "hidden" name="petMomID" value="<%=respondList.get(i).getOwner_PetMomID() %>"> </p>
				<input type= "submit" value="최종 결제 하기">
				</form> <%
			}else if(respondList.get(i).getState() == -1){
				%> PetSitterID : <%= respondList.get(i).getMatch_PetSitterID() %> , 위탁이 거절되었습니다.
				<form name="listForm" action="/priends/WebContent/rejectMProc.jsp" method="get"><p>

				<input type= "hidden" name="petSitterID" value="<%=respondList.get(i).getMatch_PetSitterID() %>">
				<input type= "hidden" name="petMomID" value="<%=respondList.get(i).getOwner_PetMomID() %>"> </p>
				<input type= "submit" value="확인">
				</form><%
			}
			else{
				%>결제 완료된 내역입니다.<%
			}

			%>

			<%
		}
	}else{
		%>위탁 신청 내역이 없습니다.<%
	}
	%>
	</div>
</body>
<%
}
%>

</html>
