package com.jason.leetcode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumMovesTest {

    private final MinimumMoves solution = new MinimumMoves();

    @Test
    void testExample1() {
        int[] nums = {1, 2, 3};
        assertEquals(3, solution.minMoves(nums));
    }

    @Test
    void testExample2() {
        int[] nums = {1, 1, 1};
        assertEquals(0, solution.minMoves(nums));
    }

    @Test
    void testSingleElement() {
        int[] nums = {5};
        assertEquals(0, solution.minMoves(nums));
    }

    @Test
    void testTwoElements() {
        int[] nums = {1, 5};
        assertEquals(4, solution.minMoves(nums));
    }

    @Test
    void testLargerGap() {
        int[] nums = {1, 2, 10};
        assertEquals(10, solution.minMoves(nums));
    }

    @Test
    void testAllSameExceptOne() {
        int[] nums = {5, 5, 5, 10};
        assertEquals(5, solution.minMoves(nums));
    }

    @Test
    void testNegativeNumbers() {
        int[] nums = {-100, 0, 100};
        assertEquals(300, solution.minMoves(nums));
    }

    @Test
    void testMixedValues() {
        int[] nums = {3, 7, 1, 9, 2};
        assertEquals(17, solution.minMoves(nums));
    }
}