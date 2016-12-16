<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

PetMomDAO dao = new PetMomDAO();
String Start_Date = "2016-12-01";
String End_Date = "2016-12-01";
String petMomID = (String)session.getAttribute("memId");
String petSitterID = request.getParameter("id");;

dao.insertRequest_info(petMomID, petSitterID, Start_Date, End_Date);


%>


<script>
		alert("신청 완료");
		location.href="/priends/WebContent/menu.jsp";
</script>
