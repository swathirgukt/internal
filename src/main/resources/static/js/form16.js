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


