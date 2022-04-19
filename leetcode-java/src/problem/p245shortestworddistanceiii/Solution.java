package problem.p245shortestworddistanceiii;

/**
 * 245. Shortest Word Distance III
 *
 * https://leetcode-cn.com/problems/shortest-word-distance-iii/
 *
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2,
 * return the shortest distance between these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 */

public class Solution {

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int prev = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; i++) {
            if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
                if (prev != -1 && (!wordsDict[i].equals(wordsDict[prev]) || word1.equals(word2))) {
                    ans = Math.min(ans, i - prev);
                }
                prev = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestWordDistance(new String[]{
            "practice", "makes", "perfect", "coding", "makes"
        }, "makes", "coding") == 1;

        assert new Solution().shortestWordDistance(new String[]{
            "practice", "makes", "perfect", "coding", "makes"
        }, "makes", "makes") == 3;
    }

}
