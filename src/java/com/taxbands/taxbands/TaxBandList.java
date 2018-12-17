package com.taxbands.taxbands;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TaxBandList implements Iterable<TaxBand>{

    private final TreeSet<TaxBand> taxBandList = new TreeSet<TaxBand>(new TaxBandComparator());
    //private Iterator<TaxBand> iterator;

    public TaxBandList(){ }

    public TaxBandList(Set<TaxBand> set){
        this.taxBandList.addAll(set);
        //this.iterator = this.taxBandList.iterator();
    }

    public long size(){
        return this.taxBandList.stream().count();
    }

    public void addBand(TaxBand taxBand ){
        taxBandList.add(taxBand);
    }

    public TaxBand getLastTaxBand(){
        return  this.taxBandList.stream()
                .reduce((f,l)-> l).orElse(new TaxBand(null,BigDecimal.ZERO, BigDecimal.ZERO));

    }

    public TaxBand getFirstTaxBand() {
        return this.taxBandList.stream()
                .findFirst().orElse(new TaxBand(null, BigDecimal.ZERO, BigDecimal.ZERO));
    }


    public TaxBandList filterBySalary(BigDecimal salary) {
        Set<TaxBand> filtered = this.taxBandList.stream().filter((tb) -> salary.compareTo(tb.salaryLowerLimit()) >= 0).collect(Collectors.toSet());
        return new TaxBandList(filtered);
    }

    public TaxBand getTaxBandByName(String bandName){
        return this.taxBandList.stream().filter((tb) -> bandName.equals(tb.name())).findFirst().orElse(null);
    }


    public TaxBand next(){
        return this.iterator().next();
    }

    public boolean hasNext(){
        return this.iterator().hasNext();
    }

    @Override
    public Iterator<TaxBand> iterator() {
        return this.taxBandList.iterator();
    }
}
