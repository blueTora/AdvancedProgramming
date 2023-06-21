/**
 * This Class make a user for the person who wants to vote.
 *
 * @author Negar
 * @since 2020-03-23
 * @version 0.0
 */
public class Person {

    private String firstName;
    private String lastName;

    /**
     * Create a Person for voting
     *
     * @param fName First Name of the person
     * @param lName Last Name of the person
     */
    public Person(String fName, String lName) {

        firstName = fName;
        lastName = lName;
    }

    /**
     * get First Name of the person
     *
     * @return firstName field
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get Last Name of the person
     *
     * @return lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return full name of the person
     */
    @Override
    public String toString() {
        return "Voter:    " + firstName + " " + lastName;
    }
}
