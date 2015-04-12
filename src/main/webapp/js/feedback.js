(function(){
	var app = angular.module("feedback", []);
	app.controller("FeedbackController", function() {
		this.feedback = {};

		this.addFeedback = function() {
			this.feedback = {};
		};
	});
	function getEvents() {
		gapi.client.feedback.getFeedback().execute(function(resp) {
			console.log(resp);
		});
	}
})();
