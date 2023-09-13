<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="org.sp.news.domain.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

.thumbStyle{
	width:50px;
	height:40px;
	display:inline-block;
}
a{text-decoration:none;}
#preview{
	height:100px;
	background:skyblue;
}
</style>

<%@ include file="../inc/header_link.jsp" %>

<script type="text/javascript">
function regist(){
	//동기방식으로 전송 
	$("form").attr({
		action:"/gallery/regist", 
		method:"post", 
		enctype:"multipart/form-data"
	});
	
	$("form").submit();
}

function del(){
	alert();	
}

function preview(files){
	for(let i=0;i<files.length;i++){
		let file = files[i]; //파일 배열에서 파일 객체를 하나 꺼내기
		
		//js도 스트림을 지원한다..따라서 file 객체에 들어있는 바이트 정보를 정보를 이용하여 스트림으로 읽어
		//이미지를 생성해 내자 
		let reader = new FileReader();	
		
		reader.onload=(e)=>{//파일이 모두 읽혀졌다면...
			console.log("readed !!!",  e);
			//e.target.result;
			
			let tag="<div class=\"thumbStyle\">";
			tag+="<div>";
			tag+="<a href=\"#\" onClick=\"del()\">X</a>";
			tag+="</div>";
			tag+="<img src=\""+e.target.result+"\" width='100%'>";
			tag+="</div>";
			
			$("#preview").append(tag);
		}
		reader.readAsDataURL(file);//읽어들이고 싶은 파일을 매개변수로 전달 
	}
}

$(function(){
	$('#content').summernote();	
	
	//등록버튼 이벤트 
	$("#bt_regist").click(function(){
		regist();	
	});
	
	//목록 이벤트
	$("#bt_list").click(function(){
		location.href="/gallery/list";
	});
	
	//파일 컴포넌트에 change 이벤트 연결
	$("input[name='file']").change(function(){
		
		console.log("유저가 선택한 파일 정보 ", this.files);
		//파일 정보로 부터 이미지를 얻어서, 화면에 미리보기 구현(시각화)
		preview(this.files);
	});
	
});
</script>
</head>
<body>

	<%@ include file="../inc/top_navi.jsp" %>

	<div class="container">
		<form>
			<input type="text" name="title" placeholder="제목입력.."> 
			<input type="text" name="writer" placeholder="작성자 입력.."> 
			
			<textarea id="content" name="content" placeholder="Write something.." style="height: 200px"></textarea>
			
			<p id="preview">
				
			</p>
			
			<p>
				<input type="file" name="file" multiple>
			</p>
			
			<input type="button" value="등록" id="bt_regist">
			<input type="button" value="목록" id="bt_list">
		</form>
	</div>

</body>
</html>
