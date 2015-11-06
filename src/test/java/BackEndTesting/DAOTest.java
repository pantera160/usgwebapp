///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package BackEndTesting;
//
//import be.usgictprofessionals.usgfinancewebapp.Model.CalculatedDataResource;
//import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
//import be.usgictprofessionals.usgfinancewebapp.controllers.DataDAO;
//import java.time.Year;
//import java.util.TreeMap;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Pantera
// */
//public class DAOTest {
//    
//    private InputData inputdata;
//    
//     @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        inputdata = new InputData();
//        inputdata.setTurnover(399.7);
//        inputdata.setCostOfSales(293.2);
//        inputdata.setDepreciation(17.5);
//        inputdata.setEbit(13.8);
//        inputdata.setFinRev(0.2);
//        inputdata.setFinExp(1.6);
//        inputdata.setFinExpInterest(1326);
//        inputdata.setFinExpBank(116);
//        inputdata.setNrIncome(0);
//        inputdata.setNrCharges(0);
//        inputdata.setTaxes(3.6);
//        inputdata.setFixedAssets(140.9);
//        inputdata.setInventory(20.3);
//        inputdata.setAr(52.7);
//        inputdata.setCash(10.7);
//        inputdata.setCurrAssets(91.8);
//        inputdata.setTotAssets(232.7);
//        inputdata.setEquity(102.8);
//        inputdata.setLtFinDebt(36.3);
//        inputdata.setStFinDebt(14.0);
//        inputdata.setAp(53.8);
//        inputdata.setCurrLiabilities(84.7);
//        inputdata.setYear(2014);
//        inputdata.setNumberOfMonths(12);
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void getInstanceTest(){
//        assertEquals(DataDAO.class, DataDAO.getInstance().getClass());
//    }
//    
//    @Test
//    public void calcDataTest_rightDataIsReturnedForGivenInput(){
//        TreeMap<Year, CalculatedDataResource> temp = DataDAO.getInstance().calcData(inputdata);
//        assertEquals(temp.get(Year.parse("2014")).getDIO(), 25.3, 0.1);
//        assertEquals(temp.get(Year.parse("2014")).getDSO(), 48.1, 0.1);
//        assertEquals(temp.get(Year.parse("2014")).getDPO(), 67.0, 0.1);
//        assertEquals(temp.get(Year.parse("2014")).getEBITDA(), 31.3, 0.1);
//        assertEquals(temp.get(Year.parse("2014")).getROE(), 0.09, 0.01);
//        assertEquals(temp.get(Year.parse("2014")).getSolvency(), 0.44, 0.01);
//        Assert.assertTrue(temp.get(Year.parse("2014")).getInputData().equals(inputdata));
//    }
//}
