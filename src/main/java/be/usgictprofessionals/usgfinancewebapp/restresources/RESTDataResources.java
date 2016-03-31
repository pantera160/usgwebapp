/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.usgictprofessionals.usgfinancewebapp.restresources;

import be.usgictprofessionals.usgfinancewebapp.jsonrecources.InputData;
import be.usgictprofessionals.usgfinancewebapp.controllers.DataDAO;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.BalansRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CompanyInfoData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.CoverageRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.ReturnRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.Sector;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.TurnoverRatioData;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.WCMData;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

/**
 *
 * @author Pantera
 */
@Path("/data")
public class RESTDataResources {

    /**
     *
     * @param data JSON string which will automatically be translated into a XmlRootElement class to easy access
     * send in all the data from the initial input page
     *this method must be called for every column of input fields. ex. if the data from 3 years is filled in, the request has to be send 3 times.
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON.
     */
    @POST
    @Path("/input/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<InputData> enterData(ArrayList<InputData> data, @PathParam("id") String id) {
        return DataDAO.getInstance().calcData(data, Integer.parseInt(id));
    }

    
    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required to fill in the fields on the input page after data submission
     */
    @GET
    @Path("/input/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<InputData> returnInput(@Context final HttpServletResponse response, @PathParam("id") String id) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getInputReturn(Integer.parseInt(id));
    }

    
    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required for the balans ratio oveview page.
     */
    @GET
    @Path("/balans/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<BalansRatioData> getBalans(@Context final HttpServletResponse response, @PathParam("id") String id) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getBalans(Integer.parseInt(id));
    }

    
    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required for the return ratio overview page
     */
    @GET
    @Path("/return/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ReturnRatioData> getReturn(@Context final HttpServletResponse response, @PathParam("id") String id) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getReturn(Integer.parseInt(id));
    }

    
    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required for the turnover ratio overview page
     */
    @GET
    @Path("/turnover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<TurnoverRatioData> getTurnover(@Context final HttpServletResponse response, @PathParam("id") String id) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getTurnover(Integer.parseInt(id));
    }

    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required for the coverage ratio overview page
     */
    @GET
    @Path("/coverage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<CoverageRatioData> getCoverage(@Context final HttpServletResponse response, @PathParam("id") String id) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getCoverage(Integer.parseInt(id));
    }

    /**
     *
     * @param data
     * @param response
     * 409 error code will be send if there hasn't been any input
     * send in the data from the input fields for the WCM savings page
     * get the calculated data to fill in the non-init non-input fields
     * @param id
     * @return 
     */
    @POST
    @Path("/wcm/{id}/{year}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WCMData enterWCMData(WCMData data, @Context final HttpServletResponse response, @PathParam("id") String id, @PathParam("year") String year) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getCalcDataWCM(data, Integer.parseInt(id), Integer.parseInt(year));
    }

    //
    /**
     *
     * @param response
     * @param id
     * @return XmlRootElement class which will automatically be translated into
     * JSON. 409 error code will be send if there hasn't been any input
     * get the data required to fill in the non-input fields on the WCM
     * savings page
     */
    @GET
    @Path("/wcm/{id}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public WCMData getWCMData(@Context final HttpServletResponse response, @PathParam("id") String id, @PathParam("year") String year) {
        if(!DataDAO.getInstance().inputHasBeenReceived()){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return DataDAO.getInstance().getInitData(Integer.parseInt(id), Integer.parseInt(year));
    }
    
    @GET
    @Path("/company/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CompanyInfoData getCompanyData(@PathParam("id") String id){
        return DataDAO.getInstance().getCompanyData(Integer.parseInt(id));
    }
    
    @POST
    @Path("/company/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public int saveCompanyData(CompanyInfoData data, @Context final HttpServletResponse response, @PathParam("id") String id){
        response.setStatus(HttpServletResponse.SC_OK);
        return DataDAO.getInstance().saveCompanyData(data, Integer.parseInt(id));
    }
    
    @GET
    @Path("/sectors")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap> getSectors(){
        return DataDAO.getInstance().getSectors();
    }
    
    @PUT
    @Path("/sector/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateSector(@PathParam("id") String id){
        
    }
    
    @PUT
    @Path("/source/{id}/{type}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateSource(@PathParam("id") String id, @PathParam("type") String type){
        return DataDAO.getInstance().getSource(id, type);
    }
    
    @GET
    @Path("/sectoroverview")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Sector> getSectorOverview(){        
        return null;
    }
    
    @GET
    @Path("/sources")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<Integer, String>> getSources(){
        return null;
    }
    
    @GET
    @Path("/years/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap> getYears(@PathParam("id") String id){
        return DataDAO.getInstance().getYears(Integer.parseInt(id));
    }
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<InputData> uploadXbrlToInputdata(@FormDataParam("file") InputStream fileInputStream,
                                 @FormDataParam("file") FormDataContentDisposition contentDispositionHeader){
        try {
            File file = File.createTempFile("tempxbrl", ".xbrl");
            FileUtils.copyInputStreamToFile(fileInputStream, file);
            return DataDAO.getInstance().xbrlToInputdata(file);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            ex.printStackTrace();
            return null;
        }
    }  
}
