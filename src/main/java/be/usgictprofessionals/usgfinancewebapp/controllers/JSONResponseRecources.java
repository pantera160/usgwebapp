/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.usgictprofessionals.usgfinancewebapp.controllers;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.BalansRatioData;
import be.usgictprofessionals.usgfinancewebapp.Model.CalculatedDataResource;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CoverageRatioData;
import be.usgictprofessionals.usgfinancewebapp.Model.Formulas;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.ReturnRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.TurnoverRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.WCMData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Thomas Straetmans
 *
 * This class will turn the data calculated from the input data into usable
 * XmlRootElement classes.
 *
 */
public class JSONResponseRecources {

    /**
     *
     * @param data TreeMap of the CalculatedDataResources sorted by year
     * @return ArrayList of InputPageData elements. Each element corresponds to
     * an other year.
     */
    public static ArrayList<InputData> getInputReturn(TreeMap<Integer, CalculatedDataResource> data) {
        ArrayList<InputData> inputreturn = new ArrayList<>();
        if (data == null) {
            InputData temp = new InputData();
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            temp.setYear(cal.get(Calendar.YEAR));
            inputreturn.add(temp);
        } else {
            for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
                inputreturn.add(entry.getValue().getInputData());
            }
        }
        return inputreturn;
    }

    /**
     *
     * @param data TreeMap of the CalculatedDataResources sorted by year
     * @return ArrayList of BalansRatioData elements. Each element corresponds
     * with a year for which the balans data has been calculated. The elements
     * are ordered by year.
     */
    public static ArrayList<BalansRatioData> getBalans(TreeMap<Integer, CalculatedDataResource> data) {
        ArrayList<BalansRatioData> balans = new ArrayList<>();

        for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
            BalansRatioData temp = new BalansRatioData();
            temp.setYear(entry.getKey());
            temp.setCurrRatio(entry.getValue().getCurrRatio());
            temp.setQuickRatio(entry.getValue().getQuickRatio());
            temp.setSolvency(entry.getValue().getSolvency());
            balans.add(temp);
        }

        return balans;
    }

    /**
     *
     * @param data TreeMap of the CalculatedDataResources sorted by year
     * @return ArrayList of CoverageRatioData elements. Each elements
     * corresponds with a year for which the coverage has been calculated. The
     * elements are order by year.
     */
    public static ArrayList<CoverageRatioData> getCoverage(TreeMap<Integer, CalculatedDataResource> data) {
        ArrayList<CoverageRatioData> coverage = new ArrayList<>();

        for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
            CoverageRatioData temp = new CoverageRatioData();
            temp.setEBITFinExp(entry.getValue().getEbit());
            temp.setNetDebtEBITDA(entry.getValue().getNetFin());
            temp.setYear(entry.getKey());
            coverage.add(temp);
        }
        return coverage;
    }

    /**
     *
     * @param data TreeMap of the CalculatedDataResources sorted by year
     * @return ArrayList of ReturnRatioData elements. Each element corresponds
     * with a year for which the return has been calculated The elements are
     * sorted by year.
     */
    public static ArrayList<ReturnRatioData> getReturn(TreeMap<Integer, CalculatedDataResource> data) {
        ArrayList<ReturnRatioData> returnData = new ArrayList<>();

        for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
            ReturnRatioData temp = new ReturnRatioData();
            temp.setROA(entry.getValue().getROA());
            temp.setROE(entry.getValue().getROE());
            temp.setYear(entry.getKey());
            returnData.add(temp);
        }

        return returnData;
    }

    /**
     *
     * @param data TreeMap of the CalculatedDataResources sorted by year
     * @return ArrayList of TurnoverRatioData elements. Each element corresponds
     * with a year for which the turnover has been calculated. The elements are
     * sorted by year.
     */
    public static ArrayList<TurnoverRatioData> getTurnover(TreeMap<Integer, CalculatedDataResource> data) {
        ArrayList<TurnoverRatioData> turnover = new ArrayList<>();

        for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
            TurnoverRatioData temp = new TurnoverRatioData();
            temp.setDIO(entry.getValue().getDIO());
            temp.setDSO(entry.getValue().getDSO());
            temp.setDPO(entry.getValue().getDPO());
            temp.setYear(entry.getKey());
            turnover.add(temp);
        }
        return turnover;
    }

    /**
     *
     * @param mostRecentYear The CalculatedDataResource from the most recent
     * year for which data has been entered on the input page
     * @return WCMInitData object which consists of all the fields that require
     * data entered on the input page or calculated date but not data for which
     * new input on the WCM page is required.
     */
    public static WCMData WCMCalcInitData(CalculatedDataResource mostRecentYear) {
        WCMData wcmInitData = new WCMData();
        wcmInitData.setDIO(mostRecentYear.getDIO());
        wcmInitData.setDPO(mostRecentYear.getDPO());
        wcmInitData.setDSO(mostRecentYear.getDSO());
        wcmInitData.setApCurrent(mostRecentYear.getInputData().getAp());
        wcmInitData.setArCurrent(mostRecentYear.getInputData().getAr());
        wcmInitData.setInvCurrent(mostRecentYear.getInputData().getInventory());
        wcmInitData.setCCCDay(Formulas.calcCCC(wcmInitData.getDIO(), wcmInitData.getDSO(), wcmInitData.getDPO()));
        wcmInitData.setCCCEuroCurrent(Formulas.calcCCC(wcmInitData.getInvCurrent(), wcmInitData.getArCurrent(), wcmInitData.getApCurrent()));
        wcmInitData.setCostAP(0.05);
        wcmInitData.setCostAR(0.05);
        wcmInitData.setCostInv(0.05);
        wcmInitData.setDebitInterest(3.00);
        wcmInitData.setCreditInterest(0.0);
        return wcmInitData;
    }

    /**
     *
     * @param data WCMData object consisting of all the data the user has
     * entered on the WCM page
     * @param mostRecentYear The CalculatedDataResource from the most recent
     * year for which data has been entered on the input page
     * @return WCMData object which consists of all the data needed for the WCM
     * page
     */
    public static WCMData WCMCalcData(WCMData data, CalculatedDataResource mostRecentYear) {
        WCMData wcmData = data;
        //calc first block
        wcmData.setCCCDayTarget(Formulas.calcCCC(data.getTargetDIO(), data.getTargetDSO(), data.getTargetDPO()));
        wcmData.setCCCSavingsDay(wcmData.getCCCDay() - wcmData.getCCCDayTarget());

        //calc 2nd block
        wcmData.setInvTarget(Formulas.calcInvTarget(data.getTargetDIO(), mostRecentYear.getInputData().getCostOfSales(),mostRecentYear.getInputData().getNumberOfMonths()));
        wcmData.setArTarget(Formulas.calcARTarget(data.getTargetDSO(), mostRecentYear.getInputData().getTurnover(), mostRecentYear.getInputData().getNumberOfMonths()));
        wcmData.setApTarget(Formulas.calcAPTarget(data.getTargetDPO(), mostRecentYear.getInputData().getCostOfSales(), mostRecentYear.getInputData().getNumberOfMonths()));
        wcmData.setCCCEuroTarget(Formulas.calcCCC(wcmData.getInvTarget(), wcmData.getArTarget(), wcmData.getApTarget()));
        wcmData.setCCCSavingsEuro(wcmData.getCCCEuroCurrent() - wcmData.getCCCEuroTarget());
        wcmData.setArSavings(wcmData.getArCurrent() - wcmData.getArTarget());
        wcmData.setApSavings(-(wcmData.getApCurrent() - wcmData.getApTarget()));
        wcmData.setInvSavings(wcmData.getInvCurrent() - wcmData.getInvTarget());

        //calc 3rd block
        wcmData.setDebitInterestSavings(Formulas.calcDebitInterest(mostRecentYear.getInputData().getNetDebt(), wcmData.getCCCSavingsEuro(), data.getDebitInterest()/100));
        wcmData.setCreditInterestSavings(Formulas.calcCreditInterest(mostRecentYear.getInputData().getNetDebt(), wcmData.getCCCSavingsEuro(), data.getCreditInterest()/100));
        wcmData.setCostARSavings(Formulas.calcCostSavingAR(wcmData.getArSavings(), data.getCostAR()/100));
        wcmData.setCostAPSavings(Formulas.calcCostSavingAP(wcmData.getApSavings(), data.getCostAP()/100));
        wcmData.setCostInvSavings(Formulas.calcCostSavingInv(wcmData.getInvSavings(), data.getCostInv()/100));
        wcmData.setTotalSavings(wcmData.getCostARSavings() + wcmData.getCostAPSavings() + wcmData.getCostInvSavings() + wcmData.getDebitInterestSavings() + wcmData.getCreditInterestSavings());
        return wcmData;
    }

}
