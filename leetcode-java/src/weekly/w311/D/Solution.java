package weekly.w311.D;

import common.Checker;

import java.util.HashMap;
import java.util.Map;

/**
 * 6183. Sum of Prefix Scores of Strings
 *
 * https://leetcode.cn/contest/weekly-contest-311/problems/sum-of-prefix-scores-of-strings/
 *
 * You are given an array words of size n consisting of non-empty strings.
 *
 * We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
 *
 * For example, if words = ["a", "ab", "abc", "cab"], then the
 * score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
 *
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 *
 * Note that a string is considered as a prefix of itself.
 */

public class Solution {

    // TLE for java, but accepted for python
    public int[] sumPrefixScores(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (var word : words) {
            for (int i = 1, n = word.length(); i <= n; i++) {
                map.merge(word.substring(0, i), 1, Integer::sum);
            }
        }

        int[] ans = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 1, n = words[i].length(); i <= n; i++) {
                int v = map.get(words[i].substring(0, j));
                ans[i] += v;
                if (v == 0) break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sumPrefixScores(new String[]{"abc","ab","bc","b"}), new int[]{5,4,3,2});
    }

}
