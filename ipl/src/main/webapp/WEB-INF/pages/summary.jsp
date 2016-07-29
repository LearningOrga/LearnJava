<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style>
table, th, td {
border: 2px solid bisque !important;
}
#contributorHeader, #rankHeader{
cursor : pointer !important;
}

.topPerformer{
background-color:#00ff00;
}
.averagePerformer{
background-color: #ffb84d;
}
.lowPerformer{
background-color: #ff0000;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - Summary</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
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
	
		<h3>Predict and Win - Summary</h3>

<table class="w3-table w3-bordered w3-striped">
<th>Sr No</th>
<th ng-click="changeSorting('userName')" id="contributorHeader">Contributor
<i class="glyphicon" ng-class="getIcon('userName')">
</i>
</th> 
<th ng-click="changeSorting('totalNumberWins')" id="contributorHeader1">Total Wins / Total Predicted
<i class="glyphicon" ng-class="getIcon('totalNumberWins')">
</i>
</th>
<th ng-click="changeSorting('totalPoints')" id="contributorHeader2">Total Points
<i class="glyphicon" ng-class="getIcon('totalPoints')">
</i>
</th>
<th ng-click="changeSorting('totalPointsInvested')" id="contributorHeader3">Total Points Invested
<i class="glyphicon" ng-class="getIcon('totalPointsInvested')">
</i>
</th>
<th ng-click="changeSorting('totalPointsEarned')" id="contributorHeader4">Total Points Earned
<i class="glyphicon" ng-class="getIcon('totalPointsEarned')">
</i>
</th>
<th ng-click="changeSorting('rule1Wins')" id="contributorHeader5">Match Wins
<i class="glyphicon" ng-class="getIcon('rule1Wins')">
</i>
</th>
<th ng-click="changeSorting('rule2Wins')" id="contributorHeader6">Toss Wins
<i class="glyphicon" ng-class="getIcon('rule2Wins')">
</i>
</th>
<th ng-click="changeSorting('winLossPer')" id="contributorHeader7">Win/Loss %
<i class="glyphicon" ng-class="getIcon('winLossPer')">
</i>
</th>
<th>Badge</th>
<!-- <th>Actions</th> -->

  <tr ng-repeat="x in matchInvSummary | orderBy:sort.active:sort.descending">
    <td>{{ $index + 1 }}</td>
    <td><b>{{ x.userName }}</b></td>
    <td>{{ x.totalNumberWins }} / {{x.totalNumberPredicted}}</td>
    <td>{{ x.totalPoints }}</td>
    <td>{{ x.totalPointsInvested }}</td>
    <td>{{ x.totalPointsEarned }}</td>
    <td>{{ x.rule1Wins }}</td> 
    <td>{{ x.rule2Wins }}</td>          
 	<td>{{ x.winLossPer }}</td>          
    <td ng-class="funCall(x.winLossPer)" class="glyphicon"></td>   
 <%--     <td><a href="<c:url value="/userWinLossReportBC.png?userId={{x.userId}}" />" class="w3-btn w3-ripple">&#10004;User Report</a></td>
     <td><a href="<c:url value="/userWinLossProgressLC.png?userId={{x.userId}}&ruleId=2" />" class="w3-btn w3-ripple">&#10004;User Line Report</a></td> --%> 
  </tr>
</table>

	</div>	
	<div id="pieChartDialogBox" title="Pie Chart View"></div>
<script>
	
	var welcomeHome = angular.module("myApp", []);
	welcomeHome.controller('myCtrl', ['$scope','$http', function($scope,$http) {
		
		$scope.matchInvSummary;
	
	//uncomment
  	var url = "playResult/allForSummary";
	$http.get(url).success(function(response){
		$scope.matchInvSummary = response;		
	}); 
	
   
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

    $scope.funCall = function (column) {
    	if(column>=70){
    		return 'topPerformer'
    	}else if(column>=30 && column<70){
    		return 'averagePerformer'
    	}
    	else{
    		return 'lowPerformer'
    	}


}
	
}]);

</script>

</body>
</html>