function popupCenter(pageURL,title,w,h) {
		 console.log("in method");
		 console.log(document.getElementsByName("salaryDate")[0].value);
	         var url =pageURL+'?salaryDate='+document.getElementsByName("salaryDate")[0].value;
		     console.log(url);
		    var left = (screen.width/2)-(w/2);
			var top = (screen.height/2)-(h/2);
			window.open (url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		}

  function banksalaries(){
  console.log("in banksalaries");
  var url="/bankSalariesReport";
  var form=document.getElementById("froms");
  form.action=url+'?salaryDate='+document.getElementsByName("salaryDate")[0].value;
  form.submit();
   }

   function printData(){
       	document.getElementById("printButton").style.visibility = 'hidden';
       	window.print();
       }
