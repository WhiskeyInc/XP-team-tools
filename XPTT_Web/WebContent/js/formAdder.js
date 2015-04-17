var formDisplayed = false;
function showDateModificationForm(divName, eventName) {
	if(formDisplayed == false){
		var newdiv = document.createElement('div');
		newdiv.innerHTML = "<br>" + "<form action='TimelineController' method='post' role='form'>"+
		"<div class='form-group'>"+
		"<label for='eventDate'>Change the date to your event!</label>"+
		"<div class='input-group' id = 'eventDate'>"+
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
		"<div class='btn-group btn-group-xs' role='group'>"+
		"<button class='btn btn-primary' type='submit'>Change!</button>"+
		"<button class='btn btn-success' type='button' onclick='history.go(0)'>Back!</button>"+
		"</div>"+
		"</form>";
		document.getElementById(divName).appendChild(newdiv);
		formDisplayed = true;
	}
}

function showParticipantAdditionForm(divName, eventName) {
	if(formDisplayed == false){
		var newdiv = document.createElement('div');
		newdiv.innerHTML = "<br>" + "<form action='TimelineController' method='post' role='form'>"+
		"<div class='form-group'>"+
		"<label for='participantAddition'>Add a partcipant to your event!</label>"+
		"<div class='input-group'>"+ 
			"<input type='text' class='form-control' name='participant' placeholder='Name'>"+
		"</div>"+
		"</div>"+
		"<input type='hidden' name='event' value='"+eventName+"'>"+
		"<input type='hidden' name='action' value='addParticipant'>"+
		"<div class='btn-group btn-group-xs' role='group'>"+
		"<button class='btn btn-primary' type='submit'>Add!</button>"+
		"<button class='btn btn-success' type='button' onclick='history.go(0)'>Back!</button>"+
		"</div>"+
		"</form>";
		document.getElementById(divName).appendChild(newdiv);
		formDisplayed = true;
	}
}

function showDeleteConfirmForm(divName, eventName) {
	if(formDisplayed == false){
		var newdiv = document.createElement('div');
		newdiv.innerHTML = "<br>" +
		"<div class='alert alert-danger'><strong>Warning: this is final!</strong> Are you really sure you want to delete this event?</div>"+
		"<form action='TimelineController' method='post' role='form'>"+
		"<input type='hidden' name='event' value='"+eventName+"'>"+
		"<input type='hidden' name='action' value='deletion'>"+
		"<div class='btn-group btn-group-xs' role='group'>"+
		"<button class='btn btn-danger' type='submit'>Delete!</button>"+
		"<button class='btn btn-success' type='button' onclick='history.go(0)'>Back!</button>"+	
		"</div>"+
		"</form>";
		document.getElementById(divName).appendChild(newdiv);
		formDisplayed = true;
	}
}