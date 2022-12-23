package problem.p127wordladder;

import java.util.*;

/**
 * 127. Word Ladder
 *
 * https://leetcode.cn/problems/word-ladder/
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private int uid = 0;
    private final List<Set<Integer>> edges = new ArrayList<>();
    private final Map<String, Integer> wordId = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (var word : wordList) {
            addEdge(word.toCharArray(), addWord(word));
        }

        addEdge(beginWord.toCharArray(), addWord(beginWord));
        if (!wordId.containsKey(endWord)) return 0;

        int startId = wordId.get(beginWord);
        int endId = wordId.get(endWord);

        int[] fromStart = new int[uid];
        Arrays.fill(fromStart, Integer.MAX_VALUE);
        Queue<Integer> startQueue = new ArrayDeque<>();
        startQueue.add(startId); fromStart[startId] = 0;

        int[] fromEnd = new int[uid];
        Arrays.fill(fromEnd, Integer.MAX_VALUE);
        Queue<Integer> endQueue = new ArrayDeque<>();
        endQueue.add(endId); fromEnd[endId] = 0;

        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
            int startQueueSize = startQueue.size();
            for (int i = 0; i < startQueueSize; i++) {
                int node = startQueue.remove();
                if (fromEnd[node] != Integer.MAX_VALUE) {
                    return (fromStart[node] + fromEnd[node]) / 2 + 1;
                }

                for (var next : edges.get(node)) {
                    if (fromStart[next] == Integer.MAX_VALUE) {
                        startQueue.add(next);
                        fromStart[next] = fromStart[node] + 1;
                    }
                }
            }

            int endQueueSize = endQueue.size();
            for (int i = 0; i < endQueueSize; i++) {
                int node = endQueue.remove();
                if (fromStart[node] != Integer.MAX_VALUE) {
                    return (fromStart[node] + fromEnd[node]) / 2 + 1;
                }

                for (var next : edges.get(node)) {
                    if (fromEnd[next] == Integer.MAX_VALUE) {
                        endQueue.add(next);
                        fromEnd[next] = fromEnd[node] + 1;
                    }
                }
            }
        }

        return 0;
    }

    private void addEdge(char[] chars, int conn) {
        for (int i = 0; i < chars.length; i++) {
            char stash = chars[i];

            chars[i] = '*';
            int id = addWord(new String(chars));
            edges.get(conn).add(id);
            edges.get(id).add(conn);
            chars[i] = stash;
        }
    }

    private int addWord(String word) {
        Integer id = wordId.get(word);
        if (id == null) {
            wordId.put(word, (id = uid++));
            edges.add(new HashSet<>());
        }
        return id;
    }

    public static void main(String[] args) {
        assert new Solution().ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")) == 5;
        assert new Solution().ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log")) == 0;
    }

}
