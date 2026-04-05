package weekly.bw171.D;

import common.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Minimum Inversion Count in Subarrays of Fixed Length
 *
 * https://leetcode.cn/contest/biweekly-contest-171/problems/minimum-inversion-count-in-subarrays-of-fixed-length/
 *
 * You are given an integer array nums of length n and an integer k.
 *
 * An inversion is a pair of indices (i, j) from nums such that i < j and nums[i] > nums[j].
 *
 * The inversion count of a subarray is the number of inversions within it.
 *
 * Return the minimum inversion count among all subarrays of nums with length k.
 */

public class Solution {

    private void update(long[] tree, int i, int v) {
        for (; i < tree.length; i += i & -i) tree[i] += v;
    }

    private long query(long[] tree, int i) {
        long ans = 0;
        for (; i > 0; i -= i & -i) ans += tree[i];
        return ans;
    }

    @Tag({"逆序对", "子数组逆序对"})
    public long minInversionCount(int[] nums, int k) {
        // 离散化
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        // 如果是对于某一个子数组, 可以使用树状数组来计算逆序对数量
        //  - 对所有数进行离散化并分配得到 id
        //  - 按顺序处理每一个位置 i, 并累加在树状数组中找到所有 > id(nums[i]) 的数量
        //      - 可以反过来, 对于比较大的数分配较小的id, 使得变为查询 [0, id(nums[i])) 的前缀和
        long[] tree = new long[sorted.length + 2]; // 我们需要使用 i-indexed 的id
        Map<Integer, Integer> id = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) id.put(sorted[i], i + 1);

        long ans = Long.MAX_VALUE, curr = 0;
        for (int i = 0; i < nums.length; i++) {
            // 在窗口中加入元素
            update(tree, id.get(nums[i]), 1);
            // 计算逆序对: 在窗口内大于 nums[i] 的元素都属于逆序对
            curr += query(tree, sorted.length) - query(tree, id.get(nums[i]));

            // 如果需要的话, 从左边移除窗口外的元素
            if (i >= k) {
                // 移除窗口中的元素
                update(tree, id.get(nums[i - k]), -1);
                // 计算需要移除的逆序对: 在窗口内小于 nums[l] 的元素都属于逆序对
                curr -= query(tree, id.get(nums[i - k]) - 1);
            }

            // 如果当前窗口的大小足够 k 个, 那么更新答案
            if (i + 1 >= k) ans = Math.min(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minInversionCount(new int[]{3,1,2,5,4}, 3) == 0;
        assert new Solution().minInversionCount(new int[]{5,3,2,1}, 4) == 6;
        assert new Solution().minInversionCount(new int[]{2,1}, 1) == 0;
    }

}
