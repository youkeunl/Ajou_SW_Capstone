<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<%@ page import="priends.work.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%
String Query = "select * from petsitter";

ArrayList<PetSitterDTO> petsitterList = new ArrayList<PetSitterDTO>();
PetSitterDTO dto = null;

String DB_address = "jdbc:mysql://localhost";
String DB_ID = "dldbrms79";
String DB_PW = "priends@";
String use_Query = "use dldbrms79";

try {
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con = null;

	con = DriverManager.getConnection(DB_address, DB_ID, DB_PW);

	java.sql.Statement st = con.createStatement();

	st.executeQuery(use_Query);
	ResultSet rs = st.executeQuery(Query);

	if (st.execute(Query)) {
		rs = st.getResultSet();
	}

	while (rs.next()) {
		dto = new PetSitterDTO();

		dto.setPetSitter_ID(rs.getString("PetSitter_ID"));
		dto.setName(rs.getString("PetSitter_name"));
		dto.setSex(rs.getString("PetSitter_Sex"));
		dto.setJob(rs.getString("PetSitter_Job"));
		dto.setCareer(rs.getInt("PetSitter_Career"));
		dto.setCertification(rs.getString("PetSitter_Certification"));
		dto.setAge(rs.getInt("PetSitter_age"));
		dto.setCharacter(rs.getString("PetSitter_Character"));
		dto.setAddress(rs.getString("PetSitter_Address"));
		dto.setPoint_x(rs.getDouble("PetSitter_MappointX"));
		dto.setPoint_y(rs.getDouble("PetSitter_MappointY"));
		dto.setPrice_day(rs.getInt("Price_Day"));
		dto.setPrice_night(rs.getInt("Price_1Night"));

		petsitterList.add(dto);
	}
	rs.close();
	st.close();
	con.close();
	

} catch (SQLException sqex) {
	%><%=sqex.getMessage()%>
	
	<%
}


%>

<body>

<div align="center">
   <img id="" alt="" src="/priends/WebContent/image/googlMap.jpg" style="width:30%; height:30%;">
</div>
    <div id="map" style="width:30%; height:30%"></div>
    <script type="text/javascript">

var map;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 37.291634, lng: 127.04033},
    zoom: 15
  });
}

    </script>
    <script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_mTC_yBsaEO3296b_p3QRi9lRMU7JO_c&callback=initMap">
    </script>
<p></p>
<p></p>
<p>첫번째 방법 한 줄 씩 출력</p>
<%
for(int i=0;i<petsitterList.size();i++){
   %><hr><p>이름 : <%=petsitterList.get(i).getName() %> , 나이: <%=petsitterList.get(i).getAge() %>, 주소: <%=petsitterList.get(i).getAddress() %>, 
   day 가격: <%=petsitterList.get(i).getPrice_day() %>, Night 가격: <%=petsitterList.get(i).getPrice_night() %></p>

<%
}


%>
<hr><p>pet sitter 이름 : ~~~ , 거리:~~~~ , 가격:~~~~~ 정보 ~~~~</p>
<hr><p>pet sitter 이름 : ~~~ , 거리:~~~~ , 가격:~~~~~ 정보 ~~~~</p>
<hr><p>pet sitter 이름 : ~~~ , 거리:~~~~ , 가격:~~~~~ 정보 ~~~~</p>
<br>
<hr>
<p>두번째 방법</p>
<div align="left">
   <img id="" alt="" src="/priends/WebContent/image/sitter.jpg" style="width:10%; height:10%;">
</div>
<div align="center">
   <img id="" alt="" src="/priends/WebContent/image/sitter.jpg" style="width:10%; height:10%;">
</div>
<div align="right">
   <img id="" alt="" src="/priends/WebContent/image/sitter.jpg" style="width:10%; height:10%;">
</div>

<%-- <% for(int i=0; i<b_list.size();i++){


   //if(b_list.get(i).getCategory().equals(select)){
%>
      
      <div class="container">
       <div class="row">
       <!--  이미지 넣는 부분-->
            <div class="col-md-7">
                <a href="">
                    <img class="img-responsive" height="400" width="700" src="<%=b_list.get(i).getImg_addr()%>" alt="">
                </a>
            </div>
            
           <!--  이미지 넣는 부분-->
            <div class="col-md-5">
                <h3>제목 : <%=b_list.get(i).getTitle() %></h3>
                <h4>작성자 :<%=b_list.get(i).getWriter() %></h4>
                <p>카테고리 : <%=b_list.get(i).getCategory()%></p>
                <a class="btn btn-primary" href="/WebReview/view.jsp?title=<%=b_list.get(i).getTitle()%>&writer=
                <%=b_list.get(i).getWriter()%>&link=<%=b_list.get(i).getImg_addr()%>
                &info=<%=i_list.get(i).getInfo()%>&content=<%=b_list.get(i).getContent()%>">View content<span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>

        </div>

<%//}

}
%>  --%>


</body>
</html>