// Updates the quantity for a book.
$(document).on('click', '.updatedQuantityButton', function () {
$.ajax({
type: "POST",
url: "AdminServlet",
data: {
  "isbn" : $(this).val(),
  "updateQuantity": $(this).parent().find(".updatedQuantity").val()
    },
success: function (responseText) {
	console.log(responseText);
if (responseText == '1') {
alert("Successfully updated book quantity.");
	location.reload();
}
else{
alert("Failed to updated book quantity.");
}
}
});
});