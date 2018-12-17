package com.taxbands.taxbands;

import java.math.BigDecimal;

public final class TaxBand {

    private final String name;

    private final BigDecimal percent;

    private final BigDecimal salaryLowerLimit;

    public TaxBand(String name, BigDecimal salaryLowerLimit, BigDecimal percent){
        this.name = name;
        this.salaryLowerLimit = salaryLowerLimit;
        this.percent = percent;
    }

    public String name(){
        return this.name;
    }

    public BigDecimal percent() {
        return percent;
    }

    public BigDecimal salaryLowerLimit() {
        return salaryLowerLimit;
    }
    public String toString(){
        return "Name: " + this.name() + " Percent: "+ this.percent().toString() +
                " Salary Is Over: " + this.salaryLowerLimit().toString();
    }

}
