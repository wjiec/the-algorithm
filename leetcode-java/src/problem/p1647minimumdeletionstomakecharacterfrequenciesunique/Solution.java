package problem.p1647minimumdeletionstomakecharacterfrequenciesunique;

import java.util.TreeMap;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 *
 * https://leetcode.cn/problems/minimum-deletions-to-make-character-frequencies-unique/
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 */

public class Solution {

    public int minDeletions(String s) {
        int[] count = new int[128];
        for (var c : s.toCharArray()) count[c]++;

        // freq => count
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int i = 'a'; i <= 'z'; i++) {
            if (count[i] != 0) freq.merge(count[i], 1, Integer::sum);
        }

        int ans = 0;
        for (Integer k = freq.lastKey(); k != null; k = freq.lowerKey(k)) {
            if (freq.get(k) > 1 && k != 0) {
                int more = freq.get(k) - 1; ans += more;
                freq.merge(k - 1, more, Integer::sum);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minDeletions("bbcebab") == 2;

        assert new Solution().minDeletions("aab") == 0;
        assert new Solution().minDeletions("aaabbbcc") == 2;
        assert new Solution().minDeletions("ceabaacb") == 2;
    }

}
