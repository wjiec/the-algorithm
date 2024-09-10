package daily.d240910p2552countincreasingquadruplets;

/**
 * 2552. Count Increasing Quadruplets
 *
 * https://leetcode.cn/problems/count-increasing-quadruplets
 *
 * Given a 0-indexed integer array nums of size n containing all numbers
 * from 1 to n, return the number of increasing quadruplets.
 *
 * A quadruplet (i, j, k, l) is increasing if:
 *
 * 0 <= i < j < k < l < n, and
 * nums[i] < nums[k] < nums[j] < nums[l].
 */

public class Solution {

    public long countQuadruplets(int[] nums) {
        int n = nums.length; long ans = 0;
        int[] lTree = new int[n + 1];
        for (int j = 0; j < n; j++) {
            int[] rTree = new int[n + 1];
            for (int k = n - 2; k > j; k--) {
                update(rTree, nums[k + 1], 1);
                if (nums[k] < nums[j]) {
                    ans += query(lTree, nums[k] - 1) * (n - k - 1 - query(rTree, nums[j]));
                }
            }

            // 更新左边的数字数量
            update(lTree, nums[j], 1);
        }

        return ans;
    }

    private void update(int[] tree, int x, int d) {
        while (x < tree.length) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    private long query(int[] tree, int x) {
        long ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private static int lowbit(int x) { return x & -x; }

    private static class Optimization {
        public long countQuadruplets(int[] nums) {
            long ans = 0;
            int[] pre = new int[nums.length + 1];
            for (int j = 0; j < nums.length; j++) {
                // 按递减顺序枚举 k, 如果 a[k] < a[j] 则找到一个可行的方案
                // 否则这个值肯定是大于 a[j] 的
                long suffix = 0;
                for (int k = nums.length - 1; k > j; k--) {
                    if (nums[k] < nums[j]) ans += pre[nums[k]] * suffix;
                    else suffix++;
                }

                for (int x = nums[j] + 1; x < pre.length; x++) pre[x]++;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().countQuadruplets(new int[]{2,5,3,1,4}) == 0;

        assert new Solution().countQuadruplets(new int[]{1,3,2,4,5}) == 2;
        assert new Solution().countQuadruplets(new int[]{1,2,3,4}) == 0;
    }

}
