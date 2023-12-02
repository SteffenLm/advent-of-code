package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class InputUtils {
    /**
     * Reads lines from the given input txt file.
     */
    public static List<String> readInput(String name) {
        var a = Path.of("src/test/java/" + name);
        try (Stream<String> lines = Files.lines(a)) {
            return lines.toList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Reads lines from the given input txt file as in values.
     */
    public static List<Integer> readInputAsInts(String name) {
        return readInput(name)
                .stream()
                .mapToInt(value -> Integer.valueOf(value))
                .boxed()
                .toList();
    }

    /**
     * Reads one line as String.
     */
    public static String readOneLineInputAsString(String name) {
        return readInput(name).get(0);
    }

    /**
     * Reads one line and map to Ints.
     */
    public static List<Integer> readOneLineInputAsInts(String name) {
        return readOneLineInputAsInts(name, ",");
    }

    public static List<Integer> readOneLineInputAsInts(String name, String delimiter) {
        var array = readInput(name).get(0).split(delimiter);
        return Arrays.stream(array)
                .mapToInt(v -> Integer.valueOf(v))
                .boxed()
                .toList();
    }

}
