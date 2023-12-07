$.ajaxSetup({
	cache: false,
	headers: {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
	}
});
        
function ArticleDTO(title, subject, text) {
            this.title = title;
            this.subject = subject;
            this.text = text;
}

$(document).delegate('#btnBack', 'click', function(event){
	event.preventDefault();
	window.history.back();
});

$(document).delegate('#btnWrite', 'click', function(event){
	event.preventDefault();
	
	var titleBuff = $('#title').val();
    var subjectBuff = $('#inputGroupSelect').val();
    var textBuff = $('#text').val();
    
    var articleDTO = new ArticleDTO(titleBuff, subjectBuff, textBuff);
	
	$.ajax({
		url: 'http://localhost:8080/epic-guys/articles/writer/add',
    	method: 'POST',
    	dataType: 'json',
    	data: JSON.stringify(articleDTO),
    	success: function() {
			alert('Article was added');
		},
		error: function() {
			alert('Article wasnt added');
		}
	});
	
	window.setTimeout(function(){
    	window.location.href = 'http://localhost:8080/epic-guys/articles/writer/viewAll';
    }, 1000);
});