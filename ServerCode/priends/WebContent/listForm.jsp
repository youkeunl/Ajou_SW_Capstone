<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="work.board.*" %>
<%@ page import="work.img.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%-- <%
String select = request.getParameter("selectbasic");
String search = request.getParameter("search");
BoardDAO dao = new BoardDAO();
BoardDTO dto = new BoardDTO();
ImgDAO img_dao = new ImgDAO();
ImgDTO img_dto = new ImgDTO();

ArrayList<BoardDTO> b_list = new ArrayList<BoardDTO>();
ArrayList<ImgDTO> i_list = new ArrayList<ImgDTO>();
b_list = dao.selectAll(select, search);
for(int i=0;i<b_list.size();i++){
	i_list.add(img_dao.selectOne(b_list.get(i).getImg_addr()));
}


//i_list.get(i).getLink() 

%> --%>

<body>


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
<p>ù��° ��� �� �� �� ���</p>
<hr><p>pet sitter �̸� : ~~~ , �Ÿ�:~~~~ , ����:~~~~~ ���� ~~~~</p>
<hr><p>pet sitter �̸� : ~~~ , �Ÿ�:~~~~ , ����:~~~~~ ���� ~~~~</p>
<hr><p>pet sitter �̸� : ~~~ , �Ÿ�:~~~~ , ����:~~~~~ ���� ~~~~</p>
<br>
<hr>
<p>�ι�° ���</p>
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
    	<!--  �̹��� �ִ� �κ�-->
            <div class="col-md-7">
                <a href="">
                    <img class="img-responsive" height="400" width="700" src="<%=b_list.get(i).getImg_addr()%>" alt="">
                </a>
            </div>
            
           <!--  �̹��� �ִ� �κ�-->
            <div class="col-md-5">
                <h3>���� : <%=b_list.get(i).getTitle() %></h3>
                <h4>�ۼ��� :<%=b_list.get(i).getWriter() %></h4>
                <p>ī�װ� : <%=b_list.get(i).getCategory()%></p>
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