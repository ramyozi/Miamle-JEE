/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.dao;

import g3.Model.Event;
import g3.beans.EventOrm;
import java.sql.Date;
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
public class DaoEvent extends Dao<Event> {

    public DaoEvent() {
        super("event");
    }

    @Override
    public Collection<Event> getAll() {
        String req = "SELECT * FROM " + tableName;
        ArrayList<Event> objects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                        new Event(
                                rs.getInt("id_" + tableName),
                                rs.getString("name"),
                                rs.getDate("date_event"),
                                rs.getString("description"),
                                rs.getBoolean("organizer")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    @Override
    public Event getById(int id) {
        Event obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE id_" + tableName + "=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Event(
                        rs.getInt("id_" + tableName),
                        rs.getString("name"),
                        rs.getDate("date_event"),
                        rs.getString("description"),
                        rs.getBoolean("organizer")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void insert(Event obj) {
        String req = "INSERT INTO " + tableName + " (name, date_event, description, organizer) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getName());
            pstmt.setDate(2, (Date) obj.getDate_event());
            pstmt.setString(3, obj.getDescription());
            pstmt.setBoolean(4, obj.getOrganizer());
            int nbLignesImpactees = pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.first()) {
                obj.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Event obj) {
        String req = "UPDATE " + tableName + " SET name=?, date_event=?, description=?, organizer=? WHERE id_event=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setString(1, obj.getName());
            pstmt.setDate(2, (Date) obj.getDate_event());
            pstmt.setString(3, obj.getDescription());
            pstmt.setBoolean(4, obj.getOrganizer());
            pstmt.setInt(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Collection<EventOrm> getBatchFrom(int qty, int offset) {
        ArrayList<EventOrm> objects = new ArrayList<>();
        String req = "SELECT * FROM " + tableName + " ORDER BY date_event ASC LIMIT ? OFFSET ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, qty);
            pstmt.setInt(2, offset);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                        new EventOrm(
                                new Event(
                                        rs.getInt("id_" + tableName),
                                        rs.getString("name"),
                                        rs.getDate("date_event"),
                                        rs.getString("description"),
                                        rs.getBoolean("organizer")
                                )
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    public Integer getFirstId() {
        Integer id = null;
        String req = "SELECT * FROM " + tableName + " ORDER BY id_" + tableName + " ASC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                id = rs.getInt("id_" + tableName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public Integer getLastId() {
        Integer id = null;
        String req = "SELECT * FROM " + tableName + " ORDER BY id_" + tableName + " DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                id = rs.getInt("id_" + tableName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

}
