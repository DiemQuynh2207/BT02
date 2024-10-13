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
<form action="<c:url value='/admin/user/insert'></c:url>" method="post">
  <label for="fullname">Fullname:</label>
  <input type="text" id="fullname" name="fullname" value="${user.fullname}"><br>
  <label for="images">Images:</label>
  	<c:if test="${user.images.substring(0,5) == 'https' }">
		<c:url value="${user.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${user.images.substring(0,5) != 'https' }">
		<c:url value="/image?fname=${user.images }" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" />
  <input type="file" id="images" name="images"><br><br>
  <label for="phone">Phone:</label><br>
    <input type="text" id="phone" name="phone" value="${user.phone}"><br>
  <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" value="${user.email}"><br>
  <label for="active">Active:</label><br>
    <input type="text" id="active" name="active" value="${user.active}"><br>
    <input type="submit" value="Insert">
</form>

</body>
</html>