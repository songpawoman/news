<%@page import="org.sp.news.domain.News"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<News> newsList=(List)request.getAttribute("newsList");
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("button").click(function(){
		location.href="/news/registform";
	});		
});

</script>
</head>
<body>

	<h2>Zebra Striped Table</h2>
	<p>For zebra-striped tables, use the nth-child() selector and add a
		background-color to all even (or odd) table rows:</p>

	<table>
		<tr>
			<th>No</th>
			<th>뉴스제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%for(int i=0;i<newsList.size();i++){ %>
		<%News news=newsList.get(i); %>
		<tr>
			<td>Jill</td>
			<td><a href="/news/content?news_idx=<%=news.getNews_idx()%>"><%=news.getTitle() %></a></td>
			<td><%=news.getWriter() %></td>
			<td><%=news.getRegdate() %></td>
			<td><%=news.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5"><button>글등록</button></td>
		</tr>
		
		<tr>
			<td colspan="5">
				[1][2]
			</td>
		</tr>
		
	</table>

</body>
</html>
