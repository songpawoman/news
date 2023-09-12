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
	function loginCheck() {
		if( $("input[name='id']").val().length <1 ){
			alert("아이디를 입력하세요");
			$("input[name='id']").focus();
		}else if($("input[name='pass']").val().length <1){
			alert("비밀번호를 입력하세요");
			$("input[name='pass']").focus();
		}else {
			//서버에 파라미터 전송 
			let json={};
			json["id"]=$("input[name='id']").val();
			json["pass"]=$("input[name='pass']").val();
			
			$.ajax({
				url:"/rest/member/login",
				type:"post",
				data: JSON.stringify(json) ,
				contentType:"application/json;charset=utf-8",
				success:function(result, status, xhr){ //200 성공 응답할 경우..
					console.log("서버의 응답 메시지 ", result);
					alert("로그인 성공");
					location.href="/";
				},
				error:function(xhr, status, e){ //500 실패 
					console.log("서버의 에러 메시지 ", e);
					alert("로그인 정보가 올바르지 않습니다");
				}
				
			});			
		}
		
	}

	
	
	
	$(function() {
		
		//로그인 버튼 이벤트 
		$("#bt_login").click(function() {
			loginCheck();
		});
		
	});
</script>
</head>
<body>

	<%@ include file="../inc/top_navi.jsp"%>

	<div class="container">
		<form>
			<div>
				<input type="text" name="id" placeholder="Your ID..">
			</div>
			<div> 
				<input type="text" name="pass" placeholder="Your Password..">
			</div> 
			<div> 
				<input type="button" value="Login" id="bt_login">
			</div> 
		</form>
	</div>

</body>
</html>
