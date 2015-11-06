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
 * Deze class wordt gebruikt voor alle communicatie tussen server en client ivm de WCM pagina.
 * De opmaak van deze class is gelijk aan deze van de data variable in JS.
 */
@XmlRootElement
public class WCMData {
    
    private double DIO, DSO, DPO, CCCDay = 0.0;
    private double targetDIO, targetDSO, targetDPO, CCCDayTarget, CCCSavingsDay = 0.0;
    
    private double arCurrent, apCurrent, invCurrent = 0.0;
    private double arTarget, apTarget, invTarget = 0.0;
    private double arSavings, apSavings, invSavings = 0.0;
    private double CCCEuroCurrent, CCCEuroTarget, CCCSavingsEuro = 0.0;
    
    private double debitInterest, creditInterest, costAR, costAP, costInv = 0.0;
    
    private double debitInterestSavings, creditInterestSavings = 0.0;
    private double costARSavings, costAPSavings, CostInvSavings = 0.0;
    private double totalSavings = 0.0;

    public double getDIO() {
        return DIO;
    }

    public void setDIO(double DIO) {
        this.DIO = Formulas.round(DIO, 4);
    }

    public double getDSO() {
        return DSO;
    }

    public void setDSO(double DSO) {
        this.DSO = Formulas.round(DSO, 2);
    }

    public double getDPO() {
        return DPO;
    }

    public void setDPO(double DPO) {
        this.DPO = Formulas.round(DPO, 2);
    }

    public double getCCCDay() {
        return CCCDay;
    }

    public void setCCCDay(double CCCDay) {
        this.CCCDay = Formulas.round(CCCDay, 2);
    }

    public double getTargetDIO() {
        return targetDIO;
    }

    public void setTargetDIO(double targetDIO) {
        this.targetDIO = Formulas.round(targetDIO, 2);
    }

    public double getTargetDSO() {
        return targetDSO;
    }

    public void setTargetDSO(double targetDSO) {
        this.targetDSO = Formulas.round(targetDSO, 2);
    }

    public double getTargetDPO() {
        return targetDPO;
    }

    public void setTargetDPO(double targetDPO) {
        this.targetDPO = Formulas.round(targetDPO, 4);
    }

    public double getCCCDayTarget() {
        return CCCDayTarget;
    }

    public void setCCCDayTarget(double CCCDayTarget) {
        this.CCCDayTarget = Formulas.round(CCCDayTarget, 2);
    }

    public double getCCCSavingsDay() {
        return CCCSavingsDay;
    }

    public void setCCCSavingsDay(double CCCSavingsDay) {
        this.CCCSavingsDay = Formulas.round(CCCSavingsDay, 2);
    }

    public double getArCurrent() {
        return arCurrent;
    }

    public void setArCurrent(double arCurrent) {
        this.arCurrent = Formulas.round(arCurrent, 2);
    }

    public double getApCurrent() {
        return apCurrent;
    }

    public void setApCurrent(double apCurrent) {
        this.apCurrent = Formulas.round(apCurrent, 2);
    }

    public double getInvCurrent() {
        return invCurrent;
    }

    public void setInvCurrent(double invCurrent) {
        this.invCurrent = Formulas.round(invCurrent, 2);
    }

    public double getArTarget() {
        return arTarget;
    }

    public void setArTarget(double arTarget) {
        this.arTarget = Formulas.round(arTarget, 2);
    }

    public double getApTarget() {
        return apTarget;
    }

    public void setApTarget(double apTarget) {
        this.apTarget = Formulas.round(apTarget, 2);
    }

    public double getInvTarget() {
        return invTarget;
    }

    public void setInvTarget(double invTarget) {
        this.invTarget = Formulas.round( invTarget, 2);
    }

    public double getArSavings() {
        return arSavings;
    }

    public void setArSavings(double arSavings) {
        this.arSavings = Formulas.round(arSavings, 2);
    }

    public double getApSavings() {
        return apSavings;
    }

    public void setApSavings(double apSavings) {
        this.apSavings = Formulas.round(apSavings, 2);
    }

    public double getInvSavings() {
        return invSavings;
    }

    public void setInvSavings(double invSavings) {
        this.invSavings = Formulas.round(invSavings, 2);
    }

    public double getCCCEuroCurrent() {
        return CCCEuroCurrent;
    }

    public void setCCCEuroCurrent(double CCCEuroCurrent) {
        this.CCCEuroCurrent = Formulas.round(CCCEuroCurrent, 2);
    }

    public double getCCCEuroTarget() {
        return CCCEuroTarget;
    }

    public void setCCCEuroTarget(double CCCEuroTarget) {
        this.CCCEuroTarget = Formulas.round(CCCEuroTarget, 2);
    }

    public double getCCCSavingsEuro() {
        return CCCSavingsEuro;
    }

    public void setCCCSavingsEuro(double CCCSavingsEuro) {
        this.CCCSavingsEuro = Formulas.round(CCCSavingsEuro, 2);
    }

    public double getDebitInterest() {
        return debitInterest;
    }

    public void setDebitInterest(double debitInterest) {
        this.debitInterest = Formulas.round(debitInterest, 2);
    }

    public double getCreditInterest() {
        return creditInterest;
    }

    public void setCreditInterest(double creditInterest) {
        this.creditInterest = Formulas.round(creditInterest, 2);
    }

    public double getCostAR() {
        return costAR;
    }

    public void setCostAR(double costAR) {
        this.costAR = Formulas.round(costAR, 2);
    }

    public double getCostAP() {
        return costAP;
    }

    public void setCostAP(double costAP) {
        this.costAP = Formulas.round(costAP, 2);
    }

    public double getCostInv() {
        return costInv;
    }

    public void setCostInv(double costInv) {
        this.costInv = Formulas.round(costInv, 2);
    }

    public double getDebitInterestSavings() {
        return debitInterestSavings;
    }

    public void setDebitInterestSavings(double debitInterestSavings) {
        this.debitInterestSavings = Formulas.round(debitInterestSavings, 2);
    }

    public double getCreditInterestSavings() {
        return creditInterestSavings;
    }

    public void setCreditInterestSavings(double creditInterestSavings) {
        this.creditInterestSavings = Formulas.round(creditInterestSavings, 2);
    }

    public double getCostARSavings() {
        return costARSavings;
    }

    public void setCostARSavings(double costARSavings) {
        this.costARSavings = Formulas.round(costARSavings, 2);
    }

    public double getCostAPSavings() {
        return costAPSavings;
    }

    public void setCostAPSavings(double costAPSavings) {
        this.costAPSavings = Formulas.round(costAPSavings, 2);
    }

    public double getCostInvSavings() {
        return CostInvSavings;
    }

    public void setCostInvSavings(double CostInvSavings) {
        this.CostInvSavings = Formulas.round(CostInvSavings, 2);
    }

    public double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(double totalSavings) {
        this.totalSavings = Formulas.round(totalSavings, 2);
    }
}
