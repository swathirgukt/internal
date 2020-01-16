/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.util;

import com.indianeagle.internal.constants.StringConstants;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Utility class for Dates
 * User: nageswaramma
 * Date: 5/30/12
 * Time: 10:17 AM
 */
public class DateUtils {

    private static final String LOP_INC_HOLIDAYS = "LOP inc Holidays";
    private static final String MARRIAGE_LEAVE_INC_HOLIDAYS = "Marriage Leave inc Holidays";

    /**
     * This method to find the absent dates
     * @param fromDate the from date
     * @param toDate to date
     * @param leaveType the leaveType
     * @return  list of absent days
     */
    public static List<Integer> findAbsentDates(Date fromDate, Date toDate, String leaveType) {
        List<Integer> dates = new ArrayList<Integer>();
        Calendar start = Calendar.getInstance();
        start.setTime(fromDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(toDate);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        if (LOP_INC_HOLIDAYS.equals(leaveType) || MARRIAGE_LEAVE_INC_HOLIDAYS.equals(leaveType)) {
            for (; !start.after(end); start.add(Calendar.DATE, 1)) {
                Date current = start.getTime();
                start.setTime(current);
                dates.add(start.get(Calendar.DAY_OF_MONTH));
            }
        } else {
            for (; !start.after(end); start.add(Calendar.DATE, 1)) {
                Integer dayOfWeek = start.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek != 1 && dayOfWeek != 7) {
                    Date current = start.getTime();
                    start.setTime(current);
                    dates.add(start.get(Calendar.DAY_OF_MONTH));
                }
            }
        }
        return dates;
    }

    /**
     * Method to find the no of months corresponds to two dates
     * @param fromDate the from date
     * @param toDate the to date
     * @return months difference between given dates
     */
    public static int findMonthsDifference(Date fromDate, Date toDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(fromDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(toDate);
        if(startCal.get(Calendar.YEAR) != endCal.get(Calendar.YEAR)) {
           int noOfMonths = (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)-1) * 12;
           return noOfMonths += (12- startCal.get(Calendar.MONTH)) + (endCal.get(Calendar.MONTH));

        } else {
            return endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
        }
    }

    /**
     * This method prepare month and year
     * @param date
     * @return String
     */
    public static String findMonthAndYear(Date date){
        StringBuffer monthYear = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return monthYear.append(new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]).append(" ").append(calendar.get(Calendar.YEAR)).toString();
    }

    /**
     * The method to show the footer Date in paySlip PDF
     * @return
     */
    public static String findPaySlipFooterDate(){
        return DateFormatter.format(Calendar.getInstance().getTime(), StringConstants.EEE_MMMM_DD_HH_MM_SS_Z_YYYY);
    }
    public static int differenceInDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return (((int)Math.ceil((double)(endDate.getTime() - startDate.getTime()) / 86400000)) + 1);
    }
}
