/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.agnos.molap.driver;

import hu.agnos.molap.driver.zolikaokos.Result;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author parisek
 */
public class ResultSet implements java.io.Serializable {

    private static final long serialVersionUID = -8940196742313994740L;
    private final String name;
    private List<Result> response;
    

    public ResultSet(String name) {
        this.name = name;
        this.response = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Result> getResponse() {
        return response;
    }

    public void addResponse(Result result) {
        this.response.add(result);
    }

    public void setResponse(List<Result> response) {
        this.response = response;
    }
    
    
   
   
}
