package problem.p244shortestworddistanceii;

import java.util.*;

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
        private final Map<String, List<Integer>> map = new HashMap<>();
        public WordDistance(String[] wordsDict) {
            for (int i = 0; i < wordsDict.length; i++) {
                if (!map.containsKey(wordsDict[i])) {
                    map.put(wordsDict[i], new ArrayList<>());
                }
                map.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2 = map.get(word2);
            int a = 0, b = 0, ans = Integer.MAX_VALUE;
            while (a < l1.size() && b < l2.size()) {
                int diff = Math.abs(l1.get(a) - l2.get(b));
                if (diff < ans) ans = diff;
                // 如果A当前下标小于B的下标，那么最佳的做法是往后移动A的下标
                // 如果我们往后移动B的下标，那么这个距离会变得更大
                if (l1.get(a) < l2.get(b)) a++;
                else b++;
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
