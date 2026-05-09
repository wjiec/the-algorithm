package weekly.w491.C;

/**
 * Q3. Minimum Bitwise OR From Grid
 *
 * https://leetcode.cn/contest/weekly-contest-491/problems/minimum-bitwise-or-from-grid/
 *
 * You are given a 2D integer array grid of size m x n.
 *
 * You must select exactly one integer from each row of the grid.
 *
 * Return an integer denoting the minimum possible bitwise OR of the selected integers from each row.
 */

public class Solution {

    public int minimumOR(int[][] grid) {
        int w = 0;
        for (var r : grid) for (var v : r) {
            w = Math.max(w, 32 - Integer.numberOfLeadingZeros(v));
        }

        int ans = 0;
        // 从高到低枚举每一个位是否能填什么数字
        for (int i = w; i >= 0; i--) {
            // 如果这一位想填 0 的话, 也就是每一行中至少存在一个数, 且这一位为 0
            //  - 同时这些数前面填的 k 位是当前 ans 的子集
            boolean hasZero = true; int pre = ans >> i;
            for (var r : grid) {
                int zeros = 0;
                for (var v : r) {
                    if ((pre | (v >> i)) != pre) continue;
                    if ((zeros += ((v >> i) & 1) ^ 1) > 0) break;
                }
                if (!(hasZero = zeros != 0)) break;
            }
            if (!hasZero) ans |= 1 << i;
        }
        return ans;
    }

    public static void main(String[] args) {
        // 16 1 17
        assert new Solution().minimumOR(new int[][]{{13,16,28,11,11,24},{34,30,6,1,8,7},{36,25,17,24,32,34}}) == 17;

        assert new Solution().minimumOR(new int[][]{{1,5},{2,4}}) == 3;
        assert new Solution().minimumOR(new int[][]{{3,5},{6,4}}) == 5;
        assert new Solution().minimumOR(new int[][]{{7,9,8}}) == 7;
    }

}
