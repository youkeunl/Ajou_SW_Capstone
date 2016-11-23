<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h3 align="center">리뷰쓰기</h3>
	<hr color="black">
	
	<div class="container">
	
		<form name="writerform" method="post" action="borderWrite.jsp" enctype="multipart/form-data">
		<table align="center" cellpadding="1">
		
			<tr align="center">
			<td colspan="2" id="view">
			
			
			</td>
			
			</tr>
			<tr align="center">
			
				<th>파일설명</th>
				<td>
				
					<%=request.getParameter("info") %>
				</td>
			</tr>
		
			<tr align="center">
			
				<th>이미지</th>
				<td>
				
					<img alt="" height="400" width="700" src="<%=request.getParameter("link") %>">
				</td>
			</tr>

		
			<tr>
				<td><br>제목</td>
				<td>
				<br>
					<%=request.getParameter("title") %>
					
				</td>
			</tr>
			
			
			<tr>
				<td><br>내용</td>
				<td>
				<br>
					<%=request.getParameter("content") %>
					
				</td>
			</tr>
			
			<tr>
				<td><br>작성자</td>
				<td><br>
					<%=request.getParameter("writer") %>
					
				</td>
			</tr>
			
			<tr>
				<td><br></td>
				<td><br>
					
					<input type="button" value="처음으로"
					onclick="location.href='/WebReview/index.jsp'">
					
					<input type="button" value="글목록"
					onclick="location.href='/WebReview/list.jsp'">
					
				</td>
			</tr>
	
		</table>
	</form>
	</div>
</body>
</html>