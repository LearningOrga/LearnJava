<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>

<style>
.left{
float:left;
margin-left:50px;
}
table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
font-size:11px !important
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
td{
background-color: rgba(205, 236, 250, 0.16) !important;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

select{
background-color: #9c27b0 !important;
color: beige;
}
.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: #e91e63 !important;
}
.backColor{
color:#e91e63 !important;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Predict and Win - Add Golden Predicts</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<!-- load momentJS (required for angular-moment) -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>

<!-- load angular-moment -->
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js"></script>
	
	

</head>
<body>

	<div ng-app="myApp" ng-controller="myCtrl" class="w3-container">

		<h2>Predict and Win - Add Golden Predicts</h2>
		
		<a href="<c:url value="/viewGoldenPredicts" />" class="w3-btn w3-ripple">&#10004;View All Golden Predictions</a>

<br></br>
<p ng-hide="true">{{matchTime | filter: computeTime()}}</p>

<h4 ng-hide="checkTimer()">Time Left to Predict : <b>{{ remainingTime|durationview }} </b></h4>
<h4 ng-show="checkTimer()">Time Left to Predict : <b>Times Up !!!</b></h4>

<p class="backColor">Predict all the four quarter finalists in order from below </p>

<form>
		<table class="w3-table w3-bordered w3-striped">
			<th>Quarter Finalist 1</th>
			<th>Quarter Finalist 2</th>
			<th>Quarter Finalist 3</th>
			<th>Quarter Finalist 4</th>

<tr>
			<td><select class="w3-input w3-border" ng-model="goldenPredict1" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam1(goldenPredict1)">
				<option value="">-- Predict Quarter Finalist 1 --</option> 
			</select></td>
		
			<td><select class="w3-input w3-border" ng-model="goldenPredict2" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam2(goldenPredict2)">
				<option value="">-- Predict Quarter Finalist 2 --</option> 
			</select></td>
		
			<td><select class="w3-input w3-border" ng-model="goldenPredict3" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam3(goldenPredict3)">
				<option value="">-- Predict Quarter Finalist 3 --</option> 
			</select></td>
			
			<td><select class="w3-input w3-border" ng-model="goldenPredict4" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam4(goldenPredict4)">
				<option value="">-- Predict Quarter Finalist 4 --</option> 
			</select></td>	
</tr>
		</table>
		
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
</form>
<br>

<p class="backColor"> Predict both the semi quarter finalists from above selected Quarter finalists only</p>

		<table class="w3-table w3-bordered w3-striped">
			<th>Semi Finalist 1</th>
			<th>Semi Finalist 2</th>
<tr>
			<td><select class="w3-input w3-border" ng-model="goldenPredict5" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam5(goldenPredict5)">
				<option value="">-- Predict Semi Finalist 1 --</option> 
			</select></td>
		
			<td><select class="w3-input w3-border" ng-model="goldenPredict6" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam6(goldenPredict6)">
				<option value="">-- Predict Semi Finalist 2 --</option> 
			</select></td>
</tr>		
		</table>

<br>

<p class="backColor"> Predict the Winner from below from above Semi finalists only.</p>

		<table class="w3-table w3-bordered w3-striped">
			<th>Winner</th>
<tr>
			<td><select class="w3-input w3-border" ng-model="goldenPredict7" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam7(goldenPredict7)">
				<option value="">-- Predict The Winner --</option> 
			</select></td>
</tr>
		</table>
		
		<br>
		
				<td>
					<button ng-disabled="team1 ||team2 ||team3 ||team4 ||team5 ||team6 ||team7 || timeLeft " ng-hide="timeLeft" ng-click="addGoldenPredicitons()"
					class="w3-btn w3-ripple">&#9998; Add / Update All the Golden Predicitions</button>
					<button ng-disabled="team1 ||team2 ||team3 ||team4 ||team5 ||team6 ||team7 || timeLeft" ng-show="timeLeft"
					class="w3-btn w3-ripple">&#9998; Oops..Times Up!!!</button>					
				</td>
											
		<br>			

		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Your Golden predictions updated successfully !!!</h4>
		<h4 ng-show="displayErrMessage" class="displaySuccessMessageClass">{{errMessagetext}}</h4>
		
		<br>
	
	<section ng-show="ajaxSuccessResponse">	

	<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Please view your predictions below...</h4>

	<table class="w3-table w3-bordered w3-striped">
		<th>Predicted Quarter Finalist 1</th>
		<th>Predicted Quarter Finalist 2</th>
		<th>Predicted Quarter Finalist 3</th>
		<th>Predicted Quarter Finalist 4</th>
		<th>Predicted Semi Finalist 1</th>
		<th>Predicted Semi Finalist 1</th>
		<th>Predicted Winner</th>
		
		<tr ng-repeat="x in dataAfterAdd">	
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

		</section>
		</div>
		
	<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$rootScope' ,'$interval','$timeout', 'datetime', '$http', function($scope,$rootScope,$interval,$timeout,datetime, $http) {

	$scope.userId = "<%= session.getAttribute("userId")%>";
	
	$scope.team1 = true;
	$scope.team2 = true;
	$scope.team3 = true;
	$scope.team4 = true;
	$scope.team5 = true;
	$scope.team6 = true;
	$scope.team7 = true;
	
	$scope.timeLeft = false; 
	$scope.ajaxSuccessResponse = false;
	$scope.cancelTimer;
	
	$scope.displayAddMessage = false;
	$scope.displayErrMessage=false;
	
	$scope.errMessagetext;
	$scope.dataAfterAdd;
	
	$scope.selectedTeam1Id;
	$scope.selectedTeam2Id;
	$scope.selectedTeam3Id;
	$scope.selectedTeam4Id;
	$scope.selectedTeam5Id;
	$scope.selectedTeam6Id;
	$scope.selectedTeam7Id;
	
		
 		var teamUrl = "team/all";
		$http.get(teamUrl).success(function(response){
			$scope.teamJson = response;
			
		});	
		


	           		
        	    $scope.selectedTeam1 = function (mySelectGoldenPredictTeam1) {
        	    	if(mySelectGoldenPredictTeam1){
        	    		$scope.selectedTeam1Id = mySelectGoldenPredictTeam1.id;
        	    		$scope.team1 = false;
        	    	}
        	    	else{
        	    		$scope.team1 = true;
        	    		$scope.selectedTeam1Id = 0;
        	    	}            	
        	    }
	        	$scope.selectedTeam2 = function (mySelectGoldenPredictTeam2) {
	        	    	if(mySelectGoldenPredictTeam2){
	        	    		$scope.selectedTeam2Id = mySelectGoldenPredictTeam2.id;
	        	    		$scope.team2 = false;
	        	    	}
	        	    	else{
	        	    		$scope.team2 = true;
	        	    		$scope.selectedTeam2Id = 0;
	        	    	}            	
	        	    }
        	    $scope.selectedTeam3 = function (mySelectGoldenPredictTeam3) {
        	    	if(mySelectGoldenPredictTeam3){
        	    		$scope.selectedTeam3Id = mySelectGoldenPredictTeam3.id;
        	    		$scope.team3 = false;
        	    	}
        	    	else{
        	    		$scope.team3 = true;
        	    		$scope.selectedTeam3Id = 0;
        	    	}            	
        	    }
        	    $scope.selectedTeam4 = function (mySelectGoldenPredictTeam4) {
        	    	if(mySelectGoldenPredictTeam4){
        	    		$scope.selectedTeam4Id = mySelectGoldenPredictTeam4.id;
        	    		$scope.team4 = false;
        	    	}
        	    	else{
        	    		$scope.tea4 = true;
        	    		$scope.selectedTeam4Id = 0;
        	    	}            	
        	    }
        	    $scope.selectedTeam5 = function (mySelectGoldenPredictTeam5) {
        	    	if(mySelectGoldenPredictTeam5){
        	    		$scope.selectedTeam5Id = mySelectGoldenPredictTeam5.id;
        	    		$scope.team5 = false;
        	    	}
        	    	else{
        	    		$scope.team5 = true;
        	    		$scope.selectedTeam5Id = 0;
        	    	}            	
        	    }
        	    $scope.selectedTeam6 = function (mySelectGoldenPredictTeam6) {
        	    	if(mySelectGoldenPredictTeam6){
        	    		$scope.selectedTeam6Id = mySelectGoldenPredictTeam6.id;
        	    		$scope.team6 = false;
        	    	}
        	    	else{
        	    		$scope.team6 = true;
        	    		$scope.selectedTeam6Id = 0;
        	    	}            	
        	    }
        	    $scope.selectedTeam7 = function (mySelectGoldenPredictTeam7) {
        	    	if(mySelectGoldenPredictTeam7){
        	    		$scope.selectedTeam7Id = mySelectGoldenPredictTeam7.id;
        	    		$scope.team7 = false;
        	    	}
        	    	else{
        	    		$scope.team7 = true;
        	    		$scope.selectedTeam7Id = 0;
        	    	}            	
        	    }
 
    var processTimeRemaining = function (x) {
    	$scope.remainingTime = datetime.getRemainigTime(x);
    }
    
    var tick = function () {    	       
        $scope.cancelTimer = $timeout(tick, 1000);
        if($scope.remainingTime<1400 || $scope.remainingTime<0){
        	$scope.timeLeft = true;
        	$timeout.cancel($scope.cancelTimer);
        	$scope.remainingTime = 0;
        }else{
        	$scope.timeLeft = false;
        }
    }
   
    $scope.cancelTimer = $timeout(tick, 1000);
    if($scope.remainingTime<1000 || $scope.remainingTime<0){
    	$scope.timeLeft = true;
    	$timeout.cancel($scope.cancelTimer);
    	$scope.remainingTime = 0;
    }else{
    	$scope.timeLeft = false;
    }  

            $scope.addGoldenPredicitons = function () {
				var url = "predict/add";
				
				var dataObj= {
						userId : parseInt($scope.userId),
						qfteam1 : $scope.selectedTeam1Id,
						qfteam2 : $scope.selectedTeam2Id,
						qfteam3 : $scope.selectedTeam3Id,
						qfteam4 : $scope.selectedTeam4Id,
						sfteam1 : $scope.selectedTeam5Id,
						sfteam2 : $scope.selectedTeam6Id,
						finalWinningTeam : $scope.selectedTeam7Id
					};
				var res = $http.post(url,dataObj);
				
				res.success(function(response){
					if(response){
						$scope.ajaxSuccessResponse = true;
						$scope.displayAddMessage = true;
						$scope.displayErrMessage = false;
						
						$scope.dataAfterAdd = response;
						console.log(response);
						}
					else{
						$scope.ajaxSuccessResponse = false;
						$scope.displayAddMessage = false;
						$scope.displayErrMessage = true;					
					}
				});
				res.error(function(err){
					console.log(err);					
					$scope.ajaxSuccessResponse = false;
					$scope.displayAddMessage = false;
					$scope.displayErrMessage = true;
					$scope.errMessagetext = "Oops..Some issue while adding Golden Predicts. Please try again or contact Admin !!!";			
				});            	
            }            
            


            $scope.computeTime = function () {
             	var sDate = "25-04-2019";
             	var temp = "19:00";
             	$scope.storedTime = sDate+" "+temp;
             	processTimeRemaining($scope.storedTime);
                } 
                
            $scope.checkTimer = function () {    
            		var sDate = "25-04-2019";
            		var temp = "19:00";
                 	var remainingTime = sDate+" "+temp;
                    var now = moment().utc();
                    if(remainingTime)
                    var endTime = new Date( remainingTime.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
                    var totalTimeLeft = moment(endTime) - now;
                    if(totalTimeLeft<1000 || totalTimeLeft<0){
                    	return true;
                    }
                    else{
                    	return false;
                    }
                   } 
            
}]);

app.factory('datetime', ['$timeout', function ($timeout) {
    var duration = function (timeSpan) {
        var days = Math.floor(timeSpan / 86400000);
        var diff = timeSpan - days * 86400000;
        var hours = Math.floor(diff / 3600000);
        diff = diff - hours * 3600000;
        var minutes = Math.floor(diff / 60000);
        diff = diff - minutes * 60000;
        var secs = Math.floor(diff / 1000);
        return { 'days': days, 'hours': hours, 'minutes': minutes, 'seconds': secs };
    };
    function getRemainigTime(remainingTime) {
        var now = moment().utc();
        if(remainingTime)
        var endTime = new Date( remainingTime.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
        return moment(endTime) - now;
    }
    return {
        duration: duration,
        getRemainigTime: getRemainigTime
    };
}]);

app.filter('durationview', ['datetime', function (datetime) {
    return function (input) {
        var duration = datetime.duration(input);
        return duration.days + "d:" + duration.hours + "h:" + duration.minutes + "m:" + duration.seconds + "s";
    };
}]);   


</script>

</body>
</html>
