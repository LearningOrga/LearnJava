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

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th{
color:#9c27b0;
}

.w3-ripple{
color: deepskyblue !important	;
}

th{
background-color: rgba(30, 167, 233, 0.22) !important;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

#contributorHeader, #rankHeader{
cursor : pointer !important;
}

.topPerformer{
    border: solid #00ff00 !important;
    border-width: 0 4px 4px 0 !important;
    display: inline-block !important;
    padding: 4px !important;
	transform: rotate(-135deg) !important;
	margin-left: 25px !important;
	margin-top: 14px !important;
}
.notPredicted{

}
.averagePerformer{
    border: solid #ffb84d !important;
    border-width: 0 4px 4px 0 !important;
    display: inline-block !important;
    padding: 4px !important;
	transform: rotate(-45deg) !important;
	margin-left: 23px !important;
	margin-top: 11px !important;
}
.lowPerformer{
    border: solid #ff0000 !important;
    border-width: 0 4px 4px 0 !important;
    display: inline-block !important;
    padding: 4px !important;
	transform: rotate(45deg) !important;
	margin-left: 25px !important;
	margin-top: 8px !important;
}

.displayLoadSuccessClass{
text-align: center !important;
color: rgba(0, 100, 255, 0.56) !important;
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

<br>
<h4  ng-show="displayLoadingText" class="displayLoadSuccessClass">{{summaryPageLoadText}}</h4>	


<table class="w3-table w3-bordered w3-striped" ng-show="!displayLoadingText">
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
		$scope.displayLoadingText=true;
		$scope.summaryPageLoadText = "Summary page loading...";
	
	//uncomment
  	var url = "playResult/allForSummary";
	$http.get(url).success(function(response){
		$scope.displayLoadingText = false;
		$scope.matchInvSummary = response;	
	}).error(function(err){
		$scope.displayLoadingText = true;
		$scope.summaryPageLoadText = "Summary page load issue. Contact Admin !!!";
		console.log(err);
	}); 
	
   
    $scope.sort = {
            active: '',
            descending: undefined
        };
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
    	if(column>=65){
    		return 'topPerformer';
    	}else if(column>=35 && column<65){
    		return 'averagePerformer';
    	}else if(column>0 && column<35){
    		return 'lowPerformer';
    	}else{
    		return 'notPredicted';
    	}


};
	
}]);

</script>

</body>
</html>