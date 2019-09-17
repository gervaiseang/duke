/**
 * subclass of Task when the input starts with event
 */
public class Event extends Task{

    protected String at;

    /**
     * Constructor for event
     * @param description the description of event
     * @param at the parameter to take in the input of the description when at is detected
     * @return [E] Event description followed by the description after the /at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * override method that converts the task into user readable format
     * @return A string that contains the task, the checkbox and the deadline
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
