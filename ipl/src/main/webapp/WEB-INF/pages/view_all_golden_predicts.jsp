<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

table, th, td {
border: 2px solid bisque !important;
font-size: 11px !important;
}

th{
background-color: #C5C500 !important;
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
	
		<h3>Predict and Win - View Golden Predicts</h3>		
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
    		<td><b>{{x.qfteam1.teamName}}</b></td>
    		<td><b>{{x.qfteam2.teamName}}</b></td>
    		<td><b>{{x.qfteam3.teamName}}</b></td>
    		<td><b>{{x.qfteam4.teamName}}</b></td>
    		<td><b>{{x.sfteam1.teamName}}</b></td>
    		<td><b>{{x.sfteam2.teamName}}</b></td>
    		<td><b>{{x.finalWinningTeam.teamName}}</b></td>
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
/* 		var viewPredictUrl = "predict/all";
		$http.get(viewPredictUrl).success(function(response){
			$scope.dataAfterView = response;
			
		});	 */

	  	var viewPredictUrl = "predict/all";

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
	
	//comment to call match result static  data service	
	//$scope.dataAfterView  = [{"id":89,"userId":{"id":1,"loginName":"sudeepg","loginPass":"sudeepg","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0,"goldenPredict":"Rising Pune Supergiants (RPS)\r"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Mumbai Indians","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":20.0,"result":"L","indicativePoints":27.5,"totalPointsEarned":53.0},{"id":90,"userId":{"id":2,"loginName":"jitendrah","loginPass":"jitendrah","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0,"goldenPredict":"GUJ"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Rising Pune Supergiants","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":5.0,"result":"W","indicativePoints":18.333333333333332,"totalPointsEarned":13.0},{"id":91,"userId":{"id":3,"loginName":"ravig","loginPass":"ravig","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0,"goldenPredict":"MI"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Mumbai Indians","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":20.0,"result":"L","indicativePoints":27.5,"totalPointsEarned":53.0},{"id":92,"userId":{"id":8,"loginName":"temp5","loginPass":"temp5","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0,"goldenPredict":"GUJ"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Rising Pune Supergiants","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":10.0,"result":"W","indicativePoints":36.666666666666664,"totalPointsEarned":27.0}];
}]);


</script>

</body>
</html>