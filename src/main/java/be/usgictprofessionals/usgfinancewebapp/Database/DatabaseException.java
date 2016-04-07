/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.Database;

/**
 *
 * @author Pantera
 */
public class DatabaseException extends Exception{
    
    public DatabaseException(String msg){
        super(msg);
    }
    
    public DatabaseException(){
        super("An error has occured while performing an action on the database. Please contact support.");
    }
}
