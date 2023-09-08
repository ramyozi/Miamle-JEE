/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g3.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author stag
 */
public class Event implements Identifiable {
    private Integer id_event;
    private String name;
    private Date date_event;
    private String description;
    private boolean organizer;

    public Event() {
        if (LocalDate.now().isAfter(date_event.toLocalDate())) {
            this.organizer = false;
        } else {
            this.organizer = true;
        }
    }

    public Event(String name, Date date, String description, boolean organizer) {
        this.name = name;
        this.date_event = date;
        this.description = description;
        if (LocalDate.now().isAfter(date_event.toLocalDate())) {
            this.organizer = false;
        } else {
            this.organizer = true;
        }
    }

    public Event(Integer id_event, String name, Date date, String description, boolean organizer) {
        this.id_event = id_event;
        this.name = name;
        this.date_event = date;
        this.description = description;
        if (LocalDate.now().isAfter(date_event.toLocalDate())) {
            this.organizer = false;
        } else {
            this.organizer = true;
        }
    }

    public boolean getOrganizer() {
        return organizer;
    }

    public void setOrganizer(boolean organizer) {
        this.organizer = organizer;
    }

    public Integer getId_event() {
        return id_event;
    }

    public String getName() {
        return name;
    }

    public void setId_event(Integer id_event) {
        this.id_event = id_event;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date_event);
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Integer getId() {
        return id_event;
    }

    @Override
    public void setId(Integer arg0) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "Event{"
                + "id_event=" + id_event
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", date='" + date_event.toString() + '\''
                + ", organizer_id=" + organizer
                + '}';
    }

}
