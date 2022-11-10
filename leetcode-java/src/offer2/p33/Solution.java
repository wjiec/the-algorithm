package offer2.p33;

import common.Checker;

import java.util.*;

/**
 * 剑指 Offer II 033. 变位词组
 *
 * https://leetcode.cn/problems/sfvd7V/
 *
 * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。
 *
 * 注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。
 */

public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (var word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            map.computeIfAbsent(new String(chars), v -> new ArrayList<>())
                .add(word);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}), List.of(
            List.of("bat"), List.of("nat","tan"), List.of("ate","eat","tea")
        ));
    }

}
