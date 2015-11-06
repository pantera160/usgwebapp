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
public class TurnoverRatioData implements JSONResponse{
    
    private double DIO;
    private double DSO;
    private double DPO;
    private int year;

    public double getDIO() {
        return DIO;
    }

    public void setDIO(double DIO) {
        this.DIO = Formulas.round(DIO, 1);
    }

    public double getDSO() {
        return DSO;
    }

    public void setDSO(double DSO) {
        this.DSO = Formulas.round(DSO, 1);
    }

    public double getDPO() {
        return DPO;
    }

    public void setDPO(double DPO) {
        this.DPO = Formulas.round(DPO, 1);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
