package problem.p244shortestworddistanceii;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 244. Shortest Word Distance II
 *
 * https://leetcode-cn.com/problems/shortest-word-distance-ii/
 *
 * Design a data structure that will be initialized with a string array,
 * and then it should answer queries of the shortest distance
 * between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance
 * between word1 and word2 in the array wordsDict.
 */

public class Solution {

    private static class WordDistance {
        private final Map<String, TreeSet<Integer>> map = new HashMap<>();
        public WordDistance(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                if (!map.containsKey(wordsDict[i])) {
                    map.put(wordsDict[i], new TreeSet<>());
                }
                map.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            int ans = Integer.MAX_VALUE;
            TreeSet<Integer> set = map.get(word2);
            for (var idx : map.get(word1)) {
                Integer ceil = set.ceiling(idx), floor = set.floor(idx);
                if (ceil != null && ceil - idx < ans) ans = ceil - idx;
                if (floor != null && idx - floor < ans) ans = idx - floor;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        WordDistance wordDistance = new WordDistance(new String[]{
            "practice", "makes", "perfect", "coding", "makes"
        });

        assert wordDistance.shortest("coding", "practice") == 3;
        assert wordDistance.shortest("makes", "coding") == 1;
    }

}
