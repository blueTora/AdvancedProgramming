import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio files.
 * It use Music Class to add new musics to list.
 * 
 * @author Negar
 * @version 1.0
 * @since 2020-03-17
 * @see java.util.ArrayList
 * @see java.util.Iterator
 */
public class MusicCollection
{
    // An ArrayList for storing the file names of music files.
    private ArrayList<Music> files;

    // A player for the music files.
    private MusicPlayer player;

    //An Array for favorite songs
    private ArrayList<Music> favorites ;
        
    /**
     * Create a MusicCollection
     */
    public MusicCollection()
    {
        player = new MusicPlayer();
        files = new ArrayList<Music>();
        favorites = new ArrayList<Music>();
    }
    
    /**
     * Add a file to the collection.
     * @param address The file address to be added.
     * @param name The file name to be added.
     * @param singer The file singer to be added.
     * @param year The file year of Publish to be added.
     */
    public void addFile(String address, String name, String singer, String year)
    {
        files.add(new Music(address, name, singer, year));
    }
    
    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    /**
     * List a file from the collection.
     * check if index is valid.
     * @param index The index of the file to be listed.
     */
    public void listFile(int index)
    {
        if(validIndex(index))
            files.get(index).print();
    }
    
    /**
     * Show a list of all the files in the collection.
     */
    public void listAllFiles()
    {
        for (int i = 0; i < files.size(); i++) {
            files.get(i).print();
        }
    }
    
    /**
     * Remove a file from the collection.
     * check if index is valid.
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index)
    {
        if(validIndex(index))
            files.remove(files.get(index));
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * check if index is valid.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index)
    {
        if(validIndex(index))
            player.startPlaying(files.get(index).getAddress());
    }

    /**
     * Stop the player.
     * if the player is playing.
     */
    public void stopPlaying()
    {
        if(player.isPlaying())
            player.stop();
    }


    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
       if(index >= 0)
       {
           if(index < files.size())
               return true;
           else
               return false;
       } else
           return false;
    }

    /**
     * Create a list of favorite musics
     * @param fileAddress the file address to be add as favorite
     */
    public void addFavorite(String fileAddress){
        Iterator<Music> it = files.iterator();

        while(it.hasNext()){
            Music file = it.next();
            if(file.getAddress().contains(fileAddress))
                favorites.add(file);
        }
    }

    /**
     * Remove music from favorites list
     * @param fileAddress the file address to be removed
     */
    public void removeFavorite(String fileAddress){
        Iterator<Music> it = files.iterator();

        while(it.hasNext()){
            Music file = it.next();
            if(file.getAddress().contains(fileAddress))
                it.remove();
        }
    }

    /**
     * Print the list of favorite musics
     * Using the print method in Music Class
     */
    public void printListFavorites(){
        for (int i = 0; i < favorites.size(); i++) {
            favorites.get(i).print();
        }
    }

    /**
     * find the music with singer name
     * @param fileName the singer of the mjsic we want to find
     * @return index of the found music
     */
    public int searchingInSinger(String fileName){

        Iterator<Music> it = files.iterator();

        while(it.hasNext()){
            Music file = it.next();

            if(file.getSinger().contains(fileName))
            {
                file.print();
                return files.indexOf(file);
            }
        }

        System.out.println("The Music was'nt Found");
        return -1;
    }

    /**
     * Find the music with addrress name
     * @param fileName the address of the music we want to find
     * @return index of the found music
     */
    public int searchingInAddress(String fileName){

        Iterator<Music> it = files.iterator();

        while(it.hasNext()){
            Music file = it.next();

            if(file.getAddress().contains(fileName))
            {
                file.print();
                return files.indexOf(file);
            }
        }

        System.out.println("The Music was'nt Found");
        return -1;
    }
}