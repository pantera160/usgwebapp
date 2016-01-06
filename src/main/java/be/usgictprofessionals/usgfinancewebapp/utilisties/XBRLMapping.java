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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Pantera
 */
public class XBRLMapping {
    
    private final InputData currentInputData;
    private final InputData precedingInputData;
    
    public XBRLMapping(File file) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        
        doc.getDocumentElement().normalize();
        
        NodeList nList = doc.getElementsByTagName("xbrli:xbrl");
        
        Element rootElement = (Element) nList.item(0);
        
        currentInputData = create(rootElement, "CurrentInstant", "CurrentDuration");
        precedingInputData = create(rootElement, "PrecedingInstant", "PrecedingDuration");
        file.delete();
    }
    
    private double get(String searchterm, Element rootElement, String context, String context2){
        NodeList nlist = rootElement.getElementsByTagName(searchterm);
        for (int i = 0; i < nlist.getLength(); i++){
            if(nlist.item(i).getNodeType() == Node.ELEMENT_NODE){
                if(((Element) nlist.item(i)).getAttribute("contextRef").equals(context) || ((Element) nlist.item(i)).getAttribute("contextRef").equals(context2)){
                    return Double.parseDouble(nlist.item(i).getTextContent());
                }
            }
        }
        return 0.1;
    }
    
    private InputData create(Element rootElement, String context, String context2){
        InputData inputData = new InputData();
        
        inputData.setFixedAssets(get("pfs:FixedAssets", rootElement, context, context2));
        inputData.setIntangiblesAssets(get("pfs:IntangibleFixedAssets", rootElement, context, context2));
        inputData.setPropertyAssets(get("pfs:TangibleFixedAssets", rootElement, context, context2));
        inputData.setFinFixedAssets(get("pfs:FinancialFixedAssets", rootElement, context, context2));
        inputData.setInventory(get("pfs:Stocks", rootElement, context, context2));
        inputData.setAr(get("pfs:AmountsReceivableWithinOneYear", rootElement, context, context2));
        inputData.setCash(get("pfs:CashBankHand", rootElement, context, context2)+ get("pfs:CurrentInvestments", rootElement, context, context2));
        inputData.setInvestments(get("pfs:CurrentInvestments", rootElement, context, context2));
        inputData.setLiquidAssets(get("pfs:CashBankHand", rootElement, context, context2));
        inputData.setCurrAssets(get("pfs:CurrentsAssets", rootElement, context, context2));
        inputData.setTotAssets(get("pfs:EquityLiabilities", rootElement, context, context2));
        inputData.setEquity(get("pfs:Equity", rootElement, context, context2));
        inputData.setLtFinDebt(get("pfs:FinancialDebtsRemainingTermMoreOneYear", rootElement, context, context2));
        inputData.setSubordinatedDebt(get("pfs:FinancialDebtsRemainingTermMoreOneYear", rootElement, context, context2));
        inputData.setStFinDebt(get("pfs:CurrentPortionAmountsPayableMoreOneYearFallingDueWithinOneYear", rootElement, context, context2)+ get("pfs:AmountsPayableWthinOneYearCreditInstitutions", rootElement, context, context2));
        inputData.setLongTermLoans(get("pfs:CurrentPortionAmountsPayableMoreOneYearFallingDueWithinOneYear", rootElement, context, context2));
        inputData.setFinDebt(get("pfs:AmountsPayableWthinOneYearCreditInstitutions", rootElement, context, context2));
        inputData.setAp(get("pfs:TradeDebtsPayableWithinOneYear", rootElement, context, context2));
        inputData.setCurrLiabilities(get("pfs:AmountsPayable", rootElement, context, context2));
        inputData.setTurnover(get("pfs:OperatingIncome", rootElement, context, context2));
        inputData.setCostOfSales(get("pfs:RawMaterialsConsumables", rootElement, context, context2)+ get("pfs:ServicesOtherGoods", rootElement, context, context2));
        inputData.setComMatCon(get("pfs:RawMaterialsConsumables", rootElement, context, context2));
        inputData.setMiscGoods(get("pfs:ServicesOtherGoods", rootElement, context, context2));
        inputData.setDepreciation(get("pfs:DepreciationOtherAmountsWrittenDownFormationExpensesIntangibleTangibleFixedAssets", rootElement, context, context2));
        inputData.setEbit(get("pfs:OperatingProfitLoss", rootElement, context, context2));
        inputData.setFinRev(get("pfs:FinancialIncome", rootElement, context, context2));
        inputData.setFinExp(get("pfs:FinancialCharges", rootElement, context, context2));
        inputData.setFinExpInterest(get("pfs:DebtCharges", rootElement, context, context2));
        inputData.setFinExpBank(0.0);
        inputData.setFinExpOther(0.0);
        inputData.setNrIncome(get("pfs:OtherExtraordinaryIncome", rootElement, context, context2));
        inputData.setNrCharges(get("pfs:OtherExtraordinaryCharges", rootElement, context, context2));
        inputData.setTaxes(get("pfs:IncomeTaxes", rootElement, context, context2) - (get("pfs:TransferFromDeferredTaxes", rootElement, context, context2) + get("pfs:TransferToDeferredTaxes", rootElement, context, context2)));
        inputData.setIncomeTaxes(get("pfs:IncomeTaxes", rootElement, context, context2));
        inputData.setWithDefTaxes(get("pfs:TransferFromDeferredTaxes", rootElement, context, context2));
        inputData.setTransDefTaxes(get("pfs:TransferToDeferredTaxes", rootElement, context, context2));
        return inputData;
    }
    
    public InputData getCurrentInputData(){
        return currentInputData;
    }
    
    public InputData getPrecedingInputData(){
        return precedingInputData;
    }
}
