import java.util.List;

@FunctionalInterface
public interface Benchmark {
    void run(List<?> args);
}
