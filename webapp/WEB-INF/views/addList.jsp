<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action ="/guestbook3/add" method ="post">
		<table border = "1">
			<tr>
				<td>이름</td>
				<td><input type="text" name ="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name ="password" value=""></td>
			</tr>
			<tr>
				<td colspan ="4"><textarea style = "width:380px; height:200px;" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan ="4">
				<input type ="hidden" name="reg_date" value="">
				<button type= "submit">확인</button></td>
			</tr>
		</table>
	</form>
	
	
	
	<c:forEach items="${gList}" var="vo">
		<table border ="1">
			<tr>
				<td>${vo.no }</td>
				<td>${vo.name }</td>
				<td>${vo.regDate }</td>
				<td><a href ="/guestbook3/deleteForm?no=${vo.no }">삭제</a></td>
			</tr>
			<tr>
				<td colspan ="4">${vo.content }</td>
			</tr>
		</table>
		<br>
	</c:forEach>
	 
</body>
</html>