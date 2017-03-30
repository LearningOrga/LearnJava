<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
font-size:11px !important;
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
.displaySuccessMessageClass{
text-align: center !important;
color: #e91e63 !important;
}
.todaysMatchClass{
background-color: #9CAEB7 !important;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Predict and Win - Home</title>

<%-- <script src="<c:url value="/js/angular.min.js"/>"></script>
<script src="<c:url value="/js/angular-moment.min.js"/>"></script>
<script src="<c:url value="/js/moment.min.js"/>"></script>

<link href="<c:url value="/css/w3.css"/>" rel="stylesheet"> --%>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>


<script src="//cdnjs.cloudflare.com/ajax/libs/angular-moment/0.9.0/angular-moment.min.js"></script>
<body>

<div ng-app="myIplApp" ng-controller="userController" class="w3-container"> 

		<h2>Predict and Win - Home</h2>
		
		<a href="<c:url value="/summary" />" class="w3-btn w3-ripple">&#10004;Summary</a>
		
		<a href="<c:url value="/addGoldenPredicts" />" class="w3-btn w3-ripple">&#10004;Golden Predicts</a>
		
		<a href="<c:url value="/winningRules" />" class="w3-btn w3-ripple">&#10004;Winning Rules</a>
		
		<%-- <a href="<c:url value="/userReports" />" class="w3-btn w3-ripple">&#10004;Reports</a> --%>
		
		<a ng-hide="userRole" href="<c:url value="/manageUsers" />" class="w3-btn w3-ripple">&#10004;Manage Users</a>
				
		<h5 class="displaySuccessMessageClass">Key point: You can only add/predict prior to 1 hour of the game time. Dont miss out !!!</h5>

		
<table class="w3-table w3-bordered w3-striped">
<th>No.</th>
<th>Match Details</th>
<th>Match Venue</th>
<th>Match Date</th>
<th>Match Day</th>
<th>Time Left</th>
<th>Actions</th>
<th>Results</th>
<th ng-hide="userRole" >Admin Tab</th>

  <tr ng-repeat="x in matches | orderBy: myDateOrderBy" ng-hide="!x.matchDate" ng-class="todaysMatchHighlight(x.matchDate)">
    <td><b>{{ $index + 1 }}</b></td>
    <td><b>{{ x.matchDetails }}</b></td>
    <td><b>{{ x.matchVenue }}</b></td>
    <td><b>{{ x.matchDate | jsDate | date }} </b> ({{x.matchTime | jsTime}})</td>
    <td>{{ x.matchDay }}</td>    
    <!-- <td ng-init="displayTime(x.matchDate, x.matchTime)">{{ remainingTime|durationview }}</td> -->
    <td ng-hide="true">{{x.matchTime | filter: computeTime(x.matchDate, x.matchTime)}}</td>
    <td ng-hide="checkTimer(x.matchDate, x.matchTime)"> <b>{{ remainingTime|durationview }}</b></td>
    <td ng-show="checkTimer(x.matchDate, x.matchTime)"> <b>Oops..Times Up!!!</b></td>
    
     
      <td>
    	<button class="w3-btn w3-ripple" ng-show ="checkTimer(x.matchDate, x.matchTime)" ng-disabled="checkTimer(x.matchDate, x.matchTime)">&#10004;Add</button>
    	<a ng-hide="checkTimer(x.matchDate, x.matchTime)" href="<c:url value="/match/addMatchPoints?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;Add</a>
    	<a href="<c:url value="/match/viewMatchPoints?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;View</a>
    </td>
    
    <td>
        <a href="<c:url value="/match/matchResults?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;Results</a>
    </td>
	<td ng-hide="userRole">
		<a href="<c:url value="/match/matchResultAdmin?matchid={{x.id}}"  />" class="w3-btn w3-ripple">&#10004;Admin</a>
	</td>
  </tr>
</table>



</div>

<script>
/* var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $http.get("http://www.w3schools.com/angular/customers.php")
    .then(function (response) {$scope.names = response.data.records;});
}); */

var welcomeHome = angular.module("myIplApp", []);

welcomeHome.controller('userController', ['$scope', '$http', '$interval','$timeout', 'datetime', function($scope,$http,$interval,$timeout,datetime) {

	$scope.timeLeft = false; 
	$scope.cancelTimer;
	$scope.storedTime;
	$scope.errs= false;
	$scope.userRole = true;
	
	// while connecting with actual data
 	var url = "match/all";
	$http.get(url).success(function(response){
		$scope.matches = response;
		
	});

	var userUrl = "user";
	$http.get(userUrl).success(function(response){
		var role = response.loginRole;
		if(role =="ROLE_ADMIN"){
			$scope.userRole = false;
		}
	});  
 
 	
  
    var processTimeRemaining = function (x) {
    	$scope.remainingTime = datetime.getRemainigTime(x);
    }
    
    var tick = function () {    	       
        $scope.cancelTimer = $timeout(tick, 1000);
        if($scope.remainingTime<1000 || $scope.remainingTime<0){
        	$scope.timeLeft = true;
        	$timeout.cancel($scope.cancelTimer);
        }else{
        	$scope.timeLeft = false;
        }
    }
   
    $scope.cancelTimer = $timeout(tick, 1000);
    if($scope.remainingTime<1000 || $scope.remainingTime<0){
    	$scope.timeLeft = true;
    	$scope.remainingTime = 0;
    	$timeout.cancel($scope.cancelTimer);
    }else{
    	$scope.timeLeft = false;
    }
    
         
    $scope.computeTime = function (date, time) {
 	var sDate = date;
 	var temp = time;
 	if(temp){
 	var sTime = temp.substring(0,5);
 	}
 	var lastMoment;
 	if(parseInt(sTime)==20){
 		lastMoment="19:00";
 	}else if(parseInt(sTime)==16){
 		lastMoment="15:00";
 	}else{
 		lastMoment="00:00";
 	}
 	$scope.storedTime = date+" "+lastMoment;
 	processTimeRemaining($scope.storedTime);
    } 
    
    $scope.checkTimer = function (date, time) {    	
     	var sDate = date;
     	var temp = time;
     	var sTime = temp.substring(0,5);
     	var lastMoment;
     	if(parseInt(sTime)==20){
     		lastMoment="19:00";
     	}else if(parseInt(sTime)==16){
     		lastMoment="15:00";
     	}else{
     		lastMoment="00:00";
     	}
     	var remainingTime = date+" "+lastMoment;
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
    
    $scope.myDateOrderBy = function(x) {
        if(x.matchDate){
        	return new Date( x.matchDate.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
        }
    }
 
    $scope.todaysMatchHighlight = function (matchDate) {
    	var matchDateString  = matchDate;
        var formattedMatchDate = new Date( matchDateString.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
    	var now = new Date(); 
    	if((formattedMatchDate.getDate()==now.getDate()) && (formattedMatchDate.getMonth()==now.getMonth()) && (formattedMatchDate.getFullYear()==now.getFullYear()) ){
    		return 'todaysMatchClass'
    	}
    	else{
    	}
    } 
    
}]);     
        welcomeHome.factory('datetime', ['$timeout', function ($timeout) {
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
        
        welcomeHome.filter('durationview', ['datetime', function (datetime) {
            return function (input) {
                var duration = datetime.duration(input);
                return duration.days + "d:" + duration.hours + "h:" + duration.minutes + "m:" + duration.seconds + "s";
            };
        }]);  
        
        welcomeHome.filter("jsDate", function () {        	
            return function (x) {
            	if(x)
            	return new Date( x.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3") );
            };
        });   
        welcomeHome.filter("jsTime", function () {        	
            return function (x) {
             	var temp = x;
             	if(temp)
             	var sTime = temp.substring(0,8);
            	return sTime + " "+"IST";
            };
        });  

</script>

</body>
</html>
