<%@page import="org.sp.news.domain.Hobby"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Hobby> hobbyList=(List)request.getAttribute("hobbyList");
%>
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

</style>

<%@ include file="../inc/header_link.jsp"%>

<script type="text/javascript">
	function regist() {
		//회원가입을 비동기로 요청하자
		$("form").attr({
			action:"/member/regist",
			method:"post"
		});
		$("form").submit();
	}

	$(function() {

		//등록버튼 이벤트 
		$("#bt_regist").click(function() {
			regist();
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
				<input type="text" name="name" placeholder="Your Name..">
			</div> 
			<div>
				<%for(Hobby hobby : hobbyList){ %> 
					<%=hobby.getHobby_name() %><input type="checkbox" 	name="hobby_idx" value="<%=hobby.getHobby_idx()%>">
				<%} %>

			</div> 
			<div> 
				수신동의<input type="radio" name="agree" value="1">
				수신거부<input type="radio" name="agree" value="0">
			</div> 
			<div> 
				<input type="text" name="email_id" placeholder="Your Email ID..">
				<select name="email_server">
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="daum.net">daum.net</option>
				</select>
			</div> 
			<div> 
				<input type="button" value="가입하기" id="bt_regist">
			</div> 
		</form>
	</div>

</body>
</html>
