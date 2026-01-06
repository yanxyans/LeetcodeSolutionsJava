package com.jason.leetcode.medium;

public class MinimumMoves {
    public int minMoves(int[] nums) {
        int minNum = Integer.MAX_VALUE;
        for (int num : nums) {
            minNum = Math.min(minNum, num);
        }

        int numMoves = 0;
        for (int num : nums) {
            numMoves += num - minNum;
        }
        return numMoves;
    }
}
