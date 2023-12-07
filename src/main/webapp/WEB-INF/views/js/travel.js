$.ajaxSetup({
	cache: false,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	}
});

function displayData(response) {
    $('#list-articles').empty();
    $('#list-dates').empty();

    response.data.forEach(function(object) {
        $('#list-articles').append('<hr class="dropdown-divider">' 
        	+ '<li class="list-group-item text-center bg-info">' 
        	+ '<a class="text-decoration-none text-light" href="http://localhost:8080/epic-guys/articles/view/article/' + object.id + '">' 
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

$(document).delegate('#btnSearch', 'click', function(event){
	event.preventDefault();
	var input = document.getElementById("labelSearch").value;
	
	$.ajax({
		url: 'http://localhost:8080/epic-guys/articles/search/travel/' + input,
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	},
    	error: function() {
			$('#list-articles').empty();
   			$('#list-dates').empty();
		}
	});
});

$(document).ready(function() {
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/articles/subject/travel',
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	}
	});
});