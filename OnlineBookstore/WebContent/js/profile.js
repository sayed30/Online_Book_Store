$(document).ready(function() {
	var check;
	// Checks if the user is logged in.
	$.ajax({
		type: "POST",
		url: "BookstoreServlet",
		data: {
			"checkLogin" : "checkLogin"
		}, dataType: "json",
		async:"false",
		success: function(responseText) {
			
			// Removes some links depending on usertype.
			if (responseText != "Customer")
				{
					$("#addressTab").remove();
					$("#creditTab").remove();
				}
			// Redirect to login.html if the user is not logged in.
			if (responseText == '0')
				{
				check = 0;
				window.location.href = "login.html";
				}
			// Update the home tab to go to the correct user homepage.
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
				
				// Sets the values for the profile page.
			  $.ajax({
			    method : "post",
			    url : "BookstoreServlet",
			    data : {
			      "viewProfile" : "viewProfile"
			    }, dataType : "json",
			    success : function(responseText) {
			      $("#fname").val(responseText.fname);
			      console.log(responseText);
			      $("#lname").val(responseText.lname);
			      $("#email").val(responseText.email);
			      $("#phone").val(responseText.phone);
			      if (responseText.subscribe == true)
			    	  {
			    	  	$("#sub").prop("checked", true);
			    	  }
			      else
			    	  {
			    	  	$("#sub").prop("checked", false);
			    	  }
			    },
			    error : function() {
			      alert("error occured");
			    }
			  });
			  $("body").show();
			}
		}
	});
});

// Saves the changes made to the user's profile.

$(document).on('click', "#save", function() {
		$("#messagePara").text("");
		if (($.trim($("#fname").val()) == '') || ($.trim($("#lname").val()) == '')
				|| ($.trim($("#phone").val()) == ''))
			{
				$("#messagePara").text("No inputs other than email should be left blank.");
			}
		else
			{
		var temp = $("#sub").is(':checked');
		$.ajax({
		method : "post",
		url : "BookstoreServlet",
		data : {
			"saveProfile" : "saveProfile",
			"fname" : $("#fname").val(),
			"lname" : $("#lname").val(),
			"phone" : $("#phone").val(),
			"sub" : temp
		  	},	
		  	success: function(responseText) {
			  if(responseText == "Success") {
				  $("#messagePara").text("Successfully updated your profile.");
			  } else {
				  $("#messagePara").text("Failed to update your profile.");
			  	}
			  },
		  error: function() {
			  $("#messagePara").text("Failed to update your profile.");
		  }
		  });
	}
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
