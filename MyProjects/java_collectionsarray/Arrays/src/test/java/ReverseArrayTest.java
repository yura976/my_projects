import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import practice.reverseArray.ReverseArray;

@DisplayName("Операции с массивами - изменение порядка элементов")
class ReverseArrayTest {

    public static Stream<Arguments> arraysStraightAndReverse() {
        return Stream.of(
            Arguments.of(new String[]{"a", "b"}, new String[]{"b", "a"}),
            Arguments.of(new String[]{"a", "b", "c"}, new String[]{"c", "b", "a"}),
            Arguments.of(
                new String[]{"1", "2", "3", "4", "5", "6", "7", "aa", "bb", "ccc", "ddd", "eee", "fff", "ggg", "hhh"},
                new String[]{"hhh", "ggg", "fff", "eee", "ddd", "ccc", "bb", "aa", "7", "6", "5", "4", "3", "2", "1"}),
            Arguments.of(new String[]{"a"}, new String[]{"a"}),
            Arguments.of(new String[0], new String[0]));
    }

    @DisplayName("Метод возвращает массив с обратным порядком элементов")
    @ParameterizedTest
    @MethodSource("arraysStraightAndReverse")
    void reverseNotEmptyArray(String[] straightArray, String[] reversedArray) {
        String[] actual = ReverseArray.reverse(straightArray);

        assertEquals(Arrays.toString(reversedArray), Arrays.toString(actual));

        assertSame(straightArray, ReverseArray.reverse(actual),
            "Необходимо изменить порядок в переданном массиве, и вернуть его же."
            + " Меняйте местами элементы в переданном массиве.");
    }
}