/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.usgictprofessionals.usgfinancewebapp.Model;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.BalansRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;


/**
 *
 * @author Pantera
 *This class will calculate all the fields required for the financial ratio's and all the calculated data shown on the input page. One class is required for every year.
 */
public class CalculatedDataResource {
    
    private double DIO, DSO, DPO, solvency, ebit, ROE, ROA, netFin, currRatio, quickRatio;
    private final InputData inputData;
    private int year;
    
    /**
     *
     * @param inputData
     * Constructor saves the inputData into a global variable and calls the calculation functions
     */
    public CalculatedDataResource(InputData inputData){
        this.inputData = inputData;
        this.year = inputData.getYear();
        initCalc();
        calcOverview();
    }
    
    /*
    *Will calculate all the data that is shown on the Input Page 
    */
    private void initCalc(){
          inputData.setRecIncome(Formulas.calcRecurringIncome(inputData.getEbit(), inputData.getFinRev(), inputData.getFinExp()));
          inputData.setNetIncome(Formulas.calcNetIncome(inputData.getRecIncome(), inputData.getNrIncome(), inputData.getNrCharges(), inputData.getTaxes()));
          inputData.setEbitda(Formulas.calcEBITDA(inputData.getEbit(), inputData.getDepreciation()));
          inputData.setWorkingCapital(Formulas.calcWorkingCapital(inputData.getCurrAssets(), inputData.getCurrLiabilities()));
          inputData.setNetDebt(Formulas.calcNetDebt(inputData.getLtFinDebt(), inputData.getStFinDebt(), inputData.getCash()));
    }
    
    /*
    *Will calculate all the data that is used on the different overview pages
    */
    private void calcOverview(){
        solvency = Formulas.calcSolvency(inputData.getEquity(), inputData.getTotAssets());
        ebit= Formulas.calcEBIT(inputData.getEbit(), inputData.getFinExp());
        netFin = Formulas.calcNetFin(inputData.getLtFinDebt(), inputData.getStFinDebt(), inputData.getCash(), inputData.getEbitda(), inputData.getNumberOfMonths());
        DIO = Formulas.calcDIO(inputData.getNumberOfMonths(), inputData.getInventory(), inputData.getCostOfSales());
        DSO = Formulas.calcDSO(inputData.getAr(), inputData.getNumberOfMonths(), inputData.getTurnover());
        DPO = Formulas.calcDPO(inputData.getNumberOfMonths(), inputData.getAp(), inputData.getCostOfSales());
        ROE = Formulas.calcROE(inputData.getNetIncome(), inputData.getEquity(), inputData.getNumberOfMonths());
        ROA = Formulas.calcROA(inputData.getNetIncome(), inputData.getTotAssets(), inputData.getNumberOfMonths());
        currRatio = Formulas.calcCurrRatio(inputData.getCurrAssets(), inputData.getCurrLiabilities());
        quickRatio = Formulas.calcQuickRatio(inputData.getCurrAssets(), inputData.getInventory(), inputData.getCurrLiabilities());
    }
    
    /**
     *
     * @return
     */
    public BalansRatioData getBalansData(){
        return new BalansRatioData();
    }

    /**
     *
     * @return
     */
    public double getSolvency() {
        return solvency;
    }

    /**
     *
     * @param solvency
     */
    public void setSolvency(double solvency) {
        this.solvency = solvency;
    }

    /**
     *
     * @return
     */
    public double getEbit() {
        return ebit;
    }

    /**
     *
     * @param ebit
     */
    public void setEbit(double ebit) {
        this.ebit = ebit;
    }

    /**
     *
     * @return
     */
    public double getNetFin() {
        return netFin;
    }

    /**
     *
     * @param netFin
     */
    public void setNetFin(double netFin) {
        this.netFin = netFin;
    }

    /**
     *
     * @return
     */
    public double getDIO() {
        return DIO;
    }

    /**
     *
     * @param DIO
     */
    public void setDIO(double DIO) {
        this.DIO = DIO;
    }

    /**
     *
     * @return
     */
    public double getDSO() {
        return DSO;
    }

    /**
     *
     * @param DSO
     */
    public void setDSO(double DSO) {
        this.DSO = DSO;
    }

    /**
     *
     * @return
     */
    public double getDPO() {
        return DPO;
    }

    /**
     *
     * @param DPO
     */
    public void setDPO(double DPO) {
        this.DPO = DPO;
    }

    /**
     *
     * @return
     */
    public double getROE() {
        return ROE;
    }

    /**
     *
     * @param ROE
     */
    public void setROE(double ROE) {
        this.ROE = ROE;
    }

    /**
     *
     * @return
     */
    public double getROA() {
        return ROA;
    }

    /**
     *
     * @param ROA
     */
    public void setROA(double ROA) {
        this.ROA = ROA;
    }

    /**
     *
     * @return
     */
    public double getCurrRatio() {
        return currRatio;
    }

    /**
     *
     * @param currRatio
     */
    public void setCurrRatio(double currRatio) {
        this.currRatio = currRatio;
    }

    /**
     *
     * @return
     */
    public double getQuickRatio() {
        return quickRatio;
    }

    /**
     *
     * @param quickRatio
     */
    public void setQuickRatio(double quickRatio) {
        this.quickRatio = quickRatio;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
     *
     * @return
     */
    public InputData getInputData(){
        return inputData;
    }
    
}
