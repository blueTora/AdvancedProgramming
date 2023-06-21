import java.util.*;
import java.util.Random;

/**
 * This Class is a Voting System which creating the votings and
 * you can manage them with it.
 * like you can make voting or deleting it when you want and
 * make votes and printing the data when ever you wish.
 * This Class use the Voting Class.
 *
 * @author Negar
 * @since 2020-03-23
 * @version 0.0
 * {@link java.util.ArrayList}
 * {@link java.util.Iterator}
 * {@link java.util.Random}
 */
public class VotingSystem {

    private ArrayList<Voting> votingList;

    /**
     * Create a voting system
     * Make new voting list
     */
    public VotingSystem(){

        votingList = new ArrayList<Voting>();
    }

    /**
     * Create a new Voting and adding to voting list
     *
     * @param question question of voting
     * @param type type of voting
     * @param votesList list of votes in voting
     */
    public void createVoting(String question, int type, ArrayList<String> votesList){
        votingList.add(new Voting(question, type, votesList));
    }

    /**
     * Deleting a Voting.
     * @param index index of the voting we want to delete
     */
    public void deleteVoting(int index){
        votingList.remove(index);
    }

    /**
     * Printing the votings question
     */
    public void printVotingQuestions(){

        for(Voting voting : votingList){
            System.out.println(voting.getQuestion());
        }
    }

    /**
     * Printing a voting question and it's options.
     * @param index index of the voting we want
     */
    public void printVoting(int index){
        for(Voting voting : votingList){
            System.out.println(voting.getQuestion());
            System.out.println(voting.getChoices());
        }
    }

    /**
     * submitting a vote.
     * if the ArrayList of choices was empty and the voting was type 0 (One choice)
     * then program will chose a option randomly from available choices in voting.
     *
     * @param votingIndex the index of  the voting we want to vote
     * @param person the person who want to vote
     * @param choices the voting options
     */
    public void vote(int votingIndex, Person person, ArrayList<String> choices){

        if(choices.size() == 0 && votingList.get(votingIndex).getType() == 0){
            Random r = new Random();
            int index = r.nextInt(votingList.get(votingIndex).getChoices().size());

            choices.add(votingList.get(votingIndex).getChoices().get(index));
        }

        votingList.get(votingIndex).vote(person, choices);
    }

    /**
     * Printing the result of a voting.
     * @param index index of the voting that we want result
     */
    public void printResults(int index){
        votingList.get(index).printVotes();
    }
}