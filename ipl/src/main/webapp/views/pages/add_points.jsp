<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta Http-Equiv="Cache-Control" Content="no-cache">
<meta Http-Equiv="Pragma" Content="no-cache">
<meta Http-Equiv="Expires" Content="0">
<meta Http-Equiv="Pragma-directive: no-cache">
<meta Http-Equiv="Cache-directive: no-cache">

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
   <script>
  $(function() {
  	var test1 = $("#getMatchId").html();

    $( "#pieChartDialogBox" ).dialog({
        width: 500,
        height: 600,
        open: function(event, ui)
        {
            var dialogBox1 = "<div><h3><b>Points Invested Charts</b></h3><table><tr><td colspan='2' align='center'>Summary Charts for Rule - 1. Winning Team</td></tr><tr>";
            dialogBox1 = dialogBox1 +"<td><img alt='Total points invested in the match' src='testChart.png?ruleId=1&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
            dialogBox1 = dialogBox1 +"</tr>	<tr><td colspan='2' align='center'>Summary Charts for Rule - 2. Toss Winning Team</td></tr><tr>";
            dialogBox1 = dialogBox1 +"<td><img alt='Total points invested in the match' src='testChart.png?ruleId=2&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>"
            dialogBox1 = dialogBox1 +"</tr> </table></div>";
            
            $(this).html(dialogBox1);
        },    	
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });
    $( "#barChartDialogBox" ).dialog({
        width: 700,
        height: 800,
        open: function(event, ui)
        {
        	console.log("test11as"+test1);
            var dialogBox2 = "<h3><b>Points Invested Charts</b></h3><table><tr><td colspan='2' align='center'>Summary Charts for Rule - 1. Winning Team</td></tr><tr>";
            dialogBox2 = dialogBox2 +"<td><img alt='Bar Chart - Points invested by everyone' src='testBarChart.png?ruleId=1&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
            dialogBox2 = dialogBox2 +"</tr>	<tr><td colspan='2' align='center'>Summary Charts for Rule - 2. Toss Winning Team</td></tr><tr>";
            dialogBox2 = dialogBox2 +"<td><img alt='Bar Chart - Points invested by everyone' src='testBarChart.png?ruleId=2&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
            dialogBox2 = dialogBox2 +"</tr> </table>";
            
            $(this).html(dialogBox2);
        },    	
        autoOpen: false,
        show: {
          effect: "blind",
          duration: 1000
        },
        hide: {
          effect: "explode",
          duration: 1000
        }
      });    
 
    $( "#pieChartOpener" ).click(function() {
      	alert( $( "#pieChartDialogBox" ).html("test"));
      $( "#pieChartDialogBox" ).dialog( "open" );
    });
    $( "#barChartOpener" ).click(function() {
        $( "#barChartDialogBox" ).dialog( "open" );
      });    
  });
  </script>


<style>
.left{
float:left;
margin-left:50px;
}
table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
font-size:13px !important
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
select{
background-color: #9c27b0 !important;
color: beige;
}
.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: #e91e63 !important;
}
.bgColor{
color: #f44336 !important;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Predict and Win - Add Points</title>

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

		<h3>Predict and Win - Add Points</h3>
		
		<a href="<c:url value="/viewMatchPoints?matchid={{matchId}}" />" class="w3-btn w3-ripple">&#10004;View All Predictions</a>

		<h5 class="displaySuccessMessageClass" ng-show="checkDate(matchDate) == 2">More points more fun... Enjoy Bumper Wednesdays!!!</h5>
		
		<h5 class="displaySuccessMessageClass" ng-show="checkFinalsDate(matchDate) == 1">Predict more for the PlayOffs and get a chance to be at the TOP !!!</h5>

<br></br>

<form>
		<table class="w3-table w3-bordered w3-striped">
			<th>Match</th>
			<th>Match Date</th>
			<th>Rules</th>
			<th>Select Team</th>
			<th>Points Invested</th>
			<th>Time Left</th>
			<th>Actions</th>

			<tr>
				<td><b>{{ matchDetails }}</b></td>
				<td>{{ matchDate | jsDate | date}} ({{matchTime | jsTime}})</td>
				<!-- <td ng-init="setRuleId(x.id)">{{x.ruleName}}</td> -->
				<td>
					<select ng-model="rulesArray" ng-options="x.ruleName for x in rulesJson" ng-change="selectedRule(rulesArray)">
					<option value="">-- Select Rule--</option>
					</select>
				</td>
				<td><select ng-model="teamArray" ng-options="o for o in teamName" ng-change="selectedTeam(teamArray)">
					<option value="">-- Select Team--</option>
					</select>
				</td>		
				<td ng-if="checkDate(matchDate) == 3">
					<select ng-model="pointsArray"
					ng-options="option.pointSel for option in points" ng-change="selectedPoints(pointsArray)">
					<option value="">-- Select Points--</option>
					</select>
				</td>
				
				<td ng-if="checkDate(matchDate) ==2">
					<select ng-model="pointsArraySpecial"
					ng-options="option.pointSel for option in pointsSpecial" ng-change="selectedPoints(pointsArraySpecial)">
					<option value="">-- Select Points--</option>
					</select>
				</td>

				<td ng-if="checkDate(matchDate) ==1">
					<select ng-model="pointsArrayFinals"
					ng-options="option.pointSel for option in pointsFinals" ng-change="selectedPoints(pointsArrayFinals)">
					<option value="">-- Select Points--</option>
					</select>
				</td>

			    <td ng-hide="true">{{matchTime | filter: computeTime(matchDate, matchTime)}}</td>
			    <td ng-hide="checkTimer(matchDate, matchTime)"> <b>{{ remainingTime|durationview }} </b></td>
			    <td ng-show="checkTimer(matchDate, matchTime)"> Times Up !!!</td>
						
				
				<td>
					<button ng-disabled="rule || team || sPoints ||timeLeft" ng-hide="timeLeft" ng-click="addPoints()"
					class="w3-btn w3-ripple">&#9998; Add</button>
					<button ng-disabled="rule || team || sPoints ||timeLeft" ng-show="timeLeft"
					class="w3-btn w3-ripple">&#9998; Add</button>					
				</td>	 		
			</tr>

		</table>
		
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
</form>		
		<br>			

		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Your selection updated successfully !!!</h4>
		<h4 ng-show="displayErrMessage" class="displaySuccessMessageClass">{{errMessagetext}}</h4>
		
	
	<section ng-show="ajaxSuccessResponse">	

	<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">Please view your selection(s) below...</h4>

	<table class="w3-table w3-bordered w3-striped">
		<th>No</th>
		<th>Match</th>
		<th>Match Date</th>
		<th>Selected Rule</th>
		<th>Selected Team</th>
		<th>Points Invested</th>
		<th>Points Indicative</th>
		
		<tr ng-repeat="x in dataAfterAdd">
		    <td><b>{{ $index + 1 }}</b></td>
    		<td><b>{{x.matchId.matchDetails}}</b></td>
    		<td><b>{{x.matchId.matchDate | jsDate | date}} ({{x.matchId.matchTime | jsTime}})</b></td>
    		<td><b>{{x.ruleId.ruleName}}</b></td>
    		<td><b>{{x.ruleValue}}</b></td>
    		<td><b>{{x.pointsInvested}}</b></td>
    		<td class="bgColor"><b>{{x.indicativePoints}}</b></td>
    	</tr>
	</table> 

<br>

		</section>
		</div>
	
<div id="getMatchId"hidden="true"><%=session.getAttribute("matchId")%></div>
<!--  		
<div id="pieChartDialogBox" title="Pie Chart View"></div>
<div id="barChartDialogBox" title="Bar Chart View"></div>

<button class="w3-btn w3-ripple" id="pieChartOpener"> &#9998; Click to view the pie graphs </button>
<button class="w3-btn w3-ripple" id="barChartOpener"> &#9998; Click to view the bar graphs </button>	
-->
	
	<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$rootScope' ,'$interval','$timeout', 'datetime', '$http', function($scope,$rootScope,$interval,$timeout,datetime, $http) {

	$scope.rule = true;
	$scope.team = true;
	$scope.sPoints = true; 
	$scope.timeLeft = false; 
	$scope.ajaxSuccessResponse = false;
	$scope.cancelTimer;
	$scope.matchId = "<%= session.getAttribute("matchId")%>";
	
	$scope.matchDetails;
	$scope.matchDate;
	$scope.matchTime;
	$scope.teamName;
	
	$scope.selectedRuleId;
	$scope.selectedTeamName;
	$scope.selectedPointsInv;
	$scope.selectedPointsInd;
	
	$scope.tempImagePieChartRule1;
	$scope.tempImagePieChartRule2;
	$scope.tempImageBarChartRule1;
	$scope.tempImageBarChartRule2;
	
	$scope.displayAddMessage = false;
	$scope.displayErrMessage=false;
	
	$scope.errMessagetext;
	
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
		
	
    $scope.points = [
            {pointSel : "5"},
            {pointSel : "10"},
            {pointSel : "15"},
            {pointSel : "20"},
            {pointSel : "25"},
            ];

    $scope.pointsSpecial = [
                            {pointSel : "50"},
                            {pointSel : "100"},
                            {pointSel : "150"},
                            {pointSel : "200"},
                            ];

    $scope.pointsFinals = [
                            {pointSel : "50"},
                            {pointSel : "75"},
                            {pointSel : "150"},
                            {pointSel : "200"},
                            ];


    var processTimeRemaining = function (x) {
    	$scope.remainingTime = datetime.getRemainigTime(x);
    }
    
    var tick = function () {    	       
        $scope.cancelTimer = $timeout(tick, 1000);
        if($scope.remainingTime<1400 || $scope.remainingTime<0){
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
       
    
    $scope.checkDate = function (matchDate) {
    	var matchDateString  = matchDate;
        if(matchDateString){
            var formattedMatchDate = new Date( matchDateString.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
        	var now = new Date();
    	 if(((formattedMatchDate.getDate()==07) && (now.getDate()==07))
    			|| ((formattedMatchDate.getDate()==08) && (now.getDate()==08))
				|| ((formattedMatchDate.getDate()==10) && (now.getDate()==10))
					|| ((formattedMatchDate.getDate()==12) && (now.getDate()==12)) ){
    		return 1;
    	}
    	else if((formattedMatchDate.getDay()==3)){
    		return 2;
    	}else{
    		return 3;
    	}            	
       }
    }

    $scope.checkFinalsDate = function (matchDate) {
    	var matchDateString  = matchDate;
        if(matchDateString){
            var formattedMatchDate = new Date( matchDateString.replace( /(\d{2})-(\d{2})-(\d{4})/, "$2/$1/$3"));
        	var now = new Date();
    	if(((formattedMatchDate.getDate()==07) && (now.getDate()==07))
    			|| ((formattedMatchDate.getDate()==08) && (now.getDate()==08))
    				|| ((formattedMatchDate.getDate()==10) && (now.getDate()==10))
    					|| ((formattedMatchDate.getDate()==12) && (now.getDate()==12)) ){
    		return true;
    	}
    	else{
    		return false;
    	}            	
       }
    }
  
		    $scope.selectedRule = function (mySelectRule) {
				$scope.ajaxSuccessResponse = false;
				$scope.displayAddMessage = false;
				$scope.displayErrMessage = false;		    	
		    	if(mySelectRule){
		    		$scope.selectedRuleId = mySelectRule.id;
		    		$scope.rule = false;
		    	}
		    	else{
		    		$scope.rule = true;
		    		$scope.selectedRuleId = "";
		    	}            	
		    }

            $scope.selectedTeam = function (mySelectTeam) {
				$scope.ajaxSuccessResponse = false;
				$scope.displayAddMessage = false;
				$scope.displayErrMessage = false;		    	            	
            	if(mySelectTeam){
            		$scope.selectedTeamName = mySelectTeam;
            		$scope.team = false;
            	}
            	else{
            		$scope.team = true;
            		$scope.selectedTeamName = "";
            	}            	
            }
            $scope.selectedPoints = function (mySelectPoints) {
				$scope.ajaxSuccessResponse = false;
				$scope.displayAddMessage = false;
				$scope.displayErrMessage = false;		    	            	
 				if(mySelectPoints){
            		$scope.selectedPointsInv = mySelectPoints.pointSel;		
 					$scope.sPoints = false;
            	}
            	else{
            		$scope.sPoints = true;
            		$scope.selectedPointsInv =  "";
            	} 				
            }          

            $scope.addPoints = function () {
            	$scope.selectedRuleId;
            	$scope.selectedTeamName;
            	$scope.selectedPointsInv;
            	
				var url = "playResult/add";
				var dataObj= {
						ruleId : $scope.selectedRuleId,
						ruleValue : $scope.selectedTeamName,
						pointsInvested : $scope.selectedPointsInv,
						matchId : parseInt($scope.matchId)
					};	
				var res = $http.post(url,dataObj);
				res.success(function(response){
					if(response){
						$scope.ajaxSuccessResponse = true;
						$scope.displayAddMessage = true;
						$scope.displayErrMessage = false;
						
						$scope.dataAfterAdd = response;
						
						/* $scope.tempImagePieChartRule1 = "testChart.png?matchId=$scope.matchId&ruleId=1";
						$scope.tempImagePieChartRule2 = "testChart.png?matchId=$scope.matchId&ruleId=2";
						$scope.tempImageBarChartRule1 = "testBarChart.png?ruleId=1&matchId=$scope.matchId";
						$scope.tempImageBarChartRule2 = "testBarChart.png?ruleId=2&matchId=$scope.matchId"; */
					
					//$scope.apply();
					}else{
						$scope.ajaxSuccessResponse = false;
						$scope.displayAddMessage = false;
						$scope.displayErrMessage = true;
						$scope.errMessagetext = "Time is passed !!!"					
					}
				});
				res.error(function(err){
					console.log(err);					
					$scope.ajaxSuccessResponse = false;
					$scope.displayAddMessage = false;
					$scope.displayErrMessage = true;
					$scope.errMessagetext = "Oops..Some issue while adding points. Please try again or contact Admin !!!";			
				});            	
            }            
            
/*            var calJsonIndPoints = function () {
           	$scope.selectedRuleId;
        	$scope.selectedTeamName;
        	$scope.selectedPointsInv;
        	
        	if($scope.selectedRuleId && $scope.selectedTeamName && $scope.selectedPointsInv){
        		
				var url = "playResult/add";
				$http({
					method: "POST",
					url : url,
					params: {
						selRuleIdAjax : $scope.selectedRuleId,
						selTeamNameAjax : $scope.selectedTeamName,
						selPointsInvAjax : $scope.selectedPointsInv,
					}					
				}).success(function(response){
					alert("success indicative");
				}).error(function(err){
					alert("err indicative");
				}); 
        		
        	}
           } */

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
