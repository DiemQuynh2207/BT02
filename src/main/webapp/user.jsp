<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
/* From Uiverse.io by barisdogansutcu */ 
button {
 position: relative;
 overflow: hidden;
 outline: none;
 cursor: pointer;
 border-radius: 50px;
 background-color: hsl(255deg 50% 40%);
 border: solid 4px hsl(50deg 100% 50%);
 font-family: inherit;
}

.default-btn,.hover-btn {
 background-color: hsl(255deg 50% 40%);
 display: -webkit-box;
 display: -moz-box;
 display: -ms-flexbox;
 display: -webkit-flex;
 display: flex;
 align-items: center;
 gap: 15px;
 padding: 15px 20px;
 border-radius: 50px;
 font-size: 17px;
 font-weight: 500;
 text-transform: uppercase;
 transition: all .3s ease;
}

.hover-btn {
 position: absolute;
 inset: 0;
 background-color: hsl(255deg 50% 49%);
 transform: translate(0%,100%);
}

.default-btn span {
 color: hsl(0, 0%, 100%);
}


button:hover .default-btn {
 transform: translate(0%,-100%);
}

button:hover .hover-btn {
 transform: translate(0%,0%);
}
.hover-btn{
display:flex;
justify-content:center;
align-items:center;
text-align:center;
}
.content{
display:flex;
justify-content:center;
align-items:center;
text-align:center;
flex-direction: column;
}
.logout-url{
text-decoration: none;

}
.logout_span{
color: yellow:
}
</style>
<body>
<div class ="content">
<h1 class="content">Hello user ${name}</h1>
<button>
  <div class="default-btn">
    <svg class="css-i6dzq1" stroke-linejoin="round" stroke-linecap="round" fill="none" stroke-width="2" stroke="#FFF" height="20" width="20" viewBox="0 0 24 24"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle r="3" cy="12" cx="12"></circle></svg>
    <span class="logout-btn">Click me</span>
  </div>
  <div class="hover-btn">
    <span class = "logout_span"><a href="logout" class = "logout-url">Logout</a></span>
  </div>
</button>
</div>
</body>
</html>