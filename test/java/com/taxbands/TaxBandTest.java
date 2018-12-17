package com.taxbands;


import com.google.common.io.Resources;
import com.taxbands.taxbands.ProgressiveTaxCalculator;
import com.taxbands.taxbands.TaxBandFileReader;
import com.taxbands.taxbands.TaxBandList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxBandTest {
    private final static Logger log = LoggerFactory.getLogger(TaxBandTest.class);

    private static TaxBandList taxBands;

    @BeforeClass
    public static void load_tax_bands() throws IOException {
        String fileName = "tax_bands";
        File file = new File(Resources.getResource(fileName).getFile());
        TaxBandFileReader rt = new TaxBandFileReader(file);
        taxBands = rt.read();
    }

    @Test
    public void test_bands_loaded() {
        assertEquals(taxBands.size(), 4);
    }

    @Test
    public void test_salary_zero(){
        BigDecimal salary = BigDecimal.ZERO;
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = BigDecimal.ZERO;
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);

    }

    @Test
    public void test_band_zero(){
        BigDecimal salary = new BigDecimal(10000);
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = BigDecimal.ZERO;
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);

    }
    @Test
    public void test_band_one(){
        BigDecimal salary = new BigDecimal(12000);
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = new BigDecimal(200);
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);

    }

    @Test
    public void test_band_two(){
        BigDecimal salary = new BigDecimal(36000);
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = new BigDecimal(3200);
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);

    }

    @Test
    public void test_band_tree(){
        BigDecimal salary = new BigDecimal(59000);
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = new BigDecimal(8700);
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);
    }

    @Test
    public void test_band_four(){
        BigDecimal salary = new BigDecimal(110000);
        ProgressiveTaxCalculator progTaxCalulator  = new ProgressiveTaxCalculator(taxBands);
        BigDecimal taxPaid = new BigDecimal(26000);
        BigDecimal taxPaidCalc = progTaxCalulator.calTaxPaid(salary);
        log.info("Calculated taxes are {}, Actual taxes are {}", taxPaidCalc, taxPaid);
        assertEquals(taxPaid.compareTo(taxPaidCalc) ,0);
    }
}
