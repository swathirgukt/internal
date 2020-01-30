package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Role;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.service.EmployeeService;
import com.indianeagle.internal.service.UserRolesService;
import com.indianeagle.internal.service.UsersService;
import com.indianeagle.internal.util.CryptoUtil;
import com.indianeagle.internal.validator.EmployeeFormValidator;
import org.apache.commons.lang.StringUtils;
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
    //@Autowired
    private EmployeeService employeeService;
    //@Autowired
    private UserRolesService userRolesService;
    //@Autowired
    private UsersService usersService;
    //@Autowired
    private com.indianeagle.internal.mail.MailingEngine mailingEngine;

    //@Autowired
    private EmployeeFormValidator employeeFormValidator;

    @InitBinder("employeeForm")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(employeeFormValidator);
    }

    @ModelAttribute("createEmployeeform")
    public void createEmployeeForm() throws Exception {
        EmployeeForm employeeForm = new EmployeeForm();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
    }

    @GetMapping("/employeeAction")
    public String updateEmployeeForm(UserDetails userDetails, EmployeeForm employeeForm /*@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm*/) {
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        employeeForm.setEmployee(employee);
        return "employeeDetails";
    }

    @GetMapping("/search")
    public String search(ModelMap model, UserDetails userDetails, EmployeeForm employeeForm /*@ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm*/) {
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        employeeForm.setEmployee(employee);
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
    public String saveEmployee(ModelMap model, @ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm, BindingResult bindingResult, UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "employeeDetails";
        }

        employeeForm.setEmpId(userDetails.getUsername());
        //employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        employeeService.createEmployee(employeeForm);
        createUser(employeeForm);
        model.addAttribute("saveMessage", "Successfully Saved");
        //addActionMessage("SuccessFully Saved");
        return "employeeDetails";
    }

    /**
     * Method renders empty employee details to create new employee
     */
    @GetMapping("/employee")
    public String employee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        model.addAttribute("departmentList", employeeForm.getDepartments());
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("rolesList", employeeForm.getRolesList());
        return "addEmployee";
    }

    /**
     * To update the employee details
     */
    @PostMapping("/updateEmployeeAction")
    public String updateEmployee(ModelMap model, @ModelAttribute("employeeForm") @Valid EmployeeForm employeeForm, BindingResult bindingResult, UserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            return "addEmployee";
        }

        if (StringUtils.isEmpty(employeeForm.getUser().getPassword())) {
            model.addAttribute("passwordRequired", "Password is required");
            return "addEmployee";
        }

        if (StringUtils.isEmpty(employeeForm.getEmpId())) {
            if (usersService.findByUserName(employeeForm.getEmployee().getEmpId()) != null) {
                model.addAttribute("employeeIdExists", "Employee Id already exists");
                return "addEmployee";
            }
            //validatePFAndBankAccount(employeeForm);
            /*if (hasActionErrors()) {
                return ERROR;
            }*/
            employeeService.createEmployee(employeeForm);
            createUser(employeeForm);
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
        return "addEmployee";
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
        User user = usersService.findByUserName(selectedEmployee.getEmpId());
        employeeForm.setEmployee(selectedEmployee);
        model.addAttribute("getEmployee", employeeForm.getEmployee());
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
        user.setUserName(employeeForm.getEmployee().getEmpId());
        user.setFirstName(employeeForm.getEmployee().getFirstName());
        user.setLastName(employeeForm.getEmployee().getLastName());
        user.setPassword(CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
        user.setEmail(employeeForm.getEmployee().getOfficialEmail());
        user.setStatus(true);
        List<Role> roleList = userRolesService.findRolesByRoleName(employeeForm.getRoleName());
        Set<Role> roles = new HashSet<Role>(roleList);
        user.setRoles(roles);
        usersService.save(user);
        mailingEngine.mailAccountInfo(user, employeeForm.getUser().getPassword());
    }


}
