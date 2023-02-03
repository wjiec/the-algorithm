package problem.p472concatenatedwords;

import common.PrettyPrinter;

import java.util.*;

/**
 * 472. Concatenated Words
 *
 * https://leetcode.cn/problems/concatenated-words/
 *
 * Given an array of strings words (without duplicates), return
 * all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised
 * entirely of at least two shorter words in the given array.
 */

public class Solution {

    private final Set<String> visited = new HashSet<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        List<String> ans = new ArrayList<>();
        for (var word : words) {
            if (check(word) > 1) ans.add(word);
            visited.add(word);
        }
        return ans;
    }

    private final Map<String, Integer> memo = new HashMap<>();

    private int check(String word) {
        int n = word.length();
        if (!memo.containsKey(word)) {
            int ans = visited.contains(word) ? 1 : 0;
            for (int i = 1; i < n - 1; i++) {
                ans = Math.max(ans, check(word.substring(0, i)) + check(word.substring(i + 1)));
            }

            memo.put(word, ans);
        }
        return memo.get(word);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findAllConcatenatedWordsInADict(new String[]{
            "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"
        }));

        PrettyPrinter.println(new Solution().findAllConcatenatedWordsInADict(new String[]{
            "cat","dog","catdog"
        }));
    }

}
