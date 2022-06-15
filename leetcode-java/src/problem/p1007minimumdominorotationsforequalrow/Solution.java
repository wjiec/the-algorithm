package problem.p1007minimumdominorotationsforequalrow;

/**
 * 1007. Minimum Domino Rotations For Equal Row
 *
 * https://leetcode.cn/problems/minimum-domino-rotations-for-equal-row/
 *
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 *
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 *
 * Return the minimum number of rotations so that all the values in tops are the same, or
 * all the values in bottoms are the same.
 *
 * If it cannot be done, return -1.
 */

public class Solution {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int a = minDominoRotations(tops, bottoms, tops[0]);
        int b = minDominoRotations(tops, bottoms, bottoms[0]);
        if (a == -1 && b == -1) return -1;
        int ans = Math.min(a == -1 ? tops.length : a, b == -1 ? tops.length : b);

        int c = minDominoRotations(bottoms, tops, tops[0]);
        int d = minDominoRotations(bottoms, tops, bottoms[0]);
        if (c == -1 && d == -1) return -1;

        ans = Math.min(ans, c == -1 ? tops.length : c);
        ans = Math.min(ans, d == -1 ? tops.length : d);

        return ans;
    }

    private int minDominoRotations(int[] a, int[] b, int v) {
        int count = 0, n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] != v) {
                if (b[i] == v) count++;
                else return -1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().minDominoRotations(new int[]{1,2,1,1,1,2,2,2}, new int[]{2,1,2,2,2,2,2,2}) == 1;

        assert new Solution().minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}) == 2;
        assert new Solution().minDominoRotations(new int[]{3,5,1,2,3}, new int[]{3,6,3,3,4}) == -1;
    }

}
