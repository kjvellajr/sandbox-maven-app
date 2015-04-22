<!DOCTYPE html>
<html lang="en" data-ng-app="feedback">
<head>
	<title>Feedback</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/common.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>

<body data-ng-controller="FeedbackController as feedbackCtrl">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Feedback</a>
			<form name="loginForm" class="navbar-form navbar-right">
				<div id="signinName"></div>
				<a href="javascript:void(0);" class="btn btn-default" id="signinButton">Sign in</a>
				<a href="javascript:void(0);" class="btn btn-default hidden" id="signoutButton">Sign out</a>
			</form>
	</nav>
	<div class="container main-content">
		<ul>
			<li ng-repeat="comment in feedbackCtrl.comments">
				<blockquote>
					<b>Category: {{comment.category}}</b>
					<br>
					{{comment.text}}
					<br>
					<cite>by: {{comment.email}}</cite>
				</blockquote>
			</li>
		</ul>
		<form name="commentForm" data-ng-controller="CommentController as commentCtrl"
				data-ng-submit="commentForm.$valid && commentCtrl.addFeedback(feedbackCtrl)" novalidate>
			<!-- live preview -->
			<blockquote>
				<b>Category: {{commentCtrl.comment.category}}</b>
				<br>
				{{commentCtrl.comment.text}}
				<br>
				<cite>by: {{commentCtrl.comment.email}}</cite>
			</blockquote>
			<h4>Submit Feedback</h4>
			<div class="form-group">
				<select class="form-control" data-ng-model="commentCtrl.comment.category" required>
					<option value="Website Design">Website Design</option>
					<option value="Bug">Bug</option>
					<option value="Other">Other</option>
				</select>
			</div>
			<div class="form-group">
				<textarea class="form-control" data-ng-model="commentCtrl.comment.text" placeholder="Write a short comment..." required></textarea>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Submit" />
			</div>
		</form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="/js/feedback.js"></script>
</body>
</html>
