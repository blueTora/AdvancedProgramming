/**
 * The ClockDisplay class implements a digital clock display for a
 * 24 hour clock. The clock shows hours and minutes and seconds. The
 * range of the clock is 00:00:00 (midnight) to 23:59:59.
 * 
 * The clock display receives "ticks" (via the timeTick method) every second
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero and the minute
 * increments when the second roll over to zero.
 * 
 * @author Negar
 * @version 0.0
 * @since 2020-03-17
 */

public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the
     * parameters.
     *
     * @param hour hour field
     * @param minute minute field
     * @param second second field
     */
    public ClockDisplay(int hour, int minute, int second)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute, second);
    }

    /**
     * This method should get called once every second - it makes
     * the clock display go one second forward.
     */
    public void timeTick()
    {
        seconds.increment();
        if(seconds.getValue() == 0) {  // it just rolled over!
            minutes.increment();
        }
        if(minutes.getValue() == 0 && seconds.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute and second.
     *
     * @param hour hour field
     * @param minute minute field
     * @param second second field
     */
    public void setTime(int hour, int minute, int second)
    {
        seconds.setValue(second);
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM:SS.
     * @return displayString field
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + ":" +
                        seconds.getDisplayValue();
    }
}