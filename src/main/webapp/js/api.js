var CLIENT_ID = "31301222987-hcr0mbatof5g5j7kbo28jrkuenunn900.apps.googleusercontent.com";
var SCOPES = "https://www.googleapis.com/auth/userinfo.email";

function init() {
	$("#signoutButton").click(function() {
		if (isTest()) {
			alert("cannot log out of local host");
		} else {
			$("#signinName").html("");
			$("#signinButton").removeClass("hidden");
			$("#signoutButton").addClass("hidden");
			gapi.auth.signOut();
		}
	});
	$("#signinButton").click(function() {
		signin(false, userAuthed);
	});
	var apisToLoad;
	var loadCallback = function() {
		if (--apisToLoad == 0) {
			signin(true, userAuthed);
		}
	};

	apisToLoad = 2;

	var ROOT;
	if (isTest()) {
		ROOT = "http://localhost:8080/_ah/api";
	} else {
		ROOT = "https://symmetric-span-89521.appspot.com/_ah/api";
	}

	gapi.client.load("event", "v1", loadCallback, ROOT);
	gapi.client.load("oauth2", "v2", loadCallback);
}

function isTest() {
	return (document.location.hostname == "localhost");
}

function signin(mode, authorizedCallback) {
	gapi.auth.authorize({client_id: CLIENT_ID, scope: SCOPES, immediate: mode, cookie_policy: 'single_host_origin'}, authorizedCallback);
}

function userAuthed() {
	var request = gapi.client.oauth2.userinfo.get().execute(function(resp) {
		if (!resp.code) {
			$("#signinName").html(resp.name);
			$("#signinButton").addClass("hidden");
			$("#signoutButton").removeClass("hidden");
		}
	});
}

function getEvents() {
	gapi.client.event.getEvents().execute(
		function(resp) {
			console.log(resp);
		}
	);
}
