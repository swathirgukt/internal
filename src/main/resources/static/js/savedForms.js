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
 function searchSaveEmployees(){
  $('#employeeDetails').html("");
if(document.getElementById('employeeName').value!=""){
    console.log("====");
 var response = makeAJAXCall('/searchEmployeeDetails','empdata');
     response.done(function(responseData){
         if(responseData){
             $("#searchSaveEmployeeResult").html(responseData);
         }
     });
     }
 if(document.getElementById('employeeId').value != "") {
    document.empdata.action="/searchEmployeeDetails";
 	document.empdata.submit();
 }
 }