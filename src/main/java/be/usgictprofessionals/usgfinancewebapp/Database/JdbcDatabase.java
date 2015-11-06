/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.usgictprofessionals.usgfinancewebapp.Database;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.BalansRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CompanyInfoData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CoverageRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.ReturnRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.TurnoverRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pantera
 */
public class JdbcDatabase implements Database {

    private static final String DBURL = "jdbc:derby://localhost:1527/USGFinanceWebapp;user=Pantera;password=admin";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static JdbcDatabase uniqueInstance;

    private JdbcDatabase() {

    }

    public static JdbcDatabase getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JdbcDatabase();
        }
        return uniqueInstance;
    }

    @Override
    public int saveCompanyData(CompanyInfoData data, int userId) {
        createConnection();
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from app.company_data where company_id = " + data.getCompanyId() + "");
            if (!result.next()) {
                return insertCompanyData(data, userId);
            } else {
                return updateCompanyData(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int insertCompanyData(CompanyInfoData data, int userId) {
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into app.COMPANY_DATA(sector_id, company, closingdate, countries, outsideeu, user_id) values("
                    + "(Select sector_id from app.Sector_averages where sector_name = '" + data.getSector() + "'), '" 
                    + data.getCompany() + "', '" + data.getClosingDate() + "', '" + data.getCountries() + "', '" + data.getOutsideEU() + "', "+userId+")"
            );
            ResultSet result = stmt.executeQuery("SELECT IDENTITY_VAL_LOCAL() AS IVL from app.COMPANY_DATA");
            result.next();
            int compId = result.getInt("IVL");
            stmt.close();
            return compId;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return -1;
        }
    }

    public int updateCompanyData(CompanyInfoData data) {
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("update app.company_data set closingdate='" + data.getClosingDate() + "', countries='" + data.getCountries() + "', outsideeu='" + data.getOutsideEU()
                    + "', sector_id = (Select sector_id from app.Sector_averages where sector_name = '" + data.getSector() + "') where company_id = " + data.getCompanyId() + "");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return data.getCompanyId();
    }

    @Override
    public CompanyInfoData getCompanyInfoData(int companyID) {
        createConnection();
        try {
            CompanyInfoData data = new CompanyInfoData();
            data.setSector("0");
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select sector_name, company, closingdate, countries, outsideeu "
                    + "from app.company_data, app.sector_averages "
                    + "where company_id = " + companyID + " AND app.company_data.sector_id = app.sector_averages.sector_id");
            while (result.next()) {
                data.setSector(result.getString("SECTOR_NAME"));
                data.setClosingDate(result.getString("CLOSINGDATE"));
                data.setCompany(result.getString("COMPANY"));
                data.setCountries(result.getString("COUNTRIES"));
                data.setOutsideEU(result.getString("OUTSIDEEU"));
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new CompanyInfoData();
        }
    }

    private static void createConnection() {
        if (conn == null) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                //Get a connection
                conn = DriverManager.getConnection(DBURL);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException except) {
                except.printStackTrace();
            }
        }
    }

    public void insertInputData(InputData data, int userID) {
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into app.input_data(user_id, recincome, netincome, ebitda, turnover, costofsales, depreciation, ebit, finrev, finexp, finexpinterest, finexpbank, finexpother, "
                    + "nrincome, nrcharges, taxes, workingcapital, netdebt, fixedassets, inventory, ar, cash, currassets, totassets, equity, ltfindebt, stfindebt, ap, currliabilities, numberofmonths, "
                    + "inputyear, finfixedassets,INTANGIBLESASSETS, propertyassets) "
                    + "values(" + userID + "," + data.getRecIncome() + " , " + data.getNetIncome() + "," + data.getEbitda() + "," + data.getTurnover() + "," + data.getCostOfSales() + "," + data.getDepreciation()
                    + "," + data.getEbit() + "," + data.getFinRev() + "," + data.getFinExp() + "," + data.getFinExpInterest() + "," + data.getFinExpBank() + "," + data.getFinExpOther() + "," + data.getNrIncome()
                    + "," + data.getNrCharges() + "," + data.getTaxes() + "," + data.getWorkingCapital() + "," + data.getNetDebt() + "," + data.getFixedAssets() + "," + data.getInventory() + "," + data.getAr() + "," + data.getCash()
                    + "," + data.getCurrAssets() + "," + data.getTotAssets() + "," + data.getEquity() + "," + data.getLtFinDebt() + "," + data.getStFinDebt() + "," + data.getAp() + "," + data.getCurrLiabilities() + "," 
                    + data.getNumberOfMonths() + "," + data.getYear() + "," + data.getFinFixedAssets() + "," + data.getIntangiblesAssets() + "," + data.getPropertyAssets()+")");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    public void updateInputData(InputData data, int userID){
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("update app.input_data set recincome="+data.getRecIncome()+", netincome="+data.getNetIncome()+", ebitda="+data.getEbitda()+" , turnover="+data.getTurnover()
                    +", costofsales="+data.getCostOfSales()+", depreciation="+data.getDepreciation()+", ebit="+data.getEbit()+", finrev="+data.getFinRev()+", finexp="+data.getFinExp()
                    +", finexpinterest="+data.getFinExpInterest()+", finexpbank="+data.getFinExpBank()+", finexpother="+data.getFinExpOther()+", nrincome="+data.getNrIncome()
                    +", nrcharges="+data.getNrCharges()+", taxes="+data.getTaxes()+", workingcapital="+data.getWorkingCapital()+", netdebt="+data.getNetDebt()+", fixedassets="+data.getFixedAssets()
                    +", inventory="+data.getInventory()+", ar="+data.getAr()+", cash="+data.getCash()+", currassets="+data.getCurrAssets()+", totassets="+data.getTotAssets()+", equity="+data.getEquity()
                    +", ltfindebt="+data.getLtFinDebt()+", stfindebt="+data.getStFinDebt()+", ap="+data.getAp()+", currliabilities="+data.getCurrLiabilities()+", numberofmonths="+data.getNumberOfMonths()
                    +", finfixedassets="+data.getFinFixedAssets()+",INTANGIBLESASSETS="+data.getIntangiblesAssets()+", propertyassets="+data.getPropertyAssets()+" where user_id="+userID+" AND inputyear = "+data.getYear()+"");
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    @Override
    public void saveInputData(InputData data, int companyId){
        createConnection();
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from app.input_data where user_id="+companyId+" AND inputyear = "+data.getYear()+"");
            if(!result.next()){
                insertInputData(data, companyId);
            }
            else{
                updateInputData(data, companyId);
            }
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    @Override
    public ArrayList<InputData> getInputData(int userID) {
        createConnection();
        try {
            ArrayList<InputData> data = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select * from app.input_data where user_id = " + userID + "ORDER BY inputyear DESC");
            while (result.next()) {
                InputData temp = new InputData();
                temp.setAp(result.getDouble("AP"));
                temp.setAr(result.getDouble("AR"));
                temp.setCash(result.getDouble("CASH"));
                temp.setCostOfSales(result.getDouble("COSTOFSALES"));
                temp.setCurrAssets(result.getDouble("CURRASSETS"));
                temp.setCurrLiabilities(result.getDouble("CURRLIABILITIES"));
                temp.setDepreciation(result.getDouble("DEPRECIATION"));
                temp.setEbit(result.getDouble("EBIT"));
                temp.setEbitda(result.getDouble("EBITDA"));
                temp.setEquity(result.getDouble("EQUITY"));
                temp.setFinExp(result.getDouble("FINEXP"));
                temp.setFinExpBank(result.getDouble("FINEXPBANK"));
                temp.setFinExpInterest(result.getDouble("FINEXPINTEREST"));
                temp.setFinExpOther(result.getDouble("FINEXPOTHER"));
                temp.setFinFixedAssets(result.getDouble("FINFIXEDASSETS"));
                temp.setFinRev(result.getDouble("FINREV"));
                temp.setFixedAssets(result.getDouble("FIXEDASSETS"));
                temp.setIntangiblesAssets(result.getDouble("INTANGIBLESASSETS"));
                temp.setInventory(result.getDouble("INVENTORY"));
                temp.setLtFinDebt(result.getDouble("LTFINDEBT"));
                temp.setNetDebt(result.getDouble("NETDEBT"));
                temp.setNetIncome(result.getDouble("NETINCOME"));
                temp.setNrCharges(result.getDouble("NRCHARGES"));
                temp.setNrIncome(result.getDouble("NRINCOME"));
                temp.setNumberOfMonths(result.getInt("NUMBEROFMONTHS"));
                temp.setPropertyAssets(result.getDouble("PROPERTYASSETS"));
                temp.setRecIncome(result.getDouble("RECINCOME"));
                temp.setStFinDebt(result.getDouble("STFINDEBT"));
                temp.setTaxes(result.getDouble("TAXES"));
                temp.setTotAssets(result.getDouble("TOTASSETS"));
                temp.setTurnover(result.getDouble("TURNOVER"));
                temp.setWorkingCapital(result.getDouble("WORKINGCAPITAL"));
                temp.setYear(result.getInt("INPUTYEAR"));
                data.add(temp);
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<String> getSectors() {
        createConnection();
        try {
            ArrayList<String> data = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("Select sector_name from app.sector_averages order by sector_id");
            while (result.next()) {
                data.add(result.getString(1));
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ReturnRatioData getReturnAvg(int compID) {
        createConnection();
        try {
            ReturnRatioData data = new ReturnRatioData();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select ROE, ROA from app.SECTOR_AVERAGES where sector_id = (select sector_id from app.COMPANY_DATA where company_id = "+compID+")");
            while(result.next()){
                data.setROA(result.getDouble("ROA"));
                data.setROE(result.getDouble("ROE"));
                data.setYear(0);
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new ReturnRatioData();
        }
    }
    
     public BalansRatioData getBalansAvg(int compID) {
        createConnection();
        try {
            BalansRatioData data = new BalansRatioData();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select solvency, curr_ratio, quick_ratio from app.SECTOR_AVERAGES where sector_id = (select sector_id from app.COMPANY_DATA where company_id = "+compID+")");
            while(result.next()){
                data.setCurrRatio(result.getDouble("CURR_RATIO"));
                data.setQuickRatio(result.getDouble("QUICK_RATIO"));
                data.setSolvency(result.getDouble("SOLVENCY"));
                data.setYear(0);
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new BalansRatioData();
        }
    }

    public TurnoverRatioData getTurnoverAvg(int compID) {
        createConnection();
        try {
            TurnoverRatioData data = new TurnoverRatioData();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select DIO, DPO, DSO from app.SECTOR_AVERAGES where sector_id = (select sector_id from app.COMPANY_DATA where company_id = "+compID+")");
            while(result.next()){
                data.setDIO(result.getDouble("DIO"));
                data.setDPO(result.getDouble("DPO"));
                data.setDSO(result.getDouble("DSO"));
                data.setYear(0);
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new TurnoverRatioData();
        }
    }

    public CoverageRatioData getCoverageAvg(int compID) {
        createConnection();
        try {
            CoverageRatioData data = new CoverageRatioData();
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select EBITFIN, DEBTEBITDA from app.SECTOR_AVERAGES where sector_id = (select sector_id from app.COMPANY_DATA where company_id = "+compID+")");
            while(result.next()){
                data.setEBITFinExp(result.getDouble("EBITFIN"));
                data.setNetDebtEBITDA(result.getDouble("DEBTEBITDA"));
                data.setYear(0);
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return new CoverageRatioData();
        }
    }
    
    public HashMap<String, String> login(String username, String password){
        createConnection();
        HashMap<String, String> data = new HashMap<>();
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select USER_ID, PRIVILEGELVL, USER_STATUS_ID, NEW_PASS from app.USERS where USERNAME = '"+username+"' AND PASSWORD = '"+password+"'");
            if(result.next()){
                data.put("succes", "true");
                data.put("userid", result.getString("USER_ID"));
                data.put("privilegelvl", Integer.toString(result.getInt("PRIVILEGELVL")));
                data.put("status_id", Integer.toString(result.getInt("USER_STATUS_ID")));
                data.put("new_pass", Integer.toString(result.getInt("NEW_PASS")));
            }
            else{
                data.put("message", "This username/password combination is incorrect. Please try again with other credentials or contact support to reset your password.");
            }
            result.close();
            stmt.close();
            return data;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            data.put("message", "'An error occured while trying to log you in. Please try again in a couple of minutes.");
        }
        return data;
    }
    
    public ArrayList<HashMap<String, String>> getCompanies(int id){
        createConnection();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select COMPANY, SECTOR_NAME, CD.COMPANY_ID " +
"from app.SECTOR_AVERAGES as SA, app.COMPANY_DATA as CD " +
"where SA.SECTOR_ID = CD.SECTOR_ID AND CD.USER_ID = "+id+"");
            while(result.next()){
                HashMap<String, String> data = new HashMap<>();
                data.put("id", Integer.toString(result.getInt("COMPANY_ID")));
                data.put("name", result.getString("COMPANY"));
                data.put("sector", result.getString("SECTOR_NAME"));
                list.add(data);
            }
            result.close();
            stmt.close();
            return list;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return list;
        }
    }

    public void deleteCompany(int id) {
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("delete from app.company_data where company_id = "+id);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }
    
    public ArrayList<User> getUsers(){
        createConnection();
        ArrayList<User> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select user_id, username, privilegelvl, creationdate, user_mail, user_status_id, subscription_date from app.USERS");
            while(result.next()){
                User user = new User();
                user.setCreatedate(result.getString("creationdate"));
                user.setEmail(result.getString("user_mail"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString("username"));
                user.setStatus(result.getInt("user_status_id")+"");
                user.setSubdate(result.getString("subscription_date"));
                list.add(user);
            }
            result.close();
            stmt.close();
            return list;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return list;
        }
    }
    
    public void deleteUser(int id) throws DatabaseException{
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("delete from app.users where user_id = "+id);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            throw new DatabaseException("Something went wrong when deleting this user. Please try again or contact support.");
        }
    }
    
    public User changepass(int id, String pass) throws DatabaseException{
        createConnection();
        User user = new User();
        try {
            stmt = conn.createStatement();
            stmt.execute("update app.users set password = '"+pass + "', new_pass = 0 where user_id = "+id);
            ResultSet result = stmt.executeQuery("select username, user_mail, user_status_id, subscription_date, creationdate, user_id, new_pass from app.users where user_id = "+id);
            while(result.next()){
                user.setCreatedate(result.getString("CREATIONDATE"));
                user.setEmail(result.getString("USER_MAIL"));
                user.setName(result.getString("USERNAME"));
                user.setPrivilegelvl(result.getInt("PRIVILEGELVL"));
                user.setNewpass(result.getInt("NEW_PASS"));
            }
            stmt.close();
            return user;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            throw new DatabaseException("Something went wrong when updating the password. Please try again or contact support.");
        }
    }
    
    public void newuser(String email, String username) throws DatabaseException{
        createConnection();
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into app.users(username, password, privilegelvl, creationdate, user_mail, user_status_id, subscription_date)" +
"values('"+username+"','Temporary', 2, CURRENT_DATE, '"+email+"', 3, CURRENT_DATE)");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            if("23505".equals(e.getSQLState())){
                throw new DatabaseException("This username already exists. Please choose an other one.");
            }
            throw new DatabaseException("Something went wrong when creating a new user. Please reload and try again or contact support.");
        }
    }
}
