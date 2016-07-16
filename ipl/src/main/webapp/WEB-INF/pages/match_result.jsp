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

#contributorHeader, #rankHeader{
cursor : pointer !important;
}

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - Match Results</title>

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
	
		<h3>Predict and Win - Match Results</h3>
		<div><b>{{dataAfterResults[0].matchId.matchDetails}} - {{dataAfterResults[0].matchId.matchDate | jsDate | date}} ({{dataAfterResults[0].matchId.matchTime | jsTime}})</b>
		</div>
		<br>
		
		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Match result not yet updated</h4>

<p>Results - Winning Team</p>

<table class="w3-table w3-bordered w3-striped">
<th ng-click="changeSorting('dataAfterResults.userId.loginName')" id="contributorHeader">Contributor
<i class="glyphicon" ng-class="getIcon('dataAfterResults.userId.loginName')">
</i>
</th>
<th>Selected Rule</th>
<th>Selected Team</th>
<th>Points Invested</th>
<th>Points Earned</th>
<th>Results</th>


		<tr ng-repeat="x in dataAfterResults | orderBy:sort.active:sort.descending" ng-show="checkRule1(x.ruleId.id)">
    		<td>{{x.userId.loginName}}</td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td>{{x.pointsInvested}}</td>
    		<td ng-hide="!x.totalPointsEarned">{{x.totalPointsEarned}}</td>
    		<td ng-show="!x.totalPointsEarned">Match Winner not yet updated</td>
    		<td ng-hide="!x.result"  ng-init="getWinLoss(x.result)">{{x.result}}</td>
    		<td ng-show="!x.result">Rule Result not yet updated</td>    		
    	</tr>
</table>

<p>Results - Toss Winning Team</p>

<table class="w3-table w3-bordered w3-striped">
<th ng-click="changeSorting('dataAfterResults.userId.loginName')" id="contributorHeader">Contributor
<i class="glyphicon" ng-class="getIcon('dataAfterResults.userId.loginName')">
</i>
</th>
<th>Selected Rule</th>
<th>Selected Team</th>
<th>Points Invested</th>
<th>Points Earned</th>
<th>Results</th>


		<tr ng-repeat="x in dataAfterResults | orderBy:sort.active:sort.descending" ng-show="checkRule2(x.ruleId.id)">
    		<td>{{x.userId.loginName}}</td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td>{{x.pointsInvested}}</td>
    		<td ng-hide="!x.totalPointsEarned">{{x.totalPointsEarned}}</td>
    		<td ng-show="!x.totalPointsEarned">Match Winner not yet updated</td>
    		<td ng-hide="!x.result"  ng-init="getWinLoss(x.result)">{{x.result}}</td>
    		<td ng-show="!x.result">Rule Result not yet updated</td>    		
    	</tr>
</table>

	</div>
		
	
<script>

var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$http', function($scope, $http) {
	$scope.displayAddMessage = false;
	$scope.winLossResult;
	
	$scope.matchId = "<%= session.getAttribute("matchId")%>";
	$scope.dataAfterResults;
	
	//uncomment to call match result service
  	 var url = "playResult/byMatchId";

	$http({
		method: "GET",
		url : url,
		params: {
			matchId : $scope.matchId,
		}					
	}).success(function(response){
		if(response!=''){
			$scope.displayAddMessage = false;
			$scope.dataAfterResults = response;
		}else{
			$scope.displayAddMessage = true;
		}
	}).error(function(err){
		console.log(err);
	});	 

	
	//$scope.dataAfterResults = [{"id":89,"userId":{"id":1,"loginName":"sudeepg","loginPass":"sudeepg","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0,"goldenPredict":"Rising Pune Supergiants (RPS)\r"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Mumbai Indians","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":20.0,"result":"L","indicativePoints":27.5,"totalPointsEarned":53.0},{"id":90,"userId":{"id":2,"loginName":"jitendrah","loginPass":"jitendrah","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0,"goldenPredict":"GUJ"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Rising Pune Supergiants","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":5.0,"result":"W","indicativePoints":18.333333333333332,"totalPointsEarned":13.0},{"id":91,"userId":{"id":3,"loginName":"ravig","loginPass":"ravig","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0,"goldenPredict":"MI"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Mumbai Indians","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":20.0,"result":"L","indicativePoints":27.5,"totalPointsEarned":53.0},{"id":92,"userId":{"id":8,"loginName":"temp5","loginPass":"temp5","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0,"goldenPredict":"GUJ"},"ruleId":{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},"ruleValue":"Rising Pune Supergiants","ruleResult":"Rising Pune Supergiants","matchId":{"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},"pointsInvested":10.0,"result":"W","indicativePoints":36.666666666666664,"totalPointsEarned":27.0}];
	

    $scope.getWinLoss = function(result) {
        if (result =="L") {
        	$scope.winLossResult = "Win";        	
        } 
        else if (result =="W"){
        	$scope.winLossResult = "Loss";
        }
        else{
        	$scope.winLossResult = "Rule Result not yet updated";
        }
    };
    
	
    $scope.sort = {
            active: '',
            descending: undefined
        } 

    
    $scope.changeSorting = function(column) {

        var sort = $scope.sort;

        if (sort.active == column) {
            sort.descending = !sort.descending;
			
        } else {
            sort.active = column;
            sort.descending = false;
        }
    };

    $scope.getIcon = function(column) {
        
        var sort = $scope.sort;
        
        if (sort.active == column) {
          return sort.descending
            ? 'glyphicon-chevron-up'
            : 'glyphicon-chevron-down';
        }
        
        return 'glyphicon-star';
    };

    $scope.checkRule1 = function (ruleId) {
        if(ruleId==1){
        	return true;
        }
    	else{
         	return false;
    	}
    }
    
    $scope.checkRule2 = function (ruleId) {
        if(ruleId==2){
        	return true;
        }
    	else{
         	return false;
    	}
    }
	
	
    
}]);

app.filter("jsDate", function () {        	
    return function (x) {
    	if(x)
    	return new Date( x.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
    };
});   
app.filter("jsTime", function () {        	
    return function (x) {
     	var temp = x;
     	if(temp)
     	var sTime = temp.substring(0,8);
    	return sTime;
    };
});

</script>

</body>
</html>