package Lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExercises {
    public static int countLines(Path path, int thres) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return (int) lines.filter(line -> line.length() >= thres).count();
        }
    }

    public static List<String> collectWords(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(String::toLowerCase)
                    .flatMap(line -> Stream.of(line.split("[^a-z]+")))
                    .filter(word -> !word.isEmpty())
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
        }
    }
}
