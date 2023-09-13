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
let imgList=[]; //자바스크립트의 파일객체 배열을 받아 놓을 전역변수 

function regist(){
	//form태그만이 폼을 만들수 있는 것은 아님..
	let formData=new FormData();
	formData.append("title", $("input[name='title']").val());
	formData.append("writer", $("input[name='writer']").val());
	formData.append("content", $("textarea[name='content']").val());

	//파일의 수는 여러개이므로, 파라미터를 배열로 생성
	for(let i=0;i<imgList.length;i++){
		let file = imgList[i];
		formData.append("file", file);
	}
	
	$.ajax({
		url:"/rest/gallery/regist",
		type:"post", 
		data:formData, 
		processData:false, /* title=제목&writer=지노&  (query string)쿼리스트링화 되는 것 방지 */
		contentType:false, /* application/x-www-form~~ 방지 */
		success: function(result, status, xhr){
			console.log("처리성공",result);
		},
		error:function(xhr, status, err){
			console.log("에러발생",err);
		}
	});
	
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
		
		//파일이 모두 읽혀졌다면...
		reader.onload=(e)=>{
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
		
		//imgList에 this.files(읽기전용)에 들어있는 js의 File들을 하나씩 꺼내서 넣어주기
		imgList=[];
		
		for(let i=0; i<this.files.length;i++){
			let file = this.files[i];
			imgList.push(file);
		}
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
