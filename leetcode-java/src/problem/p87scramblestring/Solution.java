package problem.p87scramblestring;

import java.util.HashMap;
import java.util.Map;

/**
 * 87. Scramble String
 *
 * https://leetcode.cn/problems/scramble-string/
 *
 * We can scramble a string s to get a string t using the following algorithm:
 *
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the
 * string is s, divide it to x and y where s = x + y.
 *
 * Randomly decide to swap the two substrings or to keep them in the same order.
 * i.e., after this step, s may become s = x + y or s = y + x.
 *
 * Apply step 1 recursively on each of the two substrings x and y.
 *
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled
 * string of s1, otherwise, return false.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean isScramble(String s1, String s2) {
        return isScramble(s1.toCharArray(), 0, s1.length() - 1,
            s2.toCharArray(), 0, s2.length() - 1);
    }

    private final Map<Integer, Boolean> memo = new HashMap<>();

    private boolean isScramble(char[] cs1, int l1, int r1, char[] cs2, int l2, int r2) {
        if (l1 == r1 || l2 == r2) return cs1[l1] == cs2[l2];

        int hash = (l1 << 24) | (r1 << 16) | (l2 << 8) | r2;
        if (memo.containsKey(hash)) return memo.get(hash);

        // 正向
        Map<Character, Integer> front = new HashMap<>();
        for (int i = l1, j = l2; i < r1 && j < r2; i++, j++) {
            front.merge(cs1[i], 1, Solution::sum);
            front.merge(cs2[j], -1, Solution::sum);
            if (front.isEmpty()) {
                if (isScramble(cs1, l1, i, cs2, l2, j) && isScramble(cs1, i + 1, r1, cs2, j + 1, r2)) {
                    memo.put(hash, true);
                    return true;
                }
            }
        }

        // 反向
        Map<Character, Integer> back = new HashMap<>();
        for (int i = l1, j = r2; i < r1 && j > l2; i++, j--) {
            back.merge(cs1[i], 1, Solution::sum);
            back.merge(cs2[j], -1, Solution::sum);
            if (back.isEmpty()) {
                if (isScramble(cs1, l1, i, cs2, j, r2) && isScramble(cs1, i + 1, r1, cs2, l2, j - 1)) {
                    memo.put(hash, true);
                    return true;
                }
            }
        }

        memo.put(hash, false);
        return false;
    }

    private static Integer sum(Integer a, Integer b) {
        return a + b == 0 ? null : a + b;
    }

    public static void main(String[] args) {
        assert !new Solution().isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd");

        assert new Solution().isScramble("great", "rgeat");
        assert !new Solution().isScramble("abcde", "caebd");
        assert new Solution().isScramble("a", "a");
    }

}
