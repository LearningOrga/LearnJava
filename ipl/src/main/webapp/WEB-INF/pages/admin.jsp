<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HelloWorld Admin page</title>
</head>
<body>
	Dear
	<strong>${user}</strong>, Welcome to Admin Page.
	<a href="<c:url value="/logout" />">Logout</a>
	</p>
	
	Login Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/user/{userName}" />">/user/{userName}</a></li>
		<li><a href="<c:url value="/user/all" />">/user/all</a></li>
		<li><a href="<c:url value="/user/add/{loginName}/{loginPass}/{loginRole}/{enabled}/{points}" />">/user/add/{loginName}/{loginPass}/{loginRole}/{enabled}/{points}</a></li>
		e.g.localhost:8080/ipl/user/add/amar/amar/ROLE_USER/1/500
				
	</ul>
	
	Match Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/match/all" />">/match/all</a></li>
		<li><a href="<c:url value="/match/{matchId}" />">/match/{matchId}</a></li>
		<li><a href="<c:url value="/match/add/{matchDate}/{matchDay}/{matchTime}/{matchDetails}/{matchVenue}/{matchStatus}" />">/match/add/{matchDate}/{matchDay}/{matchTime}/{matchDetails}/{matchVenue}/{matchStatus}</a></li>
		e.g. localhost:8080/ipl/match/add/22-4-16/Saturday/8.00 PM (2:30pm GMT)/Gujarat Lions vs Kolkata Knight Riders/rajkot
	</ul>
	
	Match Result Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/matchResult/all" />">/matchResult/all</a></li>
		<li><a href="<c:url value="/matchResult/{matchId}" />">/matchResult/{matchId}</a></li>
		<li><a href="<c:url value="/matchResult/add/{matchId}/{ruleId}/{ruleResult}" />">/matchResult/add/{matchId}/{ruleId}/{ruleResult}</a></li>
		e.g.http://localhost:8080/ipl/matchResult/add/3/2/GUJ
	</ul>
	
	Player Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/player/all" />">/player/all</a></li>
		<li><a href="<c:url value="/player/{playerId}" />">/player/{playerId}</a></li>
		
	</ul>
	
	Play Result Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/playResult/all" />">/playResult/all</a></li>
		<li><a href="<c:url value="/playResult/{userId}" />">/playResult/{userId}</a></li>
		<li><a href="<c:url value="playResult/{matchId}/{ruleId}" />">playResult/{matchId}/{ruleId}</a></li>
		<li><a href="<c:url value="/reconcile/{matchId}/{ruleId}" />">/reconcile/{matchId}/{ruleId}</a></li>
		<li><a href="<c:url value="/playResult/add/{userId}/{ruleId}/{ruleValue}/{matchId}/{pointsInvested}/{indicativePoints}" />">/playResult/add/{userId}/{ruleId}/{ruleValue}/{matchId}/{pointsInvested}/{indicativePoints}</a></li>
	</ul>
	
	Rule Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/rule/all" />">/rule/all</a></li>
		<li><a href="<c:url value="/rule/{ruleId}" />">/rule/{ruleId}</a></li>
		
	</ul>
	
	
		Team Controller URLS 	

	<ul style="list-style-type: disc">
		<li><a href="<c:url value="/team/all" />">/team/all</a></li>
		<li><a href="<c:url value="/team/{teamId}" />">/team/{teamId}</a></li>
		
	</ul>
	
	<ul style="list-style-type: disc">
		<li>user_master</a></li>
		<li>team_master</a></li>
		<li>rule_master</a></li>
		<li>player_master</a></li>
		<li>match_master</a></li>
		<li>match_result</a></li>
		<li>play_result</a></li>
		
	</ul>
	

</body>
</html>