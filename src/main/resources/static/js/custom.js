function makeAJAXCall(requestUrl,formId) {
    return jQuery.ajax({
        url: requestUrl,
        data: jQuery('#'+formId).serialize(),
        type: "POST"
    });
}

function submitSearch() {
    var response = makeAJAXCall("/department/search", 'departmentForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#departmentResult").html(responseData);
        }
    });
}

function submitSave() {
    document.departmentForm.action = "/department/save";
    document.departmentForm.submit();
}
function reset() {
    document.department.reset();
}
