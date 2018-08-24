// Checks the usertype and changes the home tab to have the correct html page.
$(document).ready(function() {
	var check;
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			
			if (responseText == '0')
				{
				check = 0;
				window.location.href = "login.html";
				}
			else
				{
				$.ajax({
					type: "POST",
					url: "BookstoreServlet",
					data: {
						"changeHome" : "changeHome"
					}, dataType: "json",
					async:"false",
					success: function(responseText) {
						$("#home").attr("href", responseText);
					}
				});
			}
		}
	});
});

// Rates a book within a transaction.

$(document).on('click', '.rate', function () {
	var rate = $(this).parent().find(".rateSelect").val();
	var order = $(this).parent().find(".order").val();
	console.log(order);
	console.log(rate);
	console.log($(this).val());
	$.ajax({
		type: "POST",
		url: "CustomerServlet",
		data: {
		"rateBook" : $(this).val(),
		"rating": rate,
		"order": order
		}, async:"false",
		success: function (responseText) {
			if (responseText == '1')
				{
				alert("Successfully rated a book.");
				location.reload();
				}
			else
				{
				alert("Failed to rate a book.");
				}
		}
	});
});

// Changes the status of an order.

$(document).on('click', '.shipButton', function () {
	var status = $(this).parent().find(".statusSelect").val();
	$.ajax({
		type: "POST",
		url: "ShippingServlet",
		data: {
		"orderChange" : $(this).val(),
		"status": status
		}, async:"false",
		success: function (responseText) {
			if (responseText == '1')
				{
				alert("Successfully changed order status.");
				location.reload();
				}
			else
				{
				alert("Failed to change order status.");
				}
		}
	});
});