package com.jason.leetcode.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TwoSumTest {

    private final TwoSum solution = new TwoSum();

    @Test
    void testBasicCase() {
        int[] nums = {2, 7, 11, 15};
        int[] result = solution.twoSum(nums, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testMultiplePairs() {
        int[] nums = {3, 2, 4};
        int[] result = solution.twoSum(nums, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void testNoSolution() {
        int[] nums = {1, 2, 3};
        int[] result = solution.twoSum(nums, 10);
        assertArrayEquals(new int[]{-1, -1}, result);
    }
}