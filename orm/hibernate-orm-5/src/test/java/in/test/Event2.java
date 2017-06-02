package in.test;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "event2")
public class Event2 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    Set<EventDetails2> eventDetails = new HashSet<>();

    public Set<EventDetails2> getEventDetail2s() {
        return eventDetails;
    }

    public Event2() {
    }

    public void addEventDetails(EventDetails2 eventDetails2){
        this.eventDetails.add(eventDetails2);
        eventDetails2.setEvent(this);
    }

    public Long getId() {
        return id;
    }
}
