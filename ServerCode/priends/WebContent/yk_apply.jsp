<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="priends.work.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <!-- This stylesheet contains specific styles for displaying the map
         on this page. Replace it with your own styles as described in the
         documentation:
         https://developers.google.com/maps/documentation/javascript/tutorial -->
    <!--<link rel="stylesheet" href="/maps/documentation/javascript/demos/demos.css"-->
    <style type="text/css">
    {margin: 0; padding: 0;}
    div {
      margin: 20px;
    }

    ul {
      list-style-type: none;
      width: 500px;
    }

    h2 {
      text-decoration: none;
      font-weight: bold;
      color: #333;
      font: 25px/1 Helvetica, Verdana, sans-serif;
      text-transform: uppercase;
    }

    h3 {
      font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
    }

    li img {
      float: left;
      margin: 0 15px 0 0;
      width: 120px;
      height: 140px;
    }

    li p {
      font: 200 13px/1.5 Georgia, Times New Roman, serif;
    }

    li {
      padding: 10px;
      overflow: auto;
    }

    li:hover {
      background: #eee;
      cursor: pointer;
    }

    a{
      color: #444;
      text-decoration: none;
      -webkit-transition: all .3s ease-out;
      -moz-transition: all .3s ease-out;
      -ms-transition: all .3s ease-out;
      -o-transition: all .3s ease-out;
      transition: all .3s ease-out;
    }

    </style>
  </head>
  <body>
    <div>
      <h2> 신청한 펫 맘 정보 </h2>
      <ul>
        <li>
          <img src="http://www.priends.co.kr/priends/WebContent/image/ps/PS53.JPG">
          <h3>정수정</h3>
          <p>
            직업 : 학생 , 나이 : 24 <br>
            주소 : 서울시 노원구 월계3동 <br>
            특징 : null <br>
            주의사항 : null
          </p>
        </li>

        <li>
          <img src="http://www.priends.co.kr/priends/WebContent/image/ps/PS10.JPG">
          <h3>이한나</h3>
          <p>
            직업 : 학생 , 나이 : 24 <br>
            주소 : 서울시 노원구 월계3동 <br>
            특징 : null <br>
            주의사항 : null
        </li>

        <li>
          <img src="http://www.priends.co.kr/priends/WebContent/image/ps/PS17.jpg">
          <h3>김소원</h3>
          <p>
            직업 : 학생 , 나이 : 24 <br>
            주소 : 서울시 노원구 월계3동 <br>
            특징 : null <br>
            주의사항 : null
        </li>

      <a href = "http://www.priends.co.kr/priends/WebContent/yk_information.jsp">
        <li>
          <img src="http://www.priends.co.kr/priends/WebContent/image/ps/PS62.JPG">
          <h3>우리유정이</h3>
          <p>
            직업 : 학생 , 나이 : 24 <br>
            주소 : 서울시 노원구 월계3동 <br>
            특징 : null <br>
            주의사항 : null
        </li>
      </a>
      </ul>
    </div>
</body>
</html>
