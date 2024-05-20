package daily.d240520p1542findlongestawesomesubstring;

import java.util.Arrays;

/**
 * 1542. Find Longest Awesome Substring
 *
 * https://leetcode.cn/problems/find-longest-awesome-substring
 *
 * You are given a string s. An awesome substring is a non-empty substring of s such that
 * we can make any number of swaps in order to make it a palindrome.
 *
 * Return the length of the maximum length awesome substring of s.
 */

public class Solution {

    public int longestAwesome(String s) {
        int[] prefix = new int[1 << 10];
        Arrays.fill(prefix, -2); prefix[0] = -1;

        int ans = 0, state = 0;
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            state ^= (1 << digit);
            if (prefix[state] != -2) {
                ans = Math.max(ans, i - prefix[state]);
            } else prefix[state] = i;

            for (int j = 0; j < 10; j++) {
                int next = state ^ (1 << j);
                if (prefix[next] != -2) {
                    ans = Math.max(ans, i - prefix[next]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
