package com.indianeagle.internal.form;

import java.math.BigDecimal;
import java.util.List;

public class ITForm {
    private String empNo;
    private List<BigDecimal> monthlyIT;

    /**
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }

    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    /**
     * @return the monthlyIT
     */
    public List<BigDecimal> getMonthlyIT() {
        return monthlyIT;
    }

    /**
     * @param monthlyIT the monthlyIT to set
     */
    public void setMonthlyIT(List<BigDecimal> monthlyIT) {
        this.monthlyIT = monthlyIT;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal it : this.monthlyIT) {
            total = total.add(it);
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
