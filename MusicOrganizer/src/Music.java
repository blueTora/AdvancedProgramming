/**
 * hold information of the music file.
 * such as music name , singer , file location and year of publish.
 *
 * @author Negar
 * @version 0.0
 * @since 2020-03-17
 */

public class Music {

    //the music file location
    private String address;
    //music file name
    private String name;
    //music file year of publish
    private String year;
    //music file singer's name
    private String singer;

    /**
     * reate a Music file
     *
     * @param address the music file location
     * @param name the music file name
     * @param singer the music file singer's name
     * @param year the music file year of publish
     */
    public Music(String address, String name, String singer, String year){
        this.address = address;
        this.name = name;
        this.singer = singer;
        this.year = year;
    }

    /**
     * get the name of the music
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     * get the location of the music
     * @return address field
     */
    public String getAddress() {
        return address;
    }

    /**
     * get year of published music
     * @return year field
     */
    public String getYear() {
        return year;
    }

    /**
     * get music's singer
     * @return singer field
     */
    public String getSinger() {
        return singer;
    }

    /**
     * Print name and singer and year of the music in the terminal.
     */
    public void print(){
        System.out.println("Name: " + name + "  Singer: " + singer + "   published at: " + year);
    }
}