function setEmployeeId(empId){
    console.log(empId);
     document.getElementById('employeeId').value=empId;
 }
 function  financialYear(){
 var response=makeAJAXCall('/searchEmployeeDetails','empdata');
       response.done(function (responseData) {
             if (responseData) {
            $("#form16Pdfs").html(responseData);

                     }
         });

 }
 $( document ).ready(function() {
     $("#loading").hide();
     $("#itp_overlay").hide();
 });

 function searchSaveEmployees(){

  $('#employeeDetails').html("");

if(document.getElementById('employeeName').value!=""){
    console.log("====");
    $("#loading,#itp_overlay").show();
 var response = makeAJAXCall('/searchEmployeeDetails','empdata');
 $(document).ajaxStart(function(){
          $("#loading").css("display", "block");
        });
        $(document).ajaxComplete(function(){
          $("#loading").css("display", "none");
        });
     response.done(function(responseData){
         if(responseData){
         $("#loading,#itp_overlay").hide();
             $("#searchSaveEmployeeResult").html(responseData);
         }
     });
     }
 if(document.getElementById('employeeId').value != "") {
    document.empdata.action="/searchEmployeeDetails";
 	document.empdata.submit();
 }
 }