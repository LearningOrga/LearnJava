<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>

table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
font-size: 13px !important;
}

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th{
color:#9c27b0;
}

.w3-ripple{
color: deepskyblue !important	;
}


th{
background-color: rgba(30, 167, 233, 0.22) !important;
}

#contributorHeader, #rankHeader{
cursor : pointer !important;
}

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: rgba(222, 30, 255, 0.78) !important;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.winClass{
color: rgba(32, 233, 30, 0.88) !important;
}
.lossClass{
color: #ff0000 !important;
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
	
		<h2>Predict and Win - Match Results</h2>
		<div><b>{{dataAfterResults[0].matchId.matchDetails}} - {{dataAfterResults[0].matchId.matchDate | jsDate | date}} ({{dataAfterResults[0].matchId.matchTime | jsTime}})</b>
		</div>
		<br>
		
		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Match result not yet updated</h4>

<h3>Results - Winning Team</h3>

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
    		<td ng-class="checkWinLoss(x.result)"><b>{{x.userId.loginName}}</b></td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td>{{x.pointsInvested}}</td>
    		<td ng-hide="!x.totalPointsEarned" ng-class="checkWinLoss(x.result)"><b>{{x.totalPointsEarned}}</b></td>
    		<td ng-show="!x.totalPointsEarned">Match Winner not yet updated</td>
    		<td ng-hide="!x.result"  ng-init="getWinLoss(x.result)" ng-class="checkWinLoss(x.result)"><b>{{x.result}}</b></td>
    		<td ng-show="!x.result">Rule Result not yet updated</td>    		
    	</tr>
</table>

<h3>Results - Toss Winning Team</h3>

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
    		<td ng-class="checkWinLoss(x.result)"><b>{{x.userId.loginName}}</b></td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td>{{x.pointsInvested}}</td>
    		<td ng-hide="!x.totalPointsEarned" ng-class="checkWinLoss(x.result)"><b>{{x.totalPointsEarned}}</b></td>
    		<td ng-show="!x.totalPointsEarned">Match Winner not yet updated</td>
    		<td ng-hide="!x.result"  ng-init="getWinLoss(x.result)" ng-class="checkWinLoss(x.result)"><b>{{x.result}}</b></td>
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
    
    $scope.checkWinLoss = function (result) {

    	if(result =="WIN"){
    		return 'winClass'
    	}else if(result =="LOSS"){
    		return 'lossClass'
    	}    	
}    
	
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