/**
 * <h1>Clock Display</h1>
 * This program Simulate a Digital Clock which counts second ,minute and hour.
 * It can be use in both ways that we give a time to it and count from that time or
 * use the base time of 00:00:00.
 *
 * @author Negar
 * @version 0.0
 * @since 2020-03-17
 */

public class Main {

    /**
     * This is the main method which makes use of CLockDisplay Class and
     * CLockDisplay Class use NumberDisplay Class.
     * @param args Unused.
     */
    public static void main(String[] args) {
        ClockDisplay time = new ClockDisplay();

        time.setTime(4,59,59);
        time.timeTick();
        System.out.println(time.getTime());
    }
}