function makeAJAXCall(requestUrl,formId) {
    return jQuery.ajax({
        url: requestUrl,
        data: jQuery('#'+formId).serialize(),
        type: "POST",
        error: function(msg,err,exc){
            console.log("#error: "+err);
        }
    });
}

function PopupCenter(pageURL, title,w,h) {
		var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		var targetWin = window.open (pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}

function sendPayslipMail(requestUrl,id){
    $("#sendMail"+id).html("");
    jQuery.ajax({
            url: requestUrl,
            type: "GET",
            success: function(){
            $("#sendMail"+id).html("&#10004;").css("color","green").css("font-size","15px").css("font-weight","bold");
            },
            error: function(msg,err,exc){
                console.log("#error: "+err);
            }
    });
}

function submitIncentive(){

    var error = false;
    var incentiveDate = new Date($("#incentiveDate").val());

    $("#success").text("");
    $("#incentiveDateError").text("");
    $("#incentiveTable span").remove();

    if($('#incentiveDate').val() == ''){
        $("#incentiveDateError").text("Please select date");
        $("#incentiveDate").focus();
        return;
    }else if(isNaN(incentiveDate.getTime())){
        $("#incentiveDateError").text("Please provide valid date");
        $("#incentiveDate").focus();
        return;
    }

    $("#incentiveTable input").each(function(){
        if($(this).val()==null || $(this).val()==""){
            $(this).after("<span style='color:red;margin-left:20px;'>Provide incentive amount</span>");
            error = true;
        }
    });

    if(error){return;}

    $("#IncentiveForm").submit();
}

function submitApproveLeaveForm(){

    var fromDate = new Date($("#fromDate").val());
    var toDate = new Date($("#toDate").val());
    var totalLeaves = parseFloat($("#totalNumberOfAbsentDays").val());
    var selectedLeaves = parseFloat($("#casualLeave").val())+parseFloat($("#sickLeave").val())+parseFloat($("#compensatoryLeave").val())+parseFloat($("#lop").val());


    $("#fromDateError").text("");
    $("#toDateError").text("");
    $("#leaveDaysError").text("");
    $("#success").text("");
    if($("#fromDate").val()==""){
        console.log("###Blank from Date");
        $("#fromDateError").text("Please select date");
        $("#fromDate").focus();
        return;
    }else if(isNaN(fromDate.getTime())){
        console.log("###NaN from date");
        $("#fromDateError").text("Please provide valid date");
        $("#fromDate").focus();
        return;
    }
    if($("#toDate").val()==""){
        console.log("###Blank to Date");
        $("#toDateError").text("Please select date");
        $("#toDate").focus();
        return;
    }else if(isNaN(toDate.getTime())){
        console.log("###NaN to Date");
        $("#toDateError").text("Please provide valid date");
        $("#toDate").focus();
        return;
    }

    if($("#totalNumberOfAbsentDays").val()=="" || $("#totalNumberOfAbsentDays").val()==null|| totalLeaves==0){
        $("#leaveDaysError").text("Please provide leave days");
        return;
    }

    if(totalLeaves != selectedLeaves){
        $("#leaveDaysError").text("Total leaves must be equal to selected leaves");
        return;
    }

    $("#approveLeaveForm").submit();
}


function searchDepartment() {
    var response = makeAJAXCall("/department/search", 'departmentForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#departmentResult").html(responseData);
        }
    });
}

function searchPeripheral() {
    var response = makeAJAXCall("/peripheral/search", 'peripheralForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#peripheralResult").html(responseData);
        }
    });
}

function searchEmployeeLeaveReport() {
        $("#fromDateError").text("");
        $("#toDateError").text("");

        var fromDate = new Date($('#fromDate').val());
        var toDate = new Date($('#toDate').val());

        if($('#fromDate').val() == ''){
            $("#fromDateError").text("Please select date").css("color","red");
            $("#fromDate").focus();
            return;
        }else if(isNaN(fromDate.getTime())){
            $("#fromDateError").text("Please provide valid date").css("color","red");
            $("#fromDate").focus();
            return;
        }

        if($('#toDate').val() == ''){
            $("#toDateError").text("Please select date").css("color","red");
            $("#toDate").focus();
            return;
        }else if(isNaN(toDate.getTime())){
            $("#toDateError").text("Please provide valid date").css("color","red");
            $("#toDate").focus();
            return;
        }


    var response = makeAJAXCall("/employeeLeaveReport", 'employeeLeaveReportForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#employeeLeaveReportResult").html(responseData);
        }
    });
}

function searchAllEmployeeLeaveReport() {
        $("#fromDateError").text("");
        $("#toDateError").text("");

        var fromDate = new Date($('#fromDate').val());
        var toDate = new Date($('#toDate').val());

        if($('#fromDate').val() == ''){
            $("#fromDateError").text("Please select date").css("color","red");
            $("#fromDate").focus();
            return;
        }else if(isNaN(fromDate.getTime())){
            $("#fromDateError").text("Please provide valid date").css("color","red");
            $("#fromDate").focus();
            return;
        }

        if($('#toDate').val() == ''){
            $("#toDateError").text("Please select date").css("color","red");
            $("#toDate").focus();
            return;
        }else if(isNaN(toDate.getTime())){
            $("#toDateError").text("Please provide valid date").css("color","red");
            $("#toDate").focus();
            return;
        }


    var response = makeAJAXCall("/allEmployeeLeaveReport", 'allEmployeeLeaveReportForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#allEmployeeLeaveReportResult").html(responseData);
        }
    });
}


function submitGenerateSalaryForm(){
    if(hasErrorsInDates()){
        return;
    }

    if($("#SalaryForm").prop("id")=="SalaryForm")
        $("#SalaryForm").submit();

    if($("#generateAllSalariesForm").prop("id")=="generateAllSalariesForm")
        $("#generateAllSalariesForm").submit();
}


function searchAllESalaryEmployee() {

    if(hasErrorsInDates()){
        return;
    }

    var response = makeAJAXCall("/searchAllESalaryEmployee", 'generateAllSalariesForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#eSalaryResult").html(responseData);
        }
    });
}

function hasErrorsInDates(){
    $("#mailSent").text("");
    $("#error").text("");
    $("#fromDateError").text("");
    $("#toDateError").text("");

    var fromDate = new Date($('#fromDate').val());
    var toDate = new Date($('#toDate').val());
    var error = false;

    if($('#fromDate').val() == ''){
        $("#fromDateError").text("Please select date").css("color","red");
        $("#fromDate").focus();
        error = true;
    }else if(isNaN(fromDate.getTime())){
        $("#fromDateError").text("Please provide valid date").css("color","red");
        $("#fromDate").focus();
        error = true;
    }

    if($('#toDate').val() == ''){
        $("#toDateError").text("Please select date").css("color","red");
        $("#toDate").focus();
        error = true;
    }else if(isNaN(toDate.getTime())){
        $("#toDateError").text("Please provide valid date").css("color","red");
        $("#toDate").focus();
        error = true;
    }
    return error;
}


function searchEmployeeSalaryHitory(){
    var response = jQuery.ajax({
            url: "/salaryHistory/search",
            data: jQuery('#salaryHistoryForm').serialize(),
            type: "GET",
            error: function(msg,err,exc){
                console.log("#error: "+err);
            }
        });
    response.done(function (responseData) {
        if (responseData) {
            $("#employeeSalaryHistoryResult").html(responseData);
        }
    });
}


function saveDepartment() {

    var error= false;

    $("#deptNoError").text("");
    $("#managerNoError").text("");
    $("#phoneNoError").text("");
    $("#deptNameError").text("");
    $("#locationError").text("");

     if($("#deptNo").val()==null || $("#deptNo").val()==""){
            $("#deptNoError").text("Department number is required");
            error = true;
         }

     if($("#mngrNo").val()==null || $("#mngrNo").val()==""){
            $("#managerNoError").text("Manager number is required");
            error = true;
        }

     if($("#phoneNo").val()==null || $("#phoneNo").val()==""){
            $("#phoneNoError").text("Phone number is required");
            error = true;
        }

     if($("#department").val()==null || $("#department").val()==""){
            $("#deptNameError").text("Departent name is required");
            error = true;
        }

     if($("#location").val()==null || $("#location").val()==""){
            $("#locationError").text("Location is required");
            error = true;
        }

     if(error){
        return;
        }

    $("#departmentForm").prop("action","/department/save");
    $("#departmentForm").submit();
}
function savePeripheral() {

    $("#peripheralNameError").text("");
    $("#peripheralTypeError").text("");
    $("#peripheralModelError").text("");
    $("#purchaseDateError").text("");
    $("#peripheralBrandError").text("");
    $("#serialNumberError").text("");
    $("#warrantyDateError").text("");
    $("#peripheralStatusError").text("");

    var purchaseDate = new Date($("#yearOfPurchase").val());
    var warrantyDate = new Date($("#warrantyDate").val());

    if($("#peripheralName").val()==null || $("#peripheralName").val()==""){
        $("#peripheralNameError").text("Please provide name");
        $("#peripheralName").focus();
        return;
    }
    if($("#type").val()==null || $("#type").val()==""){
        $("#peripheralTypeError").text("Please provide type");
        $("#type").focus();
        return;
    }
    if($("#model").val()==null || $("#model").val()==""){
        $("#peripheralModelError").text("Please provide model");
        $("#model").focus();
        return;
    }
    if($("#yearOfPurchase").val()==null || $("#yearOfPurchase").val()==""){
        $("#purchaseDateError").text("Please select purchase date");
        $("#yearOfPurchase").focus();
        return;
    }else if(isNaN(purchaseDate.getTime())){
        $("#purchaseDateError").text("Please provide valid purchase date");
        $("#yearOfPurchase").focus();
        return;
    }
    if($("#brand").val()==null || $("#brand").val()==""){
        $("#peripheralBrandError").text("Please provide brand");
        $("#brand").focus();
        return;
    }
    if($("#serialNumber").val()==null || $("#serialNumber").val()==""){
        $("#serialNumberError").text("Please provide serial");
        $("#serialNumber").focus();
        return;
    }
    if($("#warrantyDate").val()==null || $("#warrantyDate").val()==""){
        $("#warrantyDateError").text("Please select warranty date");
        $("#warrantyDate").focus();
        return;
    }else if(isNaN(warrantyDate.getTime())){
        $("#warrantyDateError").text("Please provide valid warranty date");
        $("#warrantyDate").focus();
        return;
    }
    if($("#status").val()==null || $("#status").val()==""){
        $("#peripheralStatusError").text("Please provide status");
        $("#status").focus();
        return;
    }

    document.peripheralForm.action = "/peripheral/save";
    document.peripheralForm.submit();
}

function saveSalaryDecider(){
    var response = jQuery.ajax({
            url: "/saveSalaries",
            type: "GET",
            error: function(msg,err,exc){
                console.log("#error: "+err);
            }
        });
    response.done(function (responseData) {
        if (responseData) {
            $("#saveSalariesResult").text(responseData);
        }
    });
}

function getLeaveBalance(){
    var response = makeAJAXCall("/findLeaveBalance",'approveLeaveForm');
    response.done(function(responseData){
        if(responseData){
            $("#leaveBalanceResult").html(responseData);
        }
    });
}

function findEmployeeLeaves(){
    $("#success").text("");

    var response = makeAJAXCall("/findEmployeeLeaves",'employeeLeavesForm');
    response.done(function(responseData){
        if(responseData){
            $("#leavesResult").html(responseData);
        }
    });
}
function saveCheque()
{	document.chequeForm.action="/saveChequeDetails";
	document.chequeForm.submit();
}
function searchChequeDetails()
{
var response = makeAJAXCall("/searchCheques", 'chequeForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#chequeSearchResult").html(responseData);
        }

    });
}

function updateMyDetails()
{	document.updateForm.action="/saveEmployee";
	document.updateForm.submit();
}

function getFormtData() {
    var response = makeAJAXCall("/searchEmployee", 'searchEmployeeForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#employeeSearchResult").html(responseData);
        }
    });
}

function searchEmployeeStatus() {
     var response = makeAJAXCall("/searchEmployeeStatus", 'statusEmployeeForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#statusSearchResult").html(responseData);
         }
     });
 }

 function createEmployee()
 {

    var x = document.forms["createEmployeeForm"]["first_name"]
    var y = document.forms["createEmployeeForm"]["lastName"]
    var z = document.forms["createEmployeeForm"]["department"]
    var a = document.forms["createEmployeeForm"]["temporaryAddress"]
    var b = document.forms["createEmployeeForm"]["datepicker"]
    var c = document.forms["createEmployeeForm"]["bank_Name"]
    var d = document.forms["createEmployeeForm"]["bank_AC"]
    var e = document.forms["createEmployeeForm"]["per_Address"]
    var f = document.forms["createEmployeeForm"]["employee_Id"]
    var g = document.forms["createEmployeeForm"]["designation"]
    var h = document.forms["createEmployeeForm"]["official_email"]
    var i = document.forms["createEmployeeForm"]["personal_email"]
    var j = document.forms["createEmployeeForm"]["mobile_No"]
    var k = document.forms["createEmployeeForm"]["emergency_Contact"]
    var l = document.forms["createEmployeeForm"]["datepicker"]
    var m = document.forms["createEmployeeForm"]["role_Auth"]

    var error = false;
    if(x.value == "") {
        console.log("come in x");
        document.getElementById("empFirstName").innerHTML="employee First Name Required";
        error = true;
    }
    if(y.value == "") {
        console.log("come in y");
        document.getElementById("empLastName").innerHTML="employee last Name Required";
        error = true;
    }
    if(z.value=="select") {
        console.log("come in z");
        document.getElementById("dept").innerHTML="select department field";
        error = true;
    }
    if(a.value == "") {
        console.log("come in a");
        document.getElementById("tempAddress").innerHTML="temporary address required";
        error = true;
    }
    if(b.value == "") {
        console.log("come in b");
        document.getElementById("dob").innerHTML="DOB required";
        error = true;
    }
    if(c.value == "") {
             console.log("come in c");
             document.getElementById("bankName").innerHTML="Bank name required";
             error = true;
         }
     if(d.value == "") {
                  console.log("come in d");
                  document.getElementById("bankAc").innerHTML="Bank account number required";
                  error = true;
              }
     if(e.value == "") {
                       console.log("come in e");
                       document.getElementById("perAddress").innerHTML="Permanent address required";
                       error = true;
                   }
     if(f.value == "") {
                            console.log("come in f");
                            document.getElementById("emp_Id").innerHTML="Employee ID required";
                            error = true;
                        }
     if(g.value == "") {
                                 console.log("come in g");
                                 document.getElementById("role").innerHTML="Designation required";
                                 error = true;
                             }
    if(h.value == "") {
                                      console.log("come in h");
                                      document.getElementById("officialemail").innerHTML="official email required";
                                      error = true;
                                  }
     if(i.value == "") {
                                           console.log("come in i");
                                           document.getElementById("personalemail").innerHTML="personal email required";
                                           error = true;
                                       }
     if(j.value == "") {
                                               console.log("come in j");
                                               document.getElementById("mobileNo").innerHTML="mobile Number  required";
                                               error = true;
                                           }
     if(k.value == "") {
                                               console.log("come in k");
                                               document.getElementById("emergencyContact").innerHTML="emergency Contact required";
                                               error = true;
                                           }
    if(l.value == "") {
                                                   console.log("come in l");
                                                   document.getElementById("joinDate").innerHTML="join date required";
                                                   error = true;
                                               }
    if(m.value == "select") {
                                                   console.log("come in m");
                                                   document.getElementById("roleAuth").innerHTML="Role required";
                                                   error = true;
                                               }


    if(error){return;}

    document.createEmployeeForm.action="/updateEmployeeController";
    document.createEmployeeForm.submit();

 }

 function monthlySalaryReport()
  {
      var x = document.forms["monthlySalaryForm"]["salaryDate"]
  var error = false;
     if(x.value == "") {
         console.log("come in x");
         document.getElementById("salDate").innerHTML="please select date";
         error = true;
     }

     if(error){return;}

  var response = makeAJAXCall("/monthlySalaryReport", 'monthlySalaryForm');
      response.done(function (responseData) {
          if (responseData) {
              $("#monthlySalaryResult").html(responseData);
          }

      });
  }

  function basicSalarySearch()
    {
    var response = makeAJAXCall("/basicSalaryDetailsReport", 'basicSalary');
        response.done(function (responseData) {
            if (responseData) {
                $("#basicSearchResult").html(responseData);
            }

        });
    }


function addRow(){
    var count = $('#incentiveTable tr').length-1;
    var tableRow="<tr><td style='padding:5px'><select style='width: 200px;height: 23px;border-radius:5px' name='incentivesVOList["+count+"].employeeVO.Id' id='employeeId"+count+"'></select></td><td><input name='incentivesVOList["+count+"].incentiveAmount' type='text' style='width: 200px;height: 23px;' onkeypress='return allowRealNo(event,this.value)' maxlength='12''> </td></tr></table>";
    $("#incentiveTable").append(tableRow);
    $('#employeeId option').each(function(){
        $('#employeeId'+count).append("<option value='"+$(this).attr('value')+"'>"+$(this).text()+"</option>");
    });
}
function removeRow(){
    var rows=$('#incentiveTable tr').length;
    if(rows>2){
       $("#incentiveTable tr:last").remove();
    }
}

function searchIncentive() {

    var incentiveDate = new Date($("#incentiveDate").val());
    if($("#incentiveDate").val()==""){
        $("#incentiveDateError").text("Please select date");
        $("#incentiveDate").focus();
        return;
    }else if(isNaN(incentiveDate.getTime())){
        $("#incentiveDateError").text("Please provide valid date");
        $("#incentiveDate").focus();
        return;
    }

    var response = makeAJAXCall('/incentive/search','incentiveForm');
        response.done(function (responseData) {
            if (responseData) {
                $("#searchIncentiveResults").html(responseData);
            }
        });
}
