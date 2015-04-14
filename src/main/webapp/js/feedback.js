(function(){
	var app = angular.module("feedback", []);
	app.controller("FeedbackController", function($scope) {
		var feedback = this;
		feedback.lines = {};

		$scope.addFeedback = function() {
			gapi.client.feedback.addFeedback(this.feedback).execute(function(resp) {
				console.log(resp);
				$scope.$apply();
			}
		};
	});
})();
