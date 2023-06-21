/**
 * <b>Motion Card</b>
 * a abstract class for Skip and Reverse and Draw classes.
 *
 * @author Negar
 * @since 2020-04-18
 * @version 0.0
 */
abstract public class MotionCard  extends Card {

    /**
     * It will call the Card class constructor.
     * each motion card has 20 point.
     * @param color card color
     */
    public MotionCard(char color){
        super(color,20);
    }

}
