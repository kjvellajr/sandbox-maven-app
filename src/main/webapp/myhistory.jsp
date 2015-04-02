<!DOCTYPE html>
<html lang="en" data-ng-app="myhistory">
<head>
	<title>My History</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/css/myhistory.css"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="/js/myhistory.js"></script>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>My History</h1>
		</div>
		<div data-ng-controller="myhistoryCtrl as myhistory">
			<div class="col-sm-4" data-ng-repeat="event in myhistory.events">
				<h3>{{event.name}}</h3>
				{{event.lastTime | date:"MM/dd/yyyy @ h:mma"}}
				<button data-ng-show="event.canAdd">Add</button>
				<section data-ng-controller="panelCtrl as panel">
					<ul class="nav nav-pills">
						<li data-ng-class="{active: panel.isSelected(0)}">
							<a href data-ng-click="panel.selectTab(0)">Details</a>
						</li>
						<li data-ng-class="{active: panel.isSelected(1)}">
							<a href data-ng-click="panel.selectTab(1)">History</a>
						</li>
					</ul>
					<div class="panel" data-ng-show="panel.isSelected(0)">
						<h4>Details</h4>
					</div>
					<div class="panel" data-ng-show="panel.isSelected(1)">
						<h4>History</h4>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>
</html>
