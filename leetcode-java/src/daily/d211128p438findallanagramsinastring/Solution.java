package daily.d211128p438findallanagramsinastring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 *
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 */

public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        int sl = s.length(), pl = p.length();
        if (sl < pl) return Collections.emptyList();

        int[] sc = new int[26], pc = new int[26];
        for (int i = 0; i < pl; i++) {
            sc[s.charAt(i) - 'a']++;
            pc[p.charAt(i) - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        if (Arrays.equals(sc, pc)) ans.add(0);

        for (int i = 0; i < sl - pl; i++) {
            sc[s.charAt(i) - 'a']--;
            sc[s.charAt(i + pl) - 'a']++;
            if (Arrays.equals(sc, pc)) ans.add(i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findAnagrams("cbaebabacd", "abc").equals(List.of(0,6));
        assert new Solution().findAnagrams("abab", "ab").equals(List.of(0,1,2));
    }

}
