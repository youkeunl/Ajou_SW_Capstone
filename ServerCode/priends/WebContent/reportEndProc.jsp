<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

request.setCharacterEncoding("UTF-8");
PetSitterDAO p_dao = new PetSitterDAO();
PetMomDAO dao = new PetMomDAO();
ReportDTO r_dto = new ReportDTO();
ReportDAO r_dao = new ReportDAO();
String petSitterID = (String)session.getAttribute("memId");
String petMomID = request.getParameter("id");;


int dung = Integer.parseInt(request.getParameter("dung"));
int ear = Integer.parseInt(request.getParameter("ear"));
int eye = Integer.parseInt(request.getParameter("eye"));
int skin = Integer.parseInt(request.getParameter("skin"));
int teeth = Integer.parseInt(request.getParameter("teeth"));
String pet_name = request.getParameter("petname");
String pet_sex = request.getParameter("pet_sex");
String dung_script = request.getParameter("dung_script");
String ear_script = request.getParameter("ear_script");
String eye_script = request.getParameter("eye_script");
String skin_script = request.getParameter("skin_script");
String teeth_script = request.getParameter("teeth_script");

int pet_id = 0;
pet_id = Integer.parseInt(request.getParameter("pet_id"));
r_dto.setDung(dung);
r_dto.setDung_script(dung_script);
r_dto.setEar(ear);
r_dto.setEar_script(ear_script);
r_dto.setEye(eye);
r_dto.setEye_script(eye_script);
r_dto.setSkin(skin);
r_dto.setSkin_script(skin_script);
r_dto.setTeeth(teeth);
r_dto.setTeeth_script(teeth_script);
r_dto.setPetMom_ID(petMomID);
r_dto.setPetSitter_ID(petSitterID);
r_dto.setPet_name("pet_name");
r_dto.setPet_sex(pet_sex);
r_dto.setState(0);
String s = null;
s=r_dao.insertReport(r_dto);
int n = p_dao.deleteState_Report(petMomID, petSitterID, pet_id);

if(n>0){
	%>
	

	<script>
		alert("<%=s%>");
			alert("작성 완료");
			location.href="/priends/WebContent/select.jsp";
	</script><%
}else{
	%>


	<script>
	alert("<%=s%>");
			alert("작성 실패");
			location.href="/priends/WebContent/select.jsp";
	</script>
	<%
}
%>


