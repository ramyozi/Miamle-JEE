/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.dao;

/**
 *
 * @author stag
 */
import g3.Model.Attendance;
import g3.beans.AttendanceOrm;
import g3.Model.Event;
import g3.Model.User;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoAttendance extends Dao<Attendance> {

    public DaoAttendance() {
        super("attendance");
    }

    @Override
    public Collection<Attendance> getAll() {
        String req = "SELECT * FROM " + tableName;
        ArrayList<Attendance> objects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                objects.add(
                        new Attendance(
                                rs.getInt("id_" + tableName),
                                rs.getInt("id_user"),
                                rs.getInt("id_event"),
                                rs.getInt("guests"),
                                rs.getString("comment")
                        )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    @Override
    public Attendance getById(int id) {
        Attendance obj = null;
        String req = "SELECT * FROM " + tableName + " WHERE id_" + tableName + "=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {
                obj = new Attendance(
                        rs.getInt("id_" + tableName),
                        rs.getInt("id_user"),
                        rs.getInt("id_event"),
                        rs.getInt("guests"),
                        rs.getString("comment")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void insert(Attendance obj) {
        String req = "INSERT INTO " + tableName + " (id_user, id_event, guests, comment) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getId_user());
            pstmt.setInt(2, obj.getId_event());
            pstmt.setInt(3, obj.getGuests());
            pstmt.setString(4, obj.getComment());
            int nbLignesImpactees = pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.first()) {
                obj.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Attendance obj) {
        String req = "UPDATE " + tableName + " SET id_user=?, id_event=?, guests=?, comment=? WHERE id_attendance=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, obj.getId_user());
            pstmt.setInt(2, obj.getId_event());
            pstmt.setInt(3, obj.getGuests());
            pstmt.setString(4, obj.getComment());
            pstmt.setInt(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance attendance) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM attendance WHERE id_attendance=?");
            pstmt.setInt(1, attendance.getId_attendance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Collection<AttendanceOrm> getOrmById(int id_event) {
        String req = "SELECT * FROM " + tableName + " WHERE id_event=?";
        ArrayList<AttendanceOrm> objects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(req);
            pstmt.setInt(1, id_event);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Attendance attendance = new Attendance(
                        rs.getInt("id_" + tableName),
                        rs.getInt("id_user"),
                        rs.getInt("id_event"),
                        rs.getInt("guests"),
                        rs.getString("comment")
                );
                User user = DaoFactory.getUserDao().getById(rs.getInt("id_user"));
                Event event = DaoFactory.getEventDao().getById(rs.getInt("id_event"));
                objects.add(new AttendanceOrm(attendance, user, event));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    public Collection<AttendanceOrm> getBatchOrmFrom(int qty, int offset) throws SQLException {
        ArrayList<AttendanceOrm> objects = new ArrayList<>();
        String req = "SELECT * FROM attendance ORDER BY pseudo DESC LIMIT ? OFFSET ? ";
        try {
            PreparedStatement stmt = connection.prepareStatement(req);

            stmt.setInt(1, qty);
            stmt.setInt(2, offset);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                objects.add(
                        new AttendanceOrm(
                                new Attendance(
                                        rs.getInt("id_attendance"),
                                        rs.getInt("id_user"),
                                        rs.getInt("id_event"),
                                        rs.getInt("guests"),
                                        rs.getString("comment")
                                )
                        )
                );
//                User user = new User(rs.getInt("user.id_user"), rs.getString("pseudo"), rs.getString("contact"), rs.getString("password"));
//                Event event = new Event(rs.getInt("event.id_event"), rs.getString("name"), rs.getDate("date_event"), rs.getString("description"), rs.getInt("organizer_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objects;
    }

    public List<Attendance> getAttendanceByEventId(int eventId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE id_event=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, eventId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Attendance attendance = new Attendance(rs.getInt("id_attendance"), rs.getInt("id_user"), rs.getInt("id_event"), rs.getInt("guests"), rs.getString("comment"));
            attendanceList.add(attendance);
        }
        return attendanceList;
    }
    
    public int GetTotalUserByEvent(int eventId) throws SQLException {
        int count=0;
        String req = "SELECT * FROM attendance WHERE id_event=? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, eventId);
        
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            count = count + rs.getInt("guests");
        }

        return count;
    }
    public boolean isSubscribed(int id_event, int id_user) throws SQLException {
        boolean valid = false;
        Integer count = 0;
        ArrayList<Integer> rendu = null;

        String req = "SELECT * FROM attendance WHERE id_event=? AND id_user=? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_event);
        ps.setInt(2, id_user);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            rendu.add(rs.getInt("id_attendance"));
        }
        if (rendu.size() > 0) {
            valid = true;
        }
        return valid;
    }

    public ArrayList<Attendance> GetTotalAttendanceByUserId(int id_user) throws SQLException {
        ArrayList<Attendance> attendanceList = null;

        String req = "SELECT * FROM attendance WHERE id_user=? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_user);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Attendance attendance = new Attendance(rs.getInt("id_attendance"), rs.getInt("id_user"), rs.getInt("id_event"), rs.getInt("guests"), rs.getString("comment"));
            attendanceList.add(attendance);
        }

        return attendanceList;
    }
}

