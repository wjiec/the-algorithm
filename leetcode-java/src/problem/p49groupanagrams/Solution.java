package problem.p49groupanagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams
 *
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 */

public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 1) return List.of(List.of(strs[0]));

        Map<String, List<String>> map = new HashMap<>();
        for (var str : strs) signatureAndMerge(str, map);

        return new ArrayList<>(map.values());
    }

    private void signatureAndMerge(String s, Map<String, List<String>> map) {
        int[] key = new int[26];
        for (var c : s.toCharArray()) key[c - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            sb.append(key[i]).append((char) ('a' + i));
        }
        map.putIfAbsent(sb.toString(), new ArrayList<>());
        map.get(sb.toString()).add(s);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
        System.out.println(new Solution().groupAnagrams(new String[]{""}));
        System.out.println(new Solution().groupAnagrams(new String[]{"a"}));
    }

}
