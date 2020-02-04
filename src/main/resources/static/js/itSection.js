function taxSlabEdit() {
    $("#taxSlabSaveBtn").show();
    $("#taxSlabCancelBtn").show();
    $("#taxSlabEditBtn").hide();
    $("#taskSlabsTable input").prop("readonly", false).css('background-color', '#FFFFFF');
}
function taxSlabSave() {
    saveOrCancelTaxSlabs();
}
function taxSlabCancel() {
    saveOrCancelTaxSlabs();
}

function saveOrCancelTaxSlabs() {
    $("#taxSlabSaveBtn").hide();
    $("#taxSlabCancelBtn").hide();
    $("#taxSlabEditBtn").show();
    $("#taskSlabsTable input").prop("readonly", true).css('background-color', '#D3D3D3');
}
function addTaxSlab() {

    taxSlabEdit();
    $("#taskSlabsTable tr:last").before(" <tr> <td>From</td><td> <input name='' type='text'  ></td><td>To</td><td><input name='' type='text' ></td><td>Tax Rate</td> <td><input name='' type='text' ></td>  <td><button class='glyphicon glyphicon-remove' onclick='$(this).parent().parent().remove();'></button></td>  </tr>");
}
function removeTaxSlab(){
    $("#taskSlabsTable tr:last").remove();
}
function addRebate() {

    $("#addRebateTable").append("<tr> <td>Name</td><td><b>:</b></td>  <td><input class='inputfield' name='' type='text' value=''></td><td>Income&nbsp;Limit</td><td><b>:</b></td><td><input class='inputfield' name='' type='text' value=''></td><td>Amount</td><td><b>:</b></td> <td><input class='inputfield' name='' type='text' value=''></td> <td><button class='submitButton' name='rebateSave' onclick='saveRebate()'><b>Save<b></button></td><td><button class='submitButton' name='rebateEdit' onclick='editRebate()'> <b>Edit</b>  </button> </td> <td> <button class='submitButton' onclick='removeRebate()'> <b>Remove</b></button></td></tr>");
}
//Rebate
//rebate save
$(document).on('click', "button[name='rebateSave']", function() {
    $(this).closest('tr').find("input").each(function() {
        $(this).prop("readonly",true).css('background-color','#D3D3D3');
    });
});

//rebate edit
$(document).on('click', "button[name='rebateEdit']", function() {
    $(this).closest('tr').find("input").each(function() {
        $(this).prop("readonly",false).css('background-color','#FFFFFF');
    });
});

function removeRebate(){
    $("#addRebateTable tr:last").remove();
}

//Sections-U/s10