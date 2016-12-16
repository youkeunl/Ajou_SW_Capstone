<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="priends.work.*" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.io.*" %>

<%
	request.setCharacterEncoding("UTF-8");
/* //파일이 저장될 서버의 경로. 되도록이면 getRealPath를 이용하자.
//String savePath = "D:/Projects/workspace/projectName/WebContent/folderName";
//String savePath = request.getServletContext().getRealPath("folderName");
String savePath = "http://www.priends.co.kr/priends/WebContent/image";
//파일 크기 15MB로 제한
int sizeLimit = 1024*1024*15;

//↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
//(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
//아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();


MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", policy);


//--------------------------아래는 전송 받은 데이터들을 DB테이블에 저장시키기 위한 작업들이다.--------------------------
//테이블 설계, 쿼리문, DTO, DAO, Service.. 등은 만들어져 있다고 가정한다.



//MultipartRequest로 전송받은 데이터를 불러온다.
//enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.


//전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
String fileName = multi.getFilesystemName("member_image");
String pet_file = multi.getFilesystemName("pet_image");
//업로드한 파일의 전체 경로를 DB에 저장하기 위함
String m_fileFullPath = savePath + "/" + fileName;
String m_filePetPath = savePath + "/" +pet_file; */

	PetMomDAO dao = new PetMomDAO();
	PetMomDTO dto = new PetMomDTO();
	PetDTO p_dto = new PetDTO();
	
	String member_image = request.getParameter("member_image");
	String pet_image = request.getParameter("pet_image");
	dto.setPetMom_ID(request.getParameter("id"));
	dto.setPetMom_Pwd(request.getParameter("pass"));
	dto.setName(request.getParameter("name"));
	dto.setAge(Integer.parseInt(request.getParameter("age")));
	dto.setJob(request.getParameter("job"));
	dto.setSex(request.getParameter("sex"));
	dto.setAddress(request.getParameter("address"));
	
	p_dto.setName(request.getParameter("pet_name"));
	p_dto.setAge(Integer.parseInt(request.getParameter("pet_age")));
	p_dto.setCharacter(request.getParameter("pet_character"));
	p_dto.setSpecies(request.getParameter("pet_species"));
	p_dto.setHealth(request.getParameter("pet_health"));
	

	int n = dao.insertOne(dto,p_dto);
	
	
	if(n > 0) {
%>
	<script>
		alert("<%=member_image%> + <%=pet_image%>");
		alert("회원 가입 완료~");
		location.href="/priends/WebContent/login.html";
	</script>
<%} else { %>
	<script>
		alert("<%=member_image%> + <%=pet_image%>");
		alert("가입 실패...");
		location.href="/priends/WebContent/regForm.html";
	</script>
<%} %>	