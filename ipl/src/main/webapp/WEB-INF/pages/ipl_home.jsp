<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
table, th, td {
border: 2px solid bisque !important;
font-size:11px !important;
}

th{
background-color: #C5C500 !important;
}
.displaySuccessMessageClass{
text-align: center !important;
color: dodgerblue !important;
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

		<h3>Predict and Win - Home</h3>
		
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
    	<a ng-hide="checkTimer(x.matchDate, x.matchTime)" href="<c:url value="/addMatchPoints?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;Add</a>
    	<a href="<c:url value="/viewMatchPoints?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;View</a>
    </td>
    
    <td>
        <a href="<c:url value="/matchResults?matchid={{x.id}}" />" class="w3-btn w3-ripple">&#10004;Results</a>
    </td>
	<td ng-hide="userRole">
		<a href="<c:url value="/matchResultAdmin?matchid={{x.id}}"  />" class="w3-btn w3-ripple">&#10004;Admin</a>
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
 
 	
    // while connecting with replicated data
   //$scope.matches = [{"id":0,"matchDate":null,"matchDay":null,"matchTime":null,"matchDetails":null,"matchVenue":null},{"id":1,"matchDate":"08-04-2016","matchDay":"Saturday","matchTime":"16:25 PM (02:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},{"id":10,"matchDate":"17-04-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Kings XI Punjab vs Rising Pune Supergiants","matchVenue":"Mohali"},{"id":11,"matchDate":"17-04-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Royal Challengers Bangalore vs Delhi Daredevils","matchVenue":"Bengaluru"},{"id":12,"matchDate":"18-04-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunrisers Hyderabad vs Mumbai Indians","matchVenue":"Hyderabad"},{"id":13,"matchDate":"19-04-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Kolkata Knight Riders","matchVenue":"Mohali"},{"id":14,"matchDate":"20-04-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Mumbai Indians vs Royal Challengers Bangalore","matchVenue":"Mumbai"},{"id":15,"matchDate":"21-04-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Gujarat Lions vs Sunrisers Hyderabad","matchVenue":"Rajkot"},{"id":16,"matchDate":"22-04-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Royal Challengers Bangalore","matchVenue":"Pune"},{"id":17,"matchDate":"23-04-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Delhi Daredevils vs Mumbai Indians","matchVenue":"Delhi"},{"id":18,"matchDate":"23-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunrisers Hyderabad vs Kings XI Punjab","matchVenue":"Hyderabad"},{"id":19,"matchDate":"24-04-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Gujarat Lions vs Royal Challengers Bangalore","matchVenue":"Rajkot"},{"id":2,"matchDate":"10-04-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Delhi Daredevils","matchVenue":"Kolkata"},{"id":20,"matchDate":"24-04-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Kolkata Knight Riders","matchVenue":"Pune"},{"id":21,"matchDate":"25-04-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Mumbai Indians","matchVenue":"Mohali"},{"id":22,"matchDate":"26-04-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunrisers Hyderabad vs Rising Pune Supergiants","matchVenue":"Hyderabad"},{"id":23,"matchDate":"27-04-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Delhi Daredevils vs Gujarat Lions","matchVenue":"Delhi"},{"id":24,"matchDate":"28-04-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Mumbai Indians vs Kolkata Knight Riders","matchVenue":"Mumbai"},{"id":25,"matchDate":"29-04-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Gujarat Lions","matchVenue":"Pune"},{"id":26,"matchDate":"30-04-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Delhi Daredevils vs Kolkata Knight Riders","matchVenue":"Delhi"},{"id":27,"matchDate":"30-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunsers Hyderabad vs Royal Challengers Bangalore","matchVenue":"Hyderabad"},{"id":28,"matchDate":"01-05-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Gujarat Lions vs Kings XI Punjab","matchVenue":"Rajkot"},{"id":29,"matchDate":"01-05-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Mumbai Indians","matchVenue":"Pune"},{"id":3,"matchDate":"11-04-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Gujarat Lions","matchVenue":"Mohali"},{"id":30,"matchDate":"02-05-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Royal Challengers Bangalore vs Kolkata Knight Riders","matchVenue":"Bengaluru"},{"id":31,"matchDate":"03-05-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Gujarat Lions vs Delhi Daredevils","matchVenue":"Rajkot"},{"id":32,"matchDate":"04-05-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Kings XI Punjab","matchVenue":"Kolkata"},{"id":33,"matchDate":"05-05-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Delhi Daredevils vs Rising Pune Supergiants","matchVenue":"Delhi"},{"id":34,"matchDate":"06-05-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunrisers Hyderabad vs Gujarat Lions","matchVenue":"Hyderabad"},{"id":35,"matchDate":"07-05-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Royal Challengers Bangalore vs Rising Pune Supergiants","matchVenue":"Bengaluru"},{"id":36,"matchDate":"07-05-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Delhi Daredevils","matchVenue":"Nagpur"},{"id":37,"matchDate":"08-05-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Mumbai Indians vs Sunrisers Hyderabad","matchVenue":"Mumbai"},{"id":38,"matchDate":"08-05-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Gujarat Lions","matchVenue":"Kolkata"},{"id":39,"matchDate":"09-05-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Royal Challengers Bangalore","matchVenue":"Nagpur"},{"id":4,"matchDate":"12-04-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Royal Challengers Bangalore vs Sunrisers Hyderabad","matchVenue":"Bengaluru"},{"id":40,"matchDate":"10-05-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Sunrisers Hyderabad","matchVenue":"Pune"},{"id":41,"matchDate":"11-05-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Royal Challengers Bangalore vs Mumbai Indians","matchVenue":"Bengaluru"},{"id":42,"matchDate":"12-05-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Sunrisers Hyderbad vs Delhi Daredevils","matchVenue":"Hyderabad"},{"id":43,"matchDate":"13-05-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Mumbai Indians vs Kings XI Punjab","matchVenue":"Mumbai"},{"id":44,"matchDate":"14-05-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Royal Challengers Bangalore vs Gujarat Lions","matchVenue":"Bengaluru"},{"id":45,"matchDate":"14-05-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Rising Pune Supergiants","matchVenue":"Kolkata"},{"id":46,"matchDate":"15-05-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Mumbai Indians vs Delhi Daredevils","matchVenue":"Mumbai"},{"id":47,"matchDate":"15-05-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kings XI Punjab vs Sunrisers Hyderabad","matchVenue":"Nagpur"},{"id":48,"matchDate":"16-05-2016","matchDay":"Monday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Royal Challengers Bangalore","matchVenue":"Kolkata"},{"id":49,"matchDate":"17-05-2016","matchDay":"Tuesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Rising Pune Supergiants vs Delhi Daredevils","matchVenue":"Pune"},{"id":5,"matchDate":"13-04-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Kolkata Knight Riders vs Mumbai Indians","matchVenue":"Kolkata"},{"id":50,"matchDate":"18-05-2016","matchDay":"Wednesday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Royal Challengers Bangalore vs Kings XI Punjab","matchVenue":"Bengaluru"},{"id":51,"matchDate":"19-05-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Gujarat Lions vs Kolkata Knight Riders","matchVenue":"Rajkot/Kanpur"},{"id":52,"matchDate":"20-05-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Delhi Daredevils vs Sunrisers Hyderabad","matchVenue":"Raipur"},{"id":53,"matchDate":"21-05-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Rising Pune Supergiants vs Kings XI Punjab","matchVenue":"Pune"},{"id":54,"matchDate":"21-05-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Gujarat Lions vs Mumbai Indians","matchVenue":"Rajkot/Kanpur"},{"id":55,"matchDate":"22-05-2016","matchDay":"Sunday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Kolkata Knight Riders vs Sunrisers Hyderabad","matchVenue":"Kolkata"},{"id":56,"matchDate":"22-05-2016","matchDay":"Sunday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Delhi Daredevils vs Royal Challengers Bangalore","matchVenue":"Raipur"},{"id":6,"matchDate":"14-04-2016","matchDay":"Thursday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Gujarat Lions vs Rising Pune Supergiants","matchVenue":"Rajkot"},{"id":7,"matchDate":"15-04-2016","matchDay":"Friday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Delhi Daredevils vs Kings XI Punjab","matchVenue":"Delhi"},{"id":8,"matchDate":"16-04-2016","matchDay":"Saturday","matchTime":"16:00 PM (10:30am GMT)","matchDetails":"Sunrisers Hyderabad vs Kolkata Knight Riders","matchVenue":"Hyderabad"},{"id":9,"matchDate":"16-04-2016","matchDay":"Saturday","matchTime":"20:00 PM (02:30pm GMT)","matchDetails":"Mumbai Indians vs Gujarat Lions","matchVenue":"Mumbai"}];
    //$scope.matches = [{"id":1,"matchDate":"03-04-16","matchDay":"Saturday","matchTime":"17:18 PM (02:30pm GMT) ","matchDetails":"Mumbai Indians vs Rising Pune Supergiants","matchVenue":"Mumbai"},{"id":10,"matchDate":"03-04-16","matchDay":"Sunday","matchTime":"17:40 PM (10:30am GMT)","matchDetails":"Kings XI Punjab vs Rising Pune Supergiants","matchVenue":"Mohali"}];
 
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
