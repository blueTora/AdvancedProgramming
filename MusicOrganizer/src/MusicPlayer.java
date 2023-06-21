/**
 * Provide basic playing of music files.
 * 
 * @author Negar
 * @version 1.0
 * @since 2020-03-17
 */

public class MusicPlayer
{
    // The current player. It might be null.
    private boolean isPlaying;

    /**
     * Constructor for objects of class MusicFilePlayer
     */
    public MusicPlayer()
    {
        isPlaying = false;
    }
    

    /**
     * Start playing the given audio file.
     * The method returns once the playing has been started.
     * @param filename The file to be played.
     */
    public void startPlaying(String filename)
    {
        System.out.println(filename + " is playing...");
		isPlaying = true;
    }

    /**
     * to stop the playing the music.
     */
    public void stop()
    {
        System.out.println("player is stopped!");
		isPlaying = false;
    }

    /**
     * check if the player is playing or not.
     * @return isPlaying field - return false or true boolean value.
     */
    public boolean isPlaying() {
        return isPlaying;
    }
}