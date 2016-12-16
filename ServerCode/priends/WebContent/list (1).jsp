<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String shower = request.getParameter("shower");
String walk = request.getParameter("walk");
String snack = request.getParameter("snack");
String education = request.getParameter("education");
String hospital = request.getParameter("hospital");
String pickup = request.getParameter("pickup");
String all = request.getParameter("all");
String search = request.getParameter("search");
String sDate = request.getParameter("sDate");
String eDate = request.getParameter("eDate");

int service = 1000000;
if(shower!=null){
	service += 100000;
}
if(walk!=null){
	service += 10000;
}
if(snack!=null){
	service += 1000;
}
if(education!=null){
	service += 100;
}
if(hospital!=null){
	service += 10;
}
if(pickup!=null){
	service += 1;
}
String service2 = Integer.toString(service);
if(request.getParameter("service2")!=null){
	service2 = request.getParameter("service2");
}
if(all!=null||service2.equals("1000000")){
	service2 = "none";
	}
if(search!=null){
	request.setAttribute("service2",service2);
	
}
/* if(sDate!=null){
	request.setAttribute("sDate",sDate);
	System.out.printf("sDate = %s",sDate);
} */



request.setAttribute("main", "/test.jsp");
%>
<jsp:forward page="/priends/WebContent/join.jsp" />