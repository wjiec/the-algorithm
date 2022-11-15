package offer2.p64;

import java.util.*;

/**
 * 剑指 Offer II 064. 神奇的字典
 *
 * https://leetcode.cn/problems/US1pGT/
 *
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。
 *
 * 实现 MagicDictionary 类：
 *
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
 * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private static class MagicDictionary {
        private final Set<String> dict = new HashSet<>();
        private final Map<String, Integer> possible = new HashMap<>();
        public MagicDictionary() {}

        public void buildDict(String[] dictionary) {
            for (var word : dictionary) {
                dict.add(word);
                for (var pattern : allPossible(word.toCharArray())) {
                    possible.merge(pattern, 1, Integer::sum);
                }
            }
        }

        public boolean search(String searchWord) {
            for (var pattern : allPossible(searchWord.toCharArray())) {
                int count = possible.getOrDefault(pattern, 0);
                if (count > 1 || (count == 1 && !dict.contains(searchWord))) return true;
            }
            return false;
        }

        private List<String> allPossible(char[] chars) {
            List<String> patterns = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                char curr = chars[i];
                chars[i] = '*';
                patterns.add(new String(chars));
                chars[i] = curr;
            }
            return patterns;
        }
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        assert !magicDictionary.search("hello");
        assert magicDictionary.search("hhllo");
        assert !magicDictionary.search("hell");
        assert !magicDictionary.search("leetcoded");
    }

}
