package com.jason.leetcode.medium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class IntervalInsertionTest {

    private final InsertInterval solution = new InsertInterval();

    @Test
    void testInsertIntoEmptyArray() {
        int[][] intervals = {};
        int[] newInterval = {5, 7};
        int[][] expected = {{5, 7}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testInsertBeforeAllIntervals() {
        int[][] intervals = {{3, 5}, {6, 9}};
        int[] newInterval = {1, 2};
        int[][] expected = {{1, 2}, {3, 5}, {6, 9}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testInsertAfterAllIntervals() {
        int[][] intervals = {{1, 2}, {3, 5}};
        int[] newInterval = {6, 8};
        int[][] expected = {{1, 2}, {3, 5}, {6, 8}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testInsertBetweenIntervals() {
        int[][] intervals = {{1, 2}, {6, 9}};
        int[] newInterval = {3, 5};
        int[][] expected = {{1, 2}, {3, 5}, {6, 9}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testMergeSingleInterval() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] expected = {{1, 5}, {6, 9}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testMergeMultipleIntervals() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] expected = {{1, 2}, {3, 10}, {12, 16}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testMergeAllIntervals() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}};
        int[] newInterval = {0, 10};
        int[][] expected = {{0, 10}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testNewIntervalCompletelyInsideExisting() {
        int[][] intervals = {{1, 5}};
        int[] newInterval = {2, 3};
        int[][] expected = {{1, 5}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testNewIntervalCompletelyCoversExisting() {
        int[][] intervals = {{3, 5}, {6, 7}};
        int[] newInterval = {2, 8};
        int[][] expected = {{2, 8}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testExactOverlapAtBoundaries() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {3, 6};
        int[][] expected = {{1, 9}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testAdjacentIntervalsNoMerge() {
        int[][] intervals = {{1, 2}, {5, 6}};
        int[] newInterval = {3, 4};
        int[][] expected = {{1, 2}, {3, 4}, {5, 6}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testSingleExistingInterval() {
        int[][] intervals = {{1, 5}};
        int[] newInterval = {6, 8};
        int[][] expected = {{1, 5}, {6, 8}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }

    @Test
    void testLargeGapInsertion() {
        int[][] intervals = {{1, 2}, {100, 200}};
        int[] newInterval = {50, 60};
        int[][] expected = {{1, 2}, {50, 60}, {100, 200}};
        Assertions.assertArrayEquals(expected, solution.insert(intervals, newInterval));
    }
}
