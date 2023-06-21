import ir.huri.jcal.JalaliCalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.GregorianCalendar;
import java.util.*;

//compile 'ir.huri:JalaliCalendar:1.3.3'

/**
 * This Class make a voting and manage the process of it.
 * THis Class use the Vote and Person Class.
 * It saves the date of the voting in Jalali Calendar.
 *
 * @author Negar
 * @since 2020-03-23
 * @version 0.0
 * {@link java.util.ArrayList}
 * {@link java.util.HashMap}
 * {@link java.util.HashSet}
 */
public class Voting {

    private int type;
    private String question;
    private ArrayList<Person> voters;
    private HashMap<String , HashSet<Vote>> listOfVotesToChoices;
    private ArrayList<String> choices;

    /**
     * Create a Voting
     * Make new ArrayList of voters and listOfVotesToChoices
     *
     * @param type type of voting
     * @param question question of voting
     * @param choices the votings options
     */
    public Voting(String question, int type, ArrayList<String> choices){

        voters = new ArrayList<Person>();
        listOfVotesToChoices = new HashMap<String , HashSet<Vote>>();
        this.type = type;
        this.question = question;
        this.choices = choices;

        for(String option : choices){
            listOfVotesToChoices.put(option , new HashSet<Vote>());
        }
    }

    /**
     * Adding a choice to our voting's options.
     * @param choice the choice that we want to add to our voting's options
     */
    public void createChoice(String choice){
        choices.add(choice);
    }

    /**
     * Submitting a vote.
     * It will check if someone have voted already or not.
     * if someone did then it wont count the vote.
     *
     * @param person the person who want to vote
     * @param options the options chosen by the person
     */
    public void vote(Person person, ArrayList<String> options){

        if(type == 1 || options.size() == 1){
            JalaliCalendar jalaliCalendar = new JalaliCalendar(new GregorianCalendar());
            String date = jalaliCalendar.toString();

            Vote vote = new Vote(person, date);

            boolean test = true;

            for(String choice : choices){

                if(listOfVotesToChoices.get(choice).contains(vote))
                    test = false;
            }

            if(test){

                voters.add(person);
                System.out.println("Vote Submitted!");

                for(String option : options){

                    listOfVotesToChoices.get(option).add(vote);
                }
            } else
                System.out.println("You have voted before!");

        }else
            System.out.println("Too many Choices\nYou Can Only Chose one Option");
    }

    /**
     * Print the Result of voting.
     */
    public void printVotes(){
        for(String option:choices){

            System.out.println(option + " -> " + listOfVotesToChoices.get(option).size());
        }
    }

    /**
     * get the question of the voting.
     * @return question field
     */
    public String getQuestion() {
        return question;
    }

    /**
     * get list of voting choice's.
     * @return choices field
     */
    public ArrayList<String> getChoices() {
        return choices;
    }

    /**
     * get list of voters of have voted in this voting.
     * @return voters field
     */
    public ArrayList<Person> getVoters() {
        return voters;
    }

    /**
     * @return type of the voting
     */
    public int getType() {
        return type;
    }
}
