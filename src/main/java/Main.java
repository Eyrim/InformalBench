import java.util.List;

public class Main {
    private static long startMillis;

    public static void main(String[] args) {
        Benchmark countTo = Main::countTo;
        Aggregator aggregator = new Aggregator(
                50,
                List.of((double) Integer.MAX_VALUE),
                countTo
        );

        aggregator.run();
    }

    private static void countTo(List<?> args) {
        for (int i = 0; i < (double) args.get(0); i++) { }
    }
}
