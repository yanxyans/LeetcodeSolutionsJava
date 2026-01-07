package com.jason.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    private final String[] letterMap = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }

        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path, res);
        return res;
    }

    public void dfs(String digits, int index, StringBuilder path, List<String> res) {
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }

        for (char letter : letterMap[digits.charAt(index) - '0'].toCharArray()) {
            path.append(letter);
            dfs(digits, index + 1, path, res);
            path.setLength(path.length() - 1);
        }
    }
}
