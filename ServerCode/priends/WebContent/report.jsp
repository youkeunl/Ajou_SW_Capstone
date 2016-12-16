<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* request.setAttribute("main", "/body.html"); */
	String petmomID = request.getParameter("id");
	String petsitterID = request.getParameter("petSitterID");
	String name = request.getParameter("petname");
	session.setAttribute("petname",name);
	String Start = request.getParameter("start");
	String End = request.getParameter("end");
	String Sex = request.getParameter("sex");
	String type = request.getParameter("type");
	
	String num = request.getParameter("num");
	String sitterID = request.getParameter("sitterID");
	String momID = request.getParameter("momID");
	String ear = request.getParameter("ear");
	String eye = request.getParameter("eye");
	String teeth = request.getParameter("teeth");
	String skin = request.getParameter("skin");
	String dung = request.getParameter("dung");
	String ear_script = request.getParameter("ear_script");
	String eye_script = request.getParameter("eye_script");
	String teeth_script = request.getParameter("teeth_script");
	String skin_script = request.getParameter("skin_script");
	String dung_script = request.getParameter("dung_script");
	String pet_name = request.getParameter("pet_name");
	String petsex = request.getParameter("petsex");
	
	if(type.equals("start")){
		request.setAttribute("main", "/priends/WebContent/reportStartForm.html");
	}else if(type.equals("end")){
		request.setAttribute("main", "/priends/WebContent/reportEndForm.html");
	}else{
		request.setAttribute("main", "/priends/WebContent/reportResultForm.html");
	}

%>
<input type="hidden" name="id" value="<%=petmomID%>">
<input type="hidden" name="petSitterID" value="<%=petsitterID%>">
<input type="hidden" name="petname" value="<%=name%>">
<input type="hidden" name="start" value="<%=Start%>">
<input type="hidden" name="end" value="<%=End%>">
<input type="hidden" name="sex" value="<%=Sex%>">

<input type= "hidden" name="num" value="<%=num %>">
<input type= "hidden" name="sitterID" value="<%=sitterID %>">
<input type= "hidden" name="momID" value="<%=momID %>">
<input type= "hidden" name="ear" value="<%=ear %>">
<input type= "hidden" name="eye" value="<%=eye %>">
<input type= "hidden" name="teeth" value="<%=teeth %>">
<input type= "hidden" name="skin" value="<%=skin %>">
<input type= "hidden" name="dung" value="<%=dung %>">
<input type= "hidden" name="ear_script" value="<%=ear_script %>">
<input type= "hidden" name="eye_script" value="<%=eye_script %>">
<input type= "hidden" name="teeth_script" value="<%=teeth_script %>">
<input type= "hidden" name="skin_script" value="<%=skin_script %>">
<input type= "hidden" name="dung_script" value="<%=dung_script %>">
<input type= "hidden" name="pet_name" value="<%=pet_name %>">
<input type= "hidden" name="petsex" value="<%=petsex %>">

<jsp:forward page="/priends/WebContent/join.jsp"/>
<%-- <jsp:forward page="/join.jsp"/> --%>