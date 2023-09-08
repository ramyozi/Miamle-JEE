/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.beans;

import g3.Model.Attendance;
import g3.Model.Contain;
import g3.Model.Meal;

/**
 *
 * @author stag
 */
public class ContainOrm {

    private Attendance attendance;
    private Contain contain;
    private Meal meal;

    public ContainOrm() {
    }

    public ContainOrm(Contain contain) {
        this.contain = contain;
    }

    public ContainOrm(Attendance attendance, Contain contain) {
        this.attendance = attendance;
        this.contain = contain;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Contain getContain() {
        return contain;
    }

    public void setContain(Contain contain) {
        this.contain = contain;
    }

    public Integer getContainByID() {
        return attendance.getId_attendance();
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

}
