<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	Object id = session.getAttribute("memId");
	String UserID = (String)id;
	
	String dotype = request.getParameter("dotype");

	PetSitterDAO dao = new PetSitterDAO();
	PetSitterDTO dto = new PetSitterDTO();
	
	String select_day = request.getParameter("select_day");
	String [] split = select_day.split(",");
	for(int i = 0 ; i < split.length; i++){
		if(Integer.parseInt(split[i]) < 10){
			split[i] = "0" + split[i];
		}
	}
	
	CalenderDAO c_dao = new CalenderDAO();

	String a = "";
	if(dotype.equals("doNo")){
		a = c_dao.update_calender(split, UserID);
	}else{
	    a = c_dao.update_calenderToY(split, UserID);
	}
	
	
	
%><%=a %><script>
		location.href="/priends/WebContent/menu.jsp";
	</script>