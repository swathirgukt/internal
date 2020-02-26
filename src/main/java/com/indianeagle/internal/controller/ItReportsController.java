
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
        System.out.println("@@Date :: "+salaryDate);
        try {
            if (salaryDate == null) {
                System.out.println("salary data is null");
                modelMap.addAttribute( "Date", "Please select  date." );
                return "html/itReports";
            }

            //System.out.println("##month: "+salaryDate.getMonth());
             else if (salaryDate.getMonth() != Calendar.APRIL) {
                System.out.println("##Inside if");
                modelMap.addAttribute( "monthName", "Please select financial Month(April)" );
                return "html/itReports";
            }

            List<ITForm> itList = monthlyReportService.getITReport( salaryDate );
            System.out.println("##List >> "+itList);
           int i= itList.size();
            System.out.println(i);
           if (itList == null) {
                modelMap.addAttribute( "noRecords","No Records Exist" );
                return "html/itReports";
            }


            System.out.println(itList.get(0).getMonthlyIT().size());
            int listSize=itList.get(0).getMonthlyIT().size();

           modelMap.addAttribute( "employeeId",userDetails.getUsername() );
           for(int j=0;j<12-listSize;j++){
               System.out.println(j);
               itList. get(0).getMonthlyIT().add(null );
           }
            System.out.println("new Size:  "+itList.get(0).getMonthlyIT().size());
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
                System.out.println(B);

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