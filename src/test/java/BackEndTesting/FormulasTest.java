/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BackEndTesting;

import be.usgictprofessionals.usgfinancewebapp.Model.Formulas;
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
//test class to check wether the formulas are correct
public class FormulasTest {
    
    public FormulasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void calcEBITDATest(){
        assertEquals(31.3, Formulas.calcEBITDA(13.8, 17.5), 0.1);
    }
    
    @Test
    public void calcRecIncomeTest(){
        assertEquals(12.4, Formulas.calcRecurringIncome(13.8, 0.2, 1.6), 0.1);
    }
    
    @Test
    public void calcNetIncomeTest(){
        assertEquals(8.8, Formulas.calcNetIncome(12.4, 0, 0, 3.6), 0.1);
    }

    @Test
    public void calcWorkingCapitalTest(){
        assertEquals(7.1, Formulas.calcWorkingCapital(91.8, 84.7), 0.1);
    }
    
    @Test
    public void calcNetDebtTest(){
        assertEquals(39.6, Formulas.calcNetDebt(36.3, 14.0, 10.7), 0.1);
    }
    
    @Test
    public void calcSolvencyTest(){
        assertEquals(0.44, Formulas.calcSolvency(102.8, 232.7), 0.1);
    }
    
    @Test
    public void calcEBITTest(){
        assertEquals(8.6, Formulas.calcEBIT(13.8, 1.6), 0.1);
    }
    
    @Test
    public void calcDIOTest(){
        assertEquals(25.3, Formulas.calcDIO(12, 20.3, 293.2), 0.1);
    }
    
    @Test
    public void calcDPOTest(){
        assertEquals(67.0, Formulas.calcDPO(12, 53.8, 293.2), 0.1);
    }
    
    @Test
    public void calcDSOTest(){
        assertEquals(48.1, Formulas.calcDSO(52.7, 12, 399.7), 0.1);
    }
    
    @Test
    public void calcCurrRatioTest(){
        assertEquals(1.08, Formulas.calcCurrRatio(91.8, 84.7), 0.01);
    }
    
    @Test
    public void calcQuickRatioTest(){
        assertEquals(0.84, Formulas.calcQuickRatio(91.8, 20.3, 84.7), 0.01);
    }
    
    @Test
    public void calcCCCDaysTest(){
        assertEquals(17.5, Formulas.calcCCCDays(20.3, 52.7, 53.8, 12, 399.7), 0.1);
    }
    
    @Test
    public void calcCCCEuroTest(){
        assertEquals(19.2, Formulas.calcCCC(20.3, 52.7, 53.8), 0.1);
    }
    
    @Test
    public void calcInvTargetTest(){
        assertEquals(16.1, Formulas.calcInvTarget(20.0, 293.2, 12), 0.1);
    }
    
    @Test
    public void calcARTargetTest(){
        assertEquals(43.8, Formulas.calcARTarget(40.0, 399.7, 12), 0.1);
    }
    
    @Test
    public void calcAPTargetTest(){
        assertEquals(48.2, Formulas.calcAPTarget(60.0, 293.2, 12), 0.1);
    }
    
    @Test
    public void calcDebitInterestTest(){
        assertEquals(0.15, Formulas.calcDebitInterest(39.6, 7.5, 0.02), 0.01);
        assertEquals(0.00, Formulas.calcDebitInterest(0, 7.5, 0.02), 0.01);
    }
    
    @Test
    public void calcCreditInterestTest(){
        assertEquals(0.00, Formulas.calcCreditInterest(39.6, 7.5, 0.001), 0.01);
        assertTrue((Formulas.calcCreditInterest(0.00, 7.5, 0.001) > 0.00));
    }
}
