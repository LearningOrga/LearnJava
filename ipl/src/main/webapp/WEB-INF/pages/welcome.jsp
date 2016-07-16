<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Predict and Win - Welcome Page</title>
    
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    
</head>
<body>
<div>
<h3>Predict and Win - Welcome Page</h3>
<p></p>
    Greeting : ${greeting}
    <br>
    <p>Please <a href="<c:url value="/login" />" class="w3-btn w3-ripple">&#10004;Login</a> to play the game. </p>
    
</div>     
</body>
</html>