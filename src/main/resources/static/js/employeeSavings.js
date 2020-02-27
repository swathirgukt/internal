
function employeeSearch() {
var x = document.forms["employeeSavingForm"]["empName"]
console.log("come in");

if(x.value == "") {
console.log("come in x");
     document.getElementById("employeeName").innerHTML="employee Name Required";
     return false;
     }
     else{
     var response = makeAJAXCall("/searchEmployeeByName", 'employeeSavingForm');
     response.done(function (responseData) {
         if (responseData) {
             $("#employeeSavingsSearchResult").html(responseData);
         }
     });
 }
 }


function retrieveFinancialYearDataToEmployee() {
   var y = document.forms["employeeSavingForm"]["employeeId"];
if(y.value == ""){
           document.getElementById("empId").innerHTML="employee id Required";
           return false;
        }
        else{
     var url="/retrieveFinancialYearDataToEmployee";
     var form=document.getElementById("employeeSavingForm");
     form.action=url;
     form.submit();
     }
 }

 function setEmployeeId(empId){
      console.log(empId);
      document.getElementById('employeeId').value=empId;
  }


  var fullName,designation,panNo,address;

  function editEmpInfo(){
      fullName = $('input[name="employeeVO.fullName"]').val();
      designation = $('input[name="employeeVO.designation"]').val();
      panNo = $('input[name="employeeVO.panNo"]').val();
      address = $('input[name="employeeVO.address"]').val();
      $('#employeeInfo').children('input').prop('readonly',false).css('background-color','#FFFFFF');
      $('#saveBtn').show();
      $('#cancelBtn').show();
      $('#editBtn').hide();
  }

  function saveEmpInfo(){
      $('#employeeInfo').children('input').prop('readonly',true).css('background-color','#D3D3D3');
      $('#saveBtn').hide();
      $('#cancelBtn').hide();
      $('#editBtn').show();
  }

  function cancelEmpInfo(){
      $('input[name="employeeVO.fullName"]').val(fullName);
      $('input[name="employeeVO.designation"]').val(designation);
      $('input[name="employeeVO.panNo"]').val(panNo);
      $('input[name="employeeVO.address"]').val(address);
      $('#employeeInfo').children('input').prop('readonly',true).css('background-color','#D3D3D3');
      $('#saveBtn').hide();
      $('#cancelBtn').hide();
      $('#editBtn').show();
  }


  function saveOrUpdateEmployeeFinancialYear() {
      document.employeeSavingForm.action = "/saveOrUpdateEmployeeFinancialYear";
      document.employeeSavingForm.submit();
  }

   $(document).on('click', '#sectionList div[name="addDeclaration"]', function(){
   console.log("in onClick");
          var sectionIndex = $(this).attr('id');
          console.log("sectionIndex:   "+ sectionIndex);
          var lastDiv = $(this).parent().children('div').last();
          var selectFieldLength = lastDiv.children('select').first().children().length;
          var currentDivIndex = parseInt(lastDiv.attr('id'));
          console.log(currentDivIndex+1);
           console.log("currentDivIndex:   "+ currentDivIndex);
          if(currentDivIndex < (selectFieldLength-1)){
          console.log("=in=====");
              var cloneDiv = $(this).parent().children('div').last().clone();
              cloneDiv.attr('id', currentDivIndex+1);
              cloneDiv.children('select').first().attr('name', 'employeeFinancialYearForm.employeeTaxSectionForms['+sectionIndex+'].employeeTaxSectionDeclarations['+(currentDivIndex+1)+'].subSectionName');
              cloneDiv.children('input').last().attr('name', 'employeeFinancialYearForm.employeeTaxSectionForms['+sectionIndex+'].employeeTaxSectionDeclarations['+(currentDivIndex+1)+'].saveAmount').val("");
              if(cloneDiv.children('div').last().attr('name').trim() != "removeDeclaration"){
                  cloneDiv.append('<div name="removeDeclaration" class="button btnGray"><a>Remove</a></div>');
              }
              $(this).parent().append(cloneDiv);
         }
      });




        $(document).on('click', '#sectionList li div[name="saveExemptionRule"]', function() {

              $(this).parent().children('input').prop("readonly",true).css('background-color','#D3D3D3');
          });

          $(document).on('click', '#sectionList li div[name="editExemptionRule"]', function() {
                  $(this).parent().children('input').prop("readonly",false).css('background-color','#FFFFFF');
              });


  $(document).on('click', '#sectionList div[name="removeDeclaration"]', function(){
         var divList = $(this).parent().parent().children('div');
         var sectionIndex = divList.first().attr('id');
         var declarationRemoveIndex = $(this).parent().attr('name');
         var declarationEndIndex = divList.last().attr('name');
         if(declarationEndIndex !=0){
             if(declarationRemoveIndex == declarationEndIndex){
                 $(this).parent().remove();
             }else{
                 var loopCount = declarationEndIndex-declarationRemoveIndex;
                 var lastDiv = divList.last();
                 for(var i=0; i<loopCount; i++ ){
                     declarationEndIndex--;
                     lastDiv.children('select').first().attr('name', "employeeFinancialYearForm.employeeTaxSectionForms["+sectionIndex+"].employeeTaxSectionDeclarations["+declarationEndIndex+"].subSectionName");
                     lastDiv.children('input').last().attr('name', "employeeFinancialYearForm.employeeTaxSectionForms["+sectionIndex+"].employeeTaxSectionDeclarations["+declarationEndIndex+"].saveAmount");
                     lastDiv.attr('name', declarationEndIndex);
                     lastDiv = lastDiv.prev();
                 }
                 $(this).parent().remove();
             }
         }
     });


