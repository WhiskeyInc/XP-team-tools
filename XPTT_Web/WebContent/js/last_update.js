// Script che scrive la data di ultima modifica

function initArray() {
	for (var i = 0; i < initArray.arguments.length; i++)
		this[i] = initArray.arguments[i];
}

function format(number) {
	if (number > 9) {
		return "" + number;
	} else {
		return "0" + number;
	}
}

var DOWArray = new initArray("Sunday", "Monday", "Tuesday", "Wednesday",
		"Thursday", "Friday", "Saturday");
var MOYArray = new initArray("January", "February", "March", "April",
		"May", "June", "July", "August", "September", "October",
		"November", "December");

var LastModDate = new Date(document.lastModified);

document.write("Last updated on: ");
document.write(DOWArray[LastModDate.getDay()], ", ");
document.write(LastModDate.getDate(), " ");
document.write(MOYArray[LastModDate.getMonth()], " ");
document.write(LastModDate.getYear() + 1900);
document.write(" at ");
document.write(format(LastModDate.getHours()) + ":"
		+ format(LastModDate.getMinutes()));
document.write(".");
