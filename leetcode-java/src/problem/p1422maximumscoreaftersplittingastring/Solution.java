package problem.p1422maximumscoreaftersplittingastring;

/**
 * 1422. Maximum Score After Splitting a String
 *
 * https://leetcode-cn.com/problems/maximum-score-after-splitting-a-string/
 *
 * Given a string s of zeros and ones, return the maximum score after
 * splitting the string into two non-empty substrings (i.e. left substring and right substring).
 *
 * The score after splitting a string is the number of zeros in the left substring
 * plus the number of ones in the right substring.
 */

public class Solution {

    public int maxScore(String s) {
        int zero = 0, one = 0, ans = 0;
        for (var c : s.toCharArray()) if (c == '1') one++;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') zero++;
            else one--;

            ans = Math.max(ans, zero + one);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore("00") == 1;

        assert new Solution().maxScore("011101") == 5;
        assert new Solution().maxScore("00111") == 5;
        assert new Solution().maxScore("1111") == 3;
    }

}
