
package com.indianeagle.internal.controller;

import com.indianeagle.internal.form.FormsResultData;
import com.indianeagle.internal.form.ITForm;
import com.indianeagle.internal.service.FormsService;
import com.indianeagle.internal.service.MonthlyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Controller for various Organization Forms
 *
 * Author : Nagendra
 * Since : 02/17/2020
 */
@Controller
public class ItReportsController {

    @Autowired
    private MonthlyReportService monthlyReportService;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );
        binder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, true ) );
    }

    @GetMapping("/itReports")
    public String itReports(ModelMap modelMap) {
        return "html/itReports";
    }

    @PostMapping("/getITReports")
    public String getITReport(ModelMap modelMap, @RequestParam Date salaryDate) {
       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (salaryDate == null) {
                modelMap.addAttribute( "Date", "Please select  date." );
                return "html/itReports";
            } else if (salaryDate.getMonth() != Calendar.APRIL) {
                modelMap.addAttribute( "monthName", "Please select financial Month(April)" );
                return "html/itReports";
            }

            List<ITForm> itList = monthlyReportService.getITReport( salaryDate );
           int i= itList.size();
           if (itList == null) {
                modelMap.addAttribute( "noRecords","No Records Exist" );
                return "html/itReports";
            }


            int listSize=itList.get(0).getMonthlyIT().size();

           modelMap.addAttribute( "employeeId",userDetails.getUsername() );
           for(int j=0;j<12-listSize;j++){
               itList. get(0).getMonthlyIT().add(null );
           }
           double total=0;
            for (BigDecimal b:itList. get(0).getMonthlyIT()) {
                if(b==null){
                    total=total+0;
                }
               else{
                   total=total+b.doubleValue();
                }
            }
            modelMap.addAttribute( "itList", itList.get(0).getMonthlyIT() );
            for(BigDecimal B:itList.get(0).getMonthlyIT()){

            }
            BigDecimal totalInBigDecimal=new BigDecimal( total );
            modelMap.addAttribute( "total",totalInBigDecimal );


        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute( "technicalError","Records are  not existed" );
            return "html/itReports";
        }
        return "html/itReports";
    }


}