<%@ include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<style>

table, th, td {
border: 2px solid bisque !important;
}
form{
border: 2px solid bisque !important;
margin-top:10px !important;
}

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
}

</style>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Predict and Win - Manage Users Admin</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>


</head>
<body ng-app="myApp" ng-controller="userCtrl">

<div class="w3-container">

<h3>Predict and Win - Manage Users Admin</h3>
<br>
<table class="w3-table w3-bordered w3-striped">
  <tr>
    <th>Edit User</th>
    <th>Login Name</th>
    <th>Login Role</th>
    <th>Login Status</th>
    <th>Total Points</th>
    <th>Golden Predict</th>
  </tr>
  <tr ng-repeat="user in users">
    <td>
     <button class="w3-btn w3-ripple" ng-click="editUser(user.id)">&#9998; Edit</button> 
    </td>
    <td>{{ user.loginName }}</td>
    <td>{{ user.loginRole }}</td>
    <td>{{ user.loginStatus }}</td>
    <td>{{ user.availablePoints }}</td>
    <td ng-hide="!user.goldenPredict">{{ user.goldenPredict }}</td>
    <td ng-hide="user.goldenPredict">Not yet predicted</td>
    <td ng-hide="true">{{ user.loginPass }}</td>
  </tr>
</table>
<br>
<!-- 
<button class="w3-btn w3-green w3-ripple" ng-click="editUser('new')">&#9998; Create New User</button>  -->

		<h4  ng-show="displayAddMessage" class="displaySuccessMessageClass">{{addMessageResult}}</h4>
		<h4  ng-show="displayUpdatedMessage" class="displaySuccessMessageClass">{{updateMessageResult}}</h4>
		

<form ng-hide="hideform">
  <h3 ng-show="edit"><b>Create New User:</b></h3>
  <h3 ng-hide="edit"><b>Edit User:</b></h3>
    <label>Login Name:</label>
    <input class="w3-input w3-border" type="text" ng-model="loginName" placeholder="First Name is the login name">
  <br>
    <label>Login Role:</label>
    <input class="w3-input w3-border" type="text" ng-model="loginRole"  placeholder="User or admin role">
  <br>
  <br>
    <label>Login Status:</label>
    <input class="w3-input w3-border" type="number" ng-model="loginStatus"  placeholder="Login status (1=Active, 0=Inactive)">
  <br>
    
    <label>Total Points:</label>
    <input class="w3-input w3-border" type="number" ng-model="availablePoints" placeholder="Total Points (Use scroller on the right to add)">
 <br>   
    <label>Golden Predict:</label>
    <!-- <input class="w3-input w3-border" type="text" ng-model="goldenPredict"  placeholder="Select a team who may win the finals">  -->
	<select class="w3-input w3-border" ng-model="goldenPredict" ng-options="x.teamName for x in teamJson" ng-change="selectedTeam(goldenPredict)">
		<option value="">Select a team who may win the finals</option> 
		</select>   
  <br>   
    <label>Password:</label>
    <input class="w3-input w3-border" type="password" ng-model="loginPass1"  placeholder="Password">   
 <br>
    <label>Repeat:</label>
    <input class="w3-input w3-border" type="password" ng-model="loginPass2" placeholder="Repeat Password">
  <br>
   <button class="w3-btn w3-green w3-ripple" ng-click="updateUsers()" ng-disabled="team || error || incomplete">&#10004; Save Changes</button> 
</form>

</div>

<script type="text/javascript">

//angular.module('myApp','$scope', '$http', []).controller('userCtrl', function($scope, $http) {
	
	var welcomeHome = angular.module("myApp", []);

	welcomeHome.controller('userCtrl', ['$scope', '$http', function($scope,$http) {
		
	
	$scope.loginName = '';
	$scope.loginRole = '';
	$scope.loginStatus='';
	$scope.availablePoints='';
	$scope.loginPass='';
	$scope.loginPass1 = '';
	$scope.loginPass2 = '';
	$scope.userId = '';
	$scope.goldenPredict = '';
	$scope.displayAddMessage = false;
	$scope.displayUpdatedMessage=false;
	$scope.addMessageResult;
	$scope.updateMessageResult;
	$scope.selectedTeamName;
	$scope.team = true;
	$scope.teamJson;
	
	//uncomment to run with json response
	var url = "user/all";
	$http.get(url).success(function(response){
		$scope.users = response;
		
	});
	
	var teamUrl = "team/all";
	$http.get(teamUrl).success(function(response){
		$scope.teamJson = response;
		
	}); 
	
	//uncomment to run with static response
/* 	$scope.users=[{"id":1,"loginName":"sudeepg","loginPass":"sudeepg","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0, "goldenPredict":"Kings XI Punjab (KXIP)"},{"id":2,"loginName":"jitendrah","loginPass":"jitendrah","loginRole":"ROLE_ADMIN","loginStatus":1,"availablePoints":500.0},{"id":3,"loginName":"ravig","loginPass":"ravig","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0},{"id":4,"loginName":"Ijlal","loginPass":"ij@bbin2015","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0},{"id":5,"loginName":"TEMP","loginPass":"test","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":1000.0},{"id":6,"loginName":"Aishwarya","loginPass":"ayrvhsia","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0},{"id":7,"loginName":"NeerajPriya","loginPass":"P@ss123","loginRole":"ROLE_USER","loginStatus":1,"availablePoints":500.0}];
	$scope.teamJson = [{"id":01,"teamName":"Kings XI Punjab (KXIP)","teamHomeVenue":null,"teamCaptain":"David Miller"},
		{"id":02,"teamName":"Delhi Daredevils (DD)","teamHomeVenue":null,"teamCaptain":"JP Duminy"},
		{"id":03,"teamName":"Mumbai Indians (MI)","teamHomeVenue":null,"teamCaptain":"Rohit Sharma"},
		{"id":04,"teamName":"Royal Challengers Bangalore (RCB)","teamHomeVenue":null,"teamCaptain":"Virat Kohli"},
		{"id":05,"teamName":"Kolkata Knight Riders (KKR)","teamHomeVenue":null,"teamCaptain":"Gautam Gambhir"},
		{"id":06,"teamName":"Sunrisers Hyderabad (SRH)","teamHomeVenue":null,"teamCaptain":"Shikhar Dhavan"},
		{"id":07,"teamName":"Rising Pune Supergiants (RPS)","teamHomeVenue":null,"teamCaptain":"MS Dhoni"},
		{"id":08,"teamName":"Gujarat Lions (GL)","teamHomeVenue":null,"teamCaptain":"Suresh Raina"}];  */
	

    $scope.selectedTeam = function (mySelectGoldenPredictTeam) {
	    	
    	if(mySelectGoldenPredictTeam){
    		$scope.selectedTeamName = mySelectGoldenPredictTeam.teamName;
    		$scope.team = false;
    	}
    	else{
    		$scope.team = true;
    		$scope.selectedTeamName = "";
    	}            	
    }
	
	
	$scope.edit = true;
	$scope.error = false;
	$scope.incomplete = false; 
	$scope.hideform = true; 
	$scope.editUser = function(id) {
	$scope.hideform = false;
	$scope.displayUpdatedMessage=false;
	$scope.displayAddMessage = false;	  
	  if (id == 'new') {
	    $scope.edit = true;
	    $scope.userId = '';	    
		$scope.loginName = '';
		$scope.loginRole = '';
		$scope.loginStatus=null;
		$scope.availablePoints=null;
		$scope.loginPass1 = '';
		$scope.loginPass2 = '';
		$scope.goldenPredict = '';
	    } else {
	    $scope.edit = false;
	    $scope.userId = $scope.users[id-1].id;
	    $scope.loginName = $scope.users[id-1].loginName;
	    $scope.loginRole = $scope.users[id-1].loginRole; 
	    $scope.loginStatus = $scope.users[id-1].loginStatus;
	    $scope.availablePoints = $scope.users[id-1].availablePoints;
	    $scope.loginPass1 = $scope.users[id-1].loginPass;
	    $scope.loginPass2 = $scope.users[id-1].loginPass;
	    $scope.goldenPredict = $scope.users[id-1].goldenPredict;
	   // $scope.teamJson[0].teamName = $scope.goldenPredict;
	  }
	};

	$scope.$watch('loginName',function() {$scope.test();});
	$scope.$watch('loginRole',function() {$scope.test();});
	$scope.$watch('loginStatus',function() {$scope.test();});
	$scope.$watch('availablePoints',function() {$scope.test();});
	$scope.$watch('loginPass1',function() {$scope.test();});
	$scope.$watch('loginPass2',function() {$scope.test();});
	
	

	$scope.test = function() {
		if ($scope.loginPass1 !== $scope.loginPass2) {
	    $scope.error = true;
	    } else {
	    $scope.error = false;
	  }
	  $scope.incomplete = false;
	  if (($scope.loginName.length==0) || ($scope.loginRole.length==0) || ($scope.loginStatus==null) || ($scope.availablePoints==null) || ($scope.loginPass1.length==0)) {
	     $scope.incomplete = true;
	  }
	};

	$scope.updateUsers = function() {
		var url;
		if($scope.edit){
			url = "user/add";
			$scope.displayUpdatedMessage=false;
			$scope.displayAddMessage = true;
		}
		 else{
			url = "user/update";
			$scope.displayAddMessage = false;
			$scope.displayUpdatedMessage=true;
		}


		$http({
			method: "GET",
			url : url,
			params: {
				selUserId : $scope.userId,
				selloginName : $scope.loginName,
				selloginRole : $scope.loginRole,
				selloginStatus : $scope.loginStatus,
				selavailablePoints : $scope.availablePoints,
				selloginPass : $scope.loginPass2,
				selgoldenPredict : $scope.selectedTeamName,
			}					
		}).success(function(response){
		if($scope.displayAddMessage){
			$scope.addMessageResult = "User "+$scope.loginName+" added successfully";
		}if($scope.displayUpdatedMessage){
			$scope.updateMessageResult = "User "+$scope.loginName+" updated successfully";
		}			
		}).error(function(err){
			alert("err::"+err);
			if($scope.displayAddMessage){
				$scope.addMessageResult = "Some issue in adding the user "+$scope.loginName+" ";
			}if($scope.displayUpdatedMessage){
				$scope.updateMessageResult = "Some issue in updating the user "+$scope.loginName+" ";
			}
		});            		
		
	}
	}]);


</script>

</body>
</html>
