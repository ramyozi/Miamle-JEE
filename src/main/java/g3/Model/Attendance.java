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
import Enum.Meal;
import java.util.HashMap;
import java.util.Objects;

public class Attendance extends User implements Comparable<Attendance>, Identifiable {

    private Integer id_attendance;
    private Integer id_user;
    private Integer id_event;
    private Integer guests;
    private String comment;
    private final HashMap<Meal, Integer> meals; // Les plats amenés : le type et la quantité
    
    public Attendance() {
        this.meals = new HashMap<>();
    }

    public Attendance(Integer id_user, Integer id_event, Integer guests, String comment) {
        this.id_user = id_user;
        this.id_event = id_event;
        this.guests = guests;
        this.comment = comment;
        this.meals = new HashMap<>();
    }

    public Attendance(Integer id_attendance, Integer id_user, Integer id_event, Integer guests, String comment) {
        this.id_attendance = id_attendance;
        this.id_user = id_user;
        this.id_event = id_event;
        this.guests = guests;
        this.comment = comment;
        this.meals = new HashMap<>();
    }


    public Integer getId_attendance() {
        return id_attendance;
    }

    public void setId_attendance(Integer id_attendance) {
        this.id_attendance = id_attendance;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_event() {
        return id_event;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id_attendance=" + id_attendance +
                ", id_user=" + id_user +
                ", id_event=" + id_event +
                ", guests=" + guests +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id_attendance, that.id_attendance) &&
                Objects.equals(id_user, that.id_user) &&
                Objects.equals(id_event, that.id_event) &&
                Objects.equals(guests, that.guests) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(meals, that.meals);
    }

    @Override
    public Integer getId() {
        return id_attendance;
    }

    @Override
    public void setId(Integer id) {
        this.id_attendance = id;
    }
    
    /**
     * Retourne la quantité de parts du type de plat passé en paramètre.
     *
     * @param type Le type de plat
     * @return La quantité amenée (en nombre de parts) de ce type de plat
     */
    public int getValueByType(Meal type) {
        return meals.getOrDefault(type, 0);
    }

    /**
     * Ajoute une quantité de parts à un plat donné.
     *
     * @param meal Le type de plat
     * @param qty La quantité à ajouter
     * @throws IllegalArgumentException si la quantité est strictement négative.
     */
    public void addMeal(Meal meal, int qty) {
        if (qty > 0) {
            this.meals.put(meal, qty);
        } else if (qty == 0) {
            clearMeal(meal);
        } else {
            throw new IllegalArgumentException("La quantité doit être >= 0");
        }
    }

    /**
     * Supprime le type de plat.
     *
     * @param meal Le type de plat
     */
    public void clearMeal(Meal meal) {
        this.meals.remove(meal);
    }

    @Override
    public int compareTo(Attendance o) {
        if (this == o) {
            return 0;
        }
        return this.pseudo.compareTo(o.pseudo);
    }
}

