package weekly.w476.D;

import common.Checker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.ToLongBiFunction;

/**
 * Q4. Count Stable Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-476/problems/count-stable-subarrays/
 *
 * You are given an integer array nums.
 *
 * A subarray of nums is called stable if it contains no inversions, i.e., there is no
 * pair of indices i < j such that nums[i] > nums[j].
 *
 * You are also given a 2D integer array queries of length q, where each queries[i] = [li, ri]
 * represents a query. For each query [li, ri], compute the number of stable subarrays that lie
 * entirely within the segment nums[li..ri].
 *
 * Return an integer array ans of length q, where ans[i] is the answer to the ith query.
 */

public class Solution {

    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        int n = nums.length;
        record Segment(int l, int r) {}
        // 对于在范围 [l, r) 的子数组, 如果要找到稳定子数组的数量, 方法是
        //  - 对于每一个位置 i, 找到最小的 p 使得以 i 为右端点的子数组 [p, i] 为非递减序列
        //  - 那么任意 [p, i] 的元素作为左端点 l', 子数组 [l', i] 都是稳定子数组
        //  - 也就是存在 i - p + 1 个稳定子数组
        //
        // 对 nums 中的所有元素按照非递减序列进行拆分, 每个查询 [l, r + 1) 都会落在某些非递减子序列组中
        List<Segment> ss = new ArrayList<>();
        for (int i = 1, p = 0; i <= n; i++) {
            if (i == n || nums[i] < nums[i - 1]) {
                ss.add(new Segment(p, i - 1));
                p = i;
            }
        }

        ToLongBiFunction<Integer, Integer> comp = (a, b) -> ((b - a + 1L) * (b - a + 2L)) / 2L;
        // 对于每一个查询 [l, r], 要么横跨多个 segment, 要么属于某一个 segment
        //  - 如果只属于某一个 segment, 那么直接计算 l' = max(s.l, l), r' = min(s.r, r)
        //      - ans = (r' - l' + 1) * (1 + (r' - l' + 1)) / 2, 令 k = (r' - l' + 1)
        //            = k * (1 + k) / 2
        //  - 如果属于多个 segment, 那么对于第一个和最后一个 segment 需要单独计算, 中间的都是满足要求的
        long[] sum = new long[ss.size() + 1];
        for (int i = 1; i <= ss.size(); i++) {
            sum[i] = sum[i - 1] + comp.applyAsLong(ss.get(i - 1).l, ss.get(i - 1).r);
        }

        // 找到第一个 segment.r >= i 的位置
        IntFunction<Integer> binarySearch = (i) -> {
            int l = -1, r = ss.size() - 1;
            while (l + 1 < r) {
                int mid = l + (r - l) / 2;
                if (ss.get(mid).r < i) l = mid;
                else r = mid;
            }
            return r;
        };

        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            // 计算 l 和 r 各自属于的 segment 索引
            int li = binarySearch.apply(l), ri = binarySearch.apply(r);

            // 如果 li == ri, 那么直接单独计算这一组的内容
            if (li == ri) {
                ans[i] = comp.applyAsLong(Math.max(l, ss.get(li).l), Math.min(r, ss.get(ri).r));
            } else {
                // 对于 [li + 1, ri - 1] 之间的我们直接计算前缀和, 前后两个单独计算
                //  - 也就是 sum[ri - 1 + 1] - sum[li + 1]
                ans[i] = (sum[ri] - sum[li + 1])
                    + comp.applyAsLong(Math.max(l, ss.get(li).l), ss.get(li).r)
                    + comp.applyAsLong(ss.get(ri).l, Math.min(r, ss.get(ri).r));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countStableSubarrays(new int[]{3, 1, 2}, new int[][]{{0, 1}, {1, 2}, {0, 2}}), new long[]{2,3,4});
        assert Checker.check(new Solution().countStableSubarrays(new int[]{2, 2}, new int[][]{{0, 1}, {0, 0}}), new long[]{3,1});
    }

}
