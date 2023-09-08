/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enum;

/**
 *
 * @author stag
 */
public enum Meal {
    STARTERS("Entrées"),
    MAIN_COURSES("Plats"),
    DESSERTS("Desserts"),
    DRINKS("Boissons");

    private final String frenchName;

    
    /**
     * Constructeur de la traduction en français.
     *
     * @param String le nom à traduire.
     */
    private Meal(String frenchName) {
        this.frenchName = frenchName;
    }

    /**
     * Setter de la traduction en français.
     *
     * @return String le nom traduit.
     */
    public String getFrenchName() {
        return frenchName;
    }
}
