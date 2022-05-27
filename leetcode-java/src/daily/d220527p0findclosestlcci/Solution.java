package daily.d220527p0findclosestlcci;

/**
 * 面试题 17.11. Find Closest LCCI
 *
 * https://leetcode.cn/problems/find-closest-lcci/
 *
 * You have a large text file containing words. Given any two different words, find the shortest distance
 * (in terms of number of words) between them in the file. If the operation will be repeated many times
 * for the same file (but different pairs of words), can you optimize your solution?
 */

public class Solution {

    public int findClosest(String[] words, String word1, String word2) {
        int a = -1, b = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) a = i;
            else if (words[i].equals(word2)) b = i;
            if (a != -1 && b != -1) ans = Math.min(ans, Math.abs(a - b));
            if (ans == 1) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findClosest(new String[]{
            "I","am","a","student","from","a","university","in","a","city"
        }, "a", "student") == 1;
    }

}
