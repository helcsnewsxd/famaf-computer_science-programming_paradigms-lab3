package namedEntity.heuristic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomHeuristic extends Heuristic {

    private final Random rnd = new Random();

    // useful for random heuristic's consistency
    private final List<String> positiveCases = new ArrayList<>();
    private final List<String> negativeCases = new ArrayList<>();

    private boolean isPositiveCase(String entity) {
        return this.positiveCases.contains(entity);
    }

    private boolean isNegativeCase(String entity) {
        return this.negativeCases.contains(entity);
    }

    public boolean isEntity(String word) {
        // already it was classified
        if (this.isPositiveCase(word))
            return true;
        if (this.isNegativeCase(word))
            return false;

        // if it was not classified yet, then lottery
        boolean b = ((int) (rnd.nextDouble() * 100)) % 2 == 0;
        if (b)
            this.positiveCases.add(word);
        else
            this.negativeCases.add(word);
        return b;

    }

    public static void main(String[] args) {
        // RandomHeuristic rh = new RandomHeuristic();
    }

}
