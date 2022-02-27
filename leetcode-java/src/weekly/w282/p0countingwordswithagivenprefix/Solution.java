package weekly.w282.p0countingwordswithagivenprefix;

/**
 * 6008. Counting Words With a Given Prefix
 *
 * https://leetcode-cn.com/contest/weekly-contest-282/problems/counting-words-with-a-given-prefix/
 *
 * You are given an array of strings words and a string pref.
 *
 * Return the number of strings in words that contain pref as a prefix.
 *
 * A prefix of a string s is any leading contiguous substring of s.
 */

public class Solution {

    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (var word : words) {
            if (word.indexOf(pref) == 0) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().prefixCount(new String[]{"pay","attention","practice","attend"}, "at") == 2;
    }

}
