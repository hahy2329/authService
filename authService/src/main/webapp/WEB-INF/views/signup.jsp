<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${contextPath }/signUp" method="post">
		email : <input type="email" name="email">
		password : <input type="password" name="password">
		<button>회원가입</button>
	</form>
</body>
</html>