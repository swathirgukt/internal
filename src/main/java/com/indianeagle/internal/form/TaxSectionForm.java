package com.indianeagle.internal.form;

import com.indianeagle.internal.form.vo.TaxSectionDeclarationVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: kalesha
 * Date: 8/11/2017
 */

@Component
public class TaxSectionForm {

    private Long id;
    private String sectionName;
    private BigDecimal sectionLimit;
    private List<TaxSectionDeclarationVO> taxSectionDeclarationVOS;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public BigDecimal getSectionLimit() {
        return sectionLimit;
    }

    public void setSectionLimit(BigDecimal sectionLimit) {
        this.sectionLimit = sectionLimit;
    }

    public List<TaxSectionDeclarationVO> getTaxSectionDeclarationVOS() {
        return taxSectionDeclarationVOS;
    }

    public void setTaxSectionDeclarationVOS(List<TaxSectionDeclarationVO> taxSectionDeclarationVOS) {
        this.taxSectionDeclarationVOS = taxSectionDeclarationVOS;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TaxSectionForm{" +
                "id=" + id +
                ", sectionName='" + sectionName + '\'' +
                ", sectionLimit=" + sectionLimit +
                ", taxSectionDeclarationVOS=" + taxSectionDeclarationVOS +
                ", active=" + active +
                '}';
    }
}
