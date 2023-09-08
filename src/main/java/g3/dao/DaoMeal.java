/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.dao;

import g3.Model.Meal;
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
public class DaoMeal extends Dao<Meal> {

    public DaoMeal() {
        super("meal");
    }

    @Override
    public Collection<Meal> getAll() {
        ArrayList<Meal> objects = new ArrayList<>();
        String req = "SELECT * FROM " + tableName;
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                        new Meal(
                                rs.getInt("id_" + tableName),
                                rs.getString("label")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMeal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    @Override
    public Meal getById(int id) {
        Meal obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Meal(
                        rs.getInt("id_" + tableName),
                        rs.getString("label")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMeal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void insert(Meal obj) {
        String req = "INSERT INTO " + tableName + " (label) VALUES (?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(2, obj.getLabel());
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
    public void update(Meal obj) {
        String req = "UPDATE " + tableName + " SET label=? WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, obj.getLabel());
            pstmt.setInt(2, obj.getId());
            int nbLignesImpactees = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            int nbLignesImpactees = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Meal obj) {
        String req = "DELETE FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, obj.getId());
            int nbLignesImpactees = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int count() {
        String req = "SELECT COUNT(*) AS total FROM " + tableName;
        int count = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMeal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public Meal getMealById(int id) {
        String req = "SELECT * FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                return new Meal(
                        rs.getInt("id_" + tableName),
                        rs.getString("label")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoMeal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
