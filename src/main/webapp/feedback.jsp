<!DOCTYPE html>
<html lang="en" data-ng-app="feedback">
<head>
	<title>Feedback</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/common.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="/js/feedback.js"></script>
	<script src="/js/api.js"></script>
	<script src="https://apis.google.com/js/client.js?onload=init"></script>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Feedback</a>
			<form class="navbar-form navbar-right">
				<div id="signinName"></div>
				<a href="javascript:void(0);" class="btn btn-default" id="signinButton">Sign in</a>
				<a href="javascript:void(0);" class="btn btn-default hidden" id="signoutButton">Sign out</a>
	</nav>
	<div class="container main-content">
		<form name="feedbackForm" data-ng-controller="FeedbackController as feedbackCtrl"
				data-ng-sumbit="feedbackCtrl.addFeedback()">
			<blockquote>
				<b>Category: {{feedbackCtrl.feedback.category}}</b>
				{{feedbackCtrl.feedback.text}}
				<cite>by: {{feedbackCtrl.feedback.email}}</cite>
			</blockquote>
			<select data-ng-model="feedbackCtrl.feedback.category">
				<option value="Website Design">Website Design</option>
				<option value="Bug">Bug</option>
				<option value="Other">Other</option>
			</select>
			<textarea data-ng-model="feedbackCtrl.feedback.text"></textarea>
			<label>by:</label>
			<span>{{feedbackCtrl.email}}</span>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>
