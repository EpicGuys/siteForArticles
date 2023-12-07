$.ajaxSetup({
	cache: false,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	}
});

function displayData(response) {
    $('#title').val(response.data.title);
    $('#text').val(response.data.text);
    $('#inputGroupSelect').val(response.data.subject);
}

function ArticleDTO(title, subject, text) {
            this.title = title;
            this.subject = subject;
            this.text = text;
}

$(document).delegate('#btnUpdate', 'click', function(event){
	event.preventDefault();
	
	var articleId = $('#articleId').text();
	
	var titleBuff = $('#title').val();
    var subjectBuff = $('#inputGroupSelect').val();
    var textBuff = $('#text').val();
    
    var articleDTO = new ArticleDTO(titleBuff, subjectBuff, textBuff);
	
	if(titleBuff != '' && textBuff != '') {
		$.ajax({
			url: 'http://localhost:8080/epic-guys/articles/writer/edit/' + articleId,
    		method: 'PUT',
    		dataType: 'json',
    		data: JSON.stringify(articleDTO),
    		success: function() {
				alert('Article was updated');
			},
			error: function() {
				alert('Article wasnt updated');
			}
		});
	
		window.setTimeout(function(){
    		window.location.href = 'http://localhost:8080/epic-guys/articles/writer/edit';
    	}, 1000);
	} else {
		alert('Fill in all the fields');
	}
});

$(document).delegate('#btnCancel', 'click', function(event){
	event.preventDefault();
	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/edit';
});

$(document).ready(function() {
	var articleId = $('#articleId').text();
	
	$.ajax({
    	url: 'http://localhost:8080/epic-guys/articles/' + articleId,
    	method: 'GET',
    	dataType: 'json',
    	success: function(data) {
        	displayData(data);
    	}
	});
});