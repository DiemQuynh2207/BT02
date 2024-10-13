<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<style>
	.search-input{
		height:30px;
		
	}
	.form-ctn{
		display: flex;
		justify-content: center;
		align-items: center;
		text-align: center;
		flex-direction: row;
	}
</style>
<body>


<a href="<c:url value='/admin/video/add'></c:url>"> ADD VIDEO</a>
<table border="1">
	<tr>
		<th>STT</th>
		<th>Poster</th>
		<th>Description</th>
		<th>Title</th>
		<th>Active</th>
		<th>Views</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT" >
		<tr>
			<td>${STT.index+1 }</td>
			<c:if test="${video.poster.substring(0,5) == 'https'}">
				<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${video.poster.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${video.description}</td>
			<td>${video.title}</td>
			<td>${video.active}</td>
			<td>${video.views}</td>
			<td>
				<a href="<c:url value='/admin/video/edit?id=${video.videoId}'/>">Sửa</a>
			 	<a href="<c:url value='/admin/video/delete?id=${video.videoId}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
