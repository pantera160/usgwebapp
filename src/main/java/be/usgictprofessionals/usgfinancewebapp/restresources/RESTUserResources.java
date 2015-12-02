/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.restresources;

import be.usgictprofessionals.usgfinancewebapp.Database.DatabaseException;
import be.usgictprofessionals.usgfinancewebapp.controllers.UserDAO;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.User;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pantera
 */
@Path("/crm")
public class RESTUserResources {
    
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getUsers(){
        return UserDAO.getInstance().getUsers();
    }
    
    @PUT
    @Path("/user/{id}")
    public void resetMail(@PathParam("id") String id){
        try{
        UserDAO.getInstance().resetPass(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @DELETE
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deteteUser(@PathParam("id") String id, @Context final HttpServletResponse response){
        try{
        UserDAO.getInstance().deleteUser(Integer.parseInt(id));
        response.setStatus(HttpServletResponse.SC_OK);
        return 1;
        } catch(DatabaseException e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return 0;
        }
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public HashMap<String, String> login(HashMap<String, String> data){
        String user = data.get("username");
        String pass = data.get("password");
        return UserDAO.getInstance().login(user, pass);
    }
    
    @GET
    @Path("/companies/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<HashMap<String, String>> getCompanies(@PathParam("id") String id){
        return UserDAO.getInstance().getCompanies(Integer.parseInt(id));
    }
    
        
    @DELETE
    @Path("/company/{id}")
    public void deleteCompany(@PathParam("id") String id, @Context final HttpServletResponse response){
        UserDAO.getInstance().deleteCompany(Integer.parseInt(id));
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    @PUT
    @Path("/passchange")
    @Consumes(MediaType.APPLICATION_JSON)
    public User changePass(HashMap<String, String> data){
        try{
            return UserDAO.getInstance().changePass(data);
        }
        catch(DatabaseException ex){
            ex.printStackTrace();
            return new User();
        }
    }
    
    @POST
    @Path("/newuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, String> newUser(HashMap<String, String> data){
        HashMap<String, String> map = new HashMap<>();
        try{
            UserDAO.getInstance().newuser(data.get("username"), data.get("email"));
            map.put("succes", "1");
            return map;
        }
        catch(DatabaseException ex){
            ex.printStackTrace();
            map.put("succes", "0");
            map.put("error", ex.getMessage());
            return map;
        }
    }
}
