package in.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "eventDetails2")
public class EventDetails2 {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    public void setEvent(Event2 event) {
        this.event = event;
    }

    @ManyToOne
    private Event2 event;

    public EventDetails2() {
    }

    public EventDetails2(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public Long getId() {
        return id;
    }
}
