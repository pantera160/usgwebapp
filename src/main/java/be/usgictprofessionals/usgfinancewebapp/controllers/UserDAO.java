/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.controllers;

import be.usgictprofessionals.usgfinancewebapp.Database.DatabaseException;
import be.usgictprofessionals.usgfinancewebapp.Database.JdbcDatabase;
import be.usgictprofessionals.usgfinancewebapp.jsonrecources.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pantera
 */
public class UserDAO {
    
    private static UserDAO uniqueInstance;
    
    private UserDAO(){
    
    };
    
    public static UserDAO getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new UserDAO();
        }
        return uniqueInstance;
    }
    
    public HashMap<String, String> login(String username, String password){
        return JdbcDatabase.getInstance().login(username, password);
    }
    
    public ArrayList<HashMap<String, String>> getCompanies(int userid){
        return JdbcDatabase.getInstance().getCompanies(userid);
    } 
    
    public void deleteCompany(int id) {
        JdbcDatabase.getInstance().deleteCompany(id);
    }
    
    public ArrayList<User> getUsers(){
        return JdbcDatabase.getInstance().getUsers();
    }
    
    public void deleteUser(int id) throws DatabaseException{
        JdbcDatabase.getInstance().deleteUser(id);
    }
    
    public User changePass(HashMap<String, String> data) throws DatabaseException{
        return JdbcDatabase.getInstance().changepass(Integer.parseInt(data.get("userid")), data.get("pass"));
    }
    
    public void newuser(String username, String email) throws DatabaseException{
        JdbcDatabase.getInstance().newuser(email, username);
    }
}
