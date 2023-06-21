import java.util.*;
/**
 * <h1>Voting System</h1>
 * This program allow you to create multiple voting and you can
 * ask how many voters have voted until now or what is the result
 * until now and in the end it can show the final result of the voting.
 *
 * @author Negar
 * @since 2020-03-23
 * @version 0.0
 */

public class Main {

    /**
     * with main method we use the voting system to
     * create votings and manage them.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        VotingSystem votingSystem = new VotingSystem();

        Person voter1 = new Person("Negar", "Karami");
        Person voter2 = new Person("Mozhdeh", "Karamrezaie");

        ArrayList<String> options = new ArrayList<String>();
        options.add("Yes");
        options.add("No");

        ArrayList<String> days = new ArrayList<String>();
        days.add("Monday");
        days.add("Sunday");
        days.add("Friday");

        votingSystem.createVoting("Do you Come?", 0, options);
        votingSystem.createVoting("When?", 1, days);

        System.out.println("Print Voting Question :");
        votingSystem.printVotingQuestions();

        System.out.println("Print Voting");
        votingSystem.printVoting(0);

        ArrayList votes1 = new ArrayList();
        votes1.add("Yes");

        ArrayList votes2 = new ArrayList();
        votes2.add("Friday");
        votes2.add("Monday");

        votingSystem.vote(0, voter1, votes1);
        votingSystem.vote(1, voter1, votes2);

        ArrayList votes3 = new ArrayList();
        votes3.add("No");

        ArrayList votes4 = new ArrayList();
        votes4.add("Monday");

        votingSystem.vote(0, voter2, votes1);
        votingSystem.vote(1, voter2, votes4);

        System.out.println("Print Result");
        votingSystem.printResults(0);
        votingSystem.printResults(1);

        System.out.println("duplicate vote");

        votingSystem.vote(0, voter1, votes3);
        votingSystem.printResults(0);

        System.out.println("a Random Choice");
        Person voter3 = new Person("Sara", "Firooznia");
        ArrayList votes5 = new ArrayList();
        votingSystem.vote(0,voter3, votes5);
        votingSystem.printResults(0);

        System.out.println("Print final Result");
        votingSystem.printResults(0);
        votingSystem.printResults(1);

        System.out.println("Delete the voting");
        votingSystem.deleteVoting(1);

        System.out.println("Print final Result");
        votingSystem.printResults(0);
        //votingSystem.printResults(1);

    }
}