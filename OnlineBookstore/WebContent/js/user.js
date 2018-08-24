// Shows the information for a book for unregistered users.

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