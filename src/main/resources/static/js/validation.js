// Function  to enter only positive int value ,Usage : onkeypress="return allowPositiveInt(event)"
function allowPositiveInt(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if ((charCode != 8) && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		return true;
	}
}
//allow the user to enter only monthly digits(1-31)
function allowOnlyMonthlyDigits(evt, id) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        var value = document.getElementById(id).value;
        if ((charCode == 46 && value.indexOf(".") == -1) || charCode == 8) {
            return true;
        }
        if(((charCode >= 48 && charCode <= 57))) {
            value = value+''+String.fromCharCode(charCode);
            if (value == '' || value <= 31) {
                return true;
            }
        }
        return false;
    }

// allow the  user to enter only real no only ,Usage : onkeypress="return allowRealNo(event,this.value)"
function allowRealNo(evt, strval) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if ((charCode != 8) && (charCode != 46) && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (((charCode == 46) && strval.indexOf(".") != -1)) {
			return false;
		} else {
			return true;
		}
	}
}

// Function  to enter only characters & spaces ,Usage : onkeypress="return allowCharactersAndSpaces(event)"
function allowCharactersAndSpaces(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if ((charCode == 8) || (charCode == 32) || (charCode >=65 &&  charCode <= 90) || (charCode >=97 &&  charCode <= 122) )
	 {
		return true;
	} else {
		return false;
 }
}