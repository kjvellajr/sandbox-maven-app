function init() {
	var apisToLoad;
	var loadCallback = function() {
		if (--apisToLoad == 0) {
			signin(true, userAuthed);
		}
	};

	apisToLoad = 2;
	var ROOT = "//" + window.location.host + "/_ah/api";
	gapi.client.load("eventApi", "v1", loadCallback, ROOT);
	gapi.client.load("oauth2", "v1", loadCallback);
}

function signin(mode, authorizedCallback) {
	gapi.auth.authorize({client_id: CLIENT_ID,
		scope: "https://www.googleapis.com/auth/userinfo.email", immediate: mode},
		authorizedCallback);
}

function userAuthed() {
	var request = gapi.client.oauth2.userinfo.get().execute(function(resp) {
		if (!resp.code) {
			$("signinButton").innerHTML = "Sign out";
		}
	});
}
