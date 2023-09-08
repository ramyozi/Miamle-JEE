/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.dao;

import g3.Model.Identifiable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author stag
 */
public abstract class Dao<T extends Identifiable> implements Crudable<T> {

    protected Connection connection;
    private String url;
    private final String user = "miamle";
    private final String pwd = "miamle";
    private final String DBType = "mariadb";
    private final String DBip = "localhost";
    private final int DBPort = 3306;
    private final String DBName = "Miamle";
    protected String tableName;

    public Dao(String tableName) {
        this.tableName = tableName;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            url = "jdbc:" + DBType + "://" + DBip + ":" + DBPort + "/" + DBName;
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connexion à la DB impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
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
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public void delete(T obj) {
        String req = "DELETE FROM " + tableName + " WHERE id_" + tableName + "=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, obj.getId());
            int nbLignesImpactees = pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
