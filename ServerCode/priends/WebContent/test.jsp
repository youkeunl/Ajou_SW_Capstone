<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<%
String shower = request.getParameter("shower");
String walk = request.getParameter("walk");
String snack = request.getParameter("snack");
String education = request.getParameter("education");
String hospital = request.getParameter("hospital");
String pickup = request.getParameter("pickup");
String all = request.getParameter("all");

int service = 0;
if(shower.equals("select")){
	service += 100000;
}
if(walk.equals("select")){
	service += 10000;
}
if(snack.equals("select")){
	service += 1000;
}
if(education.equals("select")){
	service += 100;
}
if(hospital.equals("select")){
	service += 10;
}
if(pickup.equals("select")){
	service += 1;
}
String service2 = Integer.toString(service);
if(all.equals("select")){
	service2 = "none";
	}

PetSitterDAO dao = new PetSitterDAO();
PetSitterDTO dto = new PetSitterDTO();
Main main = new Main();

Object id = session.getAttribute("memId");
String mom_id = (String)id;

ArrayList<PetSitterDTO> list = new ArrayList<PetSitterDTO>();
ArrayList<PetSitterDTO> n_list = new ArrayList<PetSitterDTO>();
ArrayList<PetSitterDTO> d_list = new ArrayList<PetSitterDTO>();
ArrayList<PetSitterDTO> p_list = new ArrayList<PetSitterDTO>();
int type = 0;
list = main.beforeSelect_price_day(mom_id,service2);
d_list = main.beforeSelect_price_distance(mom_id,service2);
p_list = main.beforeSelect_priority(mom_id,service2);
n_list = main.beforeSelect_price_night(mom_id, service2);


if(request.getParameter("type") != null){
	type = Integer.parseInt(request.getParameter("type"));
}


%>
</head>
<body>

  
  <body>
  <p></p>
<p>test</p>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="0"><input type="submit" value="추천순" ></form>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="1"><input type="submit"  value="거리순"></form>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="2"><input type="submit"  value="Day 가격순"></form>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="3"><input type="submit"  value="Night 가격순"></form>
<%=shower %>
<%=walk %>
<%=snack %>
<%=education %>
<%=hospital %>
<%=pickup %>
<%=all %>
<%=id %>
	
	<%=service %>
	<%=service2 %>
	<%
if(type == 0){
for(int i=0;i<p_list.size();i++){
	%>
	<form name="listForm" action="/priends/WebContent/testForm.html" method="get">
	<hr>
	<p>이름 : <%=p_list.get(i).getName() %> , 나이: <%=p_list.get(i).getAge() %>, 주소: <%=p_list.get(i).getAddress() %>,
	day 가격: <%=p_list.get(i).getPrice_day() %>, Night 가격: <%=p_list.get(i).getPrice_night() %>
	, X 좌표 :<%=p_list.get(i).getPoint_x() %> , Y 좌표: <%=p_list.get(i).getPoint_y()%> , 거리 : <%=p_list.get(i).getDistance()%>
	<input type="hidden" name="id" value="<%=p_list.get(i).getPetSitter_ID()%>">
	<input type="hidden" name="name" value="<%=p_list.get(i).getName()%>">
	<input type="hidden" name="age" value="<%=p_list.get(i).getAge()%>">
	<input type="hidden" name="address" value="<%=p_list.get(i).getAddress()%>">
	<input type="hidden" name="day" value="<%=p_list.get(i).getPrice_day()%>">
	<input type="hidden" name="night" value="<%=p_list.get(i).getPrice_night()%>">
	<input type="hidden" name="job" value="<%=p_list.get(i).getJob()%>">
	<input type="hidden" name="x_point" value="<%=p_list.get(i).getPoint_x()%>">
	<input type="hidden" name="y_point" value="<%=p_list.get(i).getPoint_y()%>">
	<input type="submit" value="정보 보기"></p></form>
<%}}
else if(type == 1){
	for(int i=0;i<d_list.size();i++){
		%><form name="listForm" action="/priends/WebContent/testForm.html" method="get">
		<hr>
		<p>이름 : <%=d_list.get(i).getName() %> , 나이: <%=d_list.get(i).getAge() %>, 주소: <%=d_list.get(i).getAddress() %>,
		day 가격: <%=d_list.get(i).getPrice_day() %>, Night 가격: <%=d_list.get(i).getPrice_night() %>, 거리 : <%=d_list.get(i).getDistance()%>
		, X 좌표 :<%=d_list.get(i).getPoint_x() %> , Y 좌표: <%=d_list.get(i).getPoint_y()%>
		<input type="hidden" name="id" value="<%=d_list.get(i).getPetSitter_ID()%>">
		<input type="hidden" name="name" value="<%=d_list.get(i).getName()%>">
		<input type="hidden" name="age" value="<%=d_list.get(i).getAge()%>">
		<input type="hidden" name="address" value="<%=d_list.get(i).getAddress()%>">
		<input type="hidden" name="day" value="<%=d_list.get(i).getPrice_day()%>">
		<input type="hidden" name="job" value="<%=d_list.get(i).getJob()%>">
		<input type="hidden" name="night" value="<%=d_list.get(i).getPrice_night()%>">
		<input type="hidden" name="x_point" value="<%=d_list.get(i).getPoint_x()%>">
		<input type="hidden" name="y_point" value="<%=d_list.get(i).getPoint_y()%>">
		<input type="submit" value="정보 보기"></p></form>
	<%}}
else if(type == 2){
	for(int i=0;i<d_list.size();i++){
		%><form name="listForm" action="/priends/WebContent/testForm.html" method="get">
		<hr>
		<p>이름 : <%=n_list.get(i).getName() %> , 나이: <%=n_list.get(i).getAge() %>, 주소: <%=n_list.get(i).getAddress() %>,
		day 가격: <%=n_list.get(i).getPrice_day() %>, Night 가격: <%=n_list.get(i).getPrice_night() %>, 거리 : <%=n_list.get(i).getDistance()%>
		, X 좌표 :<%=n_list.get(i).getPoint_x() %> , Y 좌표: <%=n_list.get(i).getPoint_y()%>
		<input type="hidden" name="id" value="<%=n_list.get(i).getPetSitter_ID()%>">
		<input type="hidden" name="name" value="<%=n_list.get(i).getName()%>">
		<input type="hidden" name="age" value="<%=n_list.get(i).getAge()%>">
		<input type="hidden" name="address" value="<%=n_list.get(i).getAddress()%>">
		<input type="hidden" name="day" value="<%=n_list.get(i).getPrice_day()%>">
		<input type="hidden" name="job" value="<%=n_list.get(i).getJob()%>">
		<input type="hidden" name="night" value="<%=n_list.get(i).getPrice_night()%>">
		<input type="hidden" name="x_point" value="<%=n_list.get(i).getPoint_x()%>">
		<input type="hidden" name="y_point" value="<%=n_list.get(i).getPoint_y()%>">
		<input type="submit" value="정보 보기"></p></form>
	<%}}
	else{
	for(int i=0;i<list.size();i++){
		%>
		<form name="listForm" action="/priends/WebContent/testForm.html" method="get">
	<hr>
	<p>이름 : <%=list.get(i).getName() %> , 나이: <%=list.get(i).getAge() %>, 주소: <%=list.get(i).getAddress() %>,
	day 가격: <%=list.get(i).getPrice_day() %>, Night 가격: <%=list.get(i).getPrice_night() %>, 거리 : <%=list.get(i).getDistance()%>
	, X 좌표 :<%=list.get(i).getPoint_x() %> , Y 좌표: <%=list.get(i).getPoint_y()%>
	<input type="hidden" name="id" value="<%=list.get(i).getPetSitter_ID()%>">
	<input type="hidden" name="name" value="<%=list.get(i).getName()%>">
	<input type="hidden" name="age" value="<%=list.get(i).getAge()%>">
	<input type="hidden" name="address" value="<%=list.get(i).getAddress()%>">
	<input type="hidden" name="day" value="<%=list.get(i).getPrice_day()%>">
	<input type="hidden" name="night" value="<%=list.get(i).getPrice_night()%>">
	<input type="hidden" name="job" value="<%=list.get(i).getJob()%>">
	<input type="hidden" name="x_point" value="<%=list.get(i).getPoint_x()%>">
	<input type="hidden" name="y_point" value="<%=list.get(i).getPoint_y()%>">
	<input type="submit" value="정보 보기"></p></form>
	<%}}%>
	<br>
</body>
</html>

