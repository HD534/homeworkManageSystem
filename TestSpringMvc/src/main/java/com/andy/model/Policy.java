package com.andy.model;

import java.math.BigDecimal;
import java.util.Date;

public class Policy {
    private Long policyid;

    private String policycode;

    private BigDecimal policyproductsumamt;

    private Date transactiondate;

    public Long getPolicyid() {
        return policyid;
    }

    public void setPolicyid(Long policyid) {
        this.policyid = policyid;
    }

    public String getPolicycode() {
        return policycode;
    }

    public void setPolicycode(String policycode) {
        this.policycode = policycode;
    }

    public BigDecimal getPolicyproductsumamt() {
        return policyproductsumamt;
    }

    public void setPolicyproductsumamt(BigDecimal policyproductsumamt) {
        this.policyproductsumamt = policyproductsumamt;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }
}