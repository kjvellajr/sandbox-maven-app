<!DOCTYPE html>
<html lang="en" data-ng-app="myhistory">
<head>
	<title>My History</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/main.css"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Events</h1>
		</div>
		<div class="col-sm-12">
			<form action="/sign" method="post">
				<div><textarea name="content" rows="3" cols="60"></textarea></div>
				<div><input type="submit" value="Post Greeting"/></div>
   				 <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
			</form>
			<form action="/guestbook.jsp" method="get">
				<div><input type="text" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/></div>
				<div><input type="submit" value="Switch Guestbook"/></div>
			</form>
		</div>
	</div>
</body>
</html>
