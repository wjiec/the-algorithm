package weekly.w293.p0findresultantarrayafterremovinganagrams;

import java.util.*;

/**
 * 5234. Find Resultant Array After Removing Anagrams
 *
 * https://leetcode.cn/contest/weekly-contest-293/problems/find-resultant-array-after-removing-anagrams/
 *
 * You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
 *
 * In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams,
 * and delete words[i] from words. Keep performing this operation as long as
 * you can select an index that satisfies the conditions.
 *
 * Return words after performing all operations. It can be shown that selecting the indices
 * for each operation in any arbitrary order will lead to the same result.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase
 * using all the original letters exactly once. For example, "dacb" is an anagram of "abdc".
 */

public class Solution {

    public List<String> removeAnagrams(String[] words) {
        List<String[]> list = new LinkedList<>();
        for (var word : words) {
            list.add(new String[]{word, serialize(word)});
        }

        for (boolean dup = true; dup; ) {
            dup = false;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i)[1].equals(list.get(i - 1)[1])) {
                    dup = true;
                    list.remove(i);
                    break;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (var item : list) ans.add(item[0]);
        return ans;
    }

    private String serialize(String s) {
        int[] chars = new int[128];
        for (var c : s.toCharArray()) chars[c]++;

        StringBuilder sb = new StringBuilder();
        for (int i = 'a'; i <= 'z'; i++) {
            if (chars[i] != 0) sb.append((char) i).append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeAnagrams(new String[]{"a","b","a"}).equals(List.of("a","b","a"));
    }

}
