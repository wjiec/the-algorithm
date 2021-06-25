package problem.p1051heightchecker;

/**
 * 1051. Height Checker
 *
 * https://leetcode-cn.com/problems/height-checker/
 *
 * A school is trying to take an annual photo of all the students.
 * The students are asked to stand in a single file line in non-decreasing order by height.
 * Let this ordering be represented by the integer array expected where expected[i]
 * is the expected height of the ith student in line.
 *
 * You are given an integer array heights representing the current order that the students are standing in.
 * Each heights[i] is the height of the ith student in line (0-indexed).
 *
 * Return the number of indices where heights[i] != expected[i].
 */

public class Solution {

    public int heightChecker(int[] heights) {
        int[] sort = new int[101];
        for (var h : heights) sort[h]++;

        int ans = 0;
        for (int i = 1, j = 0; i < sort.length; i++) {
            while (sort[i]-- > 0) {
                if (heights[j++] != i) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().heightChecker(new int[]{1,1,4,2,1,3}) == 3;
        assert new Solution().heightChecker(new int[]{1,2,3,4,5}) == 0;
        assert new Solution().heightChecker(new int[]{5,1,2,3,4}) == 5;
    }

}
