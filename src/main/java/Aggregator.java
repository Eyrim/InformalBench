import java.util.ArrayList;
import java.util.List;

public class Aggregator {
    private final int count;
    private final List<?> args;
    private final Benchmark function;

    public Aggregator(int count, List<?> args, Benchmark function) {
        this.count = count;
        this.args = args;
        this.function = function;
    }

    public void run() {
        List<Long> stats = new ArrayList<>();
        long startMillis;
        long elapsed;

        try {
            // Count how long it takes to do each function call
            for (int i = 0; i < this.count; i++) {
                startMillis = startCount();

                this.function.run(this.args);

                elapsed = endCount(startMillis);
                stats.add(elapsed);
            }
        } catch (Exception ex) { throw new RuntimeException(ex); }

        displayStats(stats);

    }

    private static void displayStats(List<Long> stats) {
        for (int i = 0; i < stats.size(); i++) {
            System.out.printf(
                    "%d: %f seconds\n",
                    i,
                    (double) stats.get(i) / 1000
            );
        }

        System.out.printf(
                "Average time: %f\n",
                findMean(stats)
        );
    }

    private static double findMean(List<Long> stats) {
        double sum = stats.stream()
                .map(val -> (double) val / 1000)
                .reduce(Double::sum)
                .get();

        return sum / stats.size();
    }

    private static long startCount() {
        return System.currentTimeMillis();
    }

    private static long endCount(long start) {
        return System.currentTimeMillis() - start;
    }
}
