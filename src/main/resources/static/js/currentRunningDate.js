$(document).ready(function() {
  clockUpdate();
  setInterval(clockUpdate, 1000);
})

function clockUpdate() {
  var date = new Date();
  var months = ["JAN", "FEB", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUG", "SEP", "OCT", "NOV", "DEC"];
  var days = ["SUN", "MON", "TUE", "WED", "THUR", "FRI", "SAT"];

  $('.digital-clock').css({'color': 'green', 'fontSize':'15px'});

  function addZero(x) {
    if (x < 10) {
      return x = '0' + x;
    } else {
      return x;
    }
  }

  function twelveHour(x) {
    if (x > 12) {
      return x = x - 12;
    } else if (x == 0) {
      return x = 12;
    } else {
      return x;
    }
  }

var month = months[date.getMonth()];
var day = days[date.getDay()];
var year = date.getFullYear();
var h = addZero(twelveHour(date.getHours()));
var min = addZero(date.getMinutes());
var s = addZero(date.getSeconds());

  $('.digital-clock').text(day+'-'+month +'-'+year+'    '+h + ':' + min + ':' + s)

}