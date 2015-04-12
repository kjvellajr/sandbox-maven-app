(function(){
	var app = angular.module("myhistory", []);
	app.controller("MyhistoryController", function() {
		this.events = data;
	});
	app.controller("PanelController", function() {
		this.tab = 0;
		this.selectTab = function(value) {
			this.tab = value;
		};
		this.isSelected = function(value) {
			return this.tab === value;
		};
	});

	function getEvents() {
		gapi.client.event.getEvents().execute(function(resp) {
			console.log(resp);
		});
	}
	var data = [
		{
			id: 0,
	    		name: "first event",
			lastTime: '1388123412323',
			canAdd: true,
			canEdit: true
		},
		{
			id: 1,
			name: "second event",
			lastTime: '1388123412323',
			canAdd: true,
			canEdit: true
		},
		{
			id: 2,
			name: "thrid event",
			lastTime: '1388123412323',
			canAdd: true,
			canEdit: true
		}
	];
})();
