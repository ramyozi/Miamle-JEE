/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.dao;

import g3.Model.Attendance;
import g3.Model.Contain;
import g3.beans.ContainOrm;
import g3.Model.Meal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stag
 */
public class DaoContain extends Dao<Contain> {

    public DaoContain() {
        super("contain");
    }

    @Override
    public Collection<Contain> getAll() {
        String req = "SELECT * FROM " + tableName;
        ArrayList<Contain> objects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                    new Contain(
                        rs.getInt("id_meal"),
                        rs.getInt("id_attendance"),
                        rs.getInt("quantity"),
                        rs.getString("description")
                    )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    @Override
    public Contain getById(int id) {
        Contain obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE id_attendance=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Contain(
                    rs.getInt("id_meal"),
                    rs.getInt("id_attendance"),
                    rs.getInt("quantity"),
                    rs.getString("description")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void insert(Contain obj) {
        String req = "INSERT INTO " + tableName + " (id_meal, id_attendance, quantity, description) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getId_meal());
            pstmt.setInt(2, obj.getId_attendance());
            pstmt.setInt(3, obj.getQuantity());
            pstmt.setString(4, obj.getDescription());
            int nbLignesImpactees = pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Contain obj) {
        String req = "UPDATE " + tableName + " SET  id_meal=?, id_attendance=?, quantity=?, description=? WHERE id_attendance=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, obj.getId_meal());
            pstmt.setInt(2, obj.getId_attendance());
            pstmt.setInt(3, obj.getQuantity());
            pstmt.setString(4, obj.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Contain obj) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM contain WHERE id_attendance=?");
            pstmt.setInt(1, obj.getId_attendance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Collection<ContainOrm> getOrmById(int id_attendance) {
        String req = "SELECT * FROM " + tableName + " WHERE id_attendance=?";
        ArrayList<ContainOrm> objects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id_attendance);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Contain contain = new Contain(
                    rs.getInt("id_meal"),
                    rs.getInt("id_attendance"),
                    rs.getInt("quantity"),
                    rs.getString("description")
                );
                Attendance attendance = DaoFactory.getAttendanceDao().getById(rs.getInt("id_attendance"));
                objects.add(new ContainOrm(attendance, contain));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    public Collection<ContainOrm> getBatchOrmFrom(int qty, int offset) throws SQLException {
        ArrayList<ContainOrm> objects = new ArrayList<>();
        String req = "SELECT * FROM contain ORDER BY id_meal DESC LIMIT ? OFFSET ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(req);

            stmt.setInt(1, qty);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                objects.add(
                    new ContainOrm(
                        new Contain(
                            rs.getInt("id_meal"),
                            rs.getInt("id_attendance"),
                            rs.getInt("quantity"),
                            rs.getString("description")
                        )
                    )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoContain.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    public List<Contain> getContainByAttendanceId(int attendanceID) throws SQLException {
        List<Contain> containList = new ArrayList<>();
        String query = "SELECT * FROM contain WHERE id_attendance=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, attendanceID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Contain contain = new Contain(rs.getInt("id_meal"), rs.getInt("id_attendance"), rs.getInt("quantity"), rs.getString("description"));
            containList.add(contain);
        }
        return containList;
    }
    
    public Integer GetQuantityByBothIDs(Integer id_attendance, Integer id_meal) throws SQLException {
        Integer qty = 0;
        String req = "SELECT quantity FROM contain WHERE id_attendance=? AND id_meal=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_attendance);
        ps.setInt(2, id_meal);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            qty = rs.getInt("quantity");
        }
        return qty;
    }
    
    
    public String GetDescriptionByBothIDs(Integer id_attendance, Integer id_meal) throws SQLException {
        String des = "";
        String req = "SELECT description FROM contain WHERE id_attendance=? AND id_meal=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_attendance);
        ps.setInt(2, id_meal);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            des = rs.getString("description");
        }
        return des;
    }
    
    public int GetTotalMealByID(Integer id_meal) throws SQLException {
        int count=0;
        String req = "SELECT * FROM contain WHERE id_meal=? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_meal);
        
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            count = count + rs.getInt("quantity");
        }

        return count;
    }
}
