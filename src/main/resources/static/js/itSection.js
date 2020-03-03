
// Tax slab add button event handeler function
$(document).on('click', "button[name='addTaxSlabButton']", function() {
                         removeMessages();
                         var rowIndex = $('#taxSlabTable tr').length-1;

                         var newTaxSlabRow = $("<tr>");
                         newTaxSlabRow.append("<td style='padding:10px'>From</td><td> <input name='incomeTaxSlabVOS["+rowIndex+"].minIncome' type='text'></td>");
                         newTaxSlabRow.append("<td>To</td><td><input name='incomeTaxSlabVOS["+rowIndex+"].maxIncome' type='text'></td>");
                         newTaxSlabRow.append("<td>Tax Rate</td> <td><input name='incomeTaxSlabVOS["+rowIndex+"].taxRate' type='text'></td>");
                         newTaxSlabRow.append("<td><button class='glyphicon glyphicon-remove' name='taxSlabRemoveButton'></button></td></tr>");

                         $('#taxSlabTable tr:last').before(newTaxSlabRow);

                         $('#taxSlabTable tr:last').find('input:first').prop('name','incomeTaxSlabVOS['+(rowIndex+1)+'].minIncome');
                         $('#taxSlabTable tr:last').find('input:last').prop('name','incomeTaxSlabVOS['+(rowIndex+1)+'].taxRate');
});


function addRebate() {
    removeMessages();
    var rowsCount = $('#rebateTable tr').length;

    var newRebateRow = $('<tr>');
    newRebateRow.append("<td style='padding:10px'>Name</td><td><b>:</b></td><td><input class='inputfield' name='rebateVOS["+rowsCount+"].rebateName' type='text' style='width:70px'></td>");
    newRebateRow.append("<td>Income&nbsp;Limit</td><td><b>:</b></td><td><input class='inputfield' name='rebateVOS["+rowsCount+"].rebateApplySalary' type='text' style='width:70px'></td>");
    newRebateRow.append("<td>Amount</td><td><b>:</b></td><td><input class='inputfield' name='rebateVOS["+rowsCount+"].rebateAmount' type='text' style='width:70px'></td>");
    newRebateRow.append("<td style='padding:10px'><button type='button' class='submitButton' name='rebateRemoveButton'><b>Remove</b></button></td>");

    $("#rebateTable").append(newRebateRow);
}


function showAddSectionRow(){
    removeMessages();
    $("#iTSectionTableDiv table:first").show();
    $('#addNewSectionBtn').prop('disabled', true);
}


function addSection(){
            var sectionCount = $("#iTSectionTableDiv table:not(:first)").length;
            var sectionName =  $('#sectionNameInput').val();
            if(sectionName == '' || sectionName == null) {
                 $('#sectionNameError').text("Provide section name").css('color','red');
                 return;
            }

            var sectionTable = $('<table width="100%" class="table_text_style">');
            var row1 = $('<tr>');
            var row2 = $('<tr>');
            var row3 = $('<tr>');
            var span = $('<span name="sectionError">');

            row1.append('<td><b><input type="text" style="border:0px none;" value="'+sectionName+'" readonly="true" name="taxSectionForms['+sectionCount+'].sectionName" ></b></td><td style="padding:10px"><b><button type="button" onclick="addSectionDeclaration($(this))">Add</button></b></td>');
            row2.append('<td style="padding:10px">Section&nbsp;Limit</td>]<td><input class="inputfieldsection" type="text" name="taxSectionForms['+sectionCount+'].sectionLimit"></td>');
            row2.append('<td><button style="float: right; padding-right: 10px; margin-right: 30px;" class="submitButton" name="sectionRemoveButton" type="button">Remove&nbsp;Section</button></td>');
            row3.append('<td style="padding:10px"><b><input class="inputfieldsection" type="text" name="taxSectionForms['+sectionCount+'].taxSectionDeclarations[0].subSectionName"></b></td>');
            row3.append('<td><button class="submitButton" type="button" name="subSectionRemoveButton"><b>Remove</b></button></td>');

            sectionTable.append(span);
            sectionTable.append(row1);
            sectionTable.append(row2);
            sectionTable.append(row3);

            $("#iTSectionTableDiv").append(sectionTable);

            $("#iTSectionTableDiv table:first").hide();
            $('#addNewSectionBtn').prop('disabled', false);
            removeMessages();
}

function cancelAddSection(){
          removeMessages();
          $("#iTSectionTableDiv table:first").hide()
          $('#addNewSectionBtn').prop('disabled', false);

}

function addSectionDeclaration(addButton){
           removeMessages();
           var sectionCount = addButton.closest('table').index()-1;
           var sectionDeclarationCount = addButton.closest('table').find('tr').length-2;
           var sectionDeclareRow = $('<tr>');
           sectionDeclareRow.append('<td style="padding:10px"><input class="inputfieldsection" type="text" name="taxSectionForms['+sectionCount+'].taxSectionDeclarationVOS['+sectionDeclarationCount+'].subSectionName"></td>');
           sectionDeclareRow.append('<td><button class="submitButton" type="button" name="subSectionRemoveButton"><b>Remove</b></button></td>');
           addButton.closest('table').append(sectionDeclareRow);
}


function retrieveFinancialYear(){
    removeMessages();
    var response = makeAJAXCall('/incomeTaxSections/retrieveFinancialYear', 'financialYearForm');
    response.done(function (responseData) {
        if (responseData) {
            $("#iTSectionsBody").html(responseData);
        }
    });
}

function submitFinancialYear(){
    $('#financialYearForm').prop('action','/incomeTaxSections/saveOrUpdate');
    $('#financialYearForm').submit();
}

// Save button click event handler function
$(document).on('click', "button[name='financialYearSaveButton']", function() {
                       removeMessages();
                       if(!isValidFinancialYearForm()){
                            return;
                        }
                        $('#hiddenForm input').remove();
                        submitFinancialYear();
});

// Update button click event handler function
$(document).on('click', "button[name='financialYearUpdateButton']", function() {
                       removeMessages();
                       if(!isValidFinancialYearForm()){
                            return;
                        }
                        submitFinancialYear();
});


function isValidFinancialYearForm(){
    var error = true;
    $('#taxSlabTable input').each(function(){
        if($(this).val() == null || $(this).val() == '' ){
            error = false;
            $('#taxSlabError').text('Please fill all tax slabs').css('color','red');
        }
    });
    $('#rebateTable input').each(function(){
        if($(this).val() == null || $(this).val() == '' ){
            error = false;
            $('#rebateError').text('Please fill all rebates').css('color','red');
        }
    });

    $('#iTSectionTableDiv table:not(:first)').each(function(){
        $(this).find('input').each(function(){
            if($(this).val() == null || $(this).val() == '' ){
                  error = false;
                  $('#sectionError').text('Please fill all sections').css('color','red');
            }
        });

    });

    return error;
}

function removeMessages(){
    $('#duplicateError').text('');
    $('#success').text('');
    $('#noDataError').text('');
    $('#sectionNameError').text('');
    $('#sectionNameInput').val('');
    $('#taxSlabError').text('');
    $('#rebateError').text('');
    $('#sectionError').text('');
}


// remove button functions

$(document).on('click', "button[name='taxSlabRemoveButton']", function() {
                        $(this).closest('tr').remove();

//code to maintain index for data binding after removing tax slab, but raising some issue so thats why commented
/*                        var rowIndex=0;
                        for(; rowIndex<$('#taxSlabTable tr').length-1; rowIndex++){
                         $('#taxSlabTable tr').eq(rowIndex).find('input').eq(0).prop('name','incomeTaxSlabVOS['+rowIndex+'].minIncome');
                         $('#taxSlabTable tr').eq(rowIndex).find('input').eq(1).prop('name','incomeTaxSlabVOS['+rowIndex+'].taxRate');
                         $('#taxSlabTable tr').eq(rowIndex).find('input').eq(2).prop('name','incomeTaxSlabVOS['+rowIndex+'].taxRate');
                        }

                        $('#taxSlabTable tr:last').find('input:first').prop('name','incomeTaxSlabVOS['+rowIndex+'].minIncome');
                        $('#taxSlabTable tr:last').find('input:last').prop('name','incomeTaxSlabVOS['+rowIndex+'].taxRate');*/
});

$(document).on('click', "button[name='rebateRemoveButton']", function() {
                        $(this).closest('tr').remove();
});

$(document).on('click', "button[name='subSectionRemoveButton']", function() {
                        $(this).closest('tr').remove();
});

$(document).on('click', "button[name='sectionRemoveButton']", function() {
                        $(this).closest('table').remove();
});