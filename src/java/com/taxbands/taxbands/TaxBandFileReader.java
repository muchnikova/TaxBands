package com.taxbands.taxbands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class TaxBandFileReader {

    private File file;

    public TaxBandFileReader(File file){
        this.file = file;
    }

    public TaxBandList read() throws IOException {

        BufferedReader br  = new BufferedReader(new FileReader(file));
        TaxBandList taxBandList = new TaxBandList();
        String st;
        while((st = br.readLine())!= null){
            String[] tokens = st.split(",");
            TaxBand taxBand = new TaxBand(tokens[0], new BigDecimal(tokens[1]),new BigDecimal(tokens[2]));
            taxBandList.addBand(taxBand);
        }
        return taxBandList;
    }

}
