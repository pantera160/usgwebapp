/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.usgictprofessionals.usgfinancewebapp.jsonrecources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pantera
 */
@XmlRootElement
public class User {
    
    private String name, email, status, subdate, createdate;
    private int id;
    private int newpass = 1;
    private int privilegelvl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubdate() {
        return subdate;
    }

    public void setSubdate(String subdate) {
        this.subdate = subdate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewpass() {
        return newpass;
    }

    public void setNewpass(int newpass) {
        this.newpass = newpass;
    }

    public int getPrivilegelvl() {
        return privilegelvl;
    }

    public void setPrivilegelvl(int privilegelvl) {
        this.privilegelvl = privilegelvl;
    }
}
