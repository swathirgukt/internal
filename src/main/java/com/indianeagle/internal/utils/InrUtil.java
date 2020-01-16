package com.indianeagle.internal.utils;
/*
 * ---------------------------------------------------------------------------
 * COPYRIGHT NOTICE
 * Copyright (c) 2011 by Yana Software (P) Limited.
 * All rights reserved. These materials are confidential and proprietary to
 * Yana Software (P) Limited.  No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or
 * any information storage or retrieval system), nor may the materials be
 * disclosed to third parties, or used in derivative works without the
 * express written authorization of Yana Software (P) Limited.
 * ---------------------------------------------------------------------------
 */

/**
 * Purpose of this class is to convert the given number into words as per Indian Terminology.
 * used for non negative  numbers lesser than  one Billion . Otherwise gives emptyString.
 *
 * @author Hari.Pondreti
 * @since Jul 1, 2011
 */
public class InrUtil {

    public static final String WORDS_1_9[] = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}; // single digits
    public static final String WORDS_10_19[] = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"}; // unfortunate special cases for ten, eleven, twelve, and teens
    public static final String WORDS_20_90[] = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; // tens
    public static final String WORDS_HUNDRED_100 = "Hundred";
    public static final String WORDS_THOUSAND_1000 = "Thousand";
    public static final String WORDS_LAC_100000 = "Lac";
    public static final String WORDS_CRORE_10000000 = "Crore";
    public static final String WORDS_BILLION_1000000000 = "Billion";
    public static final int NO_ZERO = 0;
    public static final int NO_TEN = 10;
    public static final int NO_HUNDRED = 100;
    public static final int NO_THOUSAND = 1000;
    public static final int NO_LAC = 100000;
    public static final int NO_CRORE = 10000000;
    public static final int NO_BILLION = 1000000000;
    public static final String SPACE = " ";
    private int inr;
    private StringBuffer inrInWords;

    public InrUtil() {
    }

    public InrUtil(int inr) {
        this.inr = inr;
    }

    private void convertNoToWords() {
        int no = getInr();
        int tempNo;

        if (no < NO_ZERO) {
        } else if (no == NO_ZERO) {
            inrInWords.append("Zero").append(SPACE);
        } else if (no > NO_BILLION) {
        } else {
            if (no > (NO_BILLION - 1)) { //999999999
                tempNo = no / NO_BILLION;
                wording(tempNo, WORDS_BILLION_1000000000);
                no = no % NO_BILLION;
            }
            if (no > (NO_CRORE - 1)) { //9999999
                tempNo = no / NO_CRORE;
                wording(tempNo, WORDS_CRORE_10000000);
                no = no % NO_CRORE;
            }
            if (no > (NO_LAC - 1)) { //99999
                tempNo = no / NO_LAC;
                wording(tempNo, WORDS_LAC_100000);
                no = no % NO_LAC;
            }
            if (no > (NO_THOUSAND - 1)) { //999
                tempNo = no / NO_THOUSAND;
                wording(tempNo, WORDS_THOUSAND_1000);
                no = no % NO_THOUSAND;
            }
            if (no > (NO_HUNDRED - 1)) { //99
                inrInWords.append(SPACE).append(WORDS_1_9[no / NO_HUNDRED]).append(SPACE).append(WORDS_HUNDRED_100).append(SPACE);
                no = no % NO_HUNDRED;
            }
            if (no > NO_ZERO) {
                wording(no, "");
            }
        }
    }

    private void wording(int n, String word) {
        if (n < NO_TEN) {
            inrInWords.append(WORDS_1_9[n]).append(SPACE).append(word);
        } else if (n < 20) {
            inrInWords.append(WORDS_10_19[n % NO_TEN]).append(SPACE).append(word);
        } else {
            if ((n % NO_TEN) != NO_ZERO)
                inrInWords.append(WORDS_20_90[n / NO_TEN]).append(SPACE).append(WORDS_1_9[n % NO_TEN]).append(SPACE).append(word);
            else
                inrInWords.append(WORDS_20_90[n / NO_TEN]).append(SPACE).append(word);
        }
        inrInWords.append(SPACE);
    }

    /**
     * @return the inr
     */
    public int getInr() {
        return inr;
    }

    /**
     * @param inr the inr to set
     */
    public void setInr(int inr) {
        this.inr = inr;
    }

    /**
     * @return the inrInWords
     */
    public String getInrInWords() {
        inrInWords = new StringBuffer();
        convertNoToWords();
        return inrInWords.toString().trim();
    }
}
