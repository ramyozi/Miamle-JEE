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
public class DaoFactory {
    private static DaoUser daou;
    private static DaoEvent daoe;
    private static DaoAttendance daoa;
    private static DaoContain daoc;
    private static DaoMeal daom;

    public static DaoUser getUserDao() {
        if (daou == null) {
            daou = new DaoUser();
        }
        return daou;
    }

    public static DaoEvent getEventDao() {
        if (daoe == null) {
            daoe = new DaoEvent();
        }
        return daoe;
    }
    public static DaoAttendance getAttendanceDao() {
        if (daoa == null) {
            daoa = new DaoAttendance();
        }
        return daoa;
    }

    public static DaoContain getContainDao() {
        if (daoc == null) {
            daoc = new DaoContain();
        }
        return daoc;
    }
    
    public static DaoMeal getMealDao() {
        if (daom == null) {
            daom = new DaoMeal();
        }
        return daom;
    }
}
