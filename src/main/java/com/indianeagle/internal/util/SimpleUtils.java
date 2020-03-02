package com.indianeagle.internal.util;

import com.indianeagle.internal.dto.Employee;
import org.apache.commons.lang.RandomStringUtils;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple Utils for Application
 *
 * @author SVK
 */
public class SimpleUtils {

    public static final SimpleDateFormat DATE_DISPLAY_WITH_TIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public static final SimpleDateFormat DATE_DISPLAY = new SimpleDateFormat("MM/dd/yyyy");

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat MONTH_YYYY = new SimpleDateFormat("MMMMM-yyyy");

    public static final SimpleDateFormat MONTHYYYY = new SimpleDateFormat("MMMMM yyyy");

    public static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

    public static final int MILLIS_15_DAY = 1000 * 60 * 60 * 24 * 15;

    public static final String AND = " And ";
    public static final String RUPEES = "Rupees ";

    public static final String ONLY = " Only";
    // Parallel arrays used in the conversion process.
    private static final String[] RCODE = {"m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i"};
    private static final int[] BVAL = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * Check null or empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * Check null or empty
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(BigDecimal value) {
        return (value == null || "".equals(value));
    }

    /**
     * Check null or empty
     *
     * @param date
     * @return
     */
    public static boolean isEmpty(Date date) {
        return (date == null || "".equals(date));
    }

    public static boolean isEmpty(Integer integer) {
        return (integer == null || "".equals(integer));
    }

    public static boolean isEmpty(Double doub) {
        return (doub == null || "".equals(doub));
    }

    public static boolean isEmpty(Long lng) {
        return (lng == null || "".equals(lng));
    }

    public static BigDecimal isEmptyValue(BigDecimal decimal) {
        if (decimal == null || "".equals(decimal))
            return new BigDecimal(0.0);
        return decimal;
    }

    /**
     * Use the simple date format to parse a date from a String
     *
     * @param createdDate The date string
     * @return The Date object
     * @throws ParseException
     */
    public static Date stringToDate(String createdDate) {

        if (isEmpty(createdDate)) {
            return null;
        }

        Date date = new Date();
        try {
            date = DATE_DISPLAY_WITH_TIME.parse(createdDate);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * Use the simple date format to parse a date from a String
     *
     * @param createdDate The date string
     * @return The Date object
     * @throws ParseException
     */
    public static String dateToString(Date createdDate) {

        if (isEmpty(createdDate)) {
            return null;
        }
        return DATE_FORMAT.format(createdDate);
    }

    /**
     * Convert a Date into a formatter string using the default date format
     *
     * @param date The date to convert
     * @return The date formatted as a string
     */
    public static final Date dateToDateWFormat(Date date) {
        try {
            if (date != null) {
                return DATE_DISPLAY.parse(DATE_DISPLAY.format(date));
            }
        } catch (ParseException e) {
            return null;
        }
        return null;

    }

    /**
     * Return defaultValue or else value
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal parseBigDecimal(String value,
                                             BigDecimal defaultValue) {
        if (value == null || "".equals(value)) {
            return defaultValue;
        } else {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
    }

    /**
     * Return defaultValue or else value
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal parseBigDecimal(Double value,
                                             BigDecimal defaultValue) {
        if (value == null || "".equals(value)) {
            return defaultValue;
        } else {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException nfe) {
                return defaultValue;
            }
        }
    }

    /**
     * Return defaultValue or else value
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal parseBigDecimal(BigDecimal value, BigDecimal defaultValue) {

        if (value == null || "".equals(value))
            return defaultValue;
        return value;
    }

    /**
     * find number of week
     *
     * @param sampleDate
     * @return
     */
    public static int numberOfWeek(String sampleDate) {

        Date date = new Date();
        try {
            date = DATE_DISPLAY_WITH_TIME.parse(sampleDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Validate amount format.
     *
     * @param myString
     * @return
     */
    public static final boolean isValidAmount(String myString) {
        boolean isDigit = true;
        char ch = '.';
        if (myString != null && myString.length() > 0) {
            for (int i = 0; i < myString.length(); i++) {
                if (!Character.isDigit(myString.charAt(i)) && ch != myString.charAt(i)) {
                    isDigit = false;
                    break;
                }
            }
        }
        return isDigit;
    }

    /**
     * Check fromDate is older or equal to toDate
     *
     * @param fromDate
     * @param toDate
     * @return true or false
     */
    public static boolean checkValidSearchDate(Date fromDate, Date toDate) {
        boolean checkValidSearchDate = false;

        if (fromDate.getTime() <= toDate.getTime())
            checkValidSearchDate = true;
        return checkValidSearchDate;
    }

    /**
     * Check which date is earlier
     *
     * @param startDate The start date
     * @param endDate   The end date
     * @return true or false
     */
    public static boolean checkDatePrecedence(Date startDate, Date endDate) {
        boolean checkDatePrecedence = false;

        if (endDate.before(startDate)) {
            checkDatePrecedence = true;
        }

        return checkDatePrecedence;
    }

    /**
     * Validate Email format.Checks whether mail is in the format or not.
     * Note : If Email is Valid then return 'false' otherwise 'true'
     *
     * @param value
     * @return
     */
    public static boolean isValidEmail(String value) {
        Pattern p = Pattern.compile("^[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");

        Matcher matcher = p.matcher(value.toUpperCase());
        return !matcher.find();
    }

    /**
     * get Previous Date For Given Date
     *
     * @param date
     * @return
     */
    public static Date prevDate(Date date) {
        return stringToDate(DATE_DISPLAY.format(date.getTime() - MILLIS_IN_DAY));
    }

    /**
     * get Next Date For Given Date
     *
     * @param date
     * @return
     */
    public static Date nextDate(Date date) {
        return stringToDate(DATE_DISPLAY.format(date.getTime() + MILLIS_IN_DAY));
    }

    /**
     * currentDate
     *
     * @return date
     */
    public static Date currentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * current year
     *
     * @return int
     */
    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Convert SQL Date from Util Date
     *
     * @param date
     * @return SQL  Date
     */
    public static java.sql.Date utilDateToSqlDate(Date date) {
        return date == null ? null : new java.sql.Date(date.getTime());
    }

    /**
     * Before 15 Days from currentDate
     *
     * @return date
     */
    public static Date before15Days() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, -15);

        return currentDate.getTime();
    }

    /**
     * Before 10 Days from currentDate
     *
     * @return date
     */
    public static Date before10Days() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, -10);

        return currentDate.getTime();
    }

    /**
     * Convert number(amount value) to words in
     *
     * @param value
     * @return
     */
    public static final String numberToWord(final BigDecimal value) {

        String numWord = "";
        String intValue = "";
        String precisionValue = "";
        char ch = '.';

        String number = value.setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString();

        if (isEmpty(number))
            return numWord;

        if (number.contains(ch + "")) {
            if (number.length() > 0) {
                for (int i = 0; i < number.length(); i++) {
                    if (ch == number.charAt(i)) {
                        intValue = number.substring(0, i);
                        precisionValue = number.substring(1 + i, i + 3);
                        break;
                    }
                }
            }
            numWord = numberToWord(Integer.parseInt(intValue)).concat(ONLY);
        } else {
            numWord = numberToWord(Integer.parseInt(number));
        }
        return numWord;
    }

    /**
     * Method to convert comma delimited string to list of string objects
     *
     * @param commaDelimitedString
     * @return list
     */
    public static List<String> commaDelimitedStringToList(String commaDelimitedString) {
        List<String> stringList = new ArrayList<String>();
        if (!isEmpty(commaDelimitedString)) {
            StringTokenizer st = new StringTokenizer(commaDelimitedString, ",");
            while (st.hasMoreTokens()) {
                stringList.add(st.nextToken().trim());
            }
        }
        return stringList;

    }

    /**
     * Convert Date in words
     *
     * @param date
     * @return
     */
    public static final String dateInWord(final Date date) {

        /**  Special Case to simplify later on */
        if (date == null) {
            return null;
        }

        final String months[] = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        String inWords = "";
        inWords = months[date.getMonth()];
        inWords = inWords + ", " + (1900 + date.getYear());
        return inWords;

    }

    /**
     * Convert number(integer value) to words
     *
     * @param number
     * @return
     */
    public static final String numberToWord(final int number) {
        return new InrUtil(number).getInrInWords();
    }

    /**
     * To generate random access code
     *
     * @return
     */
    public static String generateRandomAcessCode() {
        String RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int ACCESS_CODE_LENGTH = 8;
        return RandomStringUtils.random(ACCESS_CODE_LENGTH, RANDOM_CHARS);
    }

    /**
     * To create userName
     *
     * @return String
     */
    public static String createUserName(String eMail) {
        int i = eMail.indexOf('@');
        return eMail.substring(0, i);

    }

    /**
     * used to know whether required list is already placed in session based on id(LIKE "d-6948922-p") used by display tag
     *
     * @param reqParamsEnumeration
     * @return boolean
     */
    public static boolean reqParamExist(Enumeration<String> reqParamsEnumeration) {
        boolean listExist = false;
        String paramName;
        while (reqParamsEnumeration.hasMoreElements()) {
            paramName = reqParamsEnumeration.nextElement();
            if (paramName.startsWith("d-") && paramName.endsWith("-e")) {
                listExist = true;
                break;
            }
        }
        return listExist;
    }

    public static String getContextPath(HttpServletRequest servletRequest) {
        String reqUrl = servletRequest.getRequestURL().toString();
        String contextName = servletRequest.getContextPath();
        String contextPath = (reqUrl.substring(0, reqUrl.indexOf(contextName))) + contextName;
        return contextPath;


    }

    //================================= binaryToRoman
    public static String binaryToRoman(int binary) {
        if (binary <= 0 || binary >= 4000) {
            throw new NumberFormatException("Value outside roman numeral range.");
        }
        String roman = ""; // Roman notation will be accumualated here.

        // Loop from biggest value to smallest, successively subtracting,
        // from the binary value while adding to the roman representation.
        for (int i = 0; i < RCODE.length; i++) {
            while (binary >= BVAL[i]) {
                binary -= BVAL[i];
                roman += RCODE[i];
            }
        }
        return roman;
    }

    public static List<Employee> removeEmpsFromExcludedEmps(List<Employee> allEmps, String[] excludeEmps) {
        if (allEmps == null) {
            return null;
        }
        if (excludeEmps == null) {
            return allEmps;
        }
        List<Employee> empsToRemove = new ArrayList<Employee>();
        for (Employee emp : allEmps) {
            for (String excludeEmp : excludeEmps) {
                if ((excludeEmp != null) && (emp.getEmpId().equals(excludeEmp))) {
                    empsToRemove.add(emp);
                }
            }
        }
        allEmps.removeAll(empsToRemove);
        return allEmps;
    }

    /**
     * Check fromDate is older or equal to toDate with considering null objects
     *
     * @param fromDate
     * @param toDate
     * @return true or false
     */
    public static boolean isValidFromToDates(Date fromDate, Date toDate) {
        return fromDate.getTime() <= toDate.getTime();
    }

    /**
     * tomorrowDate for system date
     *
     * @return
     */
    public static Date tomorrowDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        try {
            return YYYY_MM_DD.parse(YYYY_MM_DD.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method calculates payable salary for no of worked days
     *
     * @param noOfDays       the worked days
     * @param grossSalary
     * @param settlementDate
     * @return BigDecimal
     */
    public static BigDecimal calculatePayableSalary(int noOfDays, BigDecimal grossSalary, Date settlementDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(settlementDate);
        BigDecimal payableSalary = BigDecimal.ZERO;
        return grossSalary.multiply(new BigDecimal(noOfDays)).divide(new BigDecimal(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, RoundingMode.HALF_UP).setScale(0, RoundingMode.HALF_UP).setScale(2);
    }

    public static BigDecimal calculateEmployeeSettlementPayableSalary(BigDecimal basic, BigDecimal hra, BigDecimal convyence, BigDecimal medicalAllowance, BigDecimal splAllowance, BigDecimal otherAllowance) {
        return BigDecimal.ZERO.add(basic).add(hra).add(convyence).add(medicalAllowance).add(splAllowance).add(otherAllowance).setScale(0, BigDecimal.ROUND_HALF_DOWN);
    }

}
