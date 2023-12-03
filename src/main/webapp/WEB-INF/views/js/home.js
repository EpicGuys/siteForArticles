function displayData(response) {
    $('#list-articles').empty();

    response.data.forEach(function(object) {
        $('#list-articles').append('<hr class="dropdown-divider">' 
        	+ '<li class="list-group-item text-center bg-info">' 
        	+ '<a class="text-decoration-none text-light" href="http://localhost:8080/epic-guys/users/' + object.id + '">' 
        	+ object.nickname + '</a>'
        	+ '</li>' 
        	+ '<hr class="dropdown-divider">');
        	
        $('#list-dates').append('<hr class="dropdown-divider">' 
        	+ '<li class="list-group-item text-center bg-info text-light">' 
        	+ 'Date: ' + object.password
        	+ '</li>' 
        	+ '<hr class="dropdown-divider">');
        
    });
}

$(document).ready(function() {
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/users',
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	},
    	error: function() {
     		alert('Not success');
    	}
	});
});