/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.beans;

import g3.Model.Event;
import g3.Model.User;
import g3.dao.DaoFactory;

/**
 *
 * @author stag
 */
public class EventOrm {

    private Event event;
    private User createur;

    public EventOrm(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public User getCreateur() {
        if (createur == null) {
            this.createur = DaoFactory.getUserDao().getById(1);
        }
        return createur;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

}
