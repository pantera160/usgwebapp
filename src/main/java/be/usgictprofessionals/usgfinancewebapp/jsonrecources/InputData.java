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
public class InputData {

    //Data from the input fields from the main page
    private double recIncome, netIncome, ebitda;
    private double turnover, costOfSales, comMatCon, miscGoods, depreciation, ebit, finRev, finExp, finExpInterest, finExpBank, finExpOther, nrIncome, nrCharges, taxes, incomeTaxes, withDefTaxes, transDefTaxes;
    private double workingCapital, netDebt;
    private double fixedAssets, inventory, ar, cash, investments, liquidAssets, currAssets, totAssets, equity, ltFinDebt, subordinatedDebt,  stFinDebt, longTermLoans, finDebt, ap, currLiabilities;
    private double propertyAssets, finFixedAssets, intangiblesAssets;
    private int numberOfMonths;
    private int year;

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getCostOfSales() {
        return costOfSales;
    }

    public void setCostOfSales(double costOfSales) {
        this.costOfSales = costOfSales;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(double depreciation) {
        this.depreciation = depreciation;
    }

    public double getEbit() {
        return ebit;
    }

    public void setEbit(double ebit) {
        this.ebit = ebit;
    }

    public double getFinRev() {
        return finRev;
    }

    public void setFinRev(double finRev) {
        this.finRev = finRev;
    }

    public double getFinExp() {
        return finExp;
    }

    public void setFinExp(double finExp) {
        this.finExp = finExp;
    }

    public double getFinExpInterest() {
        return finExpInterest;
    }

    public void setFinExpInterest(double finExpInterest) {
        this.finExpInterest = finExpInterest;
    }

    public double getFinExpBank() {
        return finExpBank;
    }

    public void setFinExpBank(double finExpBank) {
        this.finExpBank = finExpBank;
    }

    public double getNrIncome() {
        return nrIncome;
    }

    public void setNrIncome(double nrIncome) {
        this.nrIncome = nrIncome;
    }

    public double getNrCharges() {
        return nrCharges;
    }

    public void setNrCharges(double nrCharges) {
        this.nrCharges = nrCharges;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCurrAssets() {
        return currAssets;
    }

    public void setCurrAssets(double currAssets) {
        this.currAssets = currAssets;
    }

    public double getEquity() {
        return equity;
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }

    public double getLtFinDebt() {
        return ltFinDebt;
    }

    public void setLtFinDebt(double ltFinDebt) {
        this.ltFinDebt = ltFinDebt;
    }

    public double getStFinDebt() {
        return stFinDebt;
    }

    public void setStFinDebt(double stFinDebt) {
        this.stFinDebt = stFinDebt;
    }

    public double getAp() {
        return ap;
    }

    public void setAp(double ap) {
        this.ap = ap;
    }

    public double getCurrLiabilities() {
        return currLiabilities;
    }

    public void setCurrLiabilities(double currLiabilities) {
        this.currLiabilities = currLiabilities;
    }

    public double getTotAssets() {
        return totAssets;
    }

    public void setTotAssets(double totAssets) {
        this.totAssets = totAssets;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRecIncome() {
        return recIncome;
    }

    public void setRecIncome(double recIncome) {
        this.recIncome = recIncome;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    public double getEbitda() {
        return ebitda;
    }

    public void setEbitda(double ebitda) {
        this.ebitda = ebitda;
    }

    public double getFinExpOther() {
        return finExpOther;
    }

    public void setFinExpOther(double finExpOther) {
        this.finExpOther = finExpOther;
    }

    public double getWorkingCapital() {
        return workingCapital;
    }

    public void setWorkingCapital(double workingCapital) {
        this.workingCapital = workingCapital;
    }

    public double getNetDebt() {
        return netDebt;
    }

    public void setNetDebt(double netDebt) {
        this.netDebt = netDebt;
    }

    public double getPropertyAssets() {
        return propertyAssets;
    }

    public void setPropertyAssets(double propertyAssets) {
        this.propertyAssets = propertyAssets;
    }

    public double getFinFixedAssets() {
        return finFixedAssets;
    }

    public void setFinFixedAssets(double finFixedAssets) {
        this.finFixedAssets = finFixedAssets;
    }

    public double getIntangiblesAssets() {
        return intangiblesAssets;
    }

    public void setIntangiblesAssets(double intangiblesAssets) {
        this.intangiblesAssets = intangiblesAssets;
    }

    public double getSubordinatedDebt() {
        return subordinatedDebt;
    }

    public void setSubordinatedDebt(double subordinatedDebt) {
        this.subordinatedDebt = subordinatedDebt;
    }

    public double getComMatCon() {
        return comMatCon;
    }

    public void setComMatCon(double comMatCon) {
        this.comMatCon = comMatCon;
    }

    public double getMiscGoods() {
        return miscGoods;
    }

    public void setMiscGoods(double miscGoods) {
        this.miscGoods = miscGoods;
    }

    public double getIncomeTaxes() {
        return incomeTaxes;
    }

    public void setIncomeTaxes(double incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }

    public double getWithDefTaxes() {
        return withDefTaxes;
    }

    public void setWithDefTaxes(double withDefTaxes) {
        this.withDefTaxes = withDefTaxes;
    }

    public double getTransDefTaxes() {
        return transDefTaxes;
    }

    public void setTransDefTaxes(double transDefTaxes) {
        this.transDefTaxes = transDefTaxes;
    }

    public double getInvestments() {
        return investments;
    }

    public void setInvestments(double investments) {
        this.investments = investments;
    }

    public double getLiquidAssets() {
        return liquidAssets;
    }

    public void setLiquidAssets(double liquidAssets) {
        this.liquidAssets = liquidAssets;
    }

    public double getLongTermLoans() {
        return longTermLoans;
    }

    public void setLongTermLoans(double longTermLoans) {
        this.longTermLoans = longTermLoans;
    }

    public double getFinDebt() {
        return finDebt;
    }

    public void setFinDebt(double finDebt) {
        this.finDebt = finDebt;
    }
}
