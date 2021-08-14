package tasks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Task1_Basic {

    /**
     * Функция должна вернуть количество четных чисел в списке.
     * <p>
     * Пример:
     * 0,2 -> 2
     * 1,2,3,5 -> 1
     * <p>
     * Тут подойдут эти методы:
     * - Collection::stream()
     * - Stream::filter()
     * - Stream::count()
     */
    static long countEven(Collection<Integer> numbers) {
        return numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .count();
    }

    /**
     * Функция должна вернуть число строчных символов в строке.
     * <p>
     * Пример:
     * "abcDE" -> 3
     * "ABC" -> 0
     * <p>
     * Тут подойдут эти методы:
     * - String::chars()
     * - Character::isLowerCase()
     * - Stream::count()
     */
    static long countLowercaseLetters(String str) {
        return str.chars()
                .filter(Character::isLowerCase)
                .count();
    }


    /**
     * Функция должна заменить каждое слово в строке его длинной.
     * <p>
     * Слова разделяются одним или более пробелами.
     * <p>
     * Пример:
     * "a b cd" -> "1 1 2"
     * "one two   three" -> "3 3 5"
     * <p>
     * Тут подойдут эти методы:
     * - String::split()
     * - Stream::map()
     * - Stream::collect()
     * - Collectors.joining()
     */
    public static String replaceWordsOnLength(String str) {
        List<String> listString = Arrays.asList(str.split("\\s+"));
        String stringForLength = listString.stream().map(String::length).map(Object::toString).collect(Collectors.joining(" "));
        return stringForLength;
    }
}