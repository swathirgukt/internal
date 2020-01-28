package com.indianeagle.internal.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilForTest {

    public static Date getDate(String datevalue) throws ParseException {
        String sDate1 = datevalue;
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        return date1;
    }
}
