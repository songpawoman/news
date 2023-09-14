<%@page import="org.sp.news.domain.Gallery"%>
<%@page import="org.sp.news.domain.GalleryImg"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Gallery gallery =(Gallery)request.getAttribute("gallery");
%>
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

input[type=button] , button{
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

class Thumb{
	constructor(container, file, e){
		this.container=container; //어디에 넣을지 가장 바깥쪽 컨테이너  우리의 경우 preview라는 아이디의 p태그 
		this.file=file;
		this.box=document.createElement("div"); //가장 바깥쪽 div 
		this.div=document.createElement("div");
		this.a=document.createElement("a");
		this.img=document.createElement("img");
		
		//스타일적용
		this.box.classList.add("thumbStyle");
		this.a.href="#";
		this.a.innerText="X";
		this.img.src=e.target.result;
		this.img.style.width="100%";
		
		//조립 
		this.div.appendChild(this.a);
		this.box.appendChild(this.div);
		this.box.appendChild(this.img);
		
		this.container.appendChild(this.box);
		
		
		//x자를 누르면, 배열에서 해당 파일 지우기 
		this.a.addEventListener("click", function(){
			//화면에서 삭제 
			let obj = $($(this).parent()).parent();
			$(obj).remove();			
			
			//배열에서 삭제
			for(let i=0;i<imgList.length;i++){
				if(file.name==imgList[i].name){ //동일한 이미지명이 있다면..제거대상
					imgList.splice(i, 1);
					break;
				}
			}
		});
	}
	
}

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
		type:"POST", 
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

//imgList에 존재하는지 여부를 즉 중복여부 체크 
function isExist(file){
	let flag=false;
	let count=0;
	
	for(let i=0;i<imgList.length;i++){
		if(imgList[i].name==file.name){
			count++;
		}
	}
	
	if(count>0){ //발견된 회수가 1이상이면
		flag=true; //발견됨
	}
	return flag;	
}

function preview(files){
	for(let i=0;i<files.length;i++){
		let file = files[i]; //파일 배열에서 파일 객체를 하나 꺼내기
		
		//file이 이미 imgList에 존재하는지 체크,... 
		//존재하지 않는 다면 아래의 코드를 수행 
		if(isExist(file)==false){
			imgList.push(file);
			
			//js도 스트림을 지원한다..따라서 file 객체에 들어있는 바이트 정보를 정보를 이용하여 스트림으로 읽어
			//이미지를 생성해 내자 
			let reader = new FileReader();	
			
			//파일이 모두 읽혀졌다면...
			reader.onload=(e)=>{
				console.log("readed !!!",  e);
	
				/*
				let tag="<div class=\"thumbStyle\">";
				tag+="<div>";
				tag+="<a href=\"#\" onClick=\"del(this, file, e)\">X</a>";
				tag+="</div>";
				tag+="<img src=\""+e.target.result+"\" width='100%'>";
				tag+="</div>";
				$("#preview").append(tag);
				*/
				
				//문자열로 시각화 시키지 말고, 실제 DOM 객체를 생성하는 방식을 이용하자 
				//UI 컴포넌트화시켜서 처리...
				let thumb = new Thumb(document.getElementById("preview"), file, e);	
				
			}
			
			reader.readAsDataURL(file);//읽어들이고 싶은 파일을 매개변수로 전달 
		}
	}
}

function createFile(filename){
		console.log("이미지명은 ", filename);
		
		let request = new XMLHttpRequest(); // js 의 비동기통신 객체
		request.open("GET", "/static/data/"+filename);
		request.responseType="blob";
		
		request.onload = function(){//서버에서 파일을 다운받으면..
			let reader = new FileReader();
			reader.readAsDataURL(request.response);
			
			reader.onload=function(e){ //파일을 읽어들이면..
				
				//자바스크립에서 File 객체 생성 
				let file = new File([request.response], filename);
				imgList.push(file);
				
				let thumb = new Thumb(document.getElementById("preview"), file, e);	
			}
		}
		request.send(); //이 시점에 서버에 요청 시작
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
		/*
		for(let i=0; i<this.files.length;i++){
			let file = this.files[i];
			imgList.push(file);
		}
		*/
	});
	
	$("#bt_trigger").click(function(){
		$("input[name='file']").click();
	});
	
	//js에서 이미지 파일 동적으로 생성하여 imgList 배열에 채워넣기  + 미리보기 시각화 
	<%for(int i=0;i<gallery.getGalleryImgList().size();i++){%>
		<%GalleryImg galleryImg=gallery.getGalleryImgList().get(i);%>
		createFile('<%=galleryImg.getFilename()%>');
	<%}%>	
});
</script>
</head>
<body>

	<%@ include file="../inc/top_navi.jsp" %>

	<div class="container">
		<form>
			<input type="text" name="title" value="<%=gallery.getTitle()%>"> 
			<input type="text" name="writer" value="<%=gallery.getWriter()%>"> 
			
			<textarea id="content" name="content" style="height: 200px"><%=gallery.getContent() %></textarea>
			
			<p id="preview">
				
			</p>
			
			<p>
				<input type="file" name="file" multiple style="display:none">
				
				<button type="button" id="bt_trigger">파일을 선택해주세요</button>
			</p>
			
			<input type="button" value="등록" id="bt_regist">
			
			<input type="button" value="목록" id="bt_list">
		</form>
	</div>

</body>
</html>
