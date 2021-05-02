<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

table, th, td {
border: 2px solid bisque !important;
font-size: 13px !important;
}

th{
background-color: #C5C500 !important;
}


.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - Reports</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<!-- load momentJS (required for angular-moment) -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>

<!-- load angular-moment -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js"></script>

</head>
<body>

	<div ng-app="myApp" ng-controller="myCtrl" class="w3-container">
	
		<h3>Predict and Win - Reports</h3>

		<br>


	<!--  Ravi we need to popluate the below select box with all the user names. The option value will be the user ID. -->
<!-- 	<div id="inputForm">
		<select id="userSel">
			<option value="1">Sudeep </option>
			<option value="2">Ravi </option>
			<option value="3">XXXXXXX</option>
		</select> -->
		
				<select ng-model="userArray" ng-options="o.loginName for o in userName" ng-change="selectedUser(userArray)">
					<option value="">-- Select User--</option>
					</select>
				
				
				<button  ng-click="userReports()" class="w3-btn w3-ripple"> &#9998; Click to view the reports </button>	

<br>
	
	<div ng-show="ajaxSuccessResponse" id="graphs">
		<img alt='User Wise - Win / Loss Report' src='userWinLossReportBC.png?userId=1' />
	<br><br>
		<img alt='User Wise - Win / Loss Progress' src='userWinLossProgressLC.png?userId=1' />

	</div>	

	</div>
<script>

var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$http', function($scope,$http) {
	$scope.displayAddMessage = false;

	$scope.userName;
	$scope.selectedUserName;
	$scope.ajaxSuccessResponse = false;
	$scope.displayAddMessage = false;
	$scope.displayErrMessage = false;
	
    $scope.selectedUser = function (mySelectUser) {
		$scope.ajaxSuccessResponse = false;
		$scope.displayAddMessage = false;
		$scope.displayErrMessage = false;		    	            	
    	if(mySelectUser){
    		$scope.selectedUserName = mySelectUser.id;
    		$scope.user = false;
    	}
    	else{
    		$scope.user = true;
    		$scope.selectedUserName = "";
    	}            	
    }
	
    
	//uncomment to run with json response
	var url = "user/all";
	$http.get(url).success(function(response){
		$scope.userName = response;
		
	});
	
$scope.userReports = function () {
  	var url = "userWinLossReportBC.png";

	$http({
		method: "GET",
		url : url,
		params: {
			userId : $scope.selectedUserName,
		}					
	}).success(function(response){
		if(response!=''){
			
			$scope.ajaxSuccessResponse = true;
			$scope.displayAddMessage = true;
			$scope.displayErrMessage = false;
			$scope.dataAfterView = response;
		}else{
			$scope.ajaxSuccessResponse = false;
			$scope.displayAddMessage = false;
			$scope.displayErrMessage = true;
		console.log(response);					
		}
	}).error(function(err){
		console.log(err);
		$scope.ajaxSuccessResponse = false;
		$scope.displayAddMessage = false;
		$scope.displayErrMessage = true;
	});  
	 
	 }
	
	
}]);


</script>

</body>
</html>