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

function appendTable(){
    var tableRow="<table name='remove' width='100%'><tr><td style='padding:5px'><select style='width: 200px;height: 23px;border-radius:5px'><option value=''>--SELECT--</option><option value=''>IEPL079</option><option value=''>IEPL0518</option></select></td><td><input name='incentive_amount' type='text' style='width: 200px;height: 23px;'> </td></tr></table>";
    $("#addTable").append(tableRow);
}
function removeTable(){
    var tableName=$('#addTable table:last').attr('name');
    if(tableName=="remove"){
       $("#addTable table:last").remove();
    }
}
