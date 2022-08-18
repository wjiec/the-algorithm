package problem.p1540canconvertstringinkmoves;

/**
 * 1540. Can Convert String in K Moves
 *
 * https://leetcode.cn/problems/can-convert-string-in-k-moves/
 *
 * Given two strings s and t, your goal is to convert s into t in k moves or less.
 *
 * During the ith (1 <= i <= k) move you can:
 *
 * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen
 * in any previous move, and shift the character at that index i times.
 * Do nothing.
 * Shifting a character means replacing it by the next letter in the alphabet
 * (wrapping around so that 'z' becomes 'a'). Shifting a character by i means
 * applying the shift operations i times.
 *
 * Remember that any index j can be picked at most once.
 *
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 */

public class Solution {

    public boolean canConvertString(String s, String t, int k) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        if (ss.length != tt.length) return false;

        int[] base = new int[27];
        for (int i = 0; i < base.length; i++) base[i] = i;

        for (int i = 0; i < ss.length; i++) {
            if (ss[i] == tt[i]) { continue; }

            int move = ((tt[i] - 'a' + 26) - (ss[i] - 'a')) % 26;
            if (base[move] > k) return false;
            base[move] += 26;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().canConvertString("leetcode", "leetcode", 0);

        assert new Solution().canConvertString("input", "ouput", 9);
        assert !new Solution().canConvertString("abc", "bcd", 10);
        assert new Solution().canConvertString("aab", "bbb", 27);
    }

}
