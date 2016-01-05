/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.utilisties;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pantera
 */
public class XBRLMapping {
    
    public XBRLMapping(File file) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        
        doc.getDocumentElement().normalize();
        
        NodeList nList = doc.getElementsByTagName("xbrli:xbrl");
        
        Element rootElement = (Element) nList.item(0);
        
        InputData inputData = new InputData();
        
        inputData.setFixedAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:FixedAssets").item(0).getTextContent()));
        //inputData.setIntangiblesAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:???").item(0).getTextContent()));
        inputData.setPropertyAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:TangibleFixedAssets").item(0).getTextContent()));
        //inputData.setFinFixedAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:???").item(0).getTextContent()));
        inputData.setInventory(Double.parseDouble(rootElement.getElementsByTagName("pfs:Stocks").item(0).getTextContent()));
        inputData.setAr(Double.parseDouble(rootElement.getElementsByTagName("pfs:AmountsReceivableWithinOneYear").item(0).getTextContent()));
        //TODO inputData.setCash(Double.parseDouble(rootElement.getElementsByTagName("pfs:CashBankHand").item(0).getTextContent()) + ...);
        inputData.setCurrAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:CurrentAssets").item(0).getTextContent()));
        inputData.setTotAssets(Double.parseDouble(rootElement.getElementsByTagName("pfs:EquityLiabilities").item(0).getTextContent()));
        inputData.setEquity(Double.parseDouble(rootElement.getElementsByTagName("pfs:Equity").item(0).getTextContent()));
        inputData.setLtFinDebt(Double.parseDouble(rootElement.getElementsByTagName("pfs:FinancialDebtsRemainingTermMoreOneYear").item(0).getTextContent()));
        inputData.setSubordinatedDebt(Double.parseDouble(rootElement.getElementsByTagName("pfs:FinancialDebtsRemainingTermMoreOneYear").item(0).getTextContent()));
        //inputData.setStFinDebt(Double.parseDouble(rootElement.getElementsByTagName("pfs:CurrentPortionAmountsPayableMoreOneYearFallingDueWithinOneYear").item(0).getTextContent()));
        inputData.setAp(Double.parseDouble(rootElement.getElementsByTagName("pfs:TradeDebtsPayableWithinOneYear").item(0).getTextContent()));
        inputData.setCurrLiabilities(Double.parseDouble(rootElement.getElementsByTagName("pfs:AmountsPayable").item(0).getTextContent()));
        inputData.setTurnover(Double.parseDouble(rootElement.getElementsByTagName("pfs:OperatingIncome").item(0).getTextContent()));
        inputData.setCostOfSales(Double.parseDouble(rootElement.getElementsByTagName("pfs:RawMaterialsConsumables").item(0).getTextContent()) + Double.parseDouble(rootElement.getElementsByTagName("pfs:ServicesOtherGoods").item(0).getTextContent()));
        inputData.setDepreciation(Double.parseDouble(rootElement.getElementsByTagName("pfs:DepreciationOtherAmountsWrittenDownFormationExpensesIntangibleTangibleFixedAssets").item(0).getTextContent()));
        inputData.setRecIncome(Double.parseDouble(rootElement.getElementsByTagName("pfs:OperatingProfitLoss").item(0).getTextContent()));
        inputData.setFinRev(Double.parseDouble(rootElement.getElementsByTagName("pfs:FinancialIncome").item(0).getTextContent()));
        inputData.setFinExp(Double.parseDouble(rootElement.getElementsByTagName("pfs:FinancialCharges").item(0).getTextContent()));
        inputData.setFinExpInterest(Double.parseDouble(rootElement.getElementsByTagName("pfs:DebtCharges").item(0).getTextContent()));
        //inputData.setFinExpBank(Double.parseDouble(rootElement.getElementsByTagName("pfs:FinancialCharges").item(0).getTextContent()));
        inputData.setFinExpOther(0.0);
        inputData.setNrIncome(Double.parseDouble(rootElement.getElementsByTagName("pfs:OtherExtraordinaryIncome").item(0).getTextContent()));
        inputData.setNrCharges(Double.parseDouble(rootElement.getElementsByTagName("pfs:OtherExtraordinaryCharges").item(0).getTextContent()));
        //inputData.setTaxes(Double.parseDouble(rootElement.getElementsByTagName("pfs:IncomeTaxes").item(0).getTextContent()) - Double.parseDouble(rootElement.getElementsByTagName("pfs:???").item(0).getTextContent()) + Double.parseDouble(rootElement.getElementsByTagName("pfs:???").item(0).getTextContent()));
    }
}
