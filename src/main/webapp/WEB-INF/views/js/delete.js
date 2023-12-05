$(document).delegate('#btnDelete', 'click', function(event){
	event.preventDefault();
	var articleId = $('#articleId').text();
	$.ajax({
		url: 'http://localhost:8080/epic-guys/articles/writer/delete/' + articleId,
    	method: 'DELETE',
    	dataType: 'json',
    	success: function() {
			alert('Article was deleted');
		},
		error: function() {
			alert('Have problems');
		}
	});
	
	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/delete';
});

$(document).delegate('#btnCancel', 'click', function(event){
	event.preventDefault();
	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/delete';
});