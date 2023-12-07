$.ajaxSetup({
	cache: false,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	}
});

function displayData(response) {
	$('#form').append('<div class="card-header">' + response.data.subject + '</div>'
		+ '<div class="card-body">'
		+ '<h5 class="card-title">' + response.data.title + '</h5>'
		+ '<p class="card-text">' + response.data.text + '</p>'
		+ '</div>'
		+ '<div class="card-footer text-muted">' + response.data.date + '</div>'
		+ '<button id="btnBack" type="button" class="btn btn-primary">Back</button>');
}

$(document).delegate('#btnBack', 'click', function(event){
	event.preventDefault();
	window.history.back();
});

$(document).ready(function() {
	var id = $('#articleId').text();
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/articles/' + id,
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	}
	});
});