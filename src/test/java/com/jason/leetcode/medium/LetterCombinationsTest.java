package com.jason.leetcode.medium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LetterCombinationsTest {

    private final LetterCombinations solution = new LetterCombinations();

    @Test
    void testSingleDigit() {
        List<String> result = solution.letterCombinations("2");
        List<String> expected = Arrays.asList("a", "b", "c");
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testTwoDigits() {
        List<String> result = solution.letterCombinations("23");
        List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testEmptyInput() {
        List<String> result = solution.letterCombinations("");
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testThreeDigits() {
        List<String> result = solution.letterCombinations("234");
        assertEquals(27, result.size()); // 3 * 3 * 3 = 27 combinations
        assertTrue(result.contains("adg"));
        assertTrue(result.contains("beh"));
        assertTrue(result.contains("cfi"));
    }

    @Test
    void testDigitWithFourLetters() {
        List<String> result = solution.letterCombinations("7");
        List<String> expected = Arrays.asList("p", "q", "r", "s");
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testDigitNine() {
        List<String> result = solution.letterCombinations("9");
        List<String> expected = Arrays.asList("w", "x", "y", "z");
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void testMixedDigits() {
        List<String> result = solution.letterCombinations("79");
        assertEquals(16, result.size()); // 4 * 4 = 16 combinations
        assertTrue(result.contains("pw"));
        assertTrue(result.contains("sz"));
    }
}
