/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.dao;

import g3.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class DaoUser extends Dao<User> {

    public DaoUser() {
        super("user");
    }

    @Override
    public Collection<User> getAll() {
        ArrayList<User> objects = new ArrayList<>();
        String req = "SELECT * FROM " + tableName;
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                        new User(
                                rs.getInt("id_" + tableName),
                                rs.getString("pseudo"),
                                rs.getString("contact"),
                                rs.getString("password")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    @Override
    public User getById(int id) {
        User obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new User(
                        rs.getInt("id_" + tableName),
                        rs.getString("pseudo"),
                        rs.getString("contact"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void insert(User obj) {
        String req = "INSERT INTO " + tableName + " (pseudo, contact, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getPseudo());
            pstmt.setString(2, obj.getContact());
            pstmt.setString(3, obj.getPassword());
            int nbLignesImpactees = pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.first()) {
                obj.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User obj) {
        String req = "UPDATE " + tableName + " SET pseudo=?, contact=?, password=? WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, obj.getPseudo());
            pstmt.setString(2, obj.getContact());
            pstmt.setString(3, obj.getPassword());
            pstmt.setInt(4, obj.getId());
            int nbLignesImpactees = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * methode de recherche
     *
     *
     */
    public User getByName(String pseudo) {
        User obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE pseudo=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, pseudo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new User(
                        rs.getInt("id_" + tableName),
                        rs.getString("pseudo"),
                        rs.getString("contact"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    //par Pseudo
    public User getByName(User user) {
        User obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE pseudo=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, user.getPseudo());
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new User(
                        rs.getInt("id_" + tableName),
                        rs.getString("pseudo"),
                        rs.getString("contact"),
                        rs.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void delete(int id) {
        super.delete(id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void delete(User obj) {
        super.delete(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int count() {
        return super.count(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
}
