function displayData(response) {
    $('#title').val(response.data.title);
    $('#subject').val(response.data.suject);
    $('#text').val(response.data.text);
    $('#date').val(response.data.date);
}

$(document).delegate('#btnBack', 'click', function(event){
	event.preventDefault();
	window.history.back();
});

$(document).ready(function() {
	var id = $('#articleId');
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/articles/subject/sport' + id,
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	}
	});
});