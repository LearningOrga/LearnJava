<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th{
color:#9c27b0;
}

.w3-ripple{
color: deepskyblue !important	;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - Winning Rules</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<!-- load momentJS (required for angular-moment) -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>

<!-- load angular-moment -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js"></script>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">	

</head>
<body>
</body>

<body>

	<div ng-app="myApp" ng-controller="myCtrl" class="w3-container">
	
		<h2>Predict and Win - Winning Rules</h2>

<table class="w3-table w3-bordered w3-striped">
<th>Sr No</th>
<th>Winning Rules</th> 


  <tr>
    <td>1</td>
    <td>Top 3 users having the maximum points earned.</td>
  </tr>
  <tr>
    <td>2</td>
    <td>Top 1 user from girls having the maximum points earned.</td>
  </tr>
  <tr>
    <td>3</td>
    <td>Top 1 user holding the maximum number of Wins for Rule 1 (Winning Team).</td>
  </tr>
  <tr>
    <td>4</td>
    <td>Top 1 user holding the maximum number of Wins for Rule 2 (Toss Winning Team).</td>
  </tr>
  <tr>
    <td>5</td>
    <td>Maximum 3 Winners from Golden Predicts.</td>
  </tr>
  <tr>
    <td>6</td>
    <td>Top 1 user who has invested maximum points.</td>
  </tr>
  <tr>
    <td>7</td>
    <td>Top 1 user having the highest Win/Loss Percent.</td>
  </tr>
 
</table>
<br>
<div><b>Note: No user will be rewarded twice and Admins may add/update any of the above rules.</b></div>

</body>
</html>