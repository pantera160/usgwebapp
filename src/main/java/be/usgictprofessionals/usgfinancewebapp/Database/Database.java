/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.Database;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CompanyInfoData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pantera
 */
public interface Database {
    public int saveCompanyData(CompanyInfoData data, int userId);
    public CompanyInfoData getCompanyInfoData(int companyID);
    public void saveInputData(InputData data, int userID);
    public ArrayList<InputData> getInputData(int userID);
    public ArrayList<HashMap> getSectors();
}
