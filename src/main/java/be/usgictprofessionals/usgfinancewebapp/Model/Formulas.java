/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.usgictprofessionals.usgfinancewebapp.Model;

/**
 *
 * @author Pantera
 */
public class Formulas {

    /*
     Formulas to calculate the data for the input page
     */

    /**
     *
     * @param equity 
     * @param totAssets
     * @return
     */
    
    public static double calcSolvency(double equity, double totAssets) {
        return round(equity / totAssets, 3);
    }

    /**
     *
     * @param ebit
     * @param finExp
     * @return
     */
    public static double calcEBIT(double ebit, double finExp) {
        return round(ebit / finExp, 1);
    }

    /**
     *
     * @param ltFin
     * @param stFin
     * @param cash
     * @param ebitda
     * @param numberOfMonths
     * @return
     */
    public static double calcNetFin(double ltFin, double stFin, double cash, double ebitda, int numberOfMonths) {
        return round(((ltFin + stFin - cash) / ebitda)*numberOfMonths/12, 1);
    }

    /**
     *
     * @param numberMonths
     * @param inventory
     * @param costOfSales
     * @return
     */
    public static double calcDIO(double numberMonths, double inventory, double costOfSales) {
        return round((numberMonths * inventory * 365) / (12 * costOfSales), 1);
    }

    /**
     *
     * @param ar
     * @param numberMonths
     * @param turnover
     * @return
     */
    public static double calcDSO(double ar, double numberMonths, double turnover) {
        return round((ar * numberMonths * 365) / (turnover * 12), 1);
    }

    /**
     *
     * @param numberMonths
     * @param ap
     * @param costOfSales
     * @return
     */
    public static double calcDPO(double numberMonths, double ap, double costOfSales) {
        return round((numberMonths * 365 * ap) / (costOfSales * 12), 1);
    }

    /**
     *
     * @param netIncome
     * @param equity
     * @param numberOfMonths
     * @return
     */
    public static double calcROE(double netIncome, double equity, int numberOfMonths) {
        return round((netIncome / equity)*12/numberOfMonths, 3);
    }

    /**
     *
     * @param netIncome
     * @param assets
     * @return
     */
    public static double calcROA(double netIncome, double assets, int numberOfMonths) {
        return round((netIncome / assets)*12/numberOfMonths, 3);
    }

    /**
     *
     * @param currAssets
     * @param currLiability
     * @return
     */
    public static double calcCurrRatio(double currAssets, double currLiability) {
        return round(currAssets / currLiability, 3);
    }

    /**
     *
     * @param currAssets
     * @param inventory
     * @param currLiability
     * @return
     */
    public static double calcQuickRatio(double currAssets, double inventory, double currLiability) {
        return round((currAssets - inventory) / currLiability, 3);
    }

    /**
     *
     * @param ebit
     * @param finRev
     * @param finExp
     * @return
     */
    public static double calcRecurringIncome(double ebit, double finRev, double finExp) {
        return round(ebit + finRev - finExp, 1);
    }

    /**
     *
     * @param recurringIncome
     * @param nrIncome
     * @param nrCharges
     * @param taxes
     * @return
     */
    public static double calcNetIncome(double recurringIncome, double nrIncome, double nrCharges, double taxes) {
        return round(recurringIncome + nrIncome - nrCharges - taxes, 1);
    }

    /**
     *
     * @param EBIT
     * @param deprication
     * @return
     */
    public static double calcEBITDA(double EBIT, double deprication) {
        return round(EBIT + deprication, 1);
    }

    /**
     *
     * @param ltFinDebt
     * @param stFinDebt
     * @param cash
     * @return
     */
    public static double calcNetDebt(double ltFinDebt, double stFinDebt, double cash) {
        return round(ltFinDebt + stFinDebt - cash, 2);
    }

    /**
     *
     * @param currAssets
     * @param currLiability
     * @return
     */
    public static double calcWorkingCapital(double currAssets, double currLiability) {
        return round(currAssets - currLiability, 2);
    }

    /*
     Formulas to calculate the init data for the WCM page
     */

    /**
     *
     * @param inv
     * @param ar
     * @param ap
     * @param numberMonths
     * @param turnover
     * @return
     */
    
    public static double calcCCCDays(double inv, double ar, double ap, double numberMonths, double turnover) {
        return round((inv + ar - ap) * 365 * numberMonths / (turnover * 12), 2);
    }

    /**
     *
     * @param inv
     * @param ar
     * @param ap
     * @return
     */
    public static double calcCCC(double inv, double ar, double ap) {
        return round(inv + ar - ap, 2);
    }

    /*
     Formulas to calculate the data after submit for the WCM page    
     */

    /**
     *
     * @param DIO
     * @param costOfSale
     * @param numberOfMonths
     * @return
     */
    
    public static double calcInvTarget(double DIO, double costOfSale, int numberOfMonths) {
        return round((DIO * costOfSale / 365)*12/numberOfMonths, 2);
    }

    /**
     *
     * @param DSO
     * @param turnover
     * @param numberOfMonths
     * @return
     */
    public static double calcARTarget(double DSO, double turnover, int numberOfMonths) {
        return round((DSO * turnover / 365)*12/numberOfMonths, 2);
    }

    /**
     *
     * @param DPO
     * @param costOfSale
     * @param numberOfMonths
     * @return
     */
    public static double calcAPTarget(double DPO, double costOfSale, int numberOfMonths) {
        return round((DPO * costOfSale / 365)*12/numberOfMonths, 2);
    }

    /**
     *
     * @param netDebt
     * @param CCCsavings
     * @param debtInterest
     * @return
     */
    public static double calcDebitInterest(double netDebt, double CCCsavings, double debtInterest) {
        if (netDebt > 0) {
            return round(CCCsavings * debtInterest, 4);
        } else {
            return 0;
        }
    }

    /**
     *
     * @param netDebt
     * @param CCCsavings
     * @param creditInterest
     * @return
     */
    public static double calcCreditInterest(double netDebt, double CCCsavings, double creditInterest) {
        if (netDebt > 0) {
            return 0;
        } else {
            return round(CCCsavings * creditInterest, 4);
        }
    }

    /**
     *
     * @param arSaving
     * @param costAR
     * @return
     */
    public static double calcCostSavingAR(double arSaving, double costAR) {
        return round(arSaving * costAR, 4);
    }

    /**
     *
     * @param apSaving
     * @param costAP
     * @return
     */
    public static double calcCostSavingAP(double apSaving, double costAP) {
        return round(apSaving * costAP, 4);
    }

    /**
     *
     * @param invSaving
     * @param costInv
     * @return
     */
    public static double calcCostSavingInv(double invSaving, double costInv) {
        return round(invSaving * costInv, 4);
    }
    
    /**
     * 
     * @param value
     * @param precision
     * @return
     */
    public static double round (double value, int precision) {
    int scale = (int) Math.pow(10, precision);
    return (double) Math.round(value * scale) / scale;
}
}
