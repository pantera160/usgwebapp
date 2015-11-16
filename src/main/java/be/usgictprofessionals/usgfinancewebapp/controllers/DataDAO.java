package be.usgictprofessionals.usgfinancewebapp.controllers;

import be.usgictprofessionals.usgfinancewebapp.Database.JdbcDatabase;
import be.usgictprofessionals.usgfinancewebapp.Model.CalculatedDataResource;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.BalansRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CompanyInfoData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CoverageRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.ReturnRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.TurnoverRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.WCMData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Pantera
 */
public class DataDAO {
    
    private static DataDAO uniqueInstance;
    
    private DataDAO(){
        
    };
    
    public static DataDAO getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new DataDAO();
        }
        return uniqueInstance;
    }
    
    public ArrayList<InputData> calcData(ArrayList<InputData> inputdata, int companyId){
        TreeMap<Integer, CalculatedDataResource> data = new TreeMap<>(Collections.reverseOrder());
        ArrayList<InputData> datalist = new ArrayList<>();
        for(InputData i : inputdata){
            data.put(i.getYear(),new CalculatedDataResource(i));
        }
        for (Map.Entry<Integer, CalculatedDataResource> entry : data.entrySet()) {
                datalist.add(entry.getValue().getInputData());
                JdbcDatabase.getInstance().saveInputData(entry.getValue().getInputData(), companyId);
        }
        return datalist;
    }
    
    public ArrayList<InputData> getInputReturn(int companyId){
        return JdbcDatabase.getInstance().getInputData(companyId);
    }
    
    public ArrayList<BalansRatioData> getBalans(int companyId){
        ArrayList<BalansRatioData> response = JSONResponseRecources.getBalans(createCalcData(companyId));
        response.add(JdbcDatabase.getInstance().getBalansAvg(companyId));
        return response;
    }
    
    public ArrayList<CoverageRatioData> getCoverage(int companyId){
        ArrayList<CoverageRatioData> response = JSONResponseRecources.getCoverage(createCalcData(companyId));
        response.add(JdbcDatabase.getInstance().getCoverageAvg(companyId));
        return response;
    }
    
    public ArrayList<ReturnRatioData> getReturn(int companyId){
        ArrayList<ReturnRatioData> response = JSONResponseRecources.getReturn(createCalcData(companyId));
        ReturnRatioData avg = JdbcDatabase.getInstance().getReturnAvg(companyId);
        response.add(avg);
        return response;
    }
    
    public ArrayList<TurnoverRatioData> getTurnover(int companyId){
        ArrayList<TurnoverRatioData> response = JSONResponseRecources.getTurnover(createCalcData(companyId));
        response.add(JdbcDatabase.getInstance().getTurnoverAvg(companyId));
        return response;
    }
    
    public WCMData getInitData(int companyId){
        ArrayList<InputData> data = JdbcDatabase.getInstance().getInputData(companyId);
        CalculatedDataResource mostRecentYear = new CalculatedDataResource(data.get(0));
        return (WCMData) JSONResponseRecources.WCMCalcInitData(mostRecentYear);
    }
    
    public WCMData getCalcDataWCM(WCMData datawcm, int companyId){
         ArrayList<InputData> data = JdbcDatabase.getInstance().getInputData(companyId);
        CalculatedDataResource mostRecentYear = new CalculatedDataResource(data.get(0));
        return (WCMData) JSONResponseRecources.WCMCalcData(datawcm, mostRecentYear);
    }
    
    public CompanyInfoData getCompanyData(int companyId) {
        CompanyInfoData company = JdbcDatabase.getInstance().getCompanyInfoData(companyId);
        if(company == null){
            company = new CompanyInfoData();
        }
        return company;
    }

    public int saveCompanyData(CompanyInfoData data, int userId) {
        return JdbcDatabase.getInstance().saveCompanyData(data, userId);
    }
    
    public boolean inputHasBeenReceived(){
        return true;
    }
    
    private TreeMap<Integer, CalculatedDataResource> createCalcData(int companyId){
        ArrayList<InputData> inputs = JdbcDatabase.getInstance().getInputData(companyId);
        TreeMap<Integer, CalculatedDataResource> data = new TreeMap<>(Collections.reverseOrder());
        for(InputData i : inputs){
            data.put(i.getYear(),new CalculatedDataResource(i));
        }
        return data;
    }
    
    public ArrayList<String> getSectors(){
        return JdbcDatabase.getInstance().getSectors();
    }
}
