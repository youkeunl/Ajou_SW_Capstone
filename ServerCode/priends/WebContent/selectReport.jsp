<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="priends.work.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신청내역</title>
</head>
<%
Object id = session.getAttribute("memId");
String UserID = (String)id;
ReportDAO r_dao = new ReportDAO();
ReportDTO r_dto = new ReportDTO();
ArrayList<ReportDTO> r_list = new ArrayList<ReportDTO>();
String type = (String)session.getAttribute("type");
r_list = r_dao.getAllReport(UserID, type);


boolean isPetSitter = false;

if(type.equals("sitter")){
	isPetSitter = true;
}else{
	isPetSitter = false;
}

	PetSitterDAO dao = new PetSitterDAO();
	ArrayList<PetDTO> petList = dao.getReport_info(UserID);
	%>
	<body>
	<div style="width:80%; margin:auto;">
	<div style="height: auto; width: 100%; border-top: 1px solid gold;"></div>
	<h1>PetSitter Report 현황</h1>
	
		<%
	String isStartReport = "start";
	String isEndReport = "end";
	if(type.equals("sitter")){
		
	
	if(petList.size()!=0){
		for(int i = 0; i < petList.size(); i++){
			%> <div style="height: auto; width: 100%; border-top: 1px solid gold;"></div>
			Report <%=i+1%> 
			PetMom name : <%=petList.get(i).getOwner_PetMomID() %> , Pet name : <%=petList.get(i).getName() %>
			Start_Date : <%=petList.get(i).getStart_Date() %> , End_Date : <%=petList.get(i).getEnd_Date() %>, 
			Pet_ID : <%=petList.get(i).getPet_ID() %>
			<%if(petList.get(i).getState() == 2){
				%>
				<table>
			<tr>
			<td>
			<form name="listForm" action="/priends/WebContent/report.jsp" method="get"><p>
			<input type="hidden" name="type" value="start">
			<input type= "submit" value="Start Report 작성하기">
			<input type= "hidden" name="id" value="<%=petList.get(i).getOwner_PetMomID() %>">
			<input type= "hidden" name="petSitterID" value="<%=UserID %>">
			<input type= "hidden" name="petname" value="<%=petList.get(i).getName() %>">
			<input type= "hidden" name="start" value="<%=petList.get(i).getStart_Date() %>">
			<input type= "hidden" name="end" value="<%=petList.get(i).getEnd_Date() %>">
			<input type= "hidden" name="sex" value="<%=petList.get(i).getSex() %>"> 
			<input type= "hidden" name="pet_id" value="<%=petList.get(i).getPet_ID()%>">
			</p></form>
			</td>
			</tr>
			</table>
				
				<%
			}else if(petList.get(i).getState() == 3){
				%>
				<table>
			<tr>
			
			<td>
			<form name="listForm" action="report.jsp" method="get"><p>
			<input type="hidden" name="type" value="end">
			<input type= "submit" value="End Report 작성하기">
			<input type= "hidden" name="id" value="<%=petList.get(i).getOwner_PetMomID() %>">
			<input type= "hidden" name="petSitterID" value="<%=UserID %>">
			<input type= "hidden" name="petname" value="<%=petList.get(i).getName() %>">
			<input type= "hidden" name="start" value="<%=petList.get(i).getStart_Date() %>">
			<input type= "hidden" name="end" value="<%=petList.get(i).getEnd_Date() %>">
			<input type= "hidden" name="sex" value="<%=petList.get(i).getSex() %>"> 
			<input type= "hidden" name="pet_id" value="<%=petList.get(i).getPet_ID()%>">
			</p></form> 
			</td>
			</tr>
			</table>
				<%
			}
			else{
				
			}
			
		}
	}else{
		%>현재 진행중인 위탁이 없습니다.<%
	}%>
	
	<h1>작성 완료된 Report</h1>
	<%for(int i = 0; i < r_list.size(); i++){
		%> 
		
		Pet name : <%= r_list.get(i).getPet_name()%>, 성별 : <%= r_list.get(i).getPet_sex() %>
		<form name="listForm" action="/priends/WebContent/report.jsp" method="get"><p>
			<input type= "hidden" name="type" value="result">
			<input type= "submit" value="보기">
			<input type= "hidden" name="num" value="<%=r_list.get(i).getNumber() %>">
			<input type= "hidden" name="sitterID" value="<%=r_list.get(i).getPetSitter_ID() %>">
			<input type= "hidden" name="momID" value="<%=r_list.get(i).getPetMom_ID() %>">
			<input type= "hidden" name="ear" value="<%=r_list.get(i).getEar() %>">
			<input type= "hidden" name="eye" value="<%=r_list.get(i).getEye() %>">
			<input type= "hidden" name="teeth" value="<%=r_list.get(i).getTeeth() %>">
			<input type= "hidden" name="skin" value="<%=r_list.get(i).getSkin() %>">
			<input type= "hidden" name="dung" value="<%=r_list.get(i).getDung() %>">
			<input type= "hidden" name="ear_script" value="<%=r_list.get(i).getEar_script() %>">
			<input type= "hidden" name="eye_script" value="<%=r_list.get(i).getEye_script() %>">
			<input type= "hidden" name="teeth_script" value="<%=r_list.get(i).getTeeth_script() %>">
			<input type= "hidden" name="skin_script" value="<%=r_list.get(i).getSkin_script() %>">
			<input type= "hidden" name="dung_script" value="<%=r_list.get(i).getDung_script() %>">
			<input type= "hidden" name="pet_name" value="<%=r_list.get(i).getPet_name() %>">
			<input type= "hidden" name="petsex" value="<%=r_list.get(i).getPet_sex() %>">
			
			</p></form> <% 
	}
	
	
	
	}else{
		
		
		
	}
	%>
	</div>
</body>


</html>