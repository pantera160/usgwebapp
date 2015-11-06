/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.jsonrecources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pantera
 */
@XmlRootElement
public class Sector {
    
    private String sectorName;
    private double DSO, DPO, DIO, currRatio, quickRatio, solvency, compIncome, finExp, ebitfin, debtebitda, roe, roa, yearProfit, totAssets;
    private int source, source2;
    
    public Sector(){
    }

    public double getDSO() {
        return DSO;
    }

    public void setDSO(double DSO) {
        this.DSO = DSO;
    }

    public double getDPO() {
        return DPO;
    }

    public void setDPO(double DPO) {
        this.DPO = DPO;
    }

    public double getDIO() {
        return DIO;
    }

    public void setDIO(double DIO) {
        this.DIO = DIO;
    }

    public double getCurrRatio() {
        return currRatio;
    }

    public void setCurrRatio(double currRatio) {
        this.currRatio = currRatio;
    }

    public double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(double quickRatio) {
        this.quickRatio = quickRatio;
    }

    public double getSolvency() {
        return solvency;
    }

    public void setSolvency(double solvency) {
        this.solvency = solvency;
    }

    public double getCompIncome() {
        return compIncome;
    }

    public void setCompIncome(double compIncome) {
        this.compIncome = compIncome;
    }

    public double getFinExp() {
        return finExp;
    }

    public void setFinExp(double finExp) {
        this.finExp = finExp;
    }

    public double getEbitfin() {
        return ebitfin;
    }

    public void setEbitfin(double ebitfin) {
        this.ebitfin = ebitfin;
    }

    public double getDebtebitda() {
        return debtebitda;
    }

    public void setDebtebitda(double debtebitda) {
        this.debtebitda = debtebitda;
    }

    public double getRoe() {
        return roe;
    }

    public void setRoe(double roe) {
        this.roe = roe;
    }

    public double getRoa() {
        return roa;
    }

    public void setRoa(double roa) {
        this.roa = roa;
    }

    public double getYearProfit() {
        return yearProfit;
    }

    public void setYearProfit(double yearProfit) {
        this.yearProfit = yearProfit;
    }

    public double getTotAssets() {
        return totAssets;
    }

    public void setTotAssets(double totAssets) {
        this.totAssets = totAssets;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSource2() {
        return source2;
    }

    public void setSource2(int source2) {
        this.source2 = source2;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
    
    
}
