package offer2.p108;

import java.util.*;

/**
 * 剑指 Offer II 108. 单词演变
 *
 * https://leetcode.cn/problems/om3reC
 *
 * 在字典（单词列表） wordList 中，从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 *
 * 给定两个长度相同但内容不同的单词 beginWord 和 endWord 和一个字典 wordList ，
 * 找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 *
 * 如果不存在这样的转换序列，返回 0。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final Map<Integer, Set<Integer>> edges = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (var word : wordList) {
            int wordId = genWordId(word);
            for (var pattern : neighborPatterns(word)) {
                int patternId = genWordId(pattern);
                edges.computeIfAbsent(wordId, v -> new HashSet<>()).add(patternId);
                edges.computeIfAbsent(patternId, v -> new HashSet<>()).add(wordId);
            }
        }

        int end = getWordId(endWord);
        if (end == -1) return 0;

        int start = genWordId(beginWord);
        for (var pattern : neighborPatterns(beginWord)) {
            int patternId = genWordId(pattern);
            edges.computeIfAbsent(start, v -> new HashSet<>()).add(patternId);
            edges.computeIfAbsent(patternId, v -> new HashSet<>()).add(start);
        }

        int[] fromStartVisited = new int[words.size()];
        Queue<Integer> fromStartQueue = new ArrayDeque<>();
        fromStartQueue.add(start); fromStartVisited[start] = 1;

        int[] fromEndVisited = new int[words.size()];
        Queue<Integer> fromEndQueue = new ArrayDeque<>();
        fromEndQueue.add(end); fromEndVisited[end] = 1;

        // 双向BFS
        while (!fromStartQueue.isEmpty() && !fromEndQueue.isEmpty()) {
            for (int i = 0, l = fromStartQueue.size(); i < l; i++) {
                int curr = fromStartQueue.remove();
                if (fromEndVisited[curr] != 0) {
                    return (fromStartVisited[curr] + fromEndVisited[curr] + 1) / 2;
                }

                for (var next : edges.get(curr)) {
                    if (fromStartVisited[next] == 0) {
                        fromStartQueue.add(next);
                        fromStartVisited[next] = fromStartVisited[curr] + 1;
                    }
                }
            }

            for (int i = 0, l = fromEndQueue.size(); i < l; i++) {
                int curr = fromEndQueue.remove();
                if (fromStartVisited[curr] != 0) {
                    return (fromStartVisited[curr] + fromEndVisited[curr] + 1) / 2;
                }

                for (var next : edges.get(curr)) {
                    if (fromEndVisited[next] == 0) {
                        fromEndQueue.add(next);
                        fromEndVisited[next] = fromEndVisited[curr] + 1;
                    }
                }
            }
        }

        return 0;
    }

    private List<String> neighborPatterns(String word) {
        char[] chars = word.toCharArray();

        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char curr = reset(chars, i, '*');
            neighbors.add(new String(chars));
            reset(chars, i, curr);
        }
        return neighbors;
    }

    private static char reset(char[] chars, int i, char c) {
        char old = chars[i];
        chars[i] = c;
        return old;
    }

    private final List<String> words = new ArrayList<>();
    private final Map<String, Integer> wordMap = new HashMap<>();

    private int getWordId(String word) {
        return wordMap.getOrDefault(word, -1);
    }

    private int genWordId(String word) {
        if (!wordMap.containsKey(word)) {
            wordMap.put(word, words.size());
            words.add(word);
        }
        return wordMap.get(word);
    }

    public static void main(String[] args) {
        assert new Solution().ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")) == 5;
        assert new Solution().ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log")) == 0;
    }

}
