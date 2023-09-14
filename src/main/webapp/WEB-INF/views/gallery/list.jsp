<%@page import="org.sp.news.domain.GalleryImg"%>
<%@page import="org.sp.news.domain.Gallery"%>
<%@page import="org.sp.news.domain.News"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
	List<Gallery> galleryList =(List)request.getAttribute("galleryList");
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
			for (int i = 0; i < galleryList.size(); i++) {
		%>
		<%
			Gallery gallery=galleryList.get(i);
			GalleryImg galleryImg=gallery.getGalleryImgList().get(0);
		%>
		<tr>
			<td>Jill</td>
			<td><img src="/static/data/<%=galleryImg.getFilename()%>" width="35px"></td>
			<td><a href="/gallery/content?gallery_idx=<%=gallery.getGallery_idx()%>"><%=gallery.getTitle()%></a></td>
			<td><%=gallery.getWriter()%></td>
			<td><%=gallery.getRegdate()%></td>
			<td><%=gallery.getHit()%></td>
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
