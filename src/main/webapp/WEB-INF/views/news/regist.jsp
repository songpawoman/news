<%@ page contentType="text/html;charset=UTF-8"%>
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
function regist(){
	//동기방식으로 전송 
	$("form").attr({
		action:"/news/regist", 
		method:"post"
	});
	$("form").submit();
}

$(function(){
	$('#content').summernote();	
	
	//등록버튼 이벤트 
	$("#bt_regist").click(function(){
		regist();	
	});
	
	//목록 이벤트
	$("#bt_list").click(function(){
		location.href="/news/list";
	});
	
	
});
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<input type="text" name="title" placeholder="제목입력.."> 
			<input type="text" name="writer" placeholder="작성자 입력.."> 
			
			<textarea id="content" name="content" placeholder="Write something.." style="height: 200px"></textarea>

			<input type="button" value="등록" id="bt_regist">
			<input type="button" value="목록" id="bt_list">
		</form>
	</div>

</body>
</html>
