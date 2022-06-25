package weekly.bw81.A;

/**
 * 6104. Count Asterisks
 *
 * https://leetcode.cn/contest/biweekly-contest-81/problems/count-asterisks/
 *
 * You are given a string s, where every two consecutive vertical bars '|' are grouped into a pair.
 * In other words, the 1st and 2nd '|' make a pair, the 3rd and 4th '|' make a pair, and so forth.
 *
 * Return the number of '*' in s, excluding the '*' between each pair of '|'.
 *
 * Note that each '|' will belong to exactly one pair.
 */

public class Solution {

    public int countAsterisks(String s) {
        int ans = 0, line = 0;
        for (var c : s.toCharArray()) {
            if (c == '|') line++;
            else if (c == '*' && line % 2 == 0) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countAsterisks("*||*|||||*|*|***||*||***|") == 3;
    }

}
