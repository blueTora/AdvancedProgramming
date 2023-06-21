import java.util.Objects;

/**
 * This Class make the vote.
 *
 * This Class use the Person Class
 *
 * @author Negar
 * @since 2020-03-23
 * @version 0.0
 * {@link java.util.Objects}
 */
public class Vote {

    Person person;
    String date;

    /**
     * Create a Vote for the Person.
     *
     * @param person the person who voted
     * @param date date of voting
     */
    public Vote(Person person, String date){

        this.person = person;
        this.date = date;
    }

    /**
     * get the person who voted.
     * @return person field
     */
    public Person getPerson() {
        return person;
    }

    /**
     * get the date of voting.
     * @return date field
     */
    public String getDate() {
        return date;
    }

    /**
     * we have override the equal method for checking
     * if two votes are equal or not.
     *
     * @param o the Object we want to check
     * @return true if they are equal and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        Vote vote = (Vote) o;
        return getPerson().equals(vote.getPerson()) &&
                getDate().equals(vote.getDate());
    }

    /**
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(getPerson(), getDate());
    }
}