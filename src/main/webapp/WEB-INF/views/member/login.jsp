<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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
div{
	margin:5px;
}
</style>

<%@ include file="../inc/header_link.jsp"%>

<script type="text/javascript">
	function regist() {
		//동기방식으로 전송 
		$("form").attr({
			action : "/news/regist",
			method : "post"
		});
		$("form").submit();
	}

	$(function() {
		$('#content').summernote();

		//등록버튼 이벤트 
		$("#bt_regist").click(function() {
			regist();
		});

		//목록 이벤트
		$("#bt_list").click(function() {
			location.href = "/news/list";
		});

	});
</script>
</head>
<body>

	<%@ include file="../inc/top_navi.jsp"%>

	<div class="container">
		<form>
			<div>
				<input type="text" name="firstname" placeholder="Your ID..">
			</div>
			<div> 
				<input type="text" name="firstname" placeholder="Your Password..">
			</div> 
			<div> 
				<input type="button" value="가입하기">
			</div> 
		</form>
	</div>

</body>
</html>
