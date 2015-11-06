/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEndTesting;

import be.usgictprofessionals.usgfinancewebapp.Model.CalculatedDataResource;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pantera
 */
public class CalcDataResourceTest {

    private InputData inputdata;
    private CalculatedDataResource calcdata;

    public CalcDataResourceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        inputdata = new InputData();
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

        calcdata = new CalculatedDataResource(inputdata);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initCalcTest_rightDataIsReturnedForGivenInput() {
        assertEquals(12.4, calcdata.getInputData().getRecIncome(), 0.1);
        assertEquals(8.8, calcdata.getInputData().getNetIncome(), 0.1);
        assertEquals(31.3, calcdata.getInputData().getEbitda(), 0.1);
        assertEquals(7.1, calcdata.getInputData().getWorkingCapital(), 0.1);
        assertEquals(39.6, calcdata.getInputData().getNetDebt(), 0.1);
    }

    @Test
    public void calcOverviewTest_rightDataIsReturnedForGivenInput() {
        assertEquals(0.44, calcdata.getSolvency(), 0.01);
        assertEquals(8.6, calcdata.getEbit(), 0.1);
        assertEquals(1.3, calcdata.getNetFin(), 0.1);
        assertEquals(25.3, calcdata.getDIO(), 0.1);
        assertEquals(48.1, calcdata.getDSO(), 0.1);
        assertEquals(67.0, calcdata.getDPO(), 0.1);
        assertEquals(0.09, calcdata.getROE(), 0.01);
        assertEquals(0.04, calcdata.getROA(), 0.01);
        assertEquals(1.08, calcdata.getCurrRatio(), 0.01);
        assertEquals(0.84, calcdata.getQuickRatio(), 0.01);
    }
}
