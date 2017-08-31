package hu.agnos.molap.driver.zolikaokos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ruzsaz
 */
public class Result {
    
    private final String[] header;
    private final int drillVectorId;
   
    private final String[] measureValues;

    public Result(String[] header, String[] measureValues, int drillVectorId) {
        this.header = header;
        this.measureValues = measureValues;
        this.drillVectorId = drillVectorId;
    }

    public String[] getHeader() {
        return header;
    }

    public String[] getMeasureValues() {
        return measureValues;
    }

    public int getDrillVectorId() {
        return drillVectorId;
    }

    @Override
    public String toString() {
        String result = "Result{" + "header=\n\t";
        for(String s : header){
            result += s+",";
        }
        result += "\b\tdrillVectorId="+ drillVectorId + ", \n\tmeasureValues=";
        for(String s : measureValues){
            result += s+",";
        }
        result += ")";
        return result;
    }

   
    
        
}
