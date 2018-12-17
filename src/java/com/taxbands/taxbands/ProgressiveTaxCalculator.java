package com.taxbands.taxbands;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ProgressiveTaxCalculator {

    private final TaxBandList taxBandList;

    private final Map<TaxBand,BigDecimal> taxPaidMap = new HashMap<TaxBand, BigDecimal>();


    public ProgressiveTaxCalculator(TaxBandList taxBandList){
        this.taxBandList = taxBandList;
    }

    public BigDecimal calTaxPaid(BigDecimal salary){

        TaxBand currentTaxBand;
        BigDecimal taxedSalaryPart = BigDecimal.ZERO, salaryRest, taxPaidPart;
        salaryRest = salary;

        TaxBand prevTaxBand = this.taxBandList.getFirstTaxBand();

        salaryRest = salaryRest.subtract(prevTaxBand.salaryLowerLimit());

        for (TaxBand tb : this.taxBandList) {
            currentTaxBand = tb;
            if (salary.compareTo(currentTaxBand.salaryLowerLimit()) <= 0) {
                taxedSalaryPart = salaryRest;

            } else {
                taxedSalaryPart = currentTaxBand.salaryLowerLimit().subtract(prevTaxBand.salaryLowerLimit());
            }

            taxPaidPart = taxedSalaryPart.multiply(prevTaxBand.percent().multiply(new BigDecimal(0.01)))
                        .setScale(2, BigDecimal.ROUND_HALF_EVEN);

            taxPaidMap.put(prevTaxBand, taxPaidPart);

            salaryRest = salaryRest.subtract(taxedSalaryPart);

            prevTaxBand = currentTaxBand;
        }
        if (salaryRest.compareTo(BigDecimal.ZERO)>0){
            taxPaidPart = salaryRest.multiply(prevTaxBand.percent().multiply(new BigDecimal(0.01)))
                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
            taxPaidMap.put(prevTaxBand, taxPaidPart);
        }


        return taxPaidMap.entrySet().stream().map(Map.Entry::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTaxPaidSumByBand(TaxBand taxBand){
        return taxPaidMap.entrySet().stream().filter((tb)->tb.getKey().name().equals(taxBand.name()))
                .map(Map.Entry::getValue).findFirst().orElse(BigDecimal.ZERO);
    }
}

