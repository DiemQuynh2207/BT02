<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
<form action="<c:url value='/admin/video/insert'></c:url>" method="post" enctype="multipart/form-data">
  <label for="videoid">Video ID:</label>
  <input type="text" id="videoid" name="videoid" value="${video.videoId}"><br>
  <label for="poster">Poster:</label>
  	<c:if test="${video.poster.substring(0,5) == 'https' }">
		<c:url value="${video.poster}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${video.poster.substring(0,5) != 'https' }">
		<c:url value="/image?fname=${video.poster }" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" />
  <input type="file" id="poster" name="poster"><br><br>
  <label for="active">Active:</label><br>
  <label for="active">Đang hoạt động</label>
  <input type="radio" id="ston" name="status" value="1" checked><br><br>
  <label for="active">Khóa</label>
  <input type="radio" id="stoff" name="status" value="0"><br><br>
  <label for="description">Description:</label>
  <input type="text" id="description" name="description" value="${video.description}"><br>
   <label for="title">Title:</label>
  <input type="text" id="title" name="title" value="${video.title}"><br>
  <label for="views">Views:</label>
  <input type="text" id="views" name="views" value="${video.views}"><br>
    <input type="submit" value="Insert">
</form>

</body>
</html>