package string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import practice.string.SequentialWordsNumbers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Вывод текста с порядковыми номера слов")
class SequentialWordsNumbersTests {

    private static Stream<Arguments> strings() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("слово", "(1) слово"),
                Arguments.of("два слова", "(1) два (2) слова"),
                Arguments.of("Hello world!", "(1) Hello (2) world!"),
                Arguments.of("Это просто текст, для примера работы программы",
                        "(1) Это (2) просто (3) текст, (4) для (5) примера (6) работы (7) программы")
        );
    }


    @ParameterizedTest
    @MethodSource("strings")
    @DisplayName("Порядковый номер слов в строке")
    void sequentialWordsNumbersTest(String text, String expected) {
        assertEquals(expected, SequentialWordsNumbers.sequentialWordsNumbers(text));
    }

    @Test
    @DisplayName("Проверка используются ли методы регулярных выражений")
    void checkRegularExpression() {
        final List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("src", "main", "java", "practice", "string", "SequentialWordsNumbers.java");
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, String> errLines = lines.stream()
                .filter(line -> line.matches(".*(split|matches|Pattern|Matcher).*"))
                .collect(Collectors.toMap(lines::indexOf, s -> s));

        String message = errLines.entrySet().stream()
                .map(entry -> "Строка " + entry.getKey() + " - <" + entry.getValue() + ">")
                .collect(Collectors.joining("\n"));

        assertTrue(errLines.isEmpty(),
                "\nВы использовали методы для регулярных выражений в строках\n" + message);
    }

}