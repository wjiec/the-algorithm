package weekly.bw75.p2numberofwaystoselectbuildings;

/**
 * 6035. Number of Ways to Select Buildings
 *
 * https://leetcode-cn.com/contest/biweekly-contest-75/problems/number-of-ways-to-select-buildings/
 *
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 *
 * s[i] = '0' denotes that the ith building is an office and
 * s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection. However, to ensure variety,
 * no two consecutive buildings out of the selected buildings can be of the same type.
 *
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011"
 * which is not allowed due to having two consecutive buildings of the same type.
 *
 * Return the number of valid ways to select 3 buildings.
 */

public class Solution {

    public long numberOfWays(String s) {
        int n = s.length();
        int[] front = new int[n];
        for (int i = 1; i < n; i++) {
            front[i] = front[i - 1];
            if (s.charAt(i - 1) == '1') front[i]++;
        }

        int[] back = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            back[i] = back[i + 1];
            if (s.charAt(i + 1) == '1') back[i]++;
        }

        long ans = 0;
        for (int i = 1, e = n - 1; i < e; i++) {
            if (s.charAt(i) == '0') ans += (long) front[i] * back[i];
            else ans += (long) (i - front[i]) * (n - i - back[i] - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numberOfWays("001101") == 6;
        assert new Solution().numberOfWays("11100") == 0;
    }

}
