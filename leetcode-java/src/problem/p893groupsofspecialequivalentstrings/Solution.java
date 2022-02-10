package problem.p893groupsofspecialequivalentstrings;

import java.util.HashSet;
import java.util.Set;

/**
 * 893. Groups of Special-Equivalent Strings
 *
 * https://leetcode-cn.com/problems/groups-of-special-equivalent-strings/
 *
 * You are given an array words of strings.
 *
 * A move onto s consists of swapping any two even indexed characters of s, or any two odd indexed characters of s.
 *
 * Two strings s and t are special-equivalent if after any number of moves onto s, s == t.
 *
 * For example, s = "zzxy" and t = "xyzz" are special-equivalent because we may make
 * the moves "zzxy" -> "xzzy" -> "xyzz" that swap s[0] and s[2], then s[1] and s[3].
 *
 * Now, a group of special-equivalent strings from words is a non-empty subset of words such that:
 *
 * Every pair of strings in the group are special equivalent, and;
 * The group is the largest size possible (ie., there isn't a string s not in
 * the group such that s is special equivalent to every string in the group)
 * Return the number of groups of special-equivalent strings from words.
 */

public class Solution {

    public int numSpecialEquivGroups(String[] words) {
        Set<String> unique = new HashSet<>();
        for (var word : words) {
            unique.add(build(word));
        }
        return unique.size();
    }

    private String build(String s) {
        int[] even = new int[26], odd = new int[26];
        for (int i = 0, l = s.length(); i < l; i++) {
            if (i % 2 == 0) even[s.charAt(i) - 'a']++;
            else odd[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append((char) ('a' + i)).append(even[i]).append('-').append(odd[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().numSpecialEquivGroups(new String[]{"abcd","cdab","cbad","xyzz","zzxy","zzyx"}) == 3;
        assert new Solution().numSpecialEquivGroups(new String[]{"abc","acb","bac","bca","cab","cba"}) == 3;
    }

}
