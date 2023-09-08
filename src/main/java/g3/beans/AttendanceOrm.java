/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.beans;

import g3.Model.Attendance;
import g3.Model.Contain;
import g3.Model.Event;
import g3.Model.User;

/**
 *
 * @author stag
 */
public class AttendanceOrm {

    private Attendance attendance;
    private User user;
    private Event event;
    private Contain contain;

    public AttendanceOrm() {
    }

    public AttendanceOrm(Attendance attendance) {
        this.attendance = attendance;
    }
    
    public AttendanceOrm(Contain contain) {
        this.contain = contain;
    }

    public AttendanceOrm(Attendance attendance, Contain contain) {
        this.attendance = attendance;
        this.contain = contain;
    }
    
    public AttendanceOrm(Attendance attendance, User user, Event event) {
        this.attendance = attendance;
        this.user = user;
        this.event = event;
    }

    public AttendanceOrm(Attendance attendance, User user, Event event, Contain contain) {
        this.attendance = attendance;
        this.user = user;
        this.event = event;
        this.contain = contain;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Contain getContain() {
        return contain;
    }

    public void setContain(Contain contain) {
        this.contain = contain;
    }
    
    public Integer getAttendanceByEvent() {
        return attendance.getId_attendance();
    }
    
}
