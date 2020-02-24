 function searchFEmployee() {
     var response = makeAJAXCall("/searchEmployeeSettlement", 'searchFEmployeeForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#settlementResult").html(responseData);
         }
     });
 }

 function submitCalculate(){
   var w=document.getElementById("selec").value;
   var x = document.forms["employeeSettlementForm"]["resignationDate"];
   var y = document.forms["employeeSettlementForm"]["relievingDate"];
   var z = document.forms["employeeSettlementForm"]["settlementDate"];

   if (w=='Resigned' && x.value == "" || y.value == "" || z.value == "" ){
           document.getElementById("demo").innerHTML="All * fields are Required";
           return false;
      }
   if(x.value > y.value){
            document.getElementById("demo6").innerHTML="ResignationDate must be less than RelievingDate";
            return false;
          }
   if(y.value > z.value){
            document.getElementById("demo9").innerHTML="RelievingDate must be less than SettlementDate";
            return false;
          }
   else  {
       document.employeeSettlementForm.action="/viewSettlement";
       document.employeeSettlementForm.setAttribute("target","_blank");
       document.employeeSettlementForm.submit();

   }
 }

 function saveEmployeeSettlement(){
    var w=document.getElementById("selec").value;
    var x = document.forms["employeeSettlementForm"]["resignationDate"];
    var y = document.forms["employeeSettlementForm"]["relievingDate"];
    var z = document.forms["employeeSettlementForm"]["settlementDate"];

    if (w=='Resigned' && x.value == "" || y.value == "" || z.value == "" ) {
      document.getElementById("demo").innerHTML="All * fields are Required";
      return false;
    }
    if(x.value > y.value){
      document.getElementById("demo6").innerHTML="ResignationDate must be less than RelievingDate";
      return false;
    }
    if(y.value > z.value){
      document.getElementById("demo9").innerHTML="RelievingDate must be less than SettlementDate";
      return false;
    }
    else{
          document.employeeSettlementForm.action="/saveSettlement";
          document.employeeSettlementForm.setAttribute("target","_self");
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
        }
        else if(selectedValue == 'Terminated')
        {
             $('#resignationDateId').hide();
             document.getElementById('RelevingDate').innerHTML = 'Termination';
        }
   }

