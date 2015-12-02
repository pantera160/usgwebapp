/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.jsonrecources;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pantera
 */
@XmlRootElement
public class CompanyInfoData {
        
    private String company= "", closingDate = "", countries = "";
    private String outsideEU = "";
    private HashMap<String, Object> sector;
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public HashMap getSector() {
        return sector;
    }

    public void setSector(HashMap sector) {
        this.sector = sector;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getOutsideEU() {
        return outsideEU;
    }

    public void setOutsideEU(String outsideEU) {
        this.outsideEU = outsideEU;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }
}
