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
public class CoverageRatioData implements JSONResponse{
    
    private double EBITFinExp;
    private double NetDebtEBITDA;
    private int year;

    public double getEBITFinExp() {
        return EBITFinExp;
    }

    public void setEBITFinExp(double EBITFinExp) {
        this.EBITFinExp = Formulas.round(EBITFinExp, 1);
    }

    public double getNetDebtEBITDA() {
        return NetDebtEBITDA;
    }

    public void setNetDebtEBITDA(double NetDebtEBITDA) {
        this.NetDebtEBITDA = Formulas.round(NetDebtEBITDA, 1);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
}
