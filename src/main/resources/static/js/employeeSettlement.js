 function searchFEmployee() {
     var response = makeAJAXCall("/searchEmployeeSettlement", 'searchFEmployeeForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#settlementResult").html(responseData);
         }
     });
 }

 function submitCalculate(){
   var x = document.forms["employeeSettlementForm"]["resignationDate"];
   var y = document.forms["employeeSettlementForm"]["relievingDate"];
   var z = document.forms["employeeSettlementForm"]["settlementDate"];

   if (x.value == "") {
     document.getElementById("demo1").innerHTML="ResignationDate Required";
     return false;
   }if(y.value == ""){
      document.getElementById("demo2").innerHTML="RelievingDate Required";
      return false;
   }
   if(z.value == ""){
         document.getElementById("demo3").innerHTML="SettlementDate Required";
         return false;
      }
   else{
       document.employeeSettlementForm.action="/viewSettlement";
       document.employeeSettlementForm.submit();
   }
 }

 function saveEmployeeSettlement(){
 var x = document.forms["employeeSettlementForm"]["resignationDate"];
    var y = document.forms["employeeSettlementForm"]["relievingDate"];
    var z = document.forms["employeeSettlementForm"]["settlementDate"];

    if (x.value == "" || y.value == "" || z.value == "" ) {
      document.getElementById("demo").innerHTML="All * fields are Required";
      return false;
    }if(x.value > y.value){
      document.getElementById("demo6").innerHTML="ResignationDate must be less than RelievingDate";
      return false;
    }if(y.value > z.value){
      document.getElementById("demo9").innerHTML="RelievingDate must be less than SettlementDate";
      return false;
    }
    else{
          document.employeeSettlementForm.action="/saveSettlement";
  	      document.employeeSettlementForm.submit();
     }
 }
  function goBack() {
    window.history.back();
  }
   function updateDatesRow(selectedValue){
        if(selectedValue == 'Resigned')
        {
            $('#resignationDateId').show();
            document.getElementById('RelevingDate').innerHTML = 'Relieving';
        }else if(selectedValue == 'Terminated')
        {
             $('#resignationDateId').hide();
             document.getElementById('RelevingDate').innerHTML = 'Termination';
        }
   }

