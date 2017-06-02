package com.test;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "event")
public class Event2 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    Set<EventDetails2> eventDetails = new HashSet<>();

    public Set<EventDetails2> getEventDetails() {
        return eventDetails;
    }

    public Event2() {
    }

    public void addEventDetails(EventDetails2 eventDetails){
        this.eventDetails.add(eventDetails);
        eventDetails.setEvent(this);
    }

    public Long getId() {
        return id;
    }
}
