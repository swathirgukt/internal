 function searchFEmployee() {
     var response = makeAJAXCall("/searchEmployeeSettlement", 'searchFEmployeeForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#settlementResult").html(responseData);
         }
     });
 }

 function formValidation(){
      var flag=true;
      var w=document.getElementById("selec").value;
      var x = document.forms["employeeSettlementForm"]["resignationDate"];
      var y = document.forms["employeeSettlementForm"]["relievingDate"];
      var z = document.forms["employeeSettlementForm"]["settlementDate"];

      if (w=='Resigned' && x.value == "" || y.value == "" || z.value == "" ){
              document.getElementById("demo").innerHTML="All * fields are Required";
              flag=false;
         }
      if(x.value > y.value){
               document.getElementById("demo6").innerHTML="ResignationDate must be less than RelievingDate";
             flag=false;
             }
      if(y.value > z.value){
               document.getElementById("demo9").innerHTML="RelievingDate must be less than SettlementDate";
               flag=false;
             }
             return flag;
 }

 function submitCalculate(){
     if(formValidation()){
       $("#calculatebtn , #savebtn").attr("disabled", true);
       document.employeeSettlementForm.action="/viewSettlement";
       document.employeeSettlementForm.setAttribute("target","_blank");
       document.employeeSettlementForm.submit();
     }else
     {
       $("#calculatebtn , #savebtn").attr("disabled", false);
     }
 }
 function saveEmployeeSettlement(){
    if(formValidation()){
       $("#calculatebtn , #savebtn").attr("disabled", true);
       document.employeeSettlementForm.action="/saveSettlement";
       document.employeeSettlementForm.setAttribute("target","_self");
  	   document.employeeSettlementForm.submit();
    }else
    {
        $("#calculatebtn , #savebtn").attr("disabled", false);
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

