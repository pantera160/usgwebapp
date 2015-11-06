/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEndTesting;

import be.usgictprofessionals.usgfinancewebapp.Model.CalculatedDataResource;
import be.usgictprofessionals.usgfinancewebapp.controllers.JSONResponseRecources;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.WCMData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Straetmans
 */

public class ControllerTest {

    private CalculatedDataResource data;
    private WCMData initdata;
    private WCMData wcmdata;

    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        InputData inputdata = new InputData();
        inputdata.setTurnover(399.7);
        inputdata.setCostOfSales(293.2);
        inputdata.setDepreciation(17.5);
        inputdata.setEbit(13.8);
        inputdata.setFinRev(0.2);
        inputdata.setFinExp(1.6);
        inputdata.setFinExpInterest(1326);
        inputdata.setFinExpBank(116);
        inputdata.setNrIncome(0);
        inputdata.setNrCharges(0);
        inputdata.setTaxes(3.6);
        inputdata.setFixedAssets(140.9);
        inputdata.setInventory(20.3);
        inputdata.setAr(52.7);
        inputdata.setCash(10.7);
        inputdata.setCurrAssets(91.8);
        inputdata.setTotAssets(232.7);
        inputdata.setEquity(102.8);
        inputdata.setLtFinDebt(36.3);
        inputdata.setStFinDebt(14.0);
        inputdata.setAp(53.8);
        inputdata.setCurrLiabilities(84.7);
        inputdata.setYear(2014);
        inputdata.setNumberOfMonths(12);
        data = new CalculatedDataResource(inputdata);
        
        wcmdata = new WCMData();
        wcmdata.setCostAP(0.0005);
        wcmdata.setCostAR(0.0005);
        wcmdata.setCostInv(0.0005);
        wcmdata.setCreditInterest(0.001);
        wcmdata.setDebitInterest(0.02);
        wcmdata.setTargetDIO(20);
        wcmdata.setTargetDPO(60);
        wcmdata.setTargetDSO(40);
        initdata = (WCMData) JSONResponseRecources.WCMCalcInitData(data);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void WCMCalcInitDataTest_rightDataIsReturnedForGivenInput() {
        
        assertEquals(25.3, initdata.getDIO(), 0.1);
        assertEquals(48.1, initdata.getDSO(), 0.1);
        assertEquals(67.0, initdata.getDPO(), 0.1);
        //assertEquals(17.5, initdata.getCCCDay(), 0.1);
        assertEquals(20.3, initdata.getInvCurrent(), 0.1);
        assertEquals(52.7, initdata.getArCurrent(), 0.1);
        assertEquals(53.8, initdata.getApCurrent(), 0.1);
        assertEquals(19.2, initdata.getCCCEuroCurrent(), 0.1);
    }

    @Test
    public void WCMCalcDataTest_rightDataIsReturnedForGivenInput() {
        if(initdata != null){
        WCMData datacalc = (WCMData) JSONResponseRecources.WCMCalcData(wcmdata, data);
            assertEquals(16.1, datacalc.getInvTarget(), 0.1);
            assertEquals(43.8, datacalc.getArTarget(), 0.1);
            assertEquals(48.2, datacalc.getApTarget(), 0.1);
            assertEquals(11.7, datacalc.getCCCEuroTarget(), 0.1);
            //assertEquals(7.5, datacalc.getCCCSavingsEuro(), 0.1);
            //assertEquals(4.2, datacalc.getInvSavings(), 0.1);
            //assertEquals(8.9, datacalc.getArSavings(), 0.1);
           // assertEquals(-5.6, datacalc.getApSavings(), 0.1);
           // assertEquals(0.15, datacalc.getDebitInterest(), 0.01);
            //assertEquals(0.00, datacalc.getCreditInterest(), 0.01);
            //assertEquals(0.00, datacalc.getCostAR(), 0.01);
            //assertEquals(0.00, datacalc.getCostInv(), 0.01);
            //assertEquals(0.00, datacalc.getCostAP(), 0.01);
            //assertEquals(0.15, datacalc.getTotalSavings(), 0.01);
        }
    }
}
