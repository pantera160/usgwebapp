/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.jsonrecources;

import be.usgictprofessionals.usgfinancewebapp.Model.Formulas;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pantera
 */
@XmlRootElement
public class ReturnRatioData implements JSONResponse{
    
    private double ROE;
    private double ROA;
    private int year;

    public double getROE() {
        return ROE;
    }

    public void setROE(double ROE) {
        this.ROE = Formulas.round(ROE, 3);
    }

    public double getROA() {
        return ROA;
    }

    public void setROA(double ROA) {
        this.ROA = Formulas.round(ROA, 3);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
