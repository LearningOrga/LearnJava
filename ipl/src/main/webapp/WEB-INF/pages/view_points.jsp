<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
   <script>
   $(function() {
	  	var test1 = $("#getMatchId").html();

	    $( "#pieChartDialogBox" ).dialog({
	        width: 1000,
	        height: 800,
	        open: function(event, ui)
	        {
	            var dialogBox1 = "<div><h3><b>Points Invested Charts</b></h3><table><tr><td colspan='2' align='center'>Summary Charts for Rule - 1. Winning Team</td></tr><tr>";
	            dialogBox1 = dialogBox1 +"<td 	align='center'><img alt='Total points invested in the match' src='testChart.png?ruleId=1&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
	            dialogBox1 = dialogBox1 +"</tr>	<tr><td colspan='2' align='center'>Summary Charts for Rule - 2. Toss Winning Team</td></tr><tr>";
	            dialogBox1 = dialogBox1 +"<td align='center'><img alt='Total points invested in the match' src='testChart.png?ruleId=2&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>"
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
	        width: 1200,
	        height: 800,
	        open: function(event, ui)
	        {
	        	console.log("test11as"+test1);
	            var dialogBox2 = "<h3><b>Points Invested Charts</b></h3><table><tr><td colspan='2' align='center'>Summary Charts for Rule - 1. Winning Team</td></tr><tr>";
	            dialogBox2 = dialogBox2 +"<td align='center'><img alt='Bar Chart - Points invested by everyone' src='testBarChart.png?ruleId=1&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
	            dialogBox2 = dialogBox2 +"</tr>	<tr><td colspan='2' align='center'>Summary Charts for Rule - 2. Toss Winning Team</td></tr><tr>";
	            dialogBox2 = dialogBox2 +"<td align='center'><img alt='Bar Chart - Points invested by everyone' src='testBarChart.png?ruleId=2&dateString=<%= new java.util.Date() %>&matchId="+test1+"'/></td>";
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
	      	//alert( $( "#pieChartDialogBox" ).html("test"));
	      $( "#pieChartDialogBox" ).dialog( "open" );
	    });
	    $( "#barChartOpener" ).click(function() {
	        $( "#barChartDialogBox" ).dialog( "open" );
	      });    
	  });


  </script>
<style>

table, th, td {
border: 2px solid bisque !important;
font-size: 13px !important;
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
<title>Predict and Win - View Points</title>

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
<br></br>
<div id="getMatchId" hidden="true"><%=session.getAttribute("matchId")%></div>
		
<div id="pieChartDialogBox" title="Pie Chart View"></div>
<div id="barChartDialogBox" title="Bar Chart View"></div>

<button class="w3-btn w3-ripple" id="pieChartOpener"> &#9998; Click to view the pie graphs </button>
<button class="w3-btn w3-ripple" id="barChartOpener"> &#9998; Click to view the bar graphs </button>

	<div ng-app="myApp" ng-controller="myCtrl" class="w3-container">

		<h3>Predict and Win - View Points</h3>
		
		<div><b>{{dataAfterView[0].matchId.matchDetails}} - {{dataAfterView[0].matchId.matchDate | jsDate | date}} ({{dataAfterView[0].matchId.matchTime | jsTime}})</b>
		</div>
		<br>
		
		<h4 ng-show="displayAddMessage" class="displaySuccessMessageClass">No one has predicted for this match yet.</h4>

<br>
<p>Predictions - Winning Team</p>

<table class="w3-table w3-bordered w3-striped">
<th>Contributor</th>
<th>Selected Rule</th>
<th>Selected Team</th>
<th>Points Invested</th>


		<tr ng-repeat="x in dataAfterView"  ng-show="checkRule1(x.ruleId.id)">

    		<td>{{x.userId.loginName}}</td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td ng-hide="!x.pointsInvested">{{x.pointsInvested}}</td>
    		<td ng-hide="x.pointsInvested">Selections not yet done</td>
    	</tr>
</table>

<br>
<p>Predictions - Toss Winning Team</p>

<table class="w3-table w3-bordered w3-striped" >
<th>Contributor</th>
<th>Selected Rule</th>
<th>Selected Team</th>
<th>Points Invested</th>


		<tr ng-repeat="x in dataAfterView" ng-show="checkRule2(x.ruleId.id)">
    		<td>{{x.userId.loginName}}</td>
    		<td>{{x.ruleId.ruleName}}</td>
    		<td>{{x.ruleValue}}</td>
    		<td ng-hide="!x.pointsInvested">{{x.pointsInvested}}</td>
    		<td ng-hide="x.pointsInvested">Selections not yet done</td>
    	</tr>
</table>
<br>

	</div>
		
	
<script>

var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$http', function($scope,$http) {
	$scope.displayAddMessage = false;
	
	$scope.matchId = "<%= session.getAttribute("matchId")%>";
	$scope.dataAfterView;
	
	//uncomment to call play result service
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
			$scope.dataAfterView = response;
		}else{
			$scope.displayAddMessage = true;
		console.log(response);					
		}
	}).error(function(err){
		console.log(err);
	});  
	
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