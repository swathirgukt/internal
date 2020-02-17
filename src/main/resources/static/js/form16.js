//Form16 Generation
function calculateTotalIncomeEarnedByEmployee() {
        var grossSalary = $('#incomeTable tr td input[name="grossSalary"]').val();
        grossSalary = grossSalary == '' ? 0 : parseFloat(grossSalary);
        var incentive = $('#incomeTable tr td input[name="form16GenerationForm.incentives"]').val();
        incentive = incentive == '' ? 0 : parseFloat(incentive);
        var plb = $('#incomeTable tr td input[name="form16GenerationForm.plb"]').val();
        plb = plb == '' ? 0 : parseFloat(plb);
        var reimbursement = $('#incomeTable tr td input[name="form16GenerationForm.reimbursement"]').val();
        reimbursement = reimbursement == '' ? 0 : parseFloat(reimbursement);
        var bonus = $('#incomeTable tr td input[name="form16GenerationForm.bonus"]').val();
        bonus = bonus == '' ? 0 : parseFloat(bonus);
        var others = $('#incomeTable tr td input[name="form16GenerationForm.others"]').val();
        others = others == '' ? 0 : parseFloat(others);
        var previousCompanyIncome = $('#incomeTable tr td input[name="form16GenerationForm.previousCompanyIncome"]').val();
        previousCompanyIncome = previousCompanyIncome == '' ? 0 : parseFloat(previousCompanyIncome);
        totIncome = parseFloat(grossSalary) + incentive + plb + reimbursement + bonus + others + previousCompanyIncome;
        if(grossSalary != null)
        {
            $('#earnedIncome').val(parseFloat(totIncome));
        }
    }

/*Form 16 Generation*/
function saveIncome(){
        $('#incomeTable tr td input').attr('readonly',true).css('background-color','#D3D3D3');
        $('#incomeTable tr td input[name="grossSalary"]').css('background-color','#f7941e');
        $('#incomeTable tr td input[name="form16GenerationForm.incomeEarnedByEmployee"]').css('background-color','#f7941e');
    }

function editIncome(){
        $('#incomeTable tr td input').attr('readonly',false).css('background-color','#FFFFFF');
        $('#incomeTable tr td input[name="grossSalary"]').css('background-color','#f7941e').attr('readonly',true);
        $('#incomeTable tr td input[name="form16GenerationForm.incomeEarnedByEmployee"]').css('background-color','#f7941e').attr('readonly',true);
    }

$(document).on('blur', '#incomeTable tr td input', function(){
            calculateTotalIncomeEarnedByEmployee();
        });

function retrieveSalaryInfoForForm16(){
            var overlay = new loadOverlay();
            overlay.show("MenuWrapper");
            $.ajax({url:"./retrieveSalaryInfoForForm16.action",
                    type: 'POST',
                    data: $('#form16GenerationData').serialize()
            }).done(function(data){
                $('#form16GenerationBody').html(data);
                placeHolderLoad();
                calculateTotalIncomeEarnedByEmployee();
                saveEmpInfo();
                overlay.hide();
            });
        }

function calculateTax(){
            var overlay = new loadOverlay();
            overlay.show("MenuWrapper");
            $.ajax({url:"./calculateTax.action",
                    type: 'POST',
                    data: $('#form16GenerationData').serialize()
            }).done(function(data){
                $('#form16GenerationBody').html(data);
                placeHolderLoad();
                calculateTotalIncomeEarnedByEmployee();
                saveEmpInfo();
                saveIncome();
                overlay.hide();
            });
        }

function saveEmployeeIncomeTax(){
            var overlay = new loadOverlay();
            overlay.show("MenuWrapper");
            $.ajax({url:"./saveEmployeeIncomeTax.action",
                    type: 'POST',
                    data: $('#form16GenerationData').serialize()
            }).done(function(data){
                $('#form16GenerationBody').html(data);
                placeHolderLoad();
                saveEmpInfo();
                saveIncome();
                overlay.hide();
            });
        }

function sendMailEmployeeIncomeTax(){
            var overlay = new loadOverlay();
            overlay.show("MenuWrapper");
            $.ajax({url:"./sendMailEmployeeIncomeTax.action",
                    type: 'POST',
                    data: $('#form16GenerationData').serialize()
            }).done(function(data){
                $('#form16GenerationBody').html(data);
                placeHolderLoad();
                saveEmpInfo();
                saveIncome();
                overlay.hide();
            });
        }


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
    $("#addRebateTable").append("<tr> <td>Name</td><td><b>:</b></td>  <td><input class='inputfield' name='' type='text' value=''></td><td>Income&nbsp;Limit</td><td><b>:</b></td><td><input class='inputfield' name='' type='text' value=''></td><td>Amount</td><td><b>:</b></td> <td><input class='inputfield' name='' type='text' value=''></td> <td><button class='submitButton' name='rebateSave' onclick='saveRebate()'><b>Save<b></button></td><td><button class='submitButton' name='rebateEdit' onclick='editRebate()'> <b>Edit</b>  </button> </td> <td> <button class='submitButton'  name='rebateRemove'  onclick='removeRebate()'> <b>Remove</b></button></td></tr>");
}

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
   // $("#addRebateTable tr:last").remove(); name="rebateRemove"
    $('table').on('click', 'button[name="rebateRemove"]', function(e){
          $(this).closest('tr').remove()});
}

//Sections-U/s10

function addSection(){

    var tableRow="<table name='remove' width='100%'><tr><td><input id='sectionName' type='text' placeholder='Add section Name'></td><td><button onClick='saveSection()' class='button btnOrange' type='button'><b>Save</b></button></td><td><button onClick='removeTable()' class='button btnGray' type='button'><b>Cancel</b></button></td></tr></table>";
    $("#addTable").append(tableRow);
     $('#addNew').prop('disabled', true);
}

function removeTable(){
var tableName=$('#addTable table:last').attr('name');
                        if(tableName=="remove"){
                        $("#addTable table:last").remove();
                        $('#addNew').prop('disabled', false);
                        }
         }

function saveSection(){
var newSectionName=document.getElementById('sectionName').value;
     var tableRow="</span><button class='submitButton' onClick='add()' type='button'>ADD</button><table width='100%' class='table_text_style table-sm'><tr><td><span class='head'>Section&nbsp;Limit</span></td>  <td><input class='inputfieldsection' type='text' value=''></td><td><button class='submitButton' name='newSectionSave'><b>Save</b></button></td> <td>  <button class='submitButton' name='newSectionEdit'><b>Edit</b></button></td><td><button class='submitButton' style='float:right; margin-right: 5px;'onclick='removeSection()' name='newSectionRemove'><b>Remove&nbsp;Section</b> </button></td>  </tr> </table></span>";
     $("#addNewSection").append("<span id='additional'><span class='Heading'>");
     $("#addNewSection").append(newSectionName);
     $("#addNewSection").append(tableRow);
 // document.getElementById('addNameToNewSection').innerHTML=newSectionName;
            $('#addNew').prop('disabled', false);
            var tableName=$('#addTable table:last').attr('name');
            if(tableName=="remove"){$("#addTable table:last").remove();}
            $(document).on('click', "button[name='us80dSave']", function() {
                   $(this).closest('tr').find("input").each(function() {
                          $(this).prop("readonly",true).css('background-color','#D3D3D3');
                          });
                   });
}


function add(){
    var tableRow="<table width='100%'><tr><td><input id='sectionName' type='text'></td><td><button onClick='saveSection()' class='button btnOrange' type='button'><b>Save</b></button></td><td><button onClick='removeTable()' class='button btnGray' type='button'><b>Edit</b></button></td><td><button onClick='removeTable()' class='submitButton' type='button' onClick='removeTable()'><b>Remove</b></button></td></tr></table>";
    $("#additional").append(tableRow);

}

//us10 save
$(document).on('click', "button[name='us10Save']", function() {
    $(this).closest('tr').find("input").each(function() {
        $(this).prop("readonly",true).css('background-color','#D3D3D3');
    });
});

//us10 edit
$(document).on('click', "button[name='us10Edit']", function() {
    $(this).closest('tr').find("input").each(function() {
        $(this).prop("readonly",false).css('background-color','#FFFFFF');
    });
});

//function removeUs10(){
//$("#us10section tr:last").remove();us10Remove
$(document).on('click', 'button[name="us10Remove"]', function(){
                        $(this).closest('tr').remove()
                        });

$(document).on('click', "button[name='us80cSave']", function() {
                 $(this).closest('tr').find("input").each(function() {
                     $(this).prop("readonly",true).css('background-color','#D3D3D3');
                 });
             });

$(document).on('click', "button[name='us80cEdit']", function() {
                 $(this).closest('tr').find("input").each(function() {
                     $(this).prop("readonly",false).css('background-color','#FFFFFF');
                 });
             });

$(document).on('click', 'button[name="us80cRemove"]', function(){
                      $(this).closest('tr').remove()
                   });


$(document).on('click', "button[name='us80dSave']", function() {
                      $(this).closest('tr').find("input").each(function() {
                           $(this).prop("readonly",true).css('background-color','#D3D3D3');
                              });
                });

$(document).on('click', "button[name='us80dEdit']", function() {
                       $(this).closest('tr').find("input").each(function() {
                            $(this).prop("readonly",false).css('background-color','#FFFFFF');
                               });
                 });

$(document).on('click', 'button[name="us80dRemove"]', function(){
                       $(this).closest('tr').remove()
                       });

//for new section
$(document).on('click', "button[name='newSectionSave']", function() {
                        $(this).closest('tr').find("input").each(function() {
                              $(this).prop("readonly",true).css('background-color','#D3D3D3');
                        });
                 });

$(document).on('click', "button[name='newSectionEdit']", function() {
                        $(this).closest('tr').find("input").each(function() {
                               $(this).prop("readonly",false).css('background-color','#FFFFFF');
                        });
                  });

$(document).on('click', 'button[name="newSectionRemove"]', function(){
                            $(this).closest('table').remove()
                  });

$(document).on('click', 'button[name="us10RemoveSection"]', function(){
                            $(this).closest('table').remove()
                  });

$(document).on('click', 'button[name="us80cRemoveSection"]', function(){
                         $(this).closest('table').remove()
                  });

$(document).on('click', 'button[name="us80dRemoveSection"]', function(){
                            $(this).closest('table').remove()
                  });


