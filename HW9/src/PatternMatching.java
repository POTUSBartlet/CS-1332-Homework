import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class PatternMatching {

    /**
     * Brute force pattern matching algorithm to find all matches.
     * <p>
     * You should check each substring of the text from left to right,
     * stopping early if you find a mismatch.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> bruteForce(CharSequence pattern,
               CharSequence text, CharacterComparator comparator) {

        List<Integer> list = new ArrayList<>();

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Length cannot be 0");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        } else {
            int n = text.length();
            int m = pattern.length();
            for (int i = 0; i <= n - m; i++) {
                int k = 0;
                while (k < m && comparator.compare(text.charAt(i + k),
                        pattern.charAt(k)) == 0) {
                    k++;
                }
                if (k == m) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     * <p>
     * Make sure to implement the failure table before implementing this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {


        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Length cannot be 0");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        List<Integer> list = new ArrayList<>();

        if(pattern.length() > text.length()){
            return list;
        }
        int[] failureTable = buildFailureTable(pattern, comparator);

        int i = 0;
        int j = 0;
        while (i <= text.length() - pattern.length()) {
            while (j < pattern.length() && comparator
                    .compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j++;
            }
            if (j == 0) {
                i++;
            } else {
                if (j == pattern.length()) {
                    list.add(i);
                }
                int nextAlignment = failureTable[j - 1];
                i += j - nextAlignment;
                j = nextAlignment;
            }
        }
        return list;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     * <p>
     * The table built should be the length of the input text.
     * <p>
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     * <p>
     * Ex. ababac
     * <p>
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     * <p>
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a {@code CharSequence} you're
     *                   building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     * @throws IllegalArgumentException if the pattern or comparator is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern is null");
        }
        if(comparator == null){
            throw new IllegalArgumentException("comparator cannot be null");
        }
        int[] table = new int[pattern.length()];
        if (pattern.length() == 0) {
            return table;
        }

        int i = 0;
        int j = 1;

        table[0] = 0;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                table[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    table[j] = 0;
                    j++;
                } else {
                    i = table[i - 1];
                }
            }
        }
        return table;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     * <p>
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                  CharSequence text, CharacterComparator comparator) {

        List<Integer> list = new ArrayList<>();

        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null or "
                    + "have a length of zero");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        } else {
            Map<Character, Integer> lastTable = buildLastTable(pattern);
            int indexOfPattern = pattern.length() - 1;
            int indexOfText = indexOfPattern;

            while (indexOfText < text.length()) {
                if (comparator.compare(text.charAt(indexOfText),
                        pattern.charAt(indexOfPattern)) != 0) {
                    indexOfText += pattern.length()
                            - Math.min(indexOfPattern,
                            lastTable.getOrDefault(text.charAt(indexOfText),
                                    -1) + 1);
                    indexOfPattern = pattern.length() - 1;
                } else {
                    if (indexOfPattern != 0) {
                        indexOfText--;
                        indexOfPattern--;
                    } else {
                        list.add(indexOfText);
                        indexOfText += pattern.length();
                        indexOfPattern = pattern.length() - 1;
                    }
                }
            }
        }
        return list;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     * <p>
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     * <p>
     * Ex. octocat
     * <p>
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     * <p>
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        Map<Character, Integer> map = new HashMap<>();

        if (pattern == null) {
            throw new IllegalArgumentException("pattern cannot be null");
        } else {
            for (int i = 0; i < pattern.length(); i++) {
                map.put(pattern.charAt(i), i);
            }
        }
        return map;
    }

}
