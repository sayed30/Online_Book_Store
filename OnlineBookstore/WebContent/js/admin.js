// Checks to see if the user is an admin.
$(document).ready(function loadPage() {
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			
			if (responseText == "SystemAdmin")
				{
					$.ajax({
						type: "POST",
						url: "BookstoreServlet",
						data: {
						"name" : "name"
						}, dataType: "json",
						async:"false",
						success: function(responseText) {
						var response = "Welcome, " + responseText;
						$("#welcome").append(response);
						}
					});
					$("body").show();
				}
			else
				{
				
				window.location.href = "login.html";
				}
		}
	});
});

// Logs the user out.

$(document).on('click', ".logout", function () { 
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
		"logout" : "logout"
		}, async:"false",
		success: function () {
			window.location.href = "index.html"
		}
	});
});