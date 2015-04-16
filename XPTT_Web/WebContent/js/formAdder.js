var formDisplayed = false;
function showForm(divName, eventName) {
	if(formDisplayed == false){
		var newdiv = document.createElement('div');
		newdiv.innerHTML = "<form action='TimelineController' method='post' role='form'>"+
		"<div class='form-group'>"+
		"<label for='eventDate'>Change the date to your event!</label>"+
		"<div class='input-group'>"+
			"<input type='text' class='form-control' name='eventDay' placeholder='Day' maxlength='2'>"+ 
				"<span class='input-group-addon'>/</span>"+ 
			"<input type='text' class='form-control' name='eventMonth' placeholder='Month' maxlength='2'>"+ 
				"<span class='input-group-addon'>/</span>"+ 
			"<input type='text' class='form-control' name='eventYear' placeholder='Year' maxlength='4'>"+ 
				"<span class='input-group-addon'>@</span>"+ 
			"<input type='text' class='form-control' name='eventHour' placeholder='Hour' maxlength='2'>"+ 
			"<span class='input-group-addon'>:</span>"+ 
			"<input type='text' class='form-control' name='eventMin' placeholder='Min' maxlength='2'>"+
		"</div>"+
		"</div>"+
		"<input type='hidden' name='event' value='"+eventName+"'>"+
		"<input type='hidden' name='action' value='changeDate'>"+
		"<button class='btn btn-primary btn-xl' type='submit'>Change!</button>"+
		"</form>";
		document.getElementById(divName).appendChild(newdiv);
		formDisplayed = true;
	}
}