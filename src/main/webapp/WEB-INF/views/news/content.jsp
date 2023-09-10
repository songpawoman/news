<%@page import="org.sp.news.domain.News"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	News news=(News)request.getAttribute("news");
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
</style>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script type="text/javascript">

//글 한행을 표현하는 클래스 정의
/*
<div>
	<input type="text" name="msg" value="댓글 내용 작성..." style="width:70%" readonly> 
	<input type="text" name="cwriter" value="작성자"  style="width:13%" readonly> 
	<input type="text" name="cregdate" value="2023-09-25"  style="width:15%" readonly> 
</div>
*/

class Row{
	constructor(msg, cwriter, cregdate){
		//js 는 멤버변수를 constructor() 인 생성자 안에서만 정의할 수 있다..
		this.div = document.createElement("div");
		this.input_msg = document.createElement("input");
		this.input_cwriter = document.createElement("input");
		this.input_cregdate = document.createElement("input");
		
		//스타일 
		this.input_msg.name="msg";
		this.input_msg.value=msg;
		this.input_msg.style="width:70%";
		this.input_msg.readOnly=true;
		
		this.input_cwriter.name="cwriter";
		this.input_cwriter.value=cwriter;
		this.input_cwriter.style="width:13%";
		this.input_cwriter.readOnly=true;
		
		this.input_cregdate.name="cregdate";
		this.input_cregdate.value=cregdate;
		this.input_cregdate.style="width:15%";
		this.input_cregdate.readOnly=true;
		
		//조립 
		this.div.appendChild(this.input_msg);
		this.div.appendChild(this.input_cwriter);
		this.div.appendChild(this.input_cregdate);
			
		//생성된 this.div 를 reply_area 영역에 동적으로 자식요소로 추가 
		document.getElementById("reply_area").appendChild(this.div);
	}	
}

function edit(){
	//동기방식으로 전송 
	$("form").attr({
		action:"/news/edit", 
		method:"post"
	});
	$("form").submit();
}

function del(){
	//동기방식으로 전송 
	$("form").attr({
		action:"/news/del", 
		method:"post"
	});
	$("form").submit();
}


$(function(){
	$('#content').summernote();	
	
	 
	$("#bt_edit").click(function(){
		if(confirm("수정하시겠어요?")){
			edit();	
		}
	});
	
	$("#bt_del").click(function(){
		if(confirm("삭제하시겠어요?")){
			del();	
		}

	});
	
	//목록 이벤트
	$("#bt_list").click(function(){
		location.href="/news/list";
	});
	
	
});
</script>
</head>
<body>

	<h3>상세보기</h3>

	<div class="container">
		<form>
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			<input type="text" name="title" value="<%=news.getTitle()%>"> 
			<input type="text" name="writer" value="<%=news.getWriter()%>"> 
			
			<textarea id="content" name="content" style="height: 200px"><%=news.getContent() %></textarea>

			<input type="button" value="수정" id="bt_edit">
			<input type="button" value="삭제" id="bt_del">
			<input type="button" value="목록" id="bt_list">
		</form>
		<div>
			<input type="text" name="msg" placeholder="댓글 내용 작성..." style="width:70%"> 
			<input type="text" name="cwriter" placeholder="작성자"  style="width:20%"> 
			<input type="button" value="등록" id="bt_regist"  style="width:8%">
		</div>
		
		<p id="reply_area">
			<!-- 실시간으로 댓글의 행이 추가될 예정.. -->
		</p>
		
	</div>
	
</body>
</html>
