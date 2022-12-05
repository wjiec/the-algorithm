package lcci.s17.p22wordtransformerlcci;

import common.PrettyPrinter;

import java.util.*;

/**
 * 面试题 17.22. 单词转换
 *
 * https://leetcode.cn/problems/word-transformer-lcci/
 *
 * 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。
 * 每一步得到的新词都必须能在字典中找到。
 *
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final List<String> ans = new ArrayList<>();
    private final Map<String, Set<String>> patterns = new HashMap<>();

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return List.of();

        for (var word : wordList) {
            for (var pattern : conv(word)) {
                patterns.computeIfAbsent(pattern, v -> new HashSet<>())
                    .add(word);
            }
        }

        dfs(beginWord, endWord, new HashSet<>(), new ArrayDeque<>());
        return ans;
    }

    private boolean dfs(String curr, String target, Set<String> visited, Deque<String> paths) {
        paths.addLast(curr); visited.add(curr);
        if (curr.equals(target)) { ans.addAll(paths); return true; }

        for (var pattern : conv(curr)) {
            if (patterns.containsKey(pattern)) {
                for (var next : patterns.get(pattern)) {
                    if (!visited.contains(next)) {
                        if (dfs(next, target, visited, paths)) {
                            return true;
                        }
                    }
                }
            }
        }

        paths.removeLast();
        return false;
    }

    private List<String> conv(String word) {
        List<String> ans = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            chars[i] = '*';
            ans.add(new String(chars));
            chars[i] = curr;
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findLadders("hit", "cog",
            List.of("hot","dot","dog","lot","log","cog")));

        PrettyPrinter.println(new Solution().findLadders("hit", "cog",
            List.of("hot","dot","dog","lot","log")));
    }

}
