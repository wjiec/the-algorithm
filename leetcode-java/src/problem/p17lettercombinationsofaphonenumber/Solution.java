package problem.p17lettercombinationsofaphonenumber;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Note that 1 does not map to any letters.
 */

public class Solution {

    private List<String> ans = new ArrayList<>();
    private final String[] maps = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) return List.of();
        dfs(digits.toCharArray(), 0, new char[digits.length()]);
        return ans;
    }

    private void dfs(char[] chars, int curr, char[] target) {
        if (curr == chars.length) {
            ans.add(new String(target));
            return;
        }

        for (char c : maps[chars[curr] - '0'].toCharArray()) {
            target[curr] = c;
            dfs(chars, curr + 1, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().letterCombinations("23"));
        System.out.println(new Solution().letterCombinations(""));
        System.out.println(new Solution().letterCombinations("2"));
        System.out.println(new Solution().letterCombinations("2345"));
    }

}
