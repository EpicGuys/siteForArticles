function displayData(response) {
    $('#list-articles').empty();
    $('#list-dates').empty();

    response.data.forEach(function(object) {
        $('#list-articles').append('<hr class="dropdown-divider">' 
        	+ '<li class="list-group-item text-center bg-info">' 
        	+ '<a class="text-decoration-none text-light" href="http://localhost:8080/epic-guys/articles/writer/delete/article/' + object.id + '">' 
        	+ object.title + '</a>'
        	+ '</li>' 
        	+ '<hr class="dropdown-divider">');
        	
        $('#list-dates').append('<hr class="dropdown-divider">' 
        	+ '<li class="list-group-item text-center bg-info text-light">' 
        	+ 'Date: ' + object.date
        	+ '</li>' 
        	+ '<hr class="dropdown-divider">');
        
    });
}

$(document).ready(function() {
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/articles/writer/view',
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	}
	});
});