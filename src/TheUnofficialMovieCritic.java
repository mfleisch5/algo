import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.util.*;
/**
 * Created by michaelfleischmann on 7/20/17.
 */

class CountPair {
    private final int count;
    private final LinkedList<String> movies;

    /**
     * Object that represents both an inversion count and a list of movies.
     *
     * @param count the inversion count
     * @param movies the list of movies
     */

    CountPair(int count, LinkedList<String> movies) {
        this.count = count;
        this.movies = movies;
    }

    int getCount() {
        return count;
    }

    LinkedList<String> getMovies() {
        return movies;
    }
}

class CriticAnalysis {
    private LinkedList<String> critic;
    private HashMap<String, Integer> movieOrder;
    private HashMap<String, Integer> movieImportance;

    /**
     *  Creates a new analysis of critic rankings and their similarities.
     */
    CriticAnalysis() {
    }

    /**
     * Takes the original critic and sets the correct order to this critic's rankings.
     * @param critic the first critic to be inputted
     *
     */
    void setOrder(LinkedList<String> critic) {
        this.movieOrder = new HashMap<>();
        for(int i = 0; i < critic.size(); i++) {
            movieOrder.put(critic.get(i), i);
        }
    }

    void setCritic(LinkedList<String> critic) {
        this.critic = critic;
    }

    void setMovieImportance(HashMap<String, Integer> movieImportance) {
        this.movieImportance = movieImportance;
    }


    /**
     * Finds the total importance for a ranking list.
     *
     * @param rankings the inputted ranking list
     * @return the total importance
     */
    private int rankingImportance(LinkedList<String> rankings) {
        int result = 0;
        for(String ranking : rankings) {
            result += movieImportance.get(ranking);
        }
        return result;
    }

    /**
     * Merges two sorted parts of a critics rankings and counts which ones are out of order
     * @param rankings1 first half of rankings, an inversion would be if any of these had an index higher than list B
     * @param rankings2 second half of rankings
     * @return a new CountPair with a sorted list and the count of inversions
     */
    private CountPair mergeCount(LinkedList<String> rankings1, LinkedList<String> rankings2) {
        LinkedList<String> rankings = new LinkedList<>();
        int count = 0;
        int rankings1Importance = rankingImportance(rankings1);
        while(!rankings1.isEmpty() && !rankings2.isEmpty()) {
            String a = rankings1.peek();
            String b = rankings2.peek();
            if(movieOrder.get(a) > movieOrder.get(b)) {
                count += rankings1Importance * movieImportance.get(b);
                rankings.add(rankings2.pop());
            }
            else {
                rankings.add(rankings1.pop());
                rankings1Importance -= movieImportance.get(a);
            }
        }
        if(!rankings1.isEmpty()) {
            rankings.addAll(rankings1);
        }
        if(!rankings2.isEmpty()) {
            rankings.addAll(rankings2);
        }
        return new CountPair(count, rankings);

    }

    /**
     * Counts and resorts all out of order movie rankings for the critic given
     * @param critic the critic being compared to the original critic
     * @return A CountPair with a count of all ranking inversions and the sorted critic
     */
    private CountPair inversionCount(LinkedList<String> critic) {

        if(critic.size() < 2) {
            return new CountPair(0, critic);
        }
        CountPair count1 = inversionCount(new LinkedList<>(critic.subList(0, critic.size() / 2)));
        CountPair count2 = inversionCount(new LinkedList<>(critic.subList(critic.size() / 2, critic.size())));
        CountPair countAll = mergeCount(count1.getMovies(), count2.getMovies());
        return new CountPair(count1.getCount() + count2.getCount() + countAll.getCount(), countAll.getMovies());

    }

    /**
     * Counts the inversions of the critic of interest and prints them
     */
    void analyze() {
        if(movieImportance == null || movieOrder == null || critic == null) {
            throw new IllegalStateException("Must provide importance, order, and critic");
        }
        CountPair result = inversionCount(this.critic);
        System.out.println(result.getCount());
    }
}
public class TheUnofficialMovieCritic {
    public static void main(String[] args) {
        CriticAnalysis critics = new CriticAnalysis();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("[/\n]");
        LinkedList<String> rankings = new LinkedList<>();
        HashMap<String, Integer> movieImportance = new HashMap<>();
        while(scanner.hasNext()) {
            String s = scanner.next();
            if(s.equals("Critic 2")) {
                critics.setOrder(rankings);
                rankings.clear();
            }
            else if(s.equals("Movies")) {
                break;
            }
            else if(!s.equals("Critic 1")) {
                rankings.add(s);
            }
        }
        critics.setCritic(rankings);
        while(scanner.hasNext()) {
            Integer importance = scanner.nextInt();
            String movie = scanner.next();
            movieImportance.put(movie, importance);

        }
        critics.setMovieImportance(movieImportance);
        critics.analyze();
    }
}
