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
 * @author Thomas Straetmans
 * 
 * This class will serve as a data transfer class for the Balans Ratio page. It will include all the data needed for this page to make the transfer easier.
 * This data will client side be used to create a chart.
 */
@XmlRootElement
public class BalansRatioData implements JSONResponse{
    
    private double solvency;
    private double currRatio;
    private double quickRatio;
    private int year;

    public double getSolvency() {
        return solvency;
    }

    public void setSolvency(double solvency) {
        this.solvency = Formulas.round(solvency, 3);
    }

    public double getCurrRatio() {
        return currRatio;
    }

    public void setCurrRatio(double currRatio) {
        this.currRatio = Formulas.round(currRatio, 3);
    }

    public double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(double quickRatio) {
        this.quickRatio = Formulas.round(quickRatio, 3);
    }

    public double getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
}
