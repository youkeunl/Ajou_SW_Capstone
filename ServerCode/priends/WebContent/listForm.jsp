<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>리스트 출력</title>

    <!-- This stylesheet contains specific styles for displaying the map
         on this page. Replace it with your own styles as described in the
         documentation:
         https://developers.google.com/maps/documentation/javascript/tutorial -->
    <!--<link rel="stylesheet" href="/maps/documentation/javascript/demos/demos.css"-->
    <style type="text/css">
      html, body { height: 100%; margin: 0; padding: 0; }
      #map {height: 60%; width: 70%;}

      img{
        width: 60px;
        height: 60px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        float: center;
      }

    </style>
</head>
<%

PetSitterDAO dao = new PetSitterDAO();
PetSitterDTO dto = new PetSitterDTO();
Main main = new Main();

Object id = session.getAttribute("memId");
String mom_id = (String)id;

ArrayList<PetSitterDTO> list = new ArrayList<PetSitterDTO>();
ArrayList<PetSitterDTO> d_list = new ArrayList<PetSitterDTO>();
ArrayList<PetSitterDTO> p_list = new ArrayList<PetSitterDTO>();
int type = 0;
list = main.beforeSelect_price_day("M1","101111", "16-12-01" , "16-12-01");
d_list = main.beforeSelect_price_distance("M1","110011", "16-12-01" , "16-12-01");
p_list = main.beforeSelect_priority("M1","110011", "16-12-01" , "16-12-01");
if(request.getParameter("type") != null){
	type = Integer.parseInt(request.getParameter("type"));
}


%>

<body>
  <br><br>
    <div id="map" style ="margin: auto;"></div>
    <script>
// The following example creates complex markers to indicate beaches near
// Sydney, NSW, Australia. Note that the anchor is set to (0,32) to correspond
// to the base of the flagpole.

function initMap() {
  var petmom = {lat: 37.291634, lng: 127.04033};
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 13,
    center: petmom
  });
  setMarkers(map);
  var image_petmom = {
    url: '/pmmap.png',
    // This marker is 20 pixels wide by 32 pixels high./priensds/Webcontent/listForm.jsp
    size: new google.maps.Size(52, 74),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
  };

  var contentString = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h3 id="firstHeading" class="firstHeading">PetMom1</h3>'+
      '<div id="bodyContent">'+
      '<p><b>Pet Mom ID: </b> <b>M1</b> :' +
      ': 현재 나의 위치</p>'+
      '<p><img src="/sample.jpg" width="300" height="150"> </p>'+
      '</div>'+
      '</div>';

  var infowindow = new google.maps.InfoWindow({
    content: contentString
  });

  var marker = new google.maps.Marker({
    position: petmom,
    map: map,
    icon: image_petmom,
    //draggable: true,
    //info: content,
    title: 'petmom'
  });

  marker.addListener('click', function() {
    infowindow.open(map, marker);
  });

}

// Data for the markers consisting of a name, a LatLng and a zIndex for the
// order in which these markers should display on top of each other.

var pet_sitters = [
  ['petsitter1', 37.28424, 127.06934],
  ['petsitter2', 37.330124, 127.139],
  ['petsitter3', 37.259148, 127.06697],
  ['petsitter4', 37.277996, 127.04411]
];

function setMarkers(map) {

  var image = {
    url: '/psmap.png',
    // This marker is 20 pixels wide by 32 pixels high.
    size: new google.maps.Size(52, 74),
    // The origin for this image is (0, 0).
    origin: new google.maps.Point(0, 0),
    // The anchor for this image is the base of the flagpole at (0, 32).
    anchor: new google.maps.Point(0, 32)
  };

  for (var i = 0; i < pet_sitters.length; i++) {
    var pet_sitter = pet_sitters[i];
    var marker = new google.maps.Marker({
      position: {lat: pet_sitter[1], lng: pet_sitter[2]},
      map: map,
      icon: image,
      //info: content,
      title: pet_sitter[0]
    });
    markerListener(marker);
  }
}

function markerListener(localmarker){
  google.maps.event.addListener(localmarker, 'click', function(){
    //i번째 마커 클릭했을 때 실행할 내용들
    var contentString = '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<h3 id="firstHeading" class="firstHeading">PetSitter</h3>'+
        '<div id="bodyContent">'+
        '<p><b>Pet Sitter ID </b> <b>M1</b> :' +
        ': PetSitter입니다.</p>'+
        '<p><img src="/sample.jpg" width="300" height="150"> </p>'+
        '</div>'+
        '</div>';

    var infowindow = new google.maps.InfoWindow({
      content: contentString
    });

    infowindow.open(map, localmarker);
  });
}

</script>
<script async defer
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_mTC_yBsaEO3296b_p3QRi9lRMU7JO_c&callback=initMap">
</script>
<br>
<br>

<table align = "center" text-align ="center">
  <tr>
    <td>
      <a href ="http://www.priends.co.kr/priends/WebContent/image/matching.JPG">
        <img src = "http://www.priends.co.kr/priends/WebContent/image/matching.JPG"/>
      </a>
      &nbsp;&nbsp;
    </td>
    <td width="20"></td>
    <td>
      <a href ="http://www.priends.co.kr/priends/WebContent/image/price_day.JPG">
        <img src = "http://www.priends.co.kr/priends/WebContent/image/price_day.JPG"/>
      </a>
      &nbsp;&nbsp;
    </td>
    <td width="20"></td>
    <td>
      <a href ="http://www.priends.co.kr/priends/WebContent/image/price.JPG">
        <img src = "http://www.priends.co.kr/priends/WebContent/image/price.JPG"/>
      </a>
      &nbsp;&nbsp;
    </td>
    <td width="20"></td>
    <td>
      <a href ="http://www.priends.co.kr/priends/WebContent/image/distance.JPG">
        <img src = "http://www.priends.co.kr/priends/WebContent/image/distance.JPG"/>
      </a>
    </td>
  </tr>


  <tr>
    <td >
      <strong> 선호도순 </strong>
      &nbsp;&nbsp;
    </td>
    <td width="30"></td>
    <td>
      <strong> 가격순(1일 이내) </strong>
      &nbsp;&nbsp;
    </td>
    <td width="30"></td>
    <td>
      <strong> 가격순(1박 이상) </strong>
      &nbsp;&nbsp;
    </td>
    <td width="30"></td>
    <td>
      <strong> 거리순 </strong>
    </td>
  </tr>
</table>

<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="0"><input type="submit" value="추천순"></form>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="1"><input type="submit"  value="가격순"></form>
<form action="/priends/WebContent/listProc.jsp"><input type="hidden" name="type" value="2"><input type="submit"  value="거리순"></form>

<%=id %>
<%
if(type == 0){
for(int i=0;i<p_list.size();i++){
	%><form name="listForm" action="/priends/WebContent/testForm.html" method="get">
	<hr>
	<p>이름 : <%=p_list.get(i).getName() %> , 나이: <%=p_list.get(i).getAge() %>, 주소: <%=p_list.get(i).getAddress() %>,
	day 가격: <%=p_list.get(i).getPrice_day() %>, Night 가격: <%=p_list.get(i).getPrice_night() %>
	, X 좌표 :<%=p_list.get(i).getPoint_x() %> , Y 좌표: <%=p_list.get(i).getPoint_y()%> , 거리 : <%=p_list.get(i).getDistance()%>
	<input type="hidden" name="name" value="<%=p_list.get(i).getName()%>">
	<input type="hidden" name="age" value="<%=p_list.get(i).getAge()%>">
	<input type="hidden" name="address" value="<%=p_list.get(i).getAddress()%>">
	<input type="hidden" name="day" value="<%=p_list.get(i).getPrice_day()%>">
	<input type="hidden" name="night" value="<%=p_list.get(i).getPrice_night()%>">
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
		<input type="hidden" name="name" value="<%=d_list.get(i).getName()%>">
		<input type="hidden" name="age" value="<%=d_list.get(i).getAge()%>">
		<input type="hidden" name="address" value="<%=d_list.get(i).getAddress()%>">
		<input type="hidden" name="day" value="<%=d_list.get(i).getPrice_day()%>">
		<input type="hidden" name="night" value="<%=d_list.get(i).getPrice_night()%>">
		<input type="hidden" name="x_point" value="<%=d_list.get(i).getPoint_x()%>">
		<input type="hidden" name="y_point" value="<%=d_list.get(i).getPoint_y()%>">
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
	<input type="hidden" name="name" value="<%=list.get(i).getName()%>">
	<input type="hidden" name="age" value="<%=list.get(i).getAge()%>">
	<input type="hidden" name="address" value="<%=list.get(i).getAddress()%>">
	<input type="hidden" name="day" value="<%=list.get(i).getPrice_day()%>">
	<input type="hidden" name="night" value="<%=list.get(i).getPrice_night()%>">
	<input type="hidden" name="x_point" value="<%=list.get(i).getPoint_x()%>">
	<input type="hidden" name="y_point" value="<%=list.get(i).getPoint_y()%>">
	<input type="submit" value="정보 보기"></p></form>
	<%}}%>
  </body>
</html>
