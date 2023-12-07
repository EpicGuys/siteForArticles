$.ajaxSetup({
	cache: false,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	}
});

$(document).delegate('#btnDelete', 'click', function(event){
	event.preventDefault();
	var articleId = $('#articleId').text();
	$.ajax({
		url: 'http://localhost:8080/epic-guys/articles/writer/delete/' + articleId,
    	method: 'DELETE',
    	dataType: 'json'
	});
	alert('Article was deleted');
	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/delete';
});

$(document).delegate('#btnCancel', 'click', function(event){
	event.preventDefault();
	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/delete';
});