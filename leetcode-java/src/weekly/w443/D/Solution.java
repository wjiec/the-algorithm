package weekly.w443.D;

import common.Tag;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 3505. Minimum Operations to Make Elements Within K Subarrays Equal
 *
 * https://leetcode.cn/contest/weekly-contest-443/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/
 *
 * You are given an integer array nums and two integers, x and k.
 *
 * You can perform the following operation any number of times (including zero):
 *
 * Increase or decrease any element of nums by 1.
 *
 * Return the minimum number of operations needed to have at least k non-overlapping
 * subarrays of size exactly x in nums, where all elements within each subarray are equal.
 */

public class Solution {

    public long minOperations(int[] nums, int x, int k) {
        // 在数组中找出 k 个长度为 x 的不重叠子数组, 使得子数组都变成相同数的代价最小
        //  - 使用滑动窗口中位数解决所有 [r - x, r) 子数组的代价问题
        //  - 使用 dp[i][j] 解决在 [0, j) 中选择 i 个子数组的最小代价问题
        //      - 转移方程: min(dp[i][j - 1], dp[i - 1][j - x])
        int n = nums.length;
        long[] mid = median(nums, x);
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            dp[i][i * x - 1] = Long.MAX_VALUE; // boundary
            for (int j = i * x; j <= n - (k - i) * x; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - x] + mid[j - x]);
            }
        }

        return dp[k][n];
    }

    @Tag("对顶堆")
    private static class LazyHeap {
        private int count = 0; private long sum = 0;
        private final TreeMap<Integer, Integer> underlying;
        public LazyHeap(Comparator<Integer> comparator) {
            underlying = new TreeMap<>(comparator);
        }
        public void remove(int v) {
            underlying.merge(v, -1, Integer::sum);
            count--; sum -= v;
        }
        public void insert(int v) {
            underlying.merge(v, 1, Integer::sum);
            count++; sum += v;
        }
        public int peek() {
            while (!underlying.isEmpty() && underlying.firstEntry().getValue() == 0) {
                underlying.pollFirstEntry();
            }
            return underlying.isEmpty() ? -1 : underlying.firstEntry().getKey();
        }
        public int pop() {
            int v = peek();
            remove(v);
            return v;
        }
        public int replace(int v) {
            insert(v);
            return pop();
        }
    }

    // 求数组中所有长为 k 的子数组到中位数的和
    private long[] median(int[] nums, int k) {
        LazyHeap lh = new LazyHeap((a, b) -> b - a); // 最大堆
        LazyHeap rh = new LazyHeap(Integer::compare); // 最小堆

        long[] ans = new long[nums.length - k + 1];
        for (int r = 0; r < nums.length; r++) {
            int curr = nums[r];
            if (lh.count == rh.count) lh.insert(rh.replace(curr));
            else rh.insert(lh.replace(curr));

            int l = r - k + 1;
            if (l < 0) continue;

            // 计算变成中位数的答案, 中位数保存在 lh 的第一个位置
            long median = lh.peek();
            long s1 = median * lh.count - lh.sum;
            long s2 = rh.sum - median * rh.count;
            ans[l] = s1 + s2;

            // 移出元素
            int out = nums[l];
            if (out <= lh.peek()) {
                lh.remove(out);
                if (lh.count < rh.count) {
                    lh.insert(rh.pop());
                }
            } else {
                rh.remove(out);
                if (lh.count > rh.count + 1) {
                    rh.insert(lh.pop());
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5,-2,1,3,7,3,6,4,-1}, 3, 2) == 8;
        assert new Solution().minOperations(new int[]{9,-2,-2,-2,1,5}, 2, 2) == 3;
    }

}
