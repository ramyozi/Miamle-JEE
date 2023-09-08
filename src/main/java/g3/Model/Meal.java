/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.Model;

/**
 *
 * @author stag
 */
public class Meal implements Identifiable {
    private Integer id_meal;
    private String label;

    public Meal(String label) {
        this.label = label;
    }

    public Meal(Integer id_meal, String label) {
        this.id_meal = id_meal;
        this.label = label;
    }

    public Integer getId_meal() {
        return id_meal;
    }

    public void setId_meal(Integer id_meal) {
        this.id_meal = id_meal;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Integer getId() {
        return id_meal;
    }

    @Override
    public void setId(Integer id_meal) {
        this.id_meal = id_meal;
    }
    
}
