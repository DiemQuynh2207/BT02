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
<div class="search-container">
    <form action="<c:url value='/admin/category/search'></c:url>" class="form-ctn" method="post">
      <input type="text" placeholder="Search.." name="catesearch" class="search-input">
      <!-- <button type="submit"><span class="material-symbols-outlined">search</span></button> -->
      <input type = "submit" value = "Search">
    </form>
</div>

<a href="<c:url value='/admin/category/add'></c:url>"> ADD CATEGORY</a>
<table border="1">
	<tr>
		<th>STT</th>
		<th>Image</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT" >
		<tr>
			<td>${STT.index+1 }</td>
			<c:if test="${cate.images.substring(0,5) == 'https'}">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) != 'https'}">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryname}</td>
			<td>${cate.status}</td>
			<td>
				<a href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a>
			 	<a href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
