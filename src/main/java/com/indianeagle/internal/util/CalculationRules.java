package com.indianeagle.internal.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Rules for Salary Calculation
 *
 * @author vasanth.kumar
 */
public class CalculationRules {
    public static final BigDecimal PT_PAID_ON = new BigDecimal(6000);
    public static final BigDecimal ELIGIBLE_ESI_AMOUNT = new BigDecimal(15000);
    public static final BigDecimal PER_ESI = new BigDecimal(0.75);
    public static final BigDecimal PER_ORG_ESI = new BigDecimal(3.25);
    public static final BigDecimal ELIGIBLE_PF_AMOUNT = new BigDecimal(15000);
    public static final BigDecimal MIN_PF_AMOUNT = new BigDecimal(1800);
    public static final BigDecimal PER_PF = new BigDecimal(12);
    public static final BigDecimal PER_AC_1_ORG_CONTRIBUTION = new BigDecimal(3.67);
    public static final BigDecimal PER_AC_1_EMP_CONTRIBUTION = new BigDecimal(12);
    public static final BigDecimal PER_AC_2_ADM_CHARGES = new BigDecimal(1.1);
    public static final BigDecimal PER_AC_10_ORG_CONTRIBUTION = new BigDecimal(8.33);
    public static final BigDecimal PER_AC_21_ORG_CONTRIBUTION = new BigDecimal(0.5);
    public static final BigDecimal PER_AC_22_ADM_CHARGES = new BigDecimal(0.01);
    public static final HashMap<Integer, List<BigDecimal>> PF_CALC = new HashMap();
    public static final LinkedHashMap<Integer, Integer> PT_RANGE = new LinkedHashMap();

    static {
        PT_RANGE.put(0, 0);
        PT_RANGE.put(1500, 0);
        PT_RANGE.put(2000, 0);
        PT_RANGE.put(3000, 0);
        PT_RANGE.put(4000, 0);
        PT_RANGE.put(5000, 0);
        PT_RANGE.put(6000, 0);
        PT_RANGE.put(10000, 0);
        PT_RANGE.put(15000, 150);
        PT_RANGE.put(20000, 200);
        ArrayList<BigDecimal> account_1 = new ArrayList<BigDecimal>();
        account_1.add(new BigDecimal(3.67));
        account_1.add(new BigDecimal(12));
        account_1.add(new BigDecimal(0));
        account_1.add(new BigDecimal(12.5));
        ArrayList<BigDecimal> account_2 = new ArrayList<BigDecimal>();
        account_2.add(new BigDecimal(0));
        account_2.add(new BigDecimal(0));
        account_2.add(new BigDecimal(1.1));
        ArrayList<BigDecimal> account_10 = new ArrayList<BigDecimal>();
        account_10.add(new BigDecimal(8.33));
        account_10.add(new BigDecimal(0));
        account_10.add(new BigDecimal(0));
        ArrayList<BigDecimal> account_21 = new ArrayList<BigDecimal>();
        account_21.add(new BigDecimal(0.5));
        account_21.add(new BigDecimal(0));
        account_21.add(new BigDecimal(0));
        ArrayList<BigDecimal> account_22 = new ArrayList<BigDecimal>();
        account_22.add(new BigDecimal(0));
        account_22.add(new BigDecimal(0));
        account_22.add(new BigDecimal(0.01));
        PF_CALC.put(1, account_1);
        PF_CALC.put(2, account_2);
        PF_CALC.put(3, account_10);
        PF_CALC.put(4, account_21);
        PF_CALC.put(5, account_22);
    }
}