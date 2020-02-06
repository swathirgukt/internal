package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Role;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.UserRolesService;
import com.indianeagle.internal.service.UsersService;
import com.indianeagle.internal.util.CryptoUtil;
import com.indianeagle.internal.validator.EmployeeFormValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @InitBinder("employeeForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(employeeFormValidator);
    }

    @ModelAttribute("createEmployeeform")
    public String createEmployeeForm(ModelMap model) throws Exception {
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

    @GetMapping("/search")
    public String search(ModelMap model, EmployeeForm employeeForm /*@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm*/) {
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        System.out.println("employee is"+employee);
        EmployeeVO employeeVO= new EmployeeVO();
        BeanUtils.copyProperties(employee,employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        employeeForm.setDepartments(employeeService.getDepartmentList());
        model.addAttribute("departmentList", employeeForm.getDepartments());

        return "searchEmployee";
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
        System.out.println("userPassword="+userDetails.getPassword());
        user.setUserName(userDetails.getUsername());
        user.setPassword("yana123");
        employeeForm.setUser(user);
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("employeeForm", employeeForm);

        return "html/createEmployee";
    }

    /**
     * To update the employee details
     */
    @PostMapping("/updateEmployeeController")
    public String updateEmployee(ModelMap model,@Valid @ModelAttribute("employeeForm")  EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "html/createEmployee";
        }
        System.out.println("userPassword="+employeeForm.getUser().getPassword());
        if (StringUtils.isEmpty(employeeForm.getUser().getPassword())) {
            model.addAttribute("passwordRequired", "Password is required");
            return "html/createEmployee";
        }

        if (StringUtils.isEmpty(employeeForm.getEmpId())) {
            if (usersService.findByUserName(employeeForm.getEmployeeVO().getEmpId()) != null) {
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
        } else {
            User user = usersService.findByUserName(employeeForm.getEmpId());
            /*if(!user.getPassword().equals(employeeForm.getUser().getPassword())){
                employeeForm.getUser().setPassword(CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
            }*/
            employeeForm.getUser().setPassword(CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
            employeeService.updateEmployee(employeeForm);
            model.addAttribute("updateMessage", "SuccessFully Updated");

        }
        employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        return "html/createEmployee";
    }

    /**
     * To search employees
     */
    @PostMapping("/searchEmployee")
    public String searchEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "searchEmployee";
        }
        employeeForm.setEmployeeList(employeeService.searchEmployees(employeeForm));
        model.addAttribute("employeeList", employeeForm.getEmployeeList());

        return "searchEmployee";
    }

    /**
     * To edit employee
     *
     * @return
     */
    @GetMapping("/editEmployee")
    public String editEmployee(HttpServletRequest servletRequest, EmployeeForm employeeForm, ModelMap model) {
        String id = servletRequest.getParameter("employeeId");
        Employee selectedEmployee = employeeService.findEmpFromBuffer(id);

        EmployeeVO selectedEmployeeVO= new EmployeeVO();
        BeanUtils.copyProperties(selectedEmployee,selectedEmployeeVO);

        User user = usersService.findByUserName(selectedEmployee.getEmpId());
        employeeForm.setEmployeeVO(selectedEmployeeVO);
        model.addAttribute("getEmployee", employeeForm.getEmployeeVO());
        employeeForm.setEmpId(selectedEmployee.getEmpId());
        model.addAttribute("getEmployeeId", employeeForm.getEmpId());

        if (user != null) {
            user.setPassword(user.getPassword());
            employeeForm.setUser(user);
            List<Role> roles = new ArrayList<Role>(user.getRoles());
            employeeForm.setRoleName(roles.get(0).getRoleName());
        }
        return "addEmployee";
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
