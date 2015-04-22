(function(){
	var app = angular.module("feedback", []);
	app.controller("FeedbackController", function() {
		var comments = {};
	});
	app.controller("CommentController", function() {
		var comment = {};

		this.addFeedback = function(feedbackCtrl) {
			console.log(feedbackCtrl);
			console.log(feedbackCtrl.comments);
			feedbackCtrl.comments.push(this.comment);
			this.comment = {};
		};
	});
})();
