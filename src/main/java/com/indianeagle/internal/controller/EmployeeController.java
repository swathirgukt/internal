package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Employee;
import com.indianeagle.internal.dto.Role;
import com.indianeagle.internal.dto.User;
import com.indianeagle.internal.form.EmployeeForm;
import com.indianeagle.internal.form.vo.EmployeeVO;
import com.indianeagle.internal.form.vo.NomineeVO;
import com.indianeagle.internal.form.vo.SalaryVO;
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
 * @author CH.Srinath
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
    public String createEmployeeForm(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //  userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        model.addAttribute("employeeForm", employeeForm);
        return "html/updateMyDetails";
    }

    @GetMapping("/updateMyDetails")
    public String updateEmployeeForm(EmployeeForm employeeForm) {
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
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
            return "html/updateMyDetails";
        }
        employeeForm.setEmpId(userDetails.getUsername());
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        employeeService.createEmployee(employeeForm);
        //  createUser(employeeForm);
        model.addAttribute("saveMessage", "Successfully Saved");
        return "html/updateMyDetails";
    }

    /**
     * Method renders empty employee details to create new employee
     */
    @GetMapping("/createEmployee")
    public String createEmployee(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        User user = new User();
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
    public String updateEmployee(ModelMap model, @ModelAttribute("employeeForm") EmployeeForm employeeForm/*, BindingResult bindingResult*/) {
        System.out.println("============UpdateBeforeCondition==================");
       /* if (bindingResult.hasErrors()) {
            // model.addAttribute("message", "Validate error");
            //bindingResult.reject("Validate error");
            System.out.println("errors");
            return "html/createEmployee";
        }*/
        if (StringUtils.isEmpty(employeeForm.getUser().getPassword())) {
            model.addAttribute("passwordRequired", "Password is required");
            return "html/createEmployee";
        }
        try {
            if (StringUtils.isEmpty(employeeForm.getEmpId())) {
                if (employeeService.findEmployeeByEmpId(employeeForm.getEmployeeVO().getEmpId()) != null) {
                    model.addAttribute("employeeIdExists", "Employee Id already exists");
                    return "html/createEmployee";
                }
                employeeService.createEmployee(employeeForm);
                //  createUser(employeeForm);
                model.addAttribute("saveMessage", "SuccessFully Saved");
            } else {
                User user = usersService.findByUserName(employeeForm.getEmpId());
            /*if(!user.getPassword().equals(employeeForm.getUser().getPassword())){
                employeeForm.getUser().setPassword(CryptoUtil.encryptPassWord(employeeForm.getUser().getPassword()));
            }*/
                employeeForm.getUser().setPassword(BCryptPasswordUtil.encode(employeeForm.getUser().getPassword()));
                employeeService.updateEmployee(employeeForm);
                model.addAttribute("updateMessage", "SuccessFully Updated");
            }
            employeeForm = new EmployeeForm();
            employeeForm.setDepartments(employeeService.getDepartmentList());
            employeeForm.setRolesList(userRolesService.loadAll());
            return "html/createEmployee";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("exceptionMessage", "insert again");
        }
        return "html/createEmployee";
    }

    @GetMapping("/search")
    public String searchEmployeeForm(ModelMap model) {
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        model.addAttribute("employeeForm", employeeForm);
        return "html/searchEmployeeDetails";
    }


    /**
     * To search employees
     */
    @PostMapping("/searchEmployee")
    public String searchEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
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
    public String editEmployee(EmployeeForm employeeForm, ModelMap model, @PathVariable("id") Long id) {
        String uniqueId = String.valueOf(id);
        Employee selectedEmployee = employeeService.findEmpFromBuffer(uniqueId);
        User user = usersService.findByUserName(selectedEmployee.getEmpId());
        EmployeeVO selectedEmployeeVO = new EmployeeVO();
        BeanUtils.copyProperties(selectedEmployee, selectedEmployeeVO);
        employeeForm.setEmployeeVO(selectedEmployeeVO);
        SalaryVO salaryVO = new SalaryVO();
        BeanUtils.copyProperties(selectedEmployee.getSalary(), salaryVO);
        employeeForm.getEmployeeVO().setSalaryVO(salaryVO);
        NomineeVO nomineeVO = new NomineeVO();
        BeanUtils.copyProperties(selectedEmployee.getNominee(), nomineeVO);
        employeeForm.getEmployeeVO().setNomineeVO(nomineeVO);
        employeeForm.setEmpId(selectedEmployeeVO.getEmpId());
        if (user != null) {
            employeeForm.setUser(user);
            if (user.getRoles().size() > 0) {
                List<Role> roles = new ArrayList<Role>(user.getRoles());
                employeeForm.setRoleName(roles.get(0).getRoleName());
            }
        }
        model.addAttribute("employeeForm", employeeForm);
        return "html/createEmployee";
    }

    @GetMapping("/employeeStatus")
    public String empStatus(ModelMap modelMap) {
        EmployeeForm employeeForm = new EmployeeForm();
        userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeForm.setDepartments(employeeService.getDepartmentList());
        employeeForm.setRolesList(userRolesService.loadAll());
        Employee employee = employeeService.findEmployeeByEmpId(userDetails.getUsername());
        EmployeeVO employeeVO = new EmployeeVO();
        BeanUtils.copyProperties(employee, employeeVO);
        employeeForm.setEmployeeVO(employeeVO);
        modelMap.addAttribute("employeeForm", employeeForm);
        return "html/searchEmployeeStatus";
    }

    /**
     * To get the Employees based on Status
     */
    @PostMapping("/searchEmployeeStatus")
    public String searchBasedOnEmpStatus(@ModelAttribute("employeeForm") EmployeeForm employeeForm, ModelMap modelMap) {
        String fromDate = String.valueOf(employeeForm.getFromDate());
        if (StringUtils.isEmpty(fromDate)) {
            modelMap.addAttribute("errorMessage", "FromDate is required");
            return "html/searchEmployeeStatus";
        }
        String toDate = String.valueOf(employeeForm.getToDate());
        if (!StringUtils.isEmpty(toDate)) {
            if (employeeForm.getFromDate().after(employeeForm.getToDate())) {
                modelMap.addAttribute("invaliDates", "Please provide valid Dates");
                return "html/searchEmployeeStatus";
            }
        }
        employeeForm.setEmployeeList(employeeService.searchBasedOnEmpStatus(employeeForm));
        modelMap.addAttribute("employeeForm", employeeForm);
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

        System.out.println("password " + employeeForm.getUser());
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
