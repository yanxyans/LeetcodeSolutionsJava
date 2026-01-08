package com.jason.leetcode.medium;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int prevMax = 0;
        for (int num : nums) {
            int currMax = Math.max(prevMax + num, num);
            res = Math.max(res, currMax);
            prevMax = currMax;
        }
        return res;
    }
}
