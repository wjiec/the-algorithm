package lcci.s16.p2wordsfrequencylcci;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.02. 单词频率
 *
 * https://leetcode.cn/problems/words-frequency-lcci/
 *
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 *
 * 你的实现应该支持如下操作：
 *
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在书中出现的频率
 */

public class Solution {

    private static class WordsFrequency {
        private final Map<String, Integer> freq = new HashMap<>();
        public int get(String word) { return freq.getOrDefault(word, 0); }
        public WordsFrequency(String[] book) {
            for (var word : book) freq.merge(word, 1, Integer::sum);
        }
    }

    public static void main(String[] args) {
        WordsFrequency wordsFrequency = new WordsFrequency(new String[]{"i", "have", "an", "apple", "he", "have", "a", "pen"});
        assert wordsFrequency.get("you") == 0;
        assert wordsFrequency.get("have") == 2;
        assert wordsFrequency.get("an") == 1;
        assert wordsFrequency.get("apple") == 1;
        assert wordsFrequency.get("pen") == 1;
    }

}
