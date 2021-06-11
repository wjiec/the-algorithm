package problem.p821shortestdistancetoacharacter;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 821. Shortest Distance to a Character
 *
 * https://leetcode-cn.com/problems/shortest-distance-to-a-character/
 *
 * Given a string s and a character c that occurs in s, return an array of integers answer
 * where answer.length == s.length and answer[i] is the distance from index i
 * to the closest occurrence of character c in s.
 *
 * The distance between two indices i and j is abs(i - j), where abs is the absolute value function.
 */

public class Solution {

    public int[] shortestToChar(String s, char c) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0, l = s.length(); i < l; i++) {
            if (s.charAt(i) == c) indexes.add(i);
        }
        indexes.add(Integer.MAX_VALUE);

        int p = 1, l = indexes.get(0), r = indexes.get(p);
        int[] ans = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ans[i] = Math.min(Math.abs(i - l), r - i);
            if (i == r) {
                l = r;
                r = indexes.get(++p);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().shortestToChar("loveleetcode", 'e'), new int[]{3,2,1,0,1,0,0,1,2,2,1,0});
        assert Checker.check(new Solution().shortestToChar("aaab", 'b'), new int[]{3,2,1,0});
    }

}
