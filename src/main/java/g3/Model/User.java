/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Model;

/**
 *
 * @author stag
 */
public class User implements Identifiable {

    protected Integer id_user;
    protected String pseudo;
    protected String contact;
    protected String password;

    public User() {
    }

    public User(Integer id, String pseudo, String contact,  String password) {
        this.id_user = id;
        this.pseudo = pseudo;
        this.contact = contact;
        this.password = password;
    }

    public User(String pseudo, String contact, String password) {
        this.pseudo = pseudo;        
        this.contact = contact;
        this.password = password;
    }

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    // getters and setters
    public String getContact() {
        return contact;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    @Override
    public Integer getId() {
        return id_user;
    }

    @Override
    public void setId(Integer id_user) {
        this.id_user = id_user;
    }

}
