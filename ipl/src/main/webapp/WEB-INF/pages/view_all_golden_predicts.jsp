<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
font-size: 11px !important;
}

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th{
color:#9c27b0;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.w3-ripple{
color: deepskyblue !important	;
}

th{
background-color: rgba(30, 167, 233, 0.22) !important;
}
td{
background-color: rgba(205, 236, 250, 0.16) !important;
}

  .ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable{
width:auto;
height:auto;
}  

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - View Golden Predicts</title>

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
	
		<h2>Predict and Win - View Golden Predicts</h2>		
		<br>

<a href="<c:url value="/addGoldenPredicts" />" class="w3-btn w3-ripple">&#10004;Click to add / update your Golden Predict </a>
<br></br>

		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">No one has predicted yet.</h4>

<table class="w3-table w3-bordered w3-striped">
<th>Sr. No</th>
<th>Predictor Name</th>
<th>Predicted Quarter Finalist 1</th>
<th>Predicted Quarter Finalist 2</th>
<th>Predicted Quarter Finalist 3</th>
<th>Predicted Quarter Finalist 4</th>
<th>Predicted Semi Finalist 1</th>
<th>Predicted Semi Finalist 2</th>
<th>Prediced Winner</th>

		<tr ng-repeat="x in dataAfterView">
			<td><b>{{ $index + 1 }}</b></td>	
			<td><b>{{ x.userId.loginName }}</b></td>		
    		<td>{{x.qfteam1.teamName}}</td>
    		<td>{{x.qfteam2.teamName}}</td>
    		<td>{{x.qfteam3.teamName}}</td>
    		<td>{{x.qfteam4.teamName}}</td>
    		<td>{{x.sfteam1.teamName}}</td>
    		<td>{{x.sfteam2.teamName}}</td>
    		<td>{{x.finalWinningTeam.teamName}}</td>
    	</tr>
</table>
<br>

	</div>
		

	
<script>

var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$http', function($scope,$http) {
	$scope.displayAddMessage = false;
	
	$scope.dataAfterView;
	
	//Live service
 		var viewPredictUrl = "predict/all";
		$http.get(viewPredictUrl).success(function(response){
			$scope.dataAfterView = response;
			
		});

	  	//var viewPredictUrl = "predict/viewGoldenPredicts";

		$http({
			method: "GET",
			url : viewPredictUrl,				
		}).success(function(response){
			if(response!=''){
				$scope.displayAddMessage = false;
				$scope.dataAfterView = response;
			}else{
				$scope.displayAddMessage = true;
				console.log(response);					
			}
		}).error(function(err){
			console.log(err);
		});
	
	
}]);


</script>

</body>
</html>