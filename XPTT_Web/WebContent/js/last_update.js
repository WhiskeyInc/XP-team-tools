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

var DOWArray = new initArray("Domenica", "Lunedì", "Martedì", "Mercoledì",
		"Giovedì", "Venerdì", "Sabato");
var MOYArray = new initArray("Gennaio", "Febbraio", "Marzo", "Aprile",
		"Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre",
		"Novembre", "Dicembre");

var LastModDate = new Date(document.lastModified);

document.write("Ultimo aggiornamento: ");
document.write(DOWArray[LastModDate.getDay()], " ");
document.write(LastModDate.getDate(), " ");
document.write(MOYArray[LastModDate.getMonth()], " ");
document.write(LastModDate.getYear() + 1900);
document.write(" alle ");
document.write(format(LastModDate.getHours()) + ":"
		+ format(LastModDate.getMinutes()));
document.write(".");
