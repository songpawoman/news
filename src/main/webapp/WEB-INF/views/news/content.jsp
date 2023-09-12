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
<%@ include file="../inc/header_link.jsp" %>

<script>
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


function getCommentsList(){
	//비동기 방식으로 서버로부터 코멘트 데이터 가져오기 
	$.ajax({
		url:"/comments/list?news_idx="+$("input[name='news_idx']").val(),
		type:"GET", 
		success:function(result, status, xhr){  //서버가 200(성공)으로 응답할 경우 success 동작함
			console.log("서버로부터 받은 결과는 ", result);
			
			//기존에 reply_area에 추가된 자식들을 싹 지우고~~for 문 실행 
			$("#reply_area").empty();//모든 자식 요소 삭제 
			
			for(let i=0;i<result.length;i++){
				let row = new Row(result[i].msg, result[i].cwriter, result[i].cregdate);
			}
		}		
	});
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

	//댓글의 목록 가져오기(비동기 방식으로)
	getCommentsList();
	
	//댓글 등록 
	$("#bt_regist").click(function(){
		//비동방식으로 서버에 글 등록 요청을 하겠슴...
		$.ajax({
			url:"/comments/regist",
			type:"post",
			data:{
				"news.news_idx":$("input[name='news_idx']").val(),
				"msg":$("input[name='msg']").val(), 
				"cwriter":$("input[name='cwriter']").val()
			}, 
			success:function(result, status, xhr){
				console.log("등록 후 서버의 응답 ", result);
				//서버가 보낸 메시지가 ok 라면... 곧 바로 비동기 방식으로 서버로부터 list를 가져오기 
				getCommentsList();
			}
		});
	});
	
	
});
</script>
</head>
<body>

	<%@ include file="../inc/top_navi.jsp" %>

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
