<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:orange">
	<pre>
		이용에 불편을 드려 죄송합니다.
		<%RuntimeException e =(RuntimeException)request.getAttribute("e"); %>
		<%=e.getMessage() %>
		
		<a href="/">메인으로 가기</a>		
	</pre>
</body>
</html>