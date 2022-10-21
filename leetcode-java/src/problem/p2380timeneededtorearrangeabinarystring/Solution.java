package problem.p2380timeneededtorearrangeabinarystring;

/**
 * 2380. Time Needed to Rearrange a Binary String
 *
 * https://leetcode.cn/problems/time-needed-to-rearrange-a-binary-string/
 *
 * You are given a binary string s. In one second, all occurrences of "01" are simultaneously
 * replaced with "10". This process repeats until no occurrences of "01" exist.
 *
 * Return the number of seconds needed to complete this process.
 */

public class Solution {

    public int secondsToRemoveOccurrences(String s) {
        int ans = 0, zeros = 0;
        for (var c : s.toCharArray()) {
            if (c == '0') zeros++;
            else if (zeros != 0) ans = Math.max(ans + 1, zeros);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().secondsToRemoveOccurrences("001011") == 4;

        assert new Solution().secondsToRemoveOccurrences("0110101") == 4;
        assert new Solution().secondsToRemoveOccurrences("11100") == 0;
    }

}
