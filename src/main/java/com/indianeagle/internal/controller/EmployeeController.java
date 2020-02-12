package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Role;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.UserRolesService;
import com.indianeagle.internal.service.UsersService;
import com.indianeagle.internal.util.BCryptPasswordUtil;
import com.indianeagle.internal.validator.EmployeeFormValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Satya.Neelam
 * controller to save or update employee details
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserRolesService userRolesService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private com.indianeagle.internal.mail.MailingEngine mailingEngine;

    @Autowired
    private EmployeeFormValidator employeeFormValidator;

    private UserDetails userDetails;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

   /* @InitBinder("employeeForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(employeeFormValidator);
    }*/

    @ModelAttribute("createEmployeeform")
    public String createEmployeeForm(ModelMap model){
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       //  userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("userdetails"+userDetails);
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("employeeForm",employeeForm);
        System.out.println("model"+ this);
        return "html/updateMyDetails";
    }

    @GetMapping("/updateMyDetails")
    public String updateEmployeeForm(EmployeeForm employeeForm /*@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm*/) {
        System.out.println("update details");
        System.out.println("==============================");
        System.out.println("user details"+userDetails.getUsername());
        System.out.println("==============================");

        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        System.out.println("employee is"+employee);
        EmployeeVO employeeVO= new EmployeeVO();
        BeanUtils.copyProperties(employee,employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        System.out.println("uodateMy"+ this);
        return "html/updateMyDetails";
    }






    /*  */

    /**
     * Method to save the employee information
     *
     * @return
     */
    @PostMapping("/saveEmployee")
    public String saveEmployee(ModelMap model, @ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "html/updateMyDetails";
        }

       /* System.out.println("employeeForm "+employeeForm.getUser());
        System.out.println("========================");
        System.out.println("userDetasils"+userDetails.getUsername());
        System.out.println("====================================");*/

        employeeForm.setEmpId(userDetails.getUsername());

        //employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        System.out.println("department="+employeeForm.getDepartment());

        employeeService.createEmployee(employeeForm);
      //  createUser(employeeForm);
        model.addAttribute("saveMessage", "Successfully Saved");
        //addActionMessage("SuccessFully Saved");
        return "html/updateMyDetails";
    }

    /**
     * Method renders empty employee details to create new employee
     */
    @GetMapping("/createEmployee")
    public String createEmployee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        User user= new User();
        System.out.println("username="+userDetails.getUsername());
        System.out.println("userPassword="+ usersService.findByUserName(userDetails.getUsername()).getPassword());
        user.setUserName(userDetails.getUsername());
        user.setPassword(usersService.findByUserName(userDetails.getUsername()).getPassword());
        employeeForm.setUser(user);
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("employeeForm", employeeForm);

        return "html/createEmployee";
    }

    /**
     * To update the employee details
     */
    @PostMapping("/updateEmployeeController")
    public String updateEmployee(ModelMap model,@ModelAttribute("employeeForm") EmployeeForm employeeForm/*, BindingResult bindingResult*/) {
        System.out.println("============UpdateBeforeCondition==================");
       /* if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            System.out.println("errors");
            return "html/createEmployee";
        }*/
        System.out.println("userPassword=" + employeeForm.getUser().getPassword());
        if (StringUtils.isEmpty(employeeForm.getUser().getPassword())) {
            System.out.println("emptyPassword");

            model.addAttribute("passwordRequired", "Password is required");
            return "html/createEmployee";
        }
        System.out.println("====employeeFormId==="+employeeForm.getEmpId());
        try {
            if (StringUtils.isEmpty(employeeForm.getEmpId())) {
                System.out.println("============Updatein if ==================");


                if (employeeService.findEmployeeByEmpId(employeeForm.getEmployeeVO().getEmpId()) != null) {
                    model.addAttribute("employeeIdExists", "Employee Id already exists");
                    return "html/createEmployee";
                }
                //validatePFAndBankAccount(employeeForm);
            /*if (hasActionErrors()) {
                return ERROR;
            }*/

                employeeService.createEmployee(employeeForm);
                //  createUser(employeeForm);

                model.addAttribute("saveMessage", "SuccessFully Saved");

            }else {
                System.out.println("============Update in else==================");

                User user = usersService.findByUserName(employeeForm.getEmpId());
            /*if(!user.getPassword().equals(employeeForm.getUser().getPassword())){
                employeeForm.getUser().setPassword(CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
            }*/
                employeeForm.getUser().setPassword(BCryptPasswordUtil.encode(employeeForm.getUser().getPassword()));
                employeeService.updateEmployee(employeeForm);
                model.addAttribute("updateMessage", "SuccessFully Updated");

            }
            System.out.println("new employee Form");

            employeeForm = new EmployeeForm();
            employeeForm.setDepartments(employeeService.getDepartmentList());
            employeeForm.setRolesList(userRolesService.loadAll());
            return "html/createEmployee";
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("exception occured");
            model.addAttribute("exceptionMessage","insert again");
        }
        System.out.println("===========END======");
        return "html/createEmployee";
    }

    @GetMapping("/search")
    public String searchEmployeeForm(ModelMap model){
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //  userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        System.out.println("=======employee====="+employee);

        System.out.println("userdetails"+userDetails);
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        EmployeeVO employeeVO= new EmployeeVO();
        BeanUtils.copyProperties(employee,employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        model.addAttribute("employeeForm",employeeForm);
        System.out.println("model"+ this);
        return "html/searchEmployeeDetails";
    }

   /* public String search(ModelMap model, EmployeeForm employeeForm *//*@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm*//*) {
         System.out.println("update details");
         System.out.println("==============================");
         System.out.println("user details"+userDetails.getUsername());
         System.out.println("==============================");

         Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
         System.out.println("employee is"+employee);
         EmployeeVO employeeVO= new EmployeeVO();
         BeanUtils.copyProperties(employee,employeeVO);
         employeeForm.setEmployeeVO(employeeVO);
         System.out.println("uodateMy"+ this);
         return "html/searchEmployee";
    }*/



    /**
     * To search employees
     */
    @PostMapping("/searchEmployee")
    public String searchEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "html/searchEmployeeDetails";
        }
        employeeForm.setEmployeeList(employeeService.searchEmployees(employeeForm));
        model.addAttribute("employeeList", employeeForm.getEmployeeList());

        return "html/fragment/searchEmployeeResult";
    }



    /**
     * To edit employee
     *
     * @return
     */
    @GetMapping("/editEmployee/{id}")
    public String editEmployee(EmployeeForm employeeForm, ModelMap model,@PathVariable("id") Long id) {
        // String id = servletRequest.getParameter("employeeId");
        String uniqueId = String.valueOf(id);
        Employee selectedEmployee = employeeService.findEmpFromBuffer(uniqueId);

       /* EmployeeVO selectedEmployeeVO= new EmployeeVO();
        BeanUtils.copyProperties(selectedEmployee,selectedEmployeeVO);
*/
        User user = usersService.findByUserName(selectedEmployee.getEmpId());
        EmployeeVO selectedEmployeeVO = new EmployeeVO();
        BeanUtils.copyProperties(selectedEmployee, selectedEmployeeVO);
        employeeForm.setEmployeeVO(selectedEmployeeVO);
        System.out.println("selectedEmployeeId"+selectedEmployeeVO.getEmpId());
        employeeForm.setEmpId(selectedEmployeeVO.getEmpId());
        System.out.println("employeeFormId"+employeeForm.getEmpId());


        if (user != null) {
            System.out.println("==========userPassword========" + user.getPassword());
            employeeForm.setUser(user);
            System.out.println("====employeeFormPassword======" + employeeForm.getUser().getPassword());
            System.out.println("=====userroles======" + user.getRoles());
            if (user.getRoles().size() > 0) {
                List<Role> roles = new ArrayList<Role>(user.getRoles());
                employeeForm.setRoleName(roles.get(0).getRoleName());
            }
        }
        model.addAttribute("employeeForm",employeeForm);
        return "html/createEmployee";

    }

    @GetMapping("/employeeStatus")
    public String empStatus(ModelMap modelMap)
    {
        EmployeeForm employeeForm=new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());

        EmployeeVO employeeVO= new EmployeeVO();
        BeanUtils.copyProperties(employee,employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        modelMap.addAttribute("employeeForm",employeeForm);
        System.out.println("model"+ this);
        return "html/searchEmployeeStatus";
    }

    /**
     * To get the Employees based on Status
     */
    @PostMapping("/searchEmployeeStatus")
    public String searchBasedOnEmpStatus(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap modelMap) {
       String fromDate= String.valueOf(employeeForm.getFromDate());
        if (StringUtils.isEmpty(fromDate)) {
            modelMap.addAttribute("errorMessage","FromDate is required");
            return "html/searchEmployeeStatus";
        }
        String toDate= String.valueOf(employeeForm.getToDate());
        if (!StringUtils.isEmpty(toDate)) {
            if (employeeForm.getFromDate().after(employeeForm.getToDate())) {
                modelMap.addAttribute("invaliDates","Please provide valid Dates");
                return "html/searchEmployeeStatus";
            }
        }
        employeeForm.setEmployeeList(employeeService.searchBasedOnEmpStatus(employeeForm));
        modelMap.addAttribute("employeeForm",employeeForm);
        return "html/fragment/searchEmployeeStatusResult";
    }



    /**
     * Method to create user
     */
    private void createUser(EmployeeForm employeeForm) {
        User user = new User();
        user.setUserName(employeeForm.getEmployeeVO().getEmpId());
        user.setFirstName(employeeForm.getEmployeeVO().getFirstName());
        user.setLastName(employeeForm.getEmployeeVO().getLastName());

        System.out.println("password "+employeeForm.getUser());
        System.out.println("=============================");
       // System.out.println("password= "+employeeForm.getUser().getPassword());
        //System.out.println("password= "+CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
        System.out.println("=============================");
       /* if((employeeForm.getUser().getPassword())==null){
            System.out.println("password= "+CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
        }
       user.setPassword((employeeForm.getUser().getPassword()));
        user.setEmail(employeeForm.getEmployeeVO().getOfficialEmail());*/
        System.out.println(employeeForm.getUser().getUserName());
       System.out.println(employeeForm.getUser().getPassword());
        user.setStatus(true);
        List<Role> roleList = userRolesService.findRolesByRoleName(employeeForm.getRoleName());
        Set<Role> roles = new HashSet<Role>(roleList);
        user.setRoles(roles);
        usersService.save(user);
       // mailingEngine.mailAccountInfo(user, employeeForm.getUser().getPassword());
    }


}
