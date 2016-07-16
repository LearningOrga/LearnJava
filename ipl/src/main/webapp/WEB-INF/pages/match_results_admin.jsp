<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
.left{
float:left;
margin-left:50px;
}
table, th, td {
border: 2px solid bisque !important;
font-size:11px !important;
}

th{
background-color: #C5C500 !important;
}
td{
background-color: #AF9D5D !important;
}
select{
background-color: #00BCD4 !important;
}
.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Predict and Win - Match Results Admin</title>

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
	
		<h3>Predict and Win - Match Results Admin</h3>
		
		<br>
		<button ng-click="enableDisableThisMatch('D')" class="w3-btn w3-ripple">&#10004; Disable this match</button>
	    <button ng-click="enableDisableThisMatch('A')" class="w3-btn w3-ripple">&#10004; Enable this match</button>
		<br>
		</br>
		
<!-- 		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Points Added Successfully !!!</h4>
		<h4 ng-show="displayErrMessage" class="displaySuccessMessageClass">Oops..Some issue while adding. Please try again !!!</h4> -->
		
		<h4  ng-show="displayMessageStatusChangeMessage" class="displaySuccessMessageClass">{{statusChangeMessage}}</h4>

		<h4  ng-show="displaySubmitMessage" class="displaySuccessMessageClass">{{submitMessageResult}}</h4>
		<h4  ng-show="displayReconcileMessage" class="displaySuccessMessageClass">{{reconcileMessageResult}}</h4>
		<h4  ng-show="displayUpdatedPointsMessage" class="displaySuccessMessageClass">{{updatedPointsMessageResult}}</h4>		

<table class="w3-table w3-bordered w3-striped">
			<th>Match</th>
			<th>Match Date</th>
			<th>Rules</th>
			<th>Select Team</th>
			<th>Actions</th>

			<tr>
				<td><b>{{ matchDetails }}</b></td>
				<td><b>{{ matchDate | jsDate | date}} ({{matchTime | jsTime}})</b></td>
				<td>
					<select ng-model="rulesArray" ng-options="x.ruleName for x in rulesJson" ng-change="selectedRule(rulesArray)">
					<option value="">-- Select Rule--</option>
				</td>
				<td><select ng-model="teamArray" ng-options="o for o in teamName" ng-change="selectedTeam(teamArray)">
					<option value="">-- Select Winning Team--</option>
					</select>
				</td>		
				<td>
				<button ng-click="submitResults()" ng-disabled="rule || team " class="w3-btn w3-ripple">&#10004; Submit</button>
    			<button ng-click="reconcileResults()" ng-disabled="reconcile" class="w3-btn w3-ripple">&#10004; Reconcile</button>
    			<button ng-click="updatesTotalPoints()" ng-disabled="updateTotalPoints" class="w3-btn w3-ripple">&#10004; Update Total Points</button>
				</td>			   			
			</tr>

		</table>
<br>

	<section ng-show="ajaxSuccessResponse">	

	<h4 ng-show="displaySubmitMessage" class="displaySuccessMessageClass">Please view your entries  below...</h4>

	<table class="w3-table w3-bordered w3-striped">
		<th>No</th>
		<th>Selected Rule</th>
		<th>Rule Winning Team</th>
		
		<tr ng-repeat="x in dataAfterSubmit">
		    <td><b>{{ $index + 1 }}</b></td>
    		<td><b>{{x.ruleId.ruleName}}</b></td>
    		<td><b>{{x.ruleResult}}</b></td>
    	</tr>
	</table> 
</section>

	</div>	
	
<script>

var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope', '$http', function($scope, $http) {

	
	$scope.rule = true;
	$scope.team = true;
	$scope.reconcile = true;
	$scope.updateTotalPoints = true;
	

	$scope.matchId = "<%=session.getAttribute("matchId")%>";
	
	$scope.matchDetails;
	$scope.matchDate;
	$scope.matchTime;
	$scope.teamName;
	
	$scope.selectedRuleId;
	$scope.selectedTeamName;

	$scope.displaySubmitMessage = false;
	$scope.displayReconcileMessage=false;
	$scope.displayUpdatedPointsMessage=false;
	$scope.displayMessageStatusChangeMessage=false;
	
	$scope.submitMessageResult;
	$scope.reconcileMessageResult;
	$scope.updatedPointsMessageResult;
	
	$scope.statusChangeMessage;
	
	$scope.ajaxSuccessResponse = false;
	
	$scope.dataAfterAdd;
	
	
	//uncomment to call match id service
  	var url = "match/"+ $scope.matchId;
	$http.get(url).success(function(response){
		var match = response;
		
		$scope.matchDetails = match.matchDetails;
		$scope.teamName = $scope.matchDetails.split(" vs ");	
		$scope.matchDate = match.matchDate;
		$scope.matchTime = match.matchTime;
	}); 
 	var urlRule = "rule/all";
		$http.get(urlRule).success(function(response){
			$scope.rulesJson = response;
		});  

		
	//uncomment to call rule all and match id static data service			
  	/* var match = {"id":1,"matchDate":"09-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (2:30pm GMT) ","matchDetails" :"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"};
	$scope.rulesJson = [{"id":1,"ruleName":"Winning Team","ruleDesc":"Which team is winning","ruleStatus":"A","ruleBasedOn":"Team"},{"id":2,"ruleName":"Toss Winning Team","ruleDesc":"Which team is winning toss","ruleStatus":"A","ruleBasedOn":"Team"}];
	$scope.dataAfterSubmit = [{"id":1,"matchId":"1","ruleId":"1","ruleResult":"Mumbai Indians"},{"id":2,"matchId":"1","ruleId":"2","ruleResult":"Kolkata Knight Riders"}];
	$scope.matchDetails = match.matchDetails;
	$scope.matchDate = match.matchDate;
	$scope.matchTime = match.matchTime;
	$scope.teamName = $scope.matchDetails.split(" vs ");   */
	 

    $scope.selectedRule = function (mySelectRule) {
    	$scope.ajaxSuccessResponse = false;
    	$scope.displaySubmitMessage = false;
    	$scope.displayReconcileMessage=false; 
    	$scope.displayUpdatedPointsMessage=false;    	
    	$scope.reconcile = true;
    	$scope.updateTotalPoints = true;
    	if(mySelectRule){
    		$scope.selectedRuleId = mySelectRule.id;
    		$scope.rule = false;
    	}
    	else{
    		$scope.selectedRuleId = "";
    	}            	
    }

    $scope.selectedTeam = function (mySelectTeam) {
    	$scope.ajaxSuccessResponse = false;
    	$scope.displaySubmitMessage = false;
    	$scope.displayReconcileMessage=false;
    	$scope.displayUpdatedPointsMessage=false;
		$scope.reconcile = true;
		$scope.updateTotalPoints = true;
    	if(mySelectTeam){
    		$scope.selectedTeamName = mySelectTeam;
    		$scope.team = false;
    	}
    	else{
    		$scope.team = true;
    		$scope.selectedTeamName = "";
    	}            	
    }

    $scope.submitResults = function () {
    	$scope.reconcile = false;
    	$scope.selectedRuleId;
    	$scope.selectedTeamName;
    	
		$scope.displaySubmitMessage = true;
		$scope.displayReconcileMessage=false;
		
    	
		var url = "matchResult/add";
		$http({
			method: "GET",
			url : url,
			params: {
				selRuleId : $scope.selectedRuleId,
				selTeamName : $scope.selectedTeamName,
				selMatchId :$scope.matchId,
			}					
		}).success(function(response){
			$scope.dataAfterSubmit = response;
			$scope.ajaxSuccessResponse = true;
	    	$scope.reconcile = false;
			if($scope.displaySubmitMessage){
				$scope.submitMessageResult = "Submitted the results successfully !!!";
			}		

		}).error(function(err){
			$scope.ajaxSuccessResponse = false;
	    	$scope.reconcile = true;
			if($scope.displaySubmitMessage){
				$scope.submitMessageResult = "Issue while submitting the results !!!";
			}		
		});            	
    } 

    $scope.reconcileResults = function () {
    	$scope.updateTotalPoints = false;
		$scope.displaySubmitMessage = false;
		$scope.displayReconcileMessage=true;		
    	$scope.selectedRuleId;    	
		var url = "reconcile";
		$http({
			method: "GET",
			url : url,
			params: {
				selRuleId : $scope.selectedRuleId,
				selMatchId : $scope.matchId,
			}					
		}).success(function(response){
			$scope.updateTotalPoints = false;
			if($scope.displayReconcileMessage){
				$scope.reconcileMessageResult = "Reconciled the results successfully !!!";
			}		

		}).error(function(err){
			$scope.updateTotalPoints = true;
			if($scope.displayReconcileMessage){
				$scope.reconcileMessageResult = "Issue while reconciling the results !!!";
			}
		});            	
    }

    $scope.updatesTotalPoints = function () {
		$scope.displaySubmitMessage = false;
		$scope.displayReconcileMessage=false;
		$scope.displayUpdatedPointsMessage=true;
   	
		var url = "reconcileAllUsersPoints";
		$http({
			method: "GET",
			url : url,	
			params: {
				matchId : $scope.matchId,
			}				
		}).success(function(response){
			if($scope.displayUpdatedPointsMessage){
				$scope.updatedPointsMessageResult = "Updated the total points successfully !!!";
			}		

		}).error(function(err){
			if($scope.displayUpdatedPointsMessage){
				$scope.updatedPointsMessageResult = "Issue while updating the total points!!!";
			}
		});            	
    }
    
    $scope.enableDisableThisMatch = function (status) {	
    	$scope.displayMessageStatusChangeMessage=true;
		var url = "match/UpdateStatus";
		$http({
			method: "GET",
			url : url,
			params: {
				selMatchId : $scope.matchId,
				selMatchStatus: status,
				
			}					
		}).success(function(response){
				$scope.statusChangeMessage = "Changed the match status successfully with status - "+response+" ";		

		}).error(function(err){
				$scope.statusChangeMessage = "Issue while enabling the match !!!";

		});            	
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