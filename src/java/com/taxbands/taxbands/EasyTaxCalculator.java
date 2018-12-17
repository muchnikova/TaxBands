package com.taxbands.taxbands;

import java.math.BigDecimal;

public class EasyTaxCalculator {

    private final TaxBandList taxBandList;

    public EasyTaxCalculator(TaxBandList taxBandList){
        this.taxBandList = taxBandList;
    }

    public BigDecimal taxPaid(BigDecimal salary){
        TaxBand tb = taxBandList.getLastTaxBand();
         return salary.multiply(tb.percent()).divide(new BigDecimal(100));
    }
}

