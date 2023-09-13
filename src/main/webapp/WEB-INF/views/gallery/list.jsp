<%@page import="org.sp.news.domain.Member"%>
<%@page import="org.sp.news.domain.News"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>

<%@ include file="../inc/header_link.jsp" %>

<script type="text/javascript">
	$(function() {
		$("button").click(function() {
			location.href = "/gallery/registform";
		});
	});
</script>
</head>
<body>
	<%@ include file="../inc/top_navi.jsp" %>
	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%
			for (int i = 0; i < 10; i++) {
		%>
		<%

		%>
		<tr>
			<td>Jill</td>
			<td><img src="#" width="35px"></td>
			<td><a href="/news/content?news_idx=<%%>"><%%></a></td>
			<td><%%></td>
			<td><%%></td>
			<td><%%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="6"><button>글등록</button></td>
		</tr>

		<tr>
			<td colspan="6">[1][2]</td>
		</tr>

	</table>

</body>
</html>
