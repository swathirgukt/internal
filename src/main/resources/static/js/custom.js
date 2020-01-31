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

function saveDepartment() {
    document.departmentForm.action = "/department/save";
    document.departmentForm.submit();
}
function savePeripheral() {
    document.peripheralForm.action = "/peripheral/save";
    document.peripheralForm.submit();
}

function getLeaveBalance(){
    var response = makeAJAXCall("/findLeaveBalance",'approveLeaveForm');
    response.done(function(responseData){
        if(responseData){
            $("#leaveBalanceResult").html(responseData);
            console.log(responseData);
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
function submitSearch()
{
document.cheque.action="./searchChequeDetails.action";
document.cheque.submit();
}