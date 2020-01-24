function makeAJAXCall(requestUrl,formId) {
    return jQuery.ajax({
        url: requestUrl,
        data: jQuery('#'+formId).serialize(),
        type: "POST"
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

function saveDepartment() {
    document.departmentForm.action = "/department/save";
    document.departmentForm.submit();
}

function savePeripheral() {
    document.peripheralForm.action = "/peripheral/save";
    document.peripheralForm.submit();
}
