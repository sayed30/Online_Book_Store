// Checks the usertype to make sure that the user is a Customer.
$(document).ready(function loadPage() {
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			if (responseText == "Customer")
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

// Toggles the focus onto an address on addressPage.ftl

$(document).on('click', ".address", function () {
	if ($(this).css("background-color") != "rgb(255, 255, 255)")
		{
			$(".address").css("background-color", "White");
			$("#editButton").val("");
			$("#deleteButton").val("")
			$("#street").val("");
			$("#city").val("");
			$("#state").val("GA");
			$("#zip").val("");
		}
	else
		{
			$(".address").css("background-color", "White");
			$(this).css("background-color", "Gray");
			$("#editButton").val($(this).find(".addressId").attr("value"));
			$("#deleteButton").val($(this).find(".addressId").attr("value"))
			$("#street").val($(this).find(".streetAddress").attr("value"));
			$("#city").val($(this).find(".cityAddress").attr("value"));
			$("#state").val($(this).find(".stateAddress").attr("value"));
			$("#zip").val($(this).find(".zipAddress").attr("value"));
		}
});

// Shows the information for a book.

$(document).on('click', ".view", function () {
	var isbn = $(this).val();
	if ($('#' + isbn).is(':visible'))
		{
			$('#' + isbn).hide(1000);
		}
	else
		{
			$('#' + isbn).show(1000);
		}
});