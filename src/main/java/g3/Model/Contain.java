/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.Model;

/**
 *
 * @author stag
 */
public class Contain implements Identifiable{

    private Integer id_attendance;
    private Integer id_meal;
    private int quantity;
    private String description;

    public Contain(Integer id_attendance, Integer id_meal, int quantity, String description) {
        this.id_attendance = id_attendance;
        this.id_meal = id_meal;
        this.quantity = quantity;
        this.description = description;
    }

    public Contain(Integer id_attendance, Integer id_meal, int quantity) {
        this.id_attendance = id_attendance;
        this.id_meal = id_meal;
        this.quantity = quantity;
    }

    public Integer getId_attendance() {
        return id_attendance;
    }

    public void setId_attendance(Integer id_attendance) {
        this.id_attendance = id_attendance;
    }

    public Integer getId_meal() {
        return id_meal;
    }

    public void setId_meal(Integer id_meal) {
        this.id_meal = id_meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id_attendance;
    }

    @Override
    public void setId(Integer id) {
        this.id_attendance = id_attendance;
    }
    
    
}
