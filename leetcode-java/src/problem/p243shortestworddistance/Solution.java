package problem.p243shortestworddistance;

/**
 * 243. Shortest Word Distance
 *
 * https://leetcode-cn.com/problems/shortest-word-distance/
 *
 * Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2,
 * return the shortest distance between these two words in the list.
 */

public class Solution {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int a = -1, b = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; i++) {
            if (word1.equals(wordsDict[i])) a = i;
            else if (word2.equals(wordsDict[i])) b = i;
            if (a >= 0 && b >= 0) ans = Math.min(ans, Math.abs(a - b));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestDistance(new String[]{
            "practice", "makes", "perfect", "coding", "makes"
        }, "coding", "practice") == 3;

        assert new Solution().shortestDistance(new String[]{
            "practice", "makes", "perfect", "coding", "makes"
        }, "makes", "coding") == 1;
    }

}
