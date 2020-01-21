
package com.indianeagle.internal.service;

import com.indianeagle.internal.dto.Employee;
import java.util.List;

/**
 * @author hari.pondreti
 * Facade for BasicSalaryDetails of employees
 */
public interface BasicSalaryDetailsService {

    /**
     * gives the list of employees based on their status
     *
     * @param empStatus
     * @return List<Employee>
     */
    List<Employee> getBasicSalaryDetails(String empStatus);

}
