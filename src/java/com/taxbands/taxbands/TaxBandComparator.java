package com.taxbands.taxbands;

import java.util.Comparator;

public class TaxBandComparator implements Comparator<TaxBand> {

    @Override
    public int compare(TaxBand taxBand1, TaxBand taxBand2) {
        return taxBand1.salaryLowerLimit().subtract(taxBand2.salaryLowerLimit()).intValue();
    }
}
