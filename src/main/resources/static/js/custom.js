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
    var response = makeAJAXCall("/employeeLeaveReport", 'employeeLeaveReportForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#employeeLeaveReportResult").html(responseData);
        }
    });
}

function searchAllEmployeeLeaveReport() {
    var response = makeAJAXCall("/allEmployeeLeaveReport", 'allEmployeeLeaveReportForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#allEmployeeLeaveReportResult").html(responseData);
        }
    });
}
function searchAllESalaryEmployee() {
    $("#mailSent").html("");
    $("#error").html("");
    var response = makeAJAXCall("/searchAllESalaryEmployee", 'generateAllSalariesForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#eSalaryResult").html(responseData);
        }
    });
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
    document.departmentForm.action = "/department/save";
    document.departmentForm.submit();
}
function savePeripheral() {
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
 var response = makeAJAXCall("/monthlySalaryReport", 'monthlySalaryForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#monthlySalaryResult").html(responseData);
         }

     });
 }


