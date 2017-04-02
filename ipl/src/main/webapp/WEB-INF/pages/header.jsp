<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<style>
.centerAlign{
text-align: center !important;
font-family:Comic Sans MS;
color: gold;
font-weight:bold;
}
.rightAlign{
text-align: right !important;
}
.homeAlign{
float: left !important;
margin-left:30px !important;
}
.logoutAlign{
float: right !important;
margin-right:30px !important;
}
.divider{
margin-top:5px !important;
border: 2px solid #52B93F !important;
background-color: #40A700 !important;
}
.headerContainer{
background-color: rgba(0, 128, 167, 0.7) !important;
height:138px !important;
}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<div class="w3-container headerContainer"> 

<p class = "centerAlign">Hello, <b><%=session.getAttribute("loginName") %></b>. Welcome to Predict and Win 2017. <br></br> Total Points Remaining : <b><%=session.getAttribute("totalRemainingPoints") %></p>
<%-- <p class = "rightAlign">Total Points Remaining : <b><%=session.getAttribute("totalRemainingPoints") %></b></p> --%>

<a href="<c:url value="/ipl_home" />" class="w3-btn w3-ripple homeAlign">&#10004;Home</a>
<a href="<c:url value="/logout" />" class="w3-btn w3-ripple logoutAlign">&#10004;Logout</a>

</div>     



     
</body>
</html>