package problem.p1525numberofgoodwaystosplitastring;

/**
 * 1525. Number of Good Ways to Split a String
 *
 * https://leetcode.cn/problems/number-of-good-ways-to-split-a-string/
 *
 * You are given a string s.
 *
 * A split is called good if you can split s into two non-empty strings sleft and sright
 * where their concatenation is equal to s (i.e., sleft + sright = s) and the number of
 * distinct letters in sleft and sright is the same.
 *
 * Return the number of good splits you can make in s.
 */

public class Solution {

    public int numSplits(String s) {
        char[] chars = s.toCharArray();

        int kind = 0, ans = 0;
        int[] total = new int[128];
        for (var c : chars) {
            if (++total[c] == 1) kind++;
        }

        int cKind = 0;
        int[] curr = new int[128];
        for (var c : chars) {
            if (++curr[c] == 1) cKind++;
            if (--total[c] == 0) kind--;
            if (cKind == kind) ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSplits("aacaba") == 2;
        assert new Solution().numSplits("abcd") == 1;
        assert new Solution().numSplits("aaaaa") == 4;
        assert new Solution().numSplits("acbadbaada") == 2;
    }

}
